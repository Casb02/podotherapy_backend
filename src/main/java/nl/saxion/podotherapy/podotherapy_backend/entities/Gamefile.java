package nl.saxion.podotherapy.podotherapy_backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

//Stores the gamefile of a user in the database (path to the file)

@Getter
@Setter
@ToString
@Entity
public class Gamefile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "path", nullable = false)
    private String path;

    //A person can have multiple gamefiles
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(name = "date", nullable = false)
    private Date date;

    public Gamefile() {
        super();
    }

    public Gamefile(String path, Person person) {
        super();
        this.path = path;
        this.person = person;
        this.date = new Date();
    }

    public String getFileName() {
        return path.substring(path.lastIndexOf('/') + 1);
    }

}
