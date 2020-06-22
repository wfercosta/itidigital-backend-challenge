package br.com.itau.iti.challenge.entrypoint.rest;

import br.com.itau.iti.challenge.core.password.PasswordService;
import br.com.itau.iti.challenge.core.password.PasswordValidationException;
import br.com.itau.iti.challenge.entrypoint.rest.password.PasswordModelIn;
import br.com.itau.iti.challenge.entrypoint.rest.password.PasswordValidateController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.itau.iti.challenge.TestType.TEST_UNIT;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PasswordValidateController.class)
@Tag(TEST_UNIT)
public class PasswordValidateControllerTest {

	public static final String ENDPOINT_VALUE = "/validate";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PasswordService service;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("Should return status OK when the password matches the security criteria")
	public void shouldReturnStatusOKWhenPasswordMatchesTheSecurityCriteria() throws Exception {
		final String fixture = "Bgtyhn56!";

		mockMvc.perform(post(ENDPOINT_VALUE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(PasswordModelIn.of(fixture))))
				.andDo(print())
				.andExpect(status().isOk());

		verify(service).validateThis(eq(fixture));
	}

	@Test
	@DisplayName("Should return status bad request and error message when password does not match strength criteria")
	public void shouldReturnStatusBadRequestAndErrorMessageWhenPasswordIsAnEmptyValue() throws Exception {
		final String fixture = "Bg1!";

		doThrow(new PasswordValidationException()).when(service).validateThis(fixture);

		mockMvc.perform(post(ENDPOINT_VALUE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(PasswordModelIn.of(fixture))))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.messages", hasSize(1)));

		verify(service).validateThis(eq(fixture));
	}


}
