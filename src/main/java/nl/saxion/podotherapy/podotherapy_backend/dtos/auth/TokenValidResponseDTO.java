package nl.saxion.podotherapy.podotherapy_backend.dtos.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class TokenValidResponseDTO {

	@Schema(description = "Indicates whether the token is valid or not.", example = "true")
	private boolean isValid;

	public TokenValidResponseDTO(boolean isValid) {
		super();
		this.isValid = isValid;
	}

}
