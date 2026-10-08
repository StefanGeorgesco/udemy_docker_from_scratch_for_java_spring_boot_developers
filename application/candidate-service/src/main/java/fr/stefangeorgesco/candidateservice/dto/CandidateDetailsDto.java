package fr.stefangeorgesco.candidateservice.dto;

import java.util.Set;
import java.util.List;

public record CandidateDetailsDto(String id,
                                  String name,
                                  Set<String> skills,
                                  List<JobDto> recommendedJobs) {

    public static CandidateDetailsDto of(String id, String name, Set<String> skills, List<JobDto> recommendedJobs) {
        return new CandidateDetailsDto(id, name, skills, recommendedJobs);
    }
}
