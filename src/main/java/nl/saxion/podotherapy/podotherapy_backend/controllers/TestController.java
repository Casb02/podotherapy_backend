package nl.saxion.podotherapy.podotherapy_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.saxion.podotherapy.podotherapy_backend.services.JwtService;

import io.jsonwebtoken.Claims;

/**
 * This class is for testing purposes only.
 * It contains endpoints that can be used to test the application.
 */
@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private JwtService jwtService;

	/**
	 * This method returns a ResponseEntity object with the status code 200 and the response body "Test ok!".
	 *
	 * @return The ResponseEntity object with the status code 200 and the response body "Test ok!".
	 */
	@GetMapping("/ok")
	public ResponseEntity<String> ok() {
		return ResponseEntity.ok("Test ok!");
	}

	/**
	 * This endpoint can be used to intentionally throw a runtime exception for testing purposes.
	 * Note: This method is expected to throw a RuntimeException.
	 */
	@GetMapping("/error")
	public ResponseEntity<String> error() {
		throw new RuntimeException("Test error!");
	}

	/**
	 * This endpoint generates a token for a given email address.
	 *
	 * @return a ResponseEntity containing the generated token
	 */
	@GetMapping("/token/gen")
	public ResponseEntity<String> genToken() {
		return ResponseEntity.ok(jwtService.generateToken("test@mail.com"));
	}

	/**
	 * This endpoint can be used to retrieve the claims stored in a JSON Web Token (JWT).
	 *
	 * @param token The JWT as a string representation.
	 * @return The claims extracted from the JWT.
	 */
	@PostMapping("/token/claims")
	public ResponseEntity<Claims> claims(@RequestBody String token) {
		return ResponseEntity.ok(jwtService.getClaims(token));
	}
	
}
