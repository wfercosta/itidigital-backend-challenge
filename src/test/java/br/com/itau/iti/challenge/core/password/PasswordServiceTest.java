package br.com.itau.iti.challenge.core.password;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.itau.iti.challenge.TestType.TEST_UNIT;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@Tag(TEST_UNIT)
public class PasswordServiceTest {

	@InjectMocks
	private PasswordService service;

	@Test
	@DisplayName("Should not throws exception when the password matches the security criteria")
	public void shouldReturnStatusOKWhenPasswordMatchesTheSecurityCriteria() {
		assertDoesNotThrow(() -> this.service.validateThis("Bgtyhn56!"));
	}

	@Test
	@DisplayName("Should throws exception when password does not have the minimum length")
	public void shouldThrowsExceptionWhenPasswordIsAnEmptyValue() {
		assertThrows(PasswordValidationException.class, () -> this.service.validateThis("Bg1!"));
	}

	@Test
	@DisplayName("Should throws exception when password does not have at least one digit")
	public void shouldThrowsExceptionWhenPasswordDoesNotHaveAtLeastOneDigit() {
		assertThrows(PasswordValidationException.class, () -> this.service.validateThis("Bgtyhn!@b"));
	}

	@Test
	@DisplayName("Should throws exception when password does not have at least one lowercase letter")
	public void shouldThrowsExceptionWhenPasswordDoesNotHaveAtLeastOneLowercaseLetter() {
		assertThrows(PasswordValidationException.class, () -> this.service.validateThis("BGTYHN56!"));
	}

	@Test
	@DisplayName("Should throws exception when password does not have at least one uppercase letter")
	public void shouldThrowsExceptionWhenPasswordDoesNotHaveAtLeastOneUpperCaseLetter() {
		assertThrows(PasswordValidationException.class, () -> this.service.validateThis("bgtyhn56!"));
	}

	@Test
	@DisplayName("Should throws exception when password does not have at least one special character")
	public void shouldThrowsExceptionWhenPasswordDoesNotHaveAtLeastOneSpecialCharacter() {
		assertThrows(PasswordValidationException.class, () -> this.service.validateThis("Bgtyhn567"));
	}

}
