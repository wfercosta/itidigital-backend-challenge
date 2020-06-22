package br.com.itau.iti.challenge.entrypoint.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseModelOut {

	private List<String> messages = new ArrayList<>();
	private Object data;

	private ResponseModelOut(final List<String> messages, final Object data) {
		this.messages.addAll(messages);
		this.data = data;
	}

	public static class Builder {

		private List<String> messages = new ArrayList<>();
		private Object data;

		public static final Builder create() {
			return new Builder();
		}

		public Builder withMessage(final String message) {
			Objects.requireNonNull(message, "Message object must be not null");
			this.messages.add(message);
			return this;
		}

		public Builder withMessages(final List<String> messages) {
			Objects.requireNonNull(messages, "Messages object must be not null");
			this.messages.addAll(messages);
			return this;
		}

		public Builder withData(final Object data) {
			Objects.requireNonNull(data, "Data object must be not null");
			this.data = data;
			return this;
		}

		public ResponseModelOut build() {
			return new ResponseModelOut(this.messages, this.data);
		}

	}

}
