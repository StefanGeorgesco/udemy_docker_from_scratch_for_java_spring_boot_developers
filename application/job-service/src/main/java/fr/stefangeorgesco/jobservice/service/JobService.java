package fr.stefangeorgesco.jobservice.service;

import fr.stefangeorgesco.jobservice.dto.JobDto;
import fr.stefangeorgesco.jobservice.repository.JobRepository;
import fr.stefangeorgesco.jobservice.util.EntityDtoUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
public class JobService {

    private final JobRepository repository;

    public JobService(JobRepository repository) {
        this.repository = repository;
    }

    public Flux<JobDto> getAll() {
        return repository.findAll().map(EntityDtoUtil::toDto);
    }

    public Flux<JobDto> getBySkillsIn(Set<String> skills) {
        return repository.findBySkillsIn(skills).map(EntityDtoUtil::toDto);
    }

    public Mono<JobDto> save(Mono<JobDto> jobDtoMono) {
        return jobDtoMono.map(EntityDtoUtil::toEntity)
                .flatMap(repository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> deleteById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Job with id " + id + " not found")))
                .flatMap(repository::delete);
    }
}
