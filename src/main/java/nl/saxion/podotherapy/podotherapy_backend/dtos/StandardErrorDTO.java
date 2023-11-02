package nl.saxion.podotherapy.podotherapy_backend.dtos;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import jakarta.servlet.http.HttpServletRequest;

@Getter
public class StandardErrorDTO {

	private Integer status;
	private String error;
	private String message;
	private String path;
	
	public StandardErrorDTO(HttpStatus status, Throwable ex, HttpServletRequest request) {
		this.status = status.value();
		this.error = ex.getClass().getSimpleName();
		this.message = ex.getMessage();
		this.path = request.getRequestURI();
	}

}
