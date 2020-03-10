package edu.westga.cs4225.project1.group6.model;

import java.io.Serializable;

/**
 * The GameLog class keeps track of the actions done through the entirety of the
 * game. Ideally this object will be passed back and forth between the server
 * and player as turns are made.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class GameLog implements Serializable {

	private static final String THE_MESSAGE_TO_APPEND_CANNOT_BE_NULL = "The message to append cannot be null!";

	private static final long serialVersionUID = -5166445969029140172L;

	private StringBuilder gameActionLog;

	/**
	 * Creates a new instance of a GameLog.
	 * 
	 * @precondition none
	 * @postcondition an empty log is created.
	 */
	public GameLog() {
		this.gameActionLog = new StringBuilder();
	}

	/**
	 * Appends the message to the end of the log without a new line.
	 * 
	 * @precondition the message cannot be null
	 * @param message the message to append
	 */
	public void append(String message) {
		if (message == null) {
			throw new IllegalArgumentException(THE_MESSAGE_TO_APPEND_CANNOT_BE_NULL);
		}
		this.gameActionLog.append(message);
	}

	/**
	 * Appends the message to the end of the log and adds a new line.
	 * 
	 * @precondition the message cannot be null
	 * @param message the message to append
	 */
	public void appendLine(String message) {
		if (message == null) {
			throw new IllegalArgumentException(THE_MESSAGE_TO_APPEND_CANNOT_BE_NULL);
		}
		this.gameActionLog.append(message + System.lineSeparator());
	}

	/**
	 * Gets and returns the game log string.
	 * 
	 * @precondition none
	 * @return the game log string
	 */
	public String getGameLog() {
		return this.gameActionLog.toString();
	}

}
