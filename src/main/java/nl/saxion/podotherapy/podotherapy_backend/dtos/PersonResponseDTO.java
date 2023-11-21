package nl.saxion.podotherapy.podotherapy_backend.dtos;

import lombok.Getter;
import lombok.Setter;
import nl.saxion.podotherapy.podotherapy_backend.entities.Person;
import nl.saxion.podotherapy.podotherapy_backend.enums.Gender;

@Getter
@Setter
public class PersonResponseDTO {

	private Long id;
	private String username;
	private String dateOfBirth;
	private String gender;


	public PersonResponseDTO() {
		super();
	}

	public PersonResponseDTO(Long id, String username, String dateOfBirth, String gender) {
		super();
		this.id = id;
		this.username = username;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
	}

	public PersonResponseDTO(Person person) {
		super();
		this.id = person.getId();
		this.username = person.getUsername();

		if (person.getDateOfBirth() != null) {
			this.dateOfBirth = person.getDateOfBirth().toString();
			//remove "2002-09-18 00:00:00.0" to "2002-09-18"
			this.dateOfBirth = this.dateOfBirth.substring(0, 10);
		} else this.dateOfBirth = "";


		if (person.getGender() != null) {
			this.gender = person.getGender().toString();
		} else this.gender = Gender.UNKNOWN.toString();
	}

	
}
