package nl.saxion.podotherapy.podotherapy_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.saxion.podotherapy.podotherapy_backend.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	Optional<Person> findByEmail(String email);
}
