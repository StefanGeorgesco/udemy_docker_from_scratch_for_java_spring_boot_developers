package fr.stefangeorgesco.jobservice.util;

import fr.stefangeorgesco.jobservice.dto.JobDto;
import fr.stefangeorgesco.jobservice.entity.Job;

public class EntityDtoUtil {

    private EntityDtoUtil() {
    }

    public static JobDto toDto(Job job) {
        return JobDto.of(job.getId(), job.getDescription(), job.getCompany(), job.getSkills(), job.getSalary(),
                job.getIsRemote(), AppUtil.getHostName());
    }

    public static Job toEntity(JobDto jobDto) {
        return Job.of(jobDto.id(), jobDto.description(), jobDto.company(), jobDto.skills(), jobDto.salary(),
                jobDto.isRemote());
    }
}
