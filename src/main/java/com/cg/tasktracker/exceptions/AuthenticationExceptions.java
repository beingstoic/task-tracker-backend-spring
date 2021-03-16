package com.cg.tasktracker.exceptions;

public class AuthenticationExceptions extends Exception{
	public AuthenticationExceptions(String message, Throwable cause) {
		super(message, cause);
	}
	public AuthenticationExceptions(String message) {
		super(message);
	}
	public AuthenticationExceptions(Throwable cause) {
		super(cause);
	}
}
