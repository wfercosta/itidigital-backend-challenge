package br.com.itau.iti.challenge.core.usecase.password;

public class PasswordValidationException extends RuntimeException {

	public PasswordValidationException() {
		super("Password does not match the strength criteria.");
	}
}
