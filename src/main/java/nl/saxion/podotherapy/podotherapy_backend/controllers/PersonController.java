package nl.saxion.podotherapy.podotherapy_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.saxion.podotherapy.podotherapy_backend.dtos.PersonDTO;
import nl.saxion.podotherapy.podotherapy_backend.entities.Person;
import nl.saxion.podotherapy.podotherapy_backend.services.PersonService;

/**
 * The PersonController class handles requests related to the person resource.
 */
@RestController
@RequestMapping("/person")
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
	public ResponseEntity<List<PersonDTO>> findAll() {
		final List<Person> persons = service.findAll();
		final List<PersonDTO> dtos = persons.stream().map(p -> new PersonDTO(p)).toList();
		return ResponseEntity.ok(dtos);
	}

	/**
	 * Creates a new person in the database.
	 *
	 * @param dto - a PersonDTO object representing the details of the person to be created.
	 * @return ResponseEntity<PersonDTO> - a ResponseEntity object containing a PersonDTO object representing the newly created person.
	 */
	public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO dto) {
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		return ResponseEntity.ok(service.create(dto));
	}

	/**
	 * Updates an existing person in the database.
	 *
	 * @param dto - a PersonDTO object representing the details of the person to be updated.
	 * @return ResponseEntity<PersonDTO> - a ResponseEntity object containing a PersonDTO object representing the updated person.
	 */
	public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO dto) {
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
