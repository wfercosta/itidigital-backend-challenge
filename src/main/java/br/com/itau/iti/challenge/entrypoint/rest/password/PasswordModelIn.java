package br.com.itau.iti.challenge.entrypoint.rest.password;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordModelIn {

	@NotEmpty
	private String value;

	public static PasswordModelIn of(final String value) {
		return new PasswordModelIn(value);
	}

}
