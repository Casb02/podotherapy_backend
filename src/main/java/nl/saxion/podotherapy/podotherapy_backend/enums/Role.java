package nl.saxion.podotherapy.podotherapy_backend.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum Role {
	
	USER(1, "User"),
	ADMIN(2, "Administrator");
	
	private final Integer id;
	private final String description;

	Role(Integer id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public static Role fromId(Integer id) {
		return Arrays.stream(Role.values())
				.filter(role -> Objects.equals(role.getId(), id))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Unknown role: " + id));
	}
	
	public static Role fromDescription(String description) {
		return Arrays.stream(Role.values())
				.filter(role -> Objects.equals(role.getDescription(), description))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Unknown role: " + description));	
	}
	
}
