package nl.saxion.podotherapy.podotherapy_backend.dtos;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import nl.saxion.podotherapy.podotherapy_backend.entities.Person;
import nl.saxion.podotherapy.podotherapy_backend.enums.Gender;
import nl.saxion.podotherapy.podotherapy_backend.enums.Role;

@Getter
@Setter
public class PersonDTO {

	@Schema(description = "The ID of the person.", example = "23")
	private Long id;

	@Schema(description = "The username of the person.", example = "Kevin")
	private String username;

	@Schema(description = "The password of the person.", example = "root")
	private String password;

	@Schema(description = "All the roles of the person. (at least 1, admin's always have 2 or more)", example = "[\"ADMIN\", \"USER\"]")
	private Set<String> roles = new HashSet<>();

	@Schema(description = "The date of birth of the person. (yyyy-MM-dd)", example = "2012-09-18")
	private String dateOfBirth;

	@Schema(description = "The gender of the person, ENUM (MALE, FEMALE, OTHER, UNKNOWN)", example = "MALE")
	private String gender;

	@Schema(description = "The history of the person.")
	private HistoryDTO history;

	public PersonDTO() {
		super();
	}

	public PersonDTO(Long id, String username, Set<String> roles, String dateOfBirth, String gender, HistoryDTO history) {
		super();
		this.id = id;
		this.username = username;
		this.roles = roles;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.history = history;
	}
	
	public PersonDTO(Person person) {
		super();
		this.id = person.getId();
		this.username = person.getUsername();
		this.setRoles(person.getRoles());

		if (person.getDateOfBirth() != null) {
			this.dateOfBirth = person.getDateOfBirth().toString();
		} else this.dateOfBirth = "";


		if (person.getGender() != null) {
			this.gender = person.getGender().toString();
		} else this.gender = Gender.UNKNOWN.toString();

		if (person.getHistory() != null) {
			this.history = new HistoryDTO(person.getHistory());
		} else this.history = new HistoryDTO();
	}

	/**
	 * Sets the roles of the person.
	 *
	 * @param roles - a Set of Role objects representing the roles of the person.
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles.stream().map(Role::getDescription).collect(Collectors.toSet());
	}
	
}
