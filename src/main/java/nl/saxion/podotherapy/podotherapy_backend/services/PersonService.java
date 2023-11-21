package nl.saxion.podotherapy.podotherapy_backend.services;

import java.util.List;
import java.util.Objects;

import nl.saxion.podotherapy.podotherapy_backend.exceptions.DuplicationException;
import nl.saxion.podotherapy.podotherapy_backend.exceptions.NotFoundException;
import nl.saxion.podotherapy.podotherapy_backend.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import nl.saxion.podotherapy.podotherapy_backend.dtos.PersonDTO;
import nl.saxion.podotherapy.podotherapy_backend.entities.Person;
import nl.saxion.podotherapy.podotherapy_backend.enums.Role;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public Person findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Person not found: " + id));
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public List<Person> findAllNonAdmin() {
        return repository.findAllByRolesNotContaining(Role.ADMIN.getId());
    }

    public Person create(Person person) {
        person.addRole(Role.USER);
        checkUsernameDuplication(person);
        return repository.save(person);
    }

    public PersonDTO create(PersonDTO dto) {
        return new PersonDTO(create(new Person(dto)));
    }

    public Person update(Person person) {
        checkUsernameDuplication(person);
        Person p = findById(person.getId());
        p.setUsername(person.getUsername());
        p.setRoles(person.getRoles());
        return repository.save(p);
    }

    public void delete(Long id) {
        final Person p = findById(id);
        repository.delete(p);
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
