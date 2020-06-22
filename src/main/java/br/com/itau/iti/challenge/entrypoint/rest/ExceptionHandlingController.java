package br.com.itau.iti.challenge.entrypoint.rest;

import br.com.itau.iti.challenge.core.usecase.password.PasswordValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

	public static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlingController.class);


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseModelOut> handle(final MethodArgumentNotValidException exception) {

		LOG.error("Exception handling controller: {}", exception.getMessage(), exception);

		final ResponseModelOut.Builder builder = ResponseModelOut.Builder.create();

		exception.getBindingResult().getAllErrors().forEach(error -> {
			final String field = ((FieldError) error).getField();
			final String message = error.getDefaultMessage();
			builder.withMessage(String.format("Validation error on field '%s': %s", field, message));
		});

		return ResponseEntity.badRequest().body(builder.build());
	}

	@ExceptionHandler(PasswordValidationException.class)
	public ResponseEntity<ResponseModelOut> handle(final PasswordValidationException exception) {

		LOG.error("Exception handling controller: {}", exception.getMessage(), exception);

		return ResponseEntity.badRequest()
				.body(ResponseModelOut
						.Builder
						.create()
						.withMessage(exception.getMessage())
						.build());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseModelOut> handle(final Exception exception) {

		LOG.error("Exception handling controller: {}", exception.getMessage(), exception);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ResponseModelOut
						.Builder
						.create()
						.withMessage("Internal server Error")
						.build());

	}
}
