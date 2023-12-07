package nl.saxion.podotherapy.podotherapy_backend.dtos.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class AuthResponseAppDTO {

	@Schema(description = "The token of the person. (we call it jwt so it is not easy usable on web)", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBZG1pbiIsImV4cCI6MTcw..... (this is a fake token)")
	private String jwt;

	public AuthResponseAppDTO(String token) {
		super();
		this.jwt = token;
	}

}
