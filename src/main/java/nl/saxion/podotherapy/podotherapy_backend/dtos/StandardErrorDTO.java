package nl.saxion.podotherapy.podotherapy_backend.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import jakarta.servlet.http.HttpServletRequest;

@Getter
public class StandardErrorDTO {

	@Schema(description = "The status of the error.", example = "404")
	private Integer status;

	@Schema(description = "The error.", example = "Not Found")
	private String error;

	@Schema(description = "The message of the error.", example = "No history found with id 1")
	private String message;

	@Schema(description = "The path of the error.", example = "/api/v1/histories/1")
	private String path;
	
	public StandardErrorDTO(HttpStatus status, Throwable ex, HttpServletRequest request) {
		this.status = status.value();
		this.error = ex.getClass().getSimpleName();
		this.message = ex.getMessage();
		this.path = request.getRequestURI();
	}

}
