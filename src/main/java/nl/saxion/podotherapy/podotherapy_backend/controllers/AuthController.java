package nl.saxion.podotherapy.podotherapy_backend.controllers;

import nl.saxion.podotherapy.podotherapy_backend.dtos.AuthRequestUsernameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.saxion.podotherapy.podotherapy_backend.dtos.RegisterRequestDTO;
import nl.saxion.podotherapy.podotherapy_backend.dtos.AuthResponseDTO;
import nl.saxion.podotherapy.podotherapy_backend.services.AuthService;

/**
 * The AuthController class handles requests related to the authentication resource.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	/**
	 * Registers a new user with the provided information.
	 *
	 * @param dto The RegisterRequestDTO object containing the user's registration information.
	 * @return A ResponseEntity object containing the AuthResponseDTO object associated with the registered user.
	 */
	@PostMapping("/register")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO dto) {
		return ResponseEntity.ok(authService.register(dto));
	}

	/**
	 * Authenticates a user with the provided credentials.
	 *
	 * @param dto The AuthRequestUsernameDTO object containing the user's credentials.
	 * @return A ResponseEntity object containing the AuthResponseDTO object associated with the authenticated user.
	 */
	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestUsernameDTO dto) {
		return ResponseEntity.ok(authService.authenticateWithUsername(dto));
	}
}
