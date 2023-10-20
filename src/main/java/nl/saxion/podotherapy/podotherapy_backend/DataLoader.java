package nl.saxion.podotherapy.podotherapy_backend;

import nl.saxion.podotherapy.podotherapy_backend.models.User;
import nl.saxion.podotherapy.podotherapy_backend.repositories.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * This class is used to load data into the database.
 * ONLY USE THIS CLASS FOR DEVELOPMENT PURPOSES
 */
@Component
public class DataLoader implements ApplicationRunner {
    private final UserRepository userRepository;

    /**
     * Spring boot will automatically inject the correct repository
     *
     * @param userRepository The user repository (autowired)
     */
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This method will be called when the application starts.
     *
     * @param args incoming application arguments
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user1 = new User("Cas", "password");
        User user2 = new User("Example", "password");
        userRepository.save(user1);
        userRepository.save(user2);

    }
}
