package nl.saxion.podotherapy.podotherapy_backend.services;

import nl.saxion.podotherapy.podotherapy_backend.entities.Day;
import nl.saxion.podotherapy.podotherapy_backend.entities.Person;
import nl.saxion.podotherapy.podotherapy_backend.enums.Gender;
import nl.saxion.podotherapy.podotherapy_backend.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

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


		try {
			final Person user1 = new Person("Kevin", passwordEncoder.encode("root"), new SimpleDateFormat("dd-MM-yyyy").parse("18-09-2002"), Gender.MALE);
			final Person user2 = new Person("Maria", passwordEncoder.encode("root"), new SimpleDateFormat("dd-MM-yyyy").parse("12-03-2009"), Gender.FEMALE);
			final Person user3 = new Person("Fleur", passwordEncoder.encode("root"), new SimpleDateFormat("dd-MM-yyyy").parse("25-05-2007"), Gender.MALE);
			final Person user4 = new Person("Floor", passwordEncoder.encode("root"), new SimpleDateFormat("dd-MM-yyyy").parse("25-05-2007"), Gender.FEMALE);
			final Person user5 = new Person("Tim", passwordEncoder.encode("root"), new SimpleDateFormat("dd-MM-yyyy").parse("18-09-2002"), Gender.MALE);
			final Person user6 = new Person("Sanne", passwordEncoder.encode("root"), new SimpleDateFormat("dd-MM-yyyy").parse("12-03-2009"), Gender.FEMALE);
			final Person admin = new Person("Admin", passwordEncoder.encode("admin"));

			Date yesterday = new Date();
			yesterday.setDate(yesterday.getDate() - 1);

			Day oldDay = new Day(yesterday);
			user1.getHistory().addDay(oldDay);

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
			System.out.println(personService.create(user3));
			System.out.println(personService.create(user4));
			System.out.println(personService.create(user5));
			System.out.println(personService.create(user6));
			System.out.println(personService.create(admin));
			System.out.println(storageService.storeFile(gamefile1, user1));
			System.out.println(storageService.storeFile(gamefile2, user2));

			//print ArrayList with days user1.getHistory().getLastSevenDays()
			user1.getHistory().getLastSevenDays().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		System.out.println("Database initialized!");
	}
}
