package nl.saxion.podotherapy.podotherapy_backend.entities;

import jakarta.persistence.*;
import lombok.*;
import nl.saxion.podotherapy.podotherapy_backend.enums.DayStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
public class Day {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Setter(AccessLevel.NONE)
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

    @ManyToOne
    @JoinColumn(name = "history_id", insertable = false, updatable = false)
    private History history;


    public Day() {
        this.uuid = UUID.randomUUID().toString();
        this.date = new Date();
        this.status = DayStatus.NONE;
    }

    public Day(Date date) {
        this.uuid = UUID.randomUUID().toString();
        this.date = date;
        this.status = DayStatus.NONE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Day-ID: ").append(this.id)
                .append(", UUID: ").append(this.uuid)
                .append(", Date: ").append(this.date)
                .append(", Status: ").append(this.status)
                .append(", Exercise IDs: ");

        // If exerciseIds is a list of IDs
        if (this.exerciseIds != null) {
            for (int i = 0; i < this.exerciseIds.size(); i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(this.exerciseIds.get(i));
            }
        }

        return sb.toString();
    }
}