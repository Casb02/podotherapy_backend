package nl.saxion.podotherapy.podotherapy_backend.repositories;

import java.util.List;
import java.util.Optional;

import nl.saxion.podotherapy.podotherapy_backend.entities.Day;
import nl.saxion.podotherapy.podotherapy_backend.entities.History;
import org.springframework.data.jpa.repository.JpaRepository;

import nl.saxion.podotherapy.podotherapy_backend.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByUsername(String username);

    @Query("SELECT p FROM Person p WHERE :roleId NOT MEMBER OF p.roles")
    List<Person> findAllByRolesNotContaining(@Param("roleId") Integer roleId);

}
