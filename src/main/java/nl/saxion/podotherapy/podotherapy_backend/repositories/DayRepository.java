package nl.saxion.podotherapy.podotherapy_backend.repositories;

import nl.saxion.podotherapy.podotherapy_backend.entities.Day;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface DayRepository extends JpaRepository<Day, Long> {
    List<Day> findAllByDateBetweenAndHistory_Id(Date startDate, Date endDate, Long historyId);
    List<Day> findAllByHistory_Id(Long historyId);
}
