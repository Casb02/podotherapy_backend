package nl.saxion.podotherapy.podotherapy_backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import nl.saxion.podotherapy.podotherapy_backend.dtos.StandardErrorDTO;

import jakarta.servlet.http.HttpServletRequest;

/**
 * This class is a global exception handler that handles all types of exceptions.
 * It is annotated with @ControllerAdvice to indicate that it is a global handler for exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Exception handler method that handles all types of exceptions.
	 *
	 * @param ex The exception that was thrown.
	 * @param request The HttpServletRequest object representing the current request.
	 * @return A ResponseEntity object with a StandardErrorDTO representing the error response.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardErrorDTO> exception(Exception ex, HttpServletRequest request) {
		return ResponseEntity.badRequest().body(
				new StandardErrorDTO(HttpStatus.BAD_REQUEST, ex, request));
	}

}
