package br.com.itau.iti.challenge.entrypoint.rest;

import br.com.itau.iti.challenge.entrypoint.rest.password.PasswordModelIn;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.itau.iti.challenge.TestType.TEST_INTEGRATION;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Tag(TEST_INTEGRATION)
public class PasswordIntegrationTest {

	public static final String ENDPOINT_VALUE = "/validate";
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("Should return status OK when the password matches the security criteria")
	public void shouldReturnStatusOKWhenPasswordMatchesTheSecurityCriteria() throws Exception {
		mockMvc.perform(post(ENDPOINT_VALUE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(PasswordModelIn.of("Bgtyhn56!"))))
				.andDo(print())
				.andExpect(status().isNoContent());
	}

	@Test
	@DisplayName("Should return status bad request and error message when password does not have the minimum length")
	public void shouldReturnStatusBadRequestAndErrorMessageWhenPasswordIsAnEmptyValue() throws Exception {
		mockMvc.perform(post(ENDPOINT_VALUE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(PasswordModelIn.of("Bg1!"))))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.messages", hasSize(1)));
	}

	@Test
	@DisplayName("Should return status bad request and error message when password does not have at least one digit")
	public void shouldReturnStatusBadRequestAndErrorMessageWhenPasswordDoesNotHaveAtLeastOneDigit() throws Exception {
		mockMvc.perform(post(ENDPOINT_VALUE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(PasswordModelIn.of("Bgtyhn!@b"))))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.messages", hasSize(1)));
	}

	@Test
	@DisplayName("Should return status bad request and error message when password does not have at least one lowercase letter")
	public void shouldReturnStatusBadRequestAndErrorMessageWhenPasswordDoesNotHaveAtLeastOneLowercaseLetter() throws Exception {
		mockMvc.perform(post(ENDPOINT_VALUE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(PasswordModelIn.of("BGTYHN56!"))))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.messages", hasSize(1)));
	}

	@Test
	@DisplayName("Should return status bad request and error message when password does not have at least one uppercase letter")
	public void shouldReturnStatusBadRequestAndErrorMessageWhenPasswordDoesNotHaveAtLeastOneUpperCaseLetter() throws Exception {
		mockMvc.perform(post(ENDPOINT_VALUE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(PasswordModelIn.of("bgtyhn56!"))))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.messages", hasSize(1)));
	}

	@Test
	@DisplayName("Should return status bad request and error message when password does not have at least one special character")
	public void shouldReturnStatusBadRequestAndErrorMessageWhenPasswordDoesNotHaveAtLeastOneSpecialCharacter() throws Exception {
		mockMvc.perform(post(ENDPOINT_VALUE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(PasswordModelIn.of("Bgtyhn567"))))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.messages", hasSize(1)));
	}

}
