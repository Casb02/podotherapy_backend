package nl.saxion.podotherapy.podotherapy_backend.repositories;

import nl.saxion.podotherapy.podotherapy_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long>, CrudRepository<User, Long> {

    /**
     * Find a user by username
     *
     * @param username The username of the user
     * @return The user Object
     */
    User findByUsername(String username);
}