package nl.saxion.podotherapy.podotherapy_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import nl.saxion.podotherapy.podotherapy_backend.entities.Person;
import nl.saxion.podotherapy.podotherapy_backend.enums.Role;

@Service
public class DatabaseService {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void initializeDatabase() {

		System.out.println("Initializing database...");
		
		final Person user1 = new Person("TestUser", "user@email.com", passwordEncoder.encode("user"));
		final Person user2 = new Person("TestCas", "jhon@email.com", passwordEncoder.encode("cas"));
		final Person admin = new Person("Admin", "admin@email.com", passwordEncoder.encode("admin"));
		
		admin.addRole(Role.ADMIN);
	
		System.out.println(personService.create(user1));
		System.out.println(personService.create(user2));
		System.out.println(personService.create(admin));
		
		System.out.println("Database initialized!");
	}
}
