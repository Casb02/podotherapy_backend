package nl.saxion.podotherapy.podotherapy_backend.controllers;

import java.util.List;

import nl.saxion.podotherapy.podotherapy_backend.dtos.history.HistoryDTO;
import nl.saxion.podotherapy.podotherapy_backend.dtos.person.PersonCreateDTO;
import nl.saxion.podotherapy.podotherapy_backend.dtos.person.PersonDTO;
import nl.saxion.podotherapy.podotherapy_backend.dtos.person.PersonResponseDTO;
import nl.saxion.podotherapy.podotherapy_backend.dtos.person.PersonResponseDetailedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
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
	private PersonService personService;
	
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
		final List<Person> persons = personService.findAllNonAdmin();
		final List<PersonResponseDTO> dtos = persons.stream().map(PersonResponseDTO::new).toList();
		return ResponseEntity.ok(dtos);
	}

	/**
	 * Retrieves a person from the database with the specified ID.
	 *
	 * @param id - the ID of the person to retrieve
	 * @return ResponseEntity<PersonResponseDetailedDTO> - a ResponseEntity object containing a PersonResponseDetailedDTO object representing the person with the specified ID, or null if no person is found.
	 */
	@Transactional
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<PersonResponseDetailedDTO> findOne(@PathVariable Long id) {
		final PersonResponseDetailedDTO dto = personService.findByIdDetailed(id);
		return ResponseEntity.ok(dto);
	}

	@Transactional
	@GetMapping("/{id}/history")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<HistoryDTO> findHistory(@PathVariable Long id) {

		return ResponseEntity.ok(personService.findHistory(id));
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
		return ResponseEntity.ok(personService.create(dto));
	}


	/**
	 * Retrieves a person from the database.
	 *
	 * @param id - the ID of the person to be retrieved.
	 * @return ResponseEntity<PersonDTO> - a ResponseEntity object containing a PersonDTO object representing the retrieved person.
	 */
	@PostMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody PersonCreateDTO dto) {
		return ResponseEntity.ok(personService.update(id, dto));
	}

	/**
	 * Deletes a person from the database.
	 *
	 * @param id - the ID of the person to be deleted.
	 * @return ResponseEntity<Void> - a ResponseEntity object with no content.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		personService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
