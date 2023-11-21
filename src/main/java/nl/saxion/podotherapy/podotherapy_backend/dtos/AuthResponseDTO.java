package nl.saxion.podotherapy.podotherapy_backend.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class AuthResponseDTO {

	@Schema(description = "The token of the person.", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBZG1pbiIsImV4cCI6MTcw..... (this is a fake token)")
	private String token;

	public AuthResponseDTO(String token) {
		super();
		this.token = token;
	}

}
