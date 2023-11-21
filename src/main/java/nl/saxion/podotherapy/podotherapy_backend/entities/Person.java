package nl.saxion.podotherapy.podotherapy_backend.entities;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.saxion.podotherapy.podotherapy_backend.enums.Gender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import nl.saxion.podotherapy.podotherapy_backend.dtos.PersonDTO;
import nl.saxion.podotherapy.podotherapy_backend.enums.Role;

@Getter
@Setter
@Entity
public class Person implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_person_id")
    @SequenceGenerator(name = "gen_person_id", sequenceName = "seq_person_id", allocationSize = 1)
    private Long id;

    @Setter(AccessLevel.NONE)
    @Column(nullable = false, unique = true, length = 36)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String uuid;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "person_role")
    private Set<Integer> roles = new HashSet<>(Arrays.asList(Role.USER.getId()));

    @Column(length = 50)
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    private History history = new History();

    public Person() {
        super();
    }

    /**
     * Constructs a new Person object with the given parameters.
     *
     * @param id          The ID of the person. Must be of type Long.
     * @param name        The name of the person. Must be of type String.
     * @param password    The password of the person. Must be of type String.
     * @param roles       A set of roles associated with the person. Must be of type Set<Role>.
     * @param dateOfBirth Date of birth of the person. Must be of type Date.
     * @param gender      Gender of the person
     */
    public Person(Long id, String name, String password, Set<Role> roles, Date dateOfBirth, Gender gender) {
        super();
        this.id = id;
        this.uuid = UUID.randomUUID().toString();
        this.username = name;
        this.password = password;
        this.setRoles(roles);
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    /**
     * Constructs a new Person object with the given parameters.
     *
     * @param id       The ID of the person. Must be of type Long.
     * @param name     The name of the person. Must be of type String.
     * @param password The password of the person. Must be of type String.
     * @param roles    A set of roles associated with the person. Must be of type Set<Role>.
     */
    public Person(Long id, String name, String password, Set<Role> roles) {
        super();
        this.id = id;
        this.uuid = UUID.randomUUID().toString();
        this.username = name;
        this.password = password;
        this.setRoles(roles);
    }

    /**
     * Constructs a new Person object with the given parameters.
     *
     * @param name     The name of the person. Must be of type String.
     * @param password The password of the person. Must be of type String.
     */
    public Person(String name, String password) {
        super();
        this.uuid = UUID.randomUUID().toString();
        this.username = name;
        this.password = password;
    }

    /**
     * Constructs a new Person object with the given parameters.
     *
     * @param name         The name of the person. Must be of type String.
     * @param password     The password of the person. Must be of type String.
     * @param dateOfBirth  The date of birth of the person. Must be of type Date.
     * @param gender       The gender of the person. Must be of type Gender.
     */
    public Person(String name, String password, Date dateOfBirth, Gender gender) {
        super();
        this.uuid = UUID.randomUUID().toString();
        this.username = name;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    /**
     * Constructs a new Person object using the provided PersonDTO object.
     *
     * @param dto The PersonDTO object containing the person's information. Must not be null.
     */
    public Person(PersonDTO dto) {
        this(dto.getUsername(), dto.getPassword());
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
    public boolean isAccountNonLocked()  {
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

    /**
     * Sets the identifier of the person. (package-private)
     *
     * @param id the identifier of the person
     */
    private void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Person : ").append(username).append(" (").append(id).append(") \n")
          .append("UUID: ").append(uuid).append("\n ")
          .append("Date of Birth / Gender").append(dateOfBirth).append(" - ").append(gender).append("\n ")
          .append("Roles: ").append(roles).append("\n ")
          .append("Password (hash): ").append(password).append("\n ")
          .append("History: ").append(history).append("\n ");
        return sb.toString();
    }

}
