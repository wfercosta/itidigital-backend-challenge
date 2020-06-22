package br.com.itau.iti.challenge.entrypoint.rest;

import br.com.itau.iti.challenge.core.password.PasswordValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(PasswordValidationException.class)
	public ResponseEntity<ResponseModelOut> handle(final PasswordValidationException exception) {
		return ResponseEntity.badRequest()
				.body(ResponseModelOut
						.Builder
						.create()
						.withMessage(exception.getMessage())
						.build());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseModelOut> handle(final Exception exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ResponseModelOut
						.Builder
						.create()
						.withMessage("Internal server Error")
						.build());

	}
}
