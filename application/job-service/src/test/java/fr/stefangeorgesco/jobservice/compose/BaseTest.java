package fr.stefangeorgesco.jobservice.compose;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.File;
import java.time.Duration;

public abstract class BaseTest {

    private static final int MONGO_PORT = 27017;
    private static final String MONGO_SERVICE = "mongo";
    private static final String MONGO_URI_FORMAT = "mongodb://job_user:job_password@%s:%s/job";

    // singleton container: started once per JVM, shared by all test classes (and the cached Spring context),
    // removed by Ryuk after the JVM exits, i.e. after the Spring context and its MongoClient are closed.
    private static final ComposeContainer compose =
            new ComposeContainer(new File("src/test/resources/compose.yml"))
                    .withExposedService(MONGO_SERVICE, MONGO_PORT,
                            // the entrypoint starts a temporary mongod to run init scripts, then the real one,
                            // so wait for the second "Waiting for connections" log line
                            Wait.forLogMessage("(?i).*waiting for connections.*", 2)
                                    .withStartupTimeout(Duration.ofMinutes(2)));

    static {
        compose.start();
    }

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        String host = compose.getServiceHost(MONGO_SERVICE, MONGO_PORT);
        int port = compose.getServicePort(MONGO_SERVICE, MONGO_PORT);
        String mongoUri = String.format(MONGO_URI_FORMAT, host, port);
        registry.add("spring.mongodb.uri", () -> mongoUri);
    }
}
