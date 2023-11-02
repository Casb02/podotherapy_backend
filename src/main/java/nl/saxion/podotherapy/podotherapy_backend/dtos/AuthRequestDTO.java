package nl.saxion.podotherapy.podotherapy_backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDTO {
	
	private String email;
	private String password;

}
