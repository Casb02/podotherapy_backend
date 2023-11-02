package nl.saxion.podotherapy.podotherapy_backend.dtos;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import nl.saxion.podotherapy.podotherapy_backend.entities.Person;
import nl.saxion.podotherapy.podotherapy_backend.enums.Role;

@Getter
@Setter
public class PersonDTO {
	
	private Long id;
	private String name;
	private String email;
	private String password;
	private Set<String> roles = new HashSet<>();
	
	public PersonDTO() {
		super();
	}

	public PersonDTO(Long id, String name, String email, Set<String> roles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.roles = roles;
	}
	
	public PersonDTO(Person person) {
		super();
		this.id = person.getId();
		this.name = person.getName();
		this.email = person.getEmail();
		this.setRoles(person.getRoles());
	}

	/**
	 * Sets the roles of the person.
	 *
	 * @param roles - a Set of Role objects representing the roles of the person.
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles.stream().map(r -> r.getDescription()).collect(Collectors.toSet());
	}
	
}
