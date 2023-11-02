package nl.saxion.podotherapy.podotherapy_backend.config;

import nl.saxion.podotherapy.podotherapy_backend.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
	
	@Autowired
	private DatabaseService databaseService;

	/**
	 * Initializes the database with test data. This method is called automatically when the application starts.
	 */
	@Bean
	public void initializeDatabase() {
		databaseService.initializeDatabase();
	}

}
