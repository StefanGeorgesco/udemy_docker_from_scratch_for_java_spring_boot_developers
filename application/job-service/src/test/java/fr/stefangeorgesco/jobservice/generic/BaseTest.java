package fr.stefangeorgesco.jobservice.generic;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.time.Duration;

public abstract class BaseTest {

    private static final int MONGO_PORT = 27017;
    private static final String INIT_JS = "/docker-entrypoint-initdb.d/init.js";
    private static final String MONGO_URI_FORMAT = "mongodb://job_user:job_password@%s:%s/job";

    // singleton container: started once per JVM, shared by all test classes (and the cached Spring context),
    // removed by Ryuk after the JVM exits, i.e. after the Spring context and its MongoClient are closed.
    @SuppressWarnings("resource")
    private static final GenericContainer<?> mongo = new GenericContainer<>("mongo:latest")
            .withExposedPorts(MONGO_PORT)
            .withClasspathResourceMapping("script/job-init.js", INIT_JS, BindMode.READ_ONLY)
            // the entrypoint starts a temporary mongod to run init scripts, then the real one,
            // so wait for the second "Waiting for connections" log line
            .waitingFor(Wait.forLogMessage("(?i).*waiting for connections.*", 2)
                    .withStartupTimeout(Duration.ofMinutes(2)));

    static {
        mongo.start();
    }

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        String mongoUri = String.format(MONGO_URI_FORMAT, mongo.getHost(), mongo.getMappedPort(MONGO_PORT));
        registry.add("spring.mongodb.uri", () -> mongoUri);
    }
}
