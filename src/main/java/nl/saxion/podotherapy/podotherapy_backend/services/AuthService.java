package nl.saxion.podotherapy.podotherapy_backend.services;

import nl.saxion.podotherapy.podotherapy_backend.dtos.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import nl.saxion.podotherapy.podotherapy_backend.entities.Person;

@Service
public class AuthService {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * Registers a new user with the given information.
	 *
	 * @param dto The RegisterRequestDTO object containing the user's registration details.
	 * @return An AuthResponseDTO object containing the authentication token for the registered user.
	 */
	public AuthResponseDTO register(RegisterRequestDTO dto) {
		
		Person person = new Person();
		person.setUsername(dto.getName());
		person.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		person = personService.create(person);
		
		return new AuthResponseDTO(jwtService.generateToken(person.getUsername()));
	}

	/**
	 * Authenticates a user based on the provided credentials.
	 *
	 * @param dto The AuthRequestUsernameDTO object containing the user's username and password.
	 * @return The AuthResponseDTO object containing a JWT token for the authenticated user.
	 */
	public AuthResponseDTO authenticate(AuthRequestUsernameDTO dto) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						dto.getUsername(),
						dto.getPassword()));
		
		final Person person = personService.findByUsername(dto.getUsername());
		return new AuthResponseDTO(jwtService.generateToken(person.getUsername()));
	}

	/**
	 * Authenticates a user with username and password. This method is used for the dashboard. (admin)
	 *
	 * @param dto The authentication request containing the username and password.
	 * @return The authentication response containing the generated token.
	 */
	public AuthResponseDTO authenticateWithUsernameDashboard(AuthRequestUsernameDTO dto) {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						dto.getUsername(),
						dto.getPassword()));

		final Person person = personService.findByUsername(dto.getUsername());

		//check if user is admin
		if(!person.getRoles().contains("ADMIN")) {
			throw new BadCredentialsException("Bad credentials");
		}

		return new AuthResponseDTO(jwtService.generateToken(person.getUsername()));
	}

	/**
	 * This method validates a token.
	 *
	 * @param token The token to be validated.
	 * @return A TokenValidResponseDTO object indicating whether the token is valid or not.
	 */
    public TokenValidResponseDTO validate(String token) {
		return new TokenValidResponseDTO(jwtService.validate(token));
    }

	/**
	 * Authenticates a user using a username and password. This method is used for the app.
	 *
	 * @param dto The authentication request with username and password.
	 * @return The authentication response containing the generated token.
	 */
    public AuthResponseAppDTO authenticateWithUsernameApp(AuthRequestUsernameDTO dto) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						dto.getUsername(),
						dto.getPassword()));

		final Person person = personService.findByUsername(dto.getUsername());
		return new AuthResponseAppDTO(jwtService.generateToken(person.getUsername()));
    }
}
