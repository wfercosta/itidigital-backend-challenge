package br.com.itau.iti.challenge.core.password;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PasswordService {

	private static final String REGEX_VALUE = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{9,40})";

	private Pattern pattern;

	public PasswordService() {
		this.pattern = Pattern.compile(REGEX_VALUE);
	}

	public void validateThis(final String password) {
		final Matcher matcher = pattern.matcher(password);

		final boolean valid = matcher.matches() && hasUniqueCharactersThis(password);

		if (!valid) {
			throw new PasswordValidationException();
		}
	}

	private boolean hasUniqueCharactersThis(final String password) {
		final char[] characters = password.toLowerCase().toCharArray();

		Arrays.sort(characters);

		for (int index = 0; index < characters.length - 1; ++index) {
			if (characters[index] != characters[index + 1]) {
				continue;
			}
			return false;
		}

		return true;
	}
}
