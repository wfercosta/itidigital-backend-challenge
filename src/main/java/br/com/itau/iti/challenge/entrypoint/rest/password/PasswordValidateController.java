package br.com.itau.iti.challenge.entrypoint.rest.password;

import br.com.itau.iti.challenge.core.password.PasswordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordValidateController {

	private PasswordService passwordService;

	public PasswordValidateController(final PasswordService passwordService) {
		this.passwordService = passwordService;
	}

	@PostMapping("/validate")
	public void validate(@RequestBody final PasswordModelIn password) {
		this.passwordService.validateThis(password.getValue());
	}
}
