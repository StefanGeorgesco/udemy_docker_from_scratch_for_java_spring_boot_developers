package fr.stefangeorgesco.candidateservice.dto;

import java.util.Set;

public record CandidateDto(String id,
                           String name,
                           Set<String> skills,
                           String hostName) {

    public static CandidateDto of(String id,
                                  String name,
                                  Set<String> skills,
                                  String hostName) {
        return new CandidateDto(id, name, skills, hostName);
    }
}