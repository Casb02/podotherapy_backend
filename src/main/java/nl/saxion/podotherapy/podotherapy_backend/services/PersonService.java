package nl.saxion.podotherapy.podotherapy_backend.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import nl.saxion.podotherapy.podotherapy_backend.dtos.person.PersonCreateDTO;
import nl.saxion.podotherapy.podotherapy_backend.dtos.person.PersonResponseDetailedDTO;
import nl.saxion.podotherapy.podotherapy_backend.enums.Gender;
import nl.saxion.podotherapy.podotherapy_backend.exceptions.DuplicationException;
import nl.saxion.podotherapy.podotherapy_backend.exceptions.NotFoundException;
import nl.saxion.podotherapy.podotherapy_backend.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import nl.saxion.podotherapy.podotherapy_backend.entities.Person;
import nl.saxion.podotherapy.podotherapy_backend.enums.Role;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    /**
     * Finds a person by their ID.
     *
     * @param id the ID of the person to find
     * @return the person with the specified ID
     * @throws NotFoundException if no person is found with the specified ID
     */
    public Person findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Person not found: " + id));
    }

    /**
     * Finds a person by their ID and returns a detailed DTO representation of the person.
     *
     * @param id the ID of the person to find
     * @return the detailed DTO representation of the person with the specified ID
     */
    public PersonResponseDetailedDTO findByIdDetailed(Long id) {
        return new PersonResponseDetailedDTO(findById(id));
    }

    /**
     * Finds all persons in the repository.
     *
     * @return a list of all persons found in the repository
     */
    public List<Person> findAll() {
        return repository.findAll();
    }

    /**
     * Finds all persons who are not administrators.
     *
     * @return A list of Person objects representing all non-administrator persons.
     */
    public List<Person> findAllNonAdmin() {
        return repository.findAllByRolesNotContaining(Role.ADMIN.getId());
    }

    /**
     * Creates a new person by adding a user role to the given person, checking for username duplication,
     * and saving the person object using the repository.
     *
     * @param person the person object to create
     * @return the created person object
     */
    public Person create(Person person) {
        person.addRole(Role.USER); //Default role
        checkUsernameDuplication(person);
        return repository.save(person);
    }

    /**
     * Creates a new person object based on the provided PersonCreateDTO.
     *
     * @param dto The PersonCreateDTO object containing the data for creating a new person.
     * @return The PersonCreateDTO object that represents the created person.
     * @throws NotFoundException If the date format in the PersonCreateDTO is incorrect.
     */
    public PersonCreateDTO create(PersonCreateDTO dto) {
        // Date format: dd-MM-yyyy
        try {
            Date dateOfBirth = new SimpleDateFormat("dd-MM-yyyy").parse(dto.getDateOfBirth());
            Gender gender = Gender.valueOf(dto.getGender().toUpperCase());
            Person person = new Person(dto.getUsername(), dto.getPassword(), dateOfBirth, gender);
            return new PersonCreateDTO(create(person));
        } catch (Exception e) {
            throw new NotFoundException("Date format incorrect: Format must be dd-MM-yyyy (day-month-year)");
        }

    }

    /**
     * Updates the person with the specified ID using the data from the provided PersonCreateDTO.
     *
     * @param id The ID of the person to be updated.
     * @param dto The PersonCreateDTO object containing the updated data for the person.
     * @return The updated Person object.
     * @throws NotFoundException If the person with the specified ID is not found.
     */
    public Person update(Long id, PersonCreateDTO dto) {
        try {
            Person foundPerson = findById(id);
            Gender gender = Gender.valueOf(dto.getGender().toUpperCase());
            foundPerson.setUsername(dto.getUsername());
            foundPerson.setPassword(dto.getPassword());
            foundPerson.setDateOfBirth(new SimpleDateFormat("dd-MM-yyyy").parse(dto.getDateOfBirth()));
            foundPerson.setGender(gender);
            checkUsernameDuplication(foundPerson);
            return repository.save(foundPerson);
        } catch (ParseException e) {
            throw new NotFoundException("Date format incorrect: Format must be dd-MM-yyyy (day-month-year)");
        }

    }

    /**
     * Deletes a person object with the specified ID.
     *
     * @param id The ID of the person to be deleted.
     */
    public void delete(Long id) {
        final Person person = findById(id);
        repository.delete(person);
    }


    /**
     * Checks if there is any duplication of usernames in the system.
     * If a username already exists and belongs to a different person, it throws a DuplicationException.
     *
     * @param person the person object to check for username duplication
     */
    private void checkUsernameDuplication(Person person) {
        final String username = person.getUsername();
        if (username != null && username.length() > 0) {
            final Long id = person.getId();
            final Person p = repository.findByUsername(username).orElse(null);
            if (p != null && Objects.equals(p.getUsername(), username) && !Objects.equals(p.getId(), id)) {
                throw new DuplicationException("Username duplication: " + username);
            }
        }
    }

    /**
     * Retrieves the person associated with the given authentication object.
     *
     * @param authentication The authentication object. Must not be null.
     * @return Person - the person associated with the given authentication object.
     */
    public Person getPersonFromAuthentication(Authentication authentication) {
        return findByUsername(authentication.getName());
    }

    /**
     * Retrieves the person with the given username.
     *
     * @param username The username of the person to retrieve. Must not be null.
     * @return Person - the person with the given username.
     * @throws NotFoundException If the person with the given username is not found.
     */
    public Person findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(
                () -> new NotFoundException("Person not found: " + username));
    }

}
