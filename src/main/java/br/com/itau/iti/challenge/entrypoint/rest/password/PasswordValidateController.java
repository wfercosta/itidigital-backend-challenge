package br.com.itau.iti.challenge.entrypoint.rest.password;

import br.com.itau.iti.challenge.core.usecase.password.PasswordValidationUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PasswordValidateController {

	private PasswordValidationUseCase useCase;

	public PasswordValidateController(final PasswordValidationUseCase useCase) {
		this.useCase = useCase;
	}

	@PostMapping("/validate")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void validate(@Valid @RequestBody final PasswordModelIn password) {
		this.useCase.validateThis(password.getValue());
	}
}
