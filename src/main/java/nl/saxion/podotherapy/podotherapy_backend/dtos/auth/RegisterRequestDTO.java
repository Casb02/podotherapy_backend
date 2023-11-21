package nl.saxion.podotherapy.podotherapy_backend.dtos.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {

	@Schema(description = "The name of the person.", example = "Kevin")
	private String name;

	@Schema(description = "The password of the person.", example = "root")
	private String password;

}
