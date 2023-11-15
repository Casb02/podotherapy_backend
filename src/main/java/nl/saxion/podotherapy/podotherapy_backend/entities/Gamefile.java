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

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @Column(name = "is_corrupted", nullable = false)
    private boolean isCorrupted = false;

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

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
         sb.append("Gamefile-ID: ").append(this.id)
                .append(", Path: ").append(this.path)
                .append(", Date: ").append(this.date)
                .append(", Is deleted: ").append(this.isDeleted)
                .append(", Is corrupted: ").append(this.isCorrupted);
         return sb.toString();
    }
}
