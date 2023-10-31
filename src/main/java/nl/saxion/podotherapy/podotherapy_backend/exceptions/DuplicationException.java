package nl.saxion.podotherapy.podotherapy_backend.exceptions;

public class DuplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DuplicationException(String message) {
		super(message);
	}
	
}
