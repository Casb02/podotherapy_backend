package nl.saxion.podotherapy.podotherapy_backend.dtos.person;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import nl.saxion.podotherapy.podotherapy_backend.entities.Person;
import nl.saxion.podotherapy.podotherapy_backend.enums.Gender;
import nl.saxion.podotherapy.podotherapy_backend.enums.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class PersonCreateDTO {

	@Schema(description = "The username of the person.", example = "Kevin")
	private String username;

	@Schema(description = "The password of the person.", example = "root")
	private String password;

	@Schema(description = "The date of birth of the person. (yyyy-MM-dd, inline with JS datepicker)", example = "2012-09-18")
	private String dateOfBirth;

	@Schema(description = "The gender of the person, ENUM (MALE, FEMALE, OTHER, UNKNOWN)", example = "MALE")
	private String gender;

	public PersonCreateDTO() {
		super();
	}

	public PersonCreateDTO(String username, String password, String dateOfBirth, String gender) {
		super();
		this.username = username;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
	}

	public PersonCreateDTO(Person person) {
		super();

		this.username = person.getUsername();

		if (person.getDateOfBirth() != null) {
			this.dateOfBirth = person.getDateOfBirth().toString();
		} else this.dateOfBirth = "";


		if (person.getGender() != null) {
			this.gender = person.getGender().toString();
		} else this.gender = Gender.UNKNOWN.toString();


	}
	
}
