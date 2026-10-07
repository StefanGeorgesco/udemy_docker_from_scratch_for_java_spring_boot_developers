package fr.stefangeorgesco.jobservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class JobServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
