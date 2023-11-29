package nl.saxion.podotherapy.podotherapy_backend.repositories;

import nl.saxion.podotherapy.podotherapy_backend.entities.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {


}
