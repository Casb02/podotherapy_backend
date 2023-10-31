package nl.saxion.podotherapy.podotherapy_backend.config;

import nl.saxion.podotherapy.podotherapy_backend.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
	
	@Autowired
	private DatabaseService databaseService;
	
	@Bean
	public void initializeDatabase() {
		databaseService.initializeDatabase();
	}

}
