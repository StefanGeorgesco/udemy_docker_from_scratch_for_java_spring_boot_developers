package fr.stefangeorgesco.candidateservice.dto;

import java.util.Set;
import java.util.List;

public record CandidateDetailsDto(String id,
                                  String name,
                                  Set<String> skills,
                                  List<JobDto> recommendedJobs,
                                  String hostName) {

    public static CandidateDetailsDto of(String id,
                                         String name,
                                         Set<String> skills,
                                         List<JobDto> recommendedJobs,
                                         String hostName) {
        return new CandidateDetailsDto(id, name, skills, recommendedJobs, hostName);
    }
}
