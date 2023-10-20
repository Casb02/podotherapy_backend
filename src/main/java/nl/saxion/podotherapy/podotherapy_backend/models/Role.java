package nl.saxion.podotherapy.podotherapy_backend.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Represents a Role entity.
 *
 * This class is used to model a Role object, which represents a role or permission within a system.
 *
 * The Role class is annotated with JPA annotations to specify the mapping between the Role object and the underlying database table.
 * It includes properties such as id, name, and users.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
