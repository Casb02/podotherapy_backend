package nl.saxion.podotherapy.podotherapy_backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Track {
    @Id
    @Column(nullable = false, unique = true, length = 36)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String uuid;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 500)
    private String description;

    @ManyToMany(mappedBy = "tracks")
    private List<Exercise> exercises;

    /**
     * Adds a new exercise to the list of exercises.
     *
     * @param exercise  the exercise to be added
     */
    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    /**
     * Removes the specified exercise from the list of exercises.
     *
     * @param exercise the exercise to be removed
     * @return true if the exercise was successfully removed, false otherwise
     */
    public boolean removeExercise(Exercise exercise) {
        return this.exercises.remove(exercise);
    }
}