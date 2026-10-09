package fr.stefangeorgesco.candidateservice.util;

import fr.stefangeorgesco.candidateservice.dto.CandidateDetailsDto;
import fr.stefangeorgesco.candidateservice.dto.CandidateDto;
import fr.stefangeorgesco.candidateservice.entity.Candidate;

import java.util.List;

public class EntityDtoUtil {

    private EntityDtoUtil() {
    }

    public static CandidateDto toDto(Candidate candidate) {
        return CandidateDto.of(candidate.getId(), candidate.getName(), candidate.getSkills(), AppUtil.getHostName());
    }

    public static CandidateDetailsDto toDetailsDto(Candidate candidate) {
        return CandidateDetailsDto.of(candidate.getId(), candidate.getName(), candidate.getSkills(), List.of(),
                AppUtil.getHostName());
    }

    public static Candidate toEntity(CandidateDto candidateDto) {
        return Candidate.of(candidateDto.id(), candidateDto.name(), candidateDto.skills());
    }
}
