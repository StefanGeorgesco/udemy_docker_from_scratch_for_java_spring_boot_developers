package fr.stefangeorgesco.jobservice.mongobean;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.Container.ExecResult;
import org.testcontainers.mongodb.MongoDBContainer;
import org.testcontainers.utility.MountableFile;

@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {

    private static final String INIT_JS = "/tmp/job-init.js";

    // started here (instead of by Spring Boot) so that the init script can run before the context uses the database
    @Bean
    @ServiceConnection
    MongoDBContainer mongoDbContainer() throws Exception {
        MongoDBContainer mongo = new MongoDBContainer("mongo:latest")
                .withCopyFileToContainer(MountableFile.forClasspathResource("script/job-init.js"), INIT_JS);
        mongo.start();
        ExecResult result = mongo.execInContainer("mongosh", "--quiet", INIT_JS);
        if (result.getExitCode() != 0) {
            throw new IllegalStateException("MongoDB init script failed: " + result.getStderr());
        }
        return mongo;
    }
}
