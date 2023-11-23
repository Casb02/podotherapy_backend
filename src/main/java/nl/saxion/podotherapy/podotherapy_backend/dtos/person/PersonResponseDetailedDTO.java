package nl.saxion.podotherapy.podotherapy_backend.dtos.person;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import nl.saxion.podotherapy.podotherapy_backend.entities.Day;
import nl.saxion.podotherapy.podotherapy_backend.entities.Person;
import nl.saxion.podotherapy.podotherapy_backend.enums.Gender;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PersonResponseDetailedDTO {

	@Schema(description = "The ID of the person.", example = "23")
	private Long id;

	@Schema(description = "The username of the person.", example = "Kevin")
	private String username;

	@Schema(description = "The date of birth of the person. (yyyy-MM-dd)", example = "2012-09-18")
	private String dateOfBirth;

	@Schema(description = "The gender of the person, ENUM (MALE, FEMALE, OTHER, UNKNOWN)", example = "MALE")
	private String gender;

	private List<Day> lastSevenDays = new ArrayList<>() ;


	public PersonResponseDetailedDTO() {
		super();
	}

	public PersonResponseDetailedDTO(Long id, String username, String dateOfBirth, String gender, ArrayList<Day> lastSevenDays) {
		super();
		this.id = id;
		this.username = username;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.lastSevenDays = lastSevenDays;
	}

	public PersonResponseDetailedDTO(Person person) {
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

		this.lastSevenDays = person.getHistory().getLastSevenDays();
	}

	
}
