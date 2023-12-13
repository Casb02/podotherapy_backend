package nl.saxion.podotherapy.podotherapy_backend.dtos.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class AuthResponseDTO {

	@Schema(description = "The token of the person.", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBZG1pbiIsImV4cCI6MTcw..... (this is a fake token)")
	private String token;

	@Schema(description = "The username of the person.", example = "Kevin")
	private String username;

	public AuthResponseDTO(String token, String username) {
		super();
		this.token = token;
		this.username = username;
	}

}
