package nl.saxion.podotherapy.podotherapy_backend.services;

import nl.saxion.podotherapy.podotherapy_backend.dtos.AuthRequestUsernameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import nl.saxion.podotherapy.podotherapy_backend.dtos.AuthResponseDTO;
import nl.saxion.podotherapy.podotherapy_backend.dtos.RegisterRequestDTO;
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
	
	public AuthResponseDTO register(RegisterRequestDTO dto) {
		
		Person person = new Person();
		person.setUsername(dto.getName());
		person.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		person = personService.create(person);
		
		return new AuthResponseDTO(jwtService.generateToken(person.getUsername()));
	}
	
	public AuthResponseDTO authenticate(AuthRequestUsernameDTO dto) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						dto.getUsername(),
						dto.getPassword()));
		
		final Person person = personService.findByUsername(dto.getUsername());
		return new AuthResponseDTO(jwtService.generateToken(person.getUsername()));
	}

	public AuthResponseDTO authenticateWithUsername(AuthRequestUsernameDTO dto) {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						dto.getUsername(),
						dto.getPassword()));

		final Person person = personService.findByUsername(dto.getUsername());
		return new AuthResponseDTO(jwtService.generateToken(person.getUsername()));
	}
	
	
	
}
