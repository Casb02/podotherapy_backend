package nl.saxion.podotherapy.podotherapy_backend.dtos;

public class AuthResponseDTO {
	
	private String token;

	public AuthResponseDTO(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}
