package nl.saxion.podotherapy.podotherapy_backend.dtos;

import lombok.Getter;

@Getter
public class AuthResponseDTO {
	
	private String token;

	public AuthResponseDTO(String token) {
		super();
		this.token = token;
	}

}
