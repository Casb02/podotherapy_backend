package nl.saxion.podotherapy.podotherapy_backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestUsernameDTO {
	
	private String username;
	private String password;

}
