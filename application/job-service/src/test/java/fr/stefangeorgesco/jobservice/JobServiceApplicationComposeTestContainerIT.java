package fr.stefangeorgesco.jobservice;

import fr.stefangeorgesco.jobservice.dto.JobDto;
import fr.stefangeorgesco.jobservice.compose.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Objects;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureWebTestClient
class JobServiceApplicationComposeTestContainerIT extends BaseTest {

    @Autowired
    private WebTestClient client;

    @Test
    void allJobsTest() {
        client.get().uri("/job/all")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(4);
    }

    @Test
    void searchJobsTest() {
        client.get().uri(uriBuilder -> uriBuilder.path("/job/search")
                        .queryParam("skills", "Java", "Python")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(2);
    }

    @Test
    void saveAndDeleteJobTest() {
        long countBefore = countJobs();

        JobDto newJob = JobDto.of(
                null,
                "DevOps Engineer",
                "AWS",
                Set.of("Docker", "Kubernetes", "AWS"),
                120000,
                true
        );

        JobDto createdJob = client.post().uri("/job")
                .bodyValue(newJob)
                .exchange()
                .expectStatus().isOk()
                .expectBody(JobDto.class)
                .returnResult()
                .getResponseBody();

        assertThat(createdJob).isNotNull();
        assertThat(createdJob.id()).isNotBlank();
        assertThat(createdJob)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newJob);

        long countAfterSave = countJobs();

        assertThat(countAfterSave).isEqualTo(countBefore + 1);

        client.delete()
                .uri("/job/{id}", createdJob.id())
                .exchange()
                .expectStatus().isOk();

        long countAfterDelete = countJobs();

        assertThat(countAfterDelete).isEqualTo(countBefore);
    }

    private long countJobs() {
        return Objects.requireNonNull(client.get().uri("/job/all")
                        .exchange()
                        .expectBodyList(JobDto.class)
                        .returnResult()
                        .getResponseBody())
                .size();
    }
}
