package br.com.itau.iti.challenge.entrypoint.rest.password;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordModelIn {

	private String value;

	public static PasswordModelIn of(final String value) {
		return new PasswordModelIn(value);
	}

}
