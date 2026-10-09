package fr.stefangeorgesco.candidateservice;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.File;
import java.time.Duration;

@SuppressWarnings("HttpUrlsUsage")
public abstract class BaseTest {

    private static final String COMPOSE_FILE_PATH = "src/test/resources/compose.yml";

    private static final Service MONGO = Service.of(
            "mongo",
            27017,
            "spring.mongodb.uri",
            "mongodb://candidate_user:candidate_password@%s:%s/candidate",
            // the entrypoint starts a temporary mongod to run init scripts, then the real one,
            // so wait for the second "Waiting for connections" log line
            Wait.forLogMessage("(?i).*waiting for connections.*", 2)
                    .withStartupTimeout(Duration.ofSeconds(15))
    );

    private static final Service JOB_SERVICE = Service.of(
            "job-service-mock",
            1080,
            "job.service.url",
            "http://%s:%s/job",
            Wait.forHttp("/health").forStatusCode(200)
                    .withStartupTimeout(Duration.ofSeconds(15))
    );

    // singleton container: started once per JVM, shared by all test classes (and the cached Spring context),
    // removed by Ryuk after the JVM exits, i.e. after the Spring context and its MongoClient are closed.
    private static final ComposeContainer compose =
            new ComposeContainer(new File(COMPOSE_FILE_PATH))
                    .withExposedService(MONGO.name(), MONGO.port(), MONGO.waitStrategy())
                    .withExposedService(JOB_SERVICE.name(), JOB_SERVICE.port(), JOB_SERVICE.waitStrategy());

    static {
        compose.start();
    }

    @DynamicPropertySource
    static void addProperties(DynamicPropertyRegistry registry) {
        addServiceProperties(registry, MONGO);
        addServiceProperties(registry, JOB_SERVICE);
    }

    private static void addServiceProperties(DynamicPropertyRegistry registry, Service service) {
        String host = compose.getServiceHost(service.name(), service.port());
        int port = compose.getServicePort(service.name(), service.port());
        String uri = String.format(service.uriFormat(), host, port);
        registry.add(service.uriEnvVar(), () -> uri);
    }
}
