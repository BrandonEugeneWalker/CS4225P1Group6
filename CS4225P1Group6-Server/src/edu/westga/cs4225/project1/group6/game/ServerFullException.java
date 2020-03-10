package edu.westga.cs4225.project1.group6.game;

/**
 * Occurs when the server is full and another player tries
 * to join.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class ServerFullException extends RuntimeException {

	/**
	 * Creates a new ServerFullException object.
	 * 
	 * @param message the description.
	 */
	public ServerFullException(String message) {
		super(message);
	}
}
