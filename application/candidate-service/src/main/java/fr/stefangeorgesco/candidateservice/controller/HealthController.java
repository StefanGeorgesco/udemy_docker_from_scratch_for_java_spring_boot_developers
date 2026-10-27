package fr.stefangeorgesco.candidateservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
public class HealthController {

    @GetMapping("health")
    public Mono<ResponseEntity<Void>> healthRedirect() {
        return Mono.just(ResponseEntity
                .status(HttpStatus.PERMANENT_REDIRECT)
                .location(URI.create("/actuator/health"))
                .build());
    }
}