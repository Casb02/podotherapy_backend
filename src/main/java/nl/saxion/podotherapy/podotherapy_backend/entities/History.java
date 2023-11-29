package nl.saxion.podotherapy.podotherapy_backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class History {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Setter(AccessLevel.NONE)
    @Column(nullable = false, unique = true, length = 36)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String uuid;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "history_id")
    private List<Day> days = new ArrayList<>();

    public History() {
        super();
        this.uuid = UUID.randomUUID().toString();
        Day today = new Day();
        this.days = new ArrayList<>(List.of(today));
    }

    /**
     * Adds a new day to the list of days.
     *
     * @param day  the day to be added
     */
    public void addDay(Day day) {
        this.days.add(day);
    }

    public void addDays(List<Day> days) {
        this.days.addAll(days);
    }

    public void createNewDay() {

        Day day = new Day();
        this.days.add(day);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("History-ID: ").append(this.id).append(", UUID: ").append(this.uuid).append(", \n\tDays: ");

        if (this.days != null) {
            for (Day day : this.days) {
                sb.append("\n\t\t").append(day);
            }
        }

        sb.append("\n---");
        return sb.toString();
    }

}