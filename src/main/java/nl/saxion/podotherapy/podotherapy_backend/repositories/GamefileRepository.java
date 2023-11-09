package nl.saxion.podotherapy.podotherapy_backend.repositories;

import nl.saxion.podotherapy.podotherapy_backend.entities.Gamefile;
import nl.saxion.podotherapy.podotherapy_backend.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamefileRepository extends JpaRepository<Gamefile, Long> {
    Gamefile findFirstByPersonOrderByDateAsc(Person person);
    Gamefile findLastByPersonOrderByDateAsc(Person person);
}