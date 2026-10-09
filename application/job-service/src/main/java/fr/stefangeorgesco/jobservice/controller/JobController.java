package fr.stefangeorgesco.jobservice.controller;

import fr.stefangeorgesco.jobservice.dto.JobDto;
import fr.stefangeorgesco.jobservice.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@RestController
@RequestMapping("job")
@CrossOrigin
public class JobController {

    private final JobService service;

    public JobController(JobService service) {
        this.service = service;
    }

    @GetMapping("all")
    public Flux<JobDto> all() {
        return service.getAll();
    }

    @GetMapping("search")
    public Flux<JobDto> search(@RequestParam Set<String> skills) {
        return service.getBySkillsIn(skills);
    }

    @PostMapping
    public Mono<JobDto> save(@RequestBody Mono<JobDto> jobDtoMono) {
        return service.save(jobDtoMono);
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return service.deleteById(id)
                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                .onErrorReturn(ResponseEntity.notFound().build());
    }
}
