package fr.stefangeorgesco.candidateservice.dto;

import java.util.Set;

public record JobDto(String id,
                     String description,
                     String company,
                     Set<String> skills,
                     Integer salary,
                     Boolean isRemote) {
}