package fr.stefangeorgesco.candidateservice.service;

import fr.stefangeorgesco.candidateservice.client.JobClient;
import fr.stefangeorgesco.candidateservice.dto.CandidateDetailsDto;
import fr.stefangeorgesco.candidateservice.dto.CandidateDto;
import fr.stefangeorgesco.candidateservice.repository.CandidateRepository;
import fr.stefangeorgesco.candidateservice.util.EntityDtoUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CandidateService {

    private final CandidateRepository repository;
    private final JobClient jobClient;

    public CandidateService(CandidateRepository repository, JobClient jobClient) {
        this.repository = repository;
        this.jobClient = jobClient;
    }

    public Flux<CandidateDto> getAll() {
        return repository.findAll().map(EntityDtoUtil::toDto);
    }

    public Mono<CandidateDetailsDto> getById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Candidate with id " + id + " not found")))
                .map(EntityDtoUtil::toDetailsDto)
                .flatMap(this::addRecommendedJobs);
    }

    public Mono<CandidateDto> save(Mono<CandidateDto> candidateDtoMono) {
        return candidateDtoMono.map(EntityDtoUtil::toEntity)
                .flatMap(repository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> deleteById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Candidate with id " + id + " not found")))
                .flatMap(repository::delete);
    }

    private Mono<CandidateDetailsDto> addRecommendedJobs(CandidateDetailsDto candidateDetailsDto) {
        return jobClient.getRecommendedJobs(candidateDetailsDto.skills())
                .map(recommendedJobs ->
                        new CandidateDetailsDto(candidateDetailsDto.id(),
                                candidateDetailsDto.name(),
                                candidateDetailsDto.skills(),
                                recommendedJobs,
                                candidateDetailsDto.hostName()));
    }
}
