package nl.saxion.podotherapy.podotherapy_backend.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    /**
     * The id of the user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The username of the user
     */
    private String username;

    /**
     * The password of the user encoded with BCrypt
     */
    private String password;

    /**
     * The roles of the user (e.g. admin, therapist, patient)
     */
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;


    public User(String username, String password) {
        this.username = username;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        this.password = encoder.encode(password);
    }

    /**
     * Sets the password for the user.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        this.password = passwordEncoder.encode(password);
    }

    /**
     * Checks if the provided password matches the user's encrypted password.
     *
     * @param rawPassword the password to check
     * @return true if the password matches, false otherwise
     */
    public boolean checkPassword(String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(rawPassword, this.password);
    }

}