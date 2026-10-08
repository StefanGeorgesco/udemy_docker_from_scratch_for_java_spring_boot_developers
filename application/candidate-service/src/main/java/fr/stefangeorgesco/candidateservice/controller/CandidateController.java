package fr.stefangeorgesco.candidateservice.controller;

import fr.stefangeorgesco.candidateservice.dto.CandidateDto;
import fr.stefangeorgesco.candidateservice.service.CandidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("candidate")
public class CandidateController {

    private final CandidateService service;

    public CandidateController(CandidateService service) {
        this.service = service;
    }

    @GetMapping("all")
    public Flux<CandidateDto> all() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<CandidateDto>> getById(@PathVariable String id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<CandidateDto> save(@RequestBody Mono<CandidateDto> candidateDtoMono) {
        return service.save(candidateDtoMono);
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return service.deleteById(id)
                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                .onErrorReturn(ResponseEntity.notFound().build());
    }
}
