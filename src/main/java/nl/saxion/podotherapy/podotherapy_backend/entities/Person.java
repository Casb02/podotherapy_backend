package nl.saxion.podotherapy.podotherapy_backend.entities;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import nl.saxion.podotherapy.podotherapy_backend.dtos.PersonDTO;
import nl.saxion.podotherapy.podotherapy_backend.enums.Role;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Getter
@Setter
@ToString
@Entity
public class Person implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_person_id")
	@SequenceGenerator(name = "gen_person_id", sequenceName = "seq_person_id", allocationSize = 1)
	private Long id;

	//UUID for the person
	@Getter
	@Column(nullable = false, unique = true, length = 36)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String uuid;
	
	@Getter
	@Column(nullable = false, length = 50)
	private String name;
	
	@Getter
	@Column(nullable = false, unique = true, length = 100)
	private String email;
	
	@Getter
	@Column(nullable = false, length = 60)
	private String password;
	
	@Column(name = "role")
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "person_role")
	private Set<Integer> roles = new HashSet<>(Arrays.asList(Role.USER.getId()));
	
	public Person() {
		super();
	}

	/**
	 * Constructs a new Person object with the given parameters.
	 *
	 * @param id The ID of the person. Must be of type Long.
	 * @param name The name of the person. Must be of type String.
	 * @param email The email address of the person. Must be of type String.
	 * @param password The password of the person. Must be of type String.
	 * @param roles A set of roles associated with the person. Must be of type Set<Role>.
	 */
	public Person(Long id, String name, String email, String password, Set<Role> roles) {
		super();
		this.id = id;
		this.uuid = UUID.randomUUID().toString();
		this.name = name;
		this.email = email;
		this.password = password;
		this.setRoles(roles);
	}

	/**
	 * Constructs a new Person object with the given parameters.
	 *
	 * @param name The name of the person. Must be of type String.
	 * @param email The email address of the person. Must be of type String.
	 * @param password The password of the person. Must be of type String.
	 */
	public Person(String name, String email, String password) {
		super();
		this.uuid = UUID.randomUUID().toString();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	/**
	 * Constructs a new Person object using the provided PersonDTO object.
	 *
	 * @param dto The PersonDTO object containing the person's information. Must not be null.
	 */
	public Person(PersonDTO dto) {
		this(dto.getName(), dto.getEmail(), dto.getPassword());
		this.setId(dto.getId());
		this.setStringRoles(dto.getRoles());
	}

	/**
	 * Retrieves the roles assigned to the person.
	 *
	 * @return A set of Role objects representing the person's roles.
	 */
	public Set<Role> getRoles() {
		return roles.stream().map(Role::fromId).collect(Collectors.toSet());
	}

	/**
	 * Sets the roles assigned to the person.
	 *
	 * @param roles A set of Role objects representing the person's roles.
	 */
	public void setRoles(Set<Role> roles) {
		if (roles == null || roles.isEmpty())
			this.roles.clear();
		else
			this.roles = roles.stream().map(Role::getId).collect(Collectors.toSet());
	}

	/**
	 * Sets the string-based roles assigned to the person.
	 *
	 * @param roles A set of strings representing the person's roles.
	 */
	public void setStringRoles(Set<String> roles) {
		if (roles == null || roles.isEmpty())
			this.roles.clear();
		else
			this.roles = roles.stream().map(s -> Role.fromDescription(s).getId()).collect(Collectors.toSet());
	}

	/**
	 * Adds a role to the person's list of roles.
	 *
	 * @param role The role to be added.
	 */
	public void addRole(Role role) {
		this.roles.add(role.getId());
	}

	/**
	 * Retrieves the person's authorities, which are represented as a collection of granted authorities.
	 *
	 * @return A collection of granted authorities representing the person's roles.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
				.map(r -> new SimpleGrantedAuthority(Role.fromId(r).name()))
				.collect(Collectors.toSet());
	}

	/**
	 * Retrieves the username associated with the person.
	 *
	 * @return The username associated with the person.
	 */
	@Override
	public String getUsername() {
		return email;
	}

	/**
	 * Indicates whether the account associated with the person has expired.
	 * (We don't have a mechanism for expiring accounts, so this method always returns true.)
	 *
	 * @return true if the account has not expired, false otherwise.
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true; //TODO: Implement this method (override from UserDetails)
	}

	/**
	 * Indicates whether the account associated with the person is locked.
	 * (We don't have a mechanism for locking accounts, so this method always returns true.)
	 *
	 * @return true if the account is not locked, false otherwise.
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true; //TODO: Implement this method (override from UserDetails)
	}

	/**
	 * Indicates whether the credentials associated with the person are expired.
	 * (We don't have a mechanism for expiring credentials, so this method always returns true.)
	 *
	 * @return true if the credentials are not expired, false otherwise.
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;  //TODO: Implement this method (override from UserDetails)
	}

	/**
	 * Indicates whether the person is enabled.
	 * (We don't have a mechanism for disabling accounts, so this method always returns true.)
	 *
	 * @return true if the person is enabled, false otherwise.
	 */
	@Override
	public boolean isEnabled() {
		return true; //TODO: Implement this method (override from UserDetails)
	}
	
}
