package br.com.itau.iti.challenge;

import br.com.itau.iti.challenge.entrypoint.rest.password.PasswordValidateController;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Tag(TestType.TEST_SMOKE)
class ChallengeApplicationTests {

	@Autowired
	private PasswordValidateController controller;

	@Test
	void contextLoads() {
		assertThat(this.controller).isNotNull();
	}

}
