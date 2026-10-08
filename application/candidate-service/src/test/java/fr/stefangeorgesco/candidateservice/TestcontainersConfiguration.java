package fr.stefangeorgesco.candidateservice;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.Container;
import org.testcontainers.mongodb.MongoDBContainer;
import org.testcontainers.utility.MountableFile;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    private static final String INIT_JS = "/tmp/candidate-init.js";

    @Bean
    @ServiceConnection
    MongoDBContainer mongoDbContainer() throws Exception {
        MongoDBContainer mongo = new MongoDBContainer("mongo:latest")
                .withCopyFileToContainer(MountableFile.forClasspathResource("script/candidate-init.js"),
                        INIT_JS);
        mongo.start();
        Container.ExecResult result = mongo.execInContainer("mongosh", "--quiet", INIT_JS);
        if (result.getExitCode() != 0) {
            throw new IllegalStateException("MongoDB init script failed: " + result.getStderr());
        }
        return mongo;
    }

}
