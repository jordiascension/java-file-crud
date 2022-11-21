package com.emc.exceptions;

public class StudentNotFoundException extends Exception {

	private static final long serialVersionUID = 7718828512143293558L;

	public StudentNotFoundException() {
		super();
	}

	public StudentNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public StudentNotFoundException(String message) {
		super(message);

	}

	public StudentNotFoundException(Throwable cause) {
		super(cause);
	}

}
