package fr.stefangeorgesco.candidateservice;

import fr.stefangeorgesco.candidateservice.dto.CandidateDto;
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
class CandidateServiceApplicationIT extends BaseTest {

    @Autowired
    private WebTestClient client;

    @Test
    void allCandidatesTest() {
        client.get().uri("/candidate/all")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(4);
    }

    @Test
    void getCandidateByIdJobServiceReturns200Test() {
        client.get().uri("/candidate/{id}", "1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.name").isEqualTo("John Doe")
                .jsonPath("$.skills.size()").isEqualTo(3)
                .jsonPath("$.recommendedJobs.size()").isEqualTo(3);
    }

    @Test
    void getCandidateByIdJobServiceReturns500Test() {
        client.get().uri("/candidate/{id}", "2")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("2")
                .jsonPath("$.name").isEqualTo("Jane Smith")
                .jsonPath("$.skills.size()").isEqualTo(3)
                .jsonPath("$.recommendedJobs").isEmpty();
    }

    @Test
    void getCandidateByIdJobServiceReturns400Test() {
        client.get().uri("/candidate/{id}", "3")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("3")
                .jsonPath("$.name").isEqualTo("Alice Johnson")
                .jsonPath("$.skills.size()").isEqualTo(2)
                .jsonPath("$.recommendedJobs").isEmpty();
    }

    @Test
    void getCandidateByIdNotFoundTest() {
        client.get().uri("/candidate/{id}", "999")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void saveAndDeleteCandidateTest() {
        long countBefore = countCandidates();

        CandidateDto newCandidate = CandidateDto.of(
                "5",
                "New Candidate",
                Set.of("Skill1", "Skill2")
        );

        CandidateDto createdCandidate = client.post().uri("/candidate")
                .bodyValue(newCandidate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CandidateDto.class)
                .returnResult()
                .getResponseBody();

        assertThat(createdCandidate).isNotNull();
		assertThat(createdCandidate.id()).isEqualTo("5");
        assertThat(createdCandidate)
                .usingRecursiveComparison()
                .isEqualTo(newCandidate);

		long countAfterSave = countCandidates();
		assertThat(countAfterSave).isEqualTo(countBefore + 1);

		client.delete().uri("/candidate/{id}", createdCandidate.id())
				.exchange()
				.expectStatus().isOk();

		long countAfterDelete = countCandidates();
		assertThat(countAfterDelete).isEqualTo(countBefore);
	}

    private long countCandidates() {
        return Objects.requireNonNull(client.get().uri("/candidate/all")
                        .exchange()
                        .expectBodyList(Object.class)
                        .returnResult()
                        .getResponseBody())
                .size();
    }
}
