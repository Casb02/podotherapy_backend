package nl.saxion.podotherapy.podotherapy_backend.controllers;

import java.util.List;

import nl.saxion.podotherapy.podotherapy_backend.dtos.person.PersonCreateDTO;
import nl.saxion.podotherapy.podotherapy_backend.dtos.person.PersonResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import nl.saxion.podotherapy.podotherapy_backend.entities.Person;
import nl.saxion.podotherapy.podotherapy_backend.services.PersonService;

/**
 * The PersonController class handles requests related to the person resource.
 */
@RestController
@RequestMapping("/users")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Retrieves all persons from the database.
	 *
	 * @return ResponseEntity<List < PersonDTO>> - a ResponseEntity object containing a list of PersonDTO objects representing all the persons in the database.
	 */
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<PersonResponseDTO>> findAll() {
		final List<Person> persons = service.findAllNonAdmin();
		final List<PersonResponseDTO> dtos = persons.stream().map(PersonResponseDTO::new).toList();
		return ResponseEntity.ok(dtos);
	}

	/**
	 * Creates a new person in the database.
	 *
	 * @param dto - a PersonDTO object representing the details of the person to be created.
	 * @return ResponseEntity<PersonDTO> - a ResponseEntity object containing a PersonDTO object representing the newly created person.
	 */
	@PostMapping("/create")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<PersonCreateDTO> create(@RequestBody PersonCreateDTO dto) {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		return ResponseEntity.ok(service.create(dto));
	}

	/**
	 * Updates an existing person in the database.
	 *
	 * @param dto - a PersonDTO object representing the details of the person to be updated.
	 * @return ResponseEntity<PersonDTO> - a ResponseEntity object containing a PersonDTO object representing the updated person.
	 */
	@PostMapping("/update")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<PersonCreateDTO> update(@RequestBody PersonCreateDTO dto) {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		return ResponseEntity.ok(service.create(dto));
	}

	/**
	 * Deletes a person from the database.
	 *
	 * @param id - the ID of the person to be deleted.
	 * @return ResponseEntity<Void> - a ResponseEntity object with no content.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
