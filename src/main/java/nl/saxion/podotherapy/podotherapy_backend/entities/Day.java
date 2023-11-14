package nl.saxion.podotherapy.podotherapy_backend.entities;

import jakarta.persistence.*;
import lombok.*;
import nl.saxion.podotherapy.podotherapy_backend.enums.DayStatus;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Day {
    @Id
    @Column(nullable = false, unique = true, length = 36)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String uuid;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private DayStatus status = DayStatus.NONE;

    @ElementCollection
    @CollectionTable(name = "day_exercise_ids", joinColumns = @JoinColumn(name = "day_uuid"))
    @Column(name = "exercise_id")
    private List<String> exerciseIds;

}