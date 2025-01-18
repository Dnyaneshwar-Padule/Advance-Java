package com.tca.exceptions;

@SuppressWarnings("serial")
public class ParseStudentException extends Exception {

	public ParseStudentException() {
	}

	public ParseStudentException(String message) {
		super(message);
	}

	public ParseStudentException(Throwable cause) {
		super(cause);
	}

	public ParseStudentException(String message, Throwable cause) {
		super(message, cause);
	}

}
