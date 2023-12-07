package nl.saxion.podotherapy.podotherapy_backend.controllers;

import nl.saxion.podotherapy.podotherapy_backend.dtos.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	@PostMapping("/authenticate/dashboard")
	public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestUsernameDTO dto) {
		return ResponseEntity.ok(authService.authenticateWithUsernameDashboard(dto));
	}

	/**
	 * Authenticates a user with the provided credentials.
	 *
	 * @param dto The AuthRequestUsernameDTO object containing the user's credentials.
	 * @return A ResponseEntity object containing the AuthResponseDTO object associated with the authenticated user.
	 */
	@PostMapping("/authenticate/app")
	public ResponseEntity<AuthResponseAppDTO> authenticateApp(@RequestBody AuthRequestUsernameDTO dto) {
		return ResponseEntity.ok(authService.authenticateWithUsernameApp(dto));
	}

	/**
	 * Validates the provided token.
	 *
	 * @param body The TokenValidRequestDTO object containing the token to be validated.
	 * @return A ResponseEntity object containing the TokenValidResponseDTO object indicating whether the token is valid.
	 */
	@PostMapping("/validate/token")
	public ResponseEntity<TokenValidResponseDTO> validate(@RequestBody TokenValidRequestDTO body) {
		return ResponseEntity.ok(authService.validate(body.getToken()));
	}
}
