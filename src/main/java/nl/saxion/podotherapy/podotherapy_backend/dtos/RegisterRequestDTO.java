package nl.saxion.podotherapy.podotherapy_backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {

	private String name;
	private String email;
	private String password;

}
