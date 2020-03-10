package edu.westga.cs4225.project1.group6.client;

/**
 * This exception is called when the connection to the server fails.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class CouldNotConnectException extends RuntimeException {

	private static final long serialVersionUID = -2463810137681719726L;

	/**
	 * This exception is thrown when the server could not be connected to.
	 * 
	 * @precondition none
	 * @postcondition the exception is raised.
	 * @param message the error message
	 */
	public CouldNotConnectException(String message) {
		super(message);
	}
}
