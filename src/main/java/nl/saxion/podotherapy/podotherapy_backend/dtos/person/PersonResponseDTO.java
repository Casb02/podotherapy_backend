package nl.saxion.podotherapy.podotherapy_backend.dtos.person;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import nl.saxion.podotherapy.podotherapy_backend.entities.Person;
import nl.saxion.podotherapy.podotherapy_backend.enums.Gender;

@Getter
@Setter
public class PersonResponseDTO {

	@Schema(description = "The ID of the person.", example = "23")
	private Long id;

	@Schema(description = "The username of the person.", example = "Kevin")
	private String username;

	@Schema(description = "The date of birth of the person. (yyyy-MM-dd)", example = "18-09-2012")
	private String dateOfBirth;

	@Schema(description = "The gender of the person, ENUM (MALE, FEMALE, OTHER, UNKNOWN)", example = "MALE")
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
			//Parse it to mm-dd-yyyy
			String[] date = this.dateOfBirth.split("-");
			this.dateOfBirth = date[1] + "-" + date[2] + "-" + date[0];
		} else this.dateOfBirth = "";


		if (person.getGender() != null) {
			this.gender = person.getGender().toString();
		} else this.gender = Gender.UNKNOWN.toString();
	}

	
}
