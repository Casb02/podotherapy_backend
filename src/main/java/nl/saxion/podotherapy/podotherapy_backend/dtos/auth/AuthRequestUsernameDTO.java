package nl.saxion.podotherapy.podotherapy_backend.dtos.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestUsernameDTO {
	@Schema(description = "The username of the person.", example = "Kevin")
	private String username;

	@Schema(description = "The password of the person.", example = "root")
	private String password;
}
