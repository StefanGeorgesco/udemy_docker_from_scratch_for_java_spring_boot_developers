package fr.stefangeorgesco.candidateservice;

import org.testcontainers.containers.wait.strategy.WaitStrategy;

public record Service(String name,
                      int port,
                      String uriEnvVar,
                      String uriFormat,
                      WaitStrategy waitStrategy) {

    public static Service of(String name, int port, String uriEnvVar, String uriFormat, WaitStrategy waitStrategy) {
        return new Service(name, port, uriEnvVar, uriFormat, waitStrategy);
    }
}
