package nl.saxion.podotherapy.podotherapy_backend.services;

import nl.saxion.podotherapy.podotherapy_backend.entities.Person;
import nl.saxion.podotherapy.podotherapy_backend.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DatabaseService {

	@Autowired
	private PersonService personService;

	@Autowired
	private StorageService storageService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void initializeDatabase() {

		System.out.println("Initializing database...");
		
		final Person user1 = new Person("TestUser", passwordEncoder.encode("user"));
		final Person user2 = new Person("TestCas", passwordEncoder.encode("cas"));
		final Person admin = new Person("Admin", passwordEncoder.encode("admin"));



		// Maak MockMultipartFile objecten aan
		final MultipartFile gamefile1 = new MockMultipartFile(
				"file",
				"gamefile.bin",
				"text/plain",
				"Data for a game file example 1".getBytes()
		);

		final MultipartFile gamefile2 = new MockMultipartFile(
				"file",
				"gamefile.bin",
				"text/plain",
				"Data for a game file example 2 - more data example".getBytes()
		);

		admin.addRole(Role.ADMIN);
	
		System.out.println(personService.create(user1));
		System.out.println(personService.create(user2));
		System.out.println(personService.create(admin));
		System.out.println(storageService.storeFile(gamefile1, user1));
		System.out.println(storageService.storeFile(gamefile2, user2));
		
		System.out.println("Database initialized!");
	}
}
