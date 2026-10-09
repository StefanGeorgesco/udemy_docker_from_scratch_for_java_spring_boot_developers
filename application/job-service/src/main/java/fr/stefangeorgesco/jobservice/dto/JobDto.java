package fr.stefangeorgesco.jobservice.dto;

import java.util.Set;

public record JobDto(String id,
                     String description,
                     String company, Set<String> skills,
                     Integer salary,
                     Boolean isRemote,
                     String hostName) {

    public static JobDto of(String id,
                            String description,
                            String company,
                            Set<String> skills,
                            Integer salary,
                            Boolean isRemote,
                            String hostName) {
        return new JobDto(id, description, company, skills, salary, isRemote, hostName);
    }
}