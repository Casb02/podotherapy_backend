package nl.saxion.podotherapy.podotherapy_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.saxion.podotherapy.podotherapy_backend.dtos.RegisterRequestDTO;
import nl.saxion.podotherapy.podotherapy_backend.dtos.AuthRequestDTO;
import nl.saxion.podotherapy.podotherapy_backend.dtos.AuthResponseDTO;
import nl.saxion.podotherapy.podotherapy_backend.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService; 

	@PostMapping("/register")
	public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO dto) {
		return ResponseEntity.ok(authService.register(dto));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO dto) {
		return ResponseEntity.ok(authService.authenticate(dto));
	}
}
