package edu.westga.cs4225.project1.group6.model;

/**
 * Enum type for describing the status of a player. The player status is either
 * normal or unconscious depending on how the fight went. If a player fails to
 * make a turn selection in time their status should become unresponsive. If a
 * player is unresponsive for two turns in a row they become disconnected which
 * is equal to unconscious to the game.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public enum PlayerStatus {
	Normal, Unconscious, Unresponsive, Disconnected;

	/**
	 * Represents the PlayerStatus as a String.
	 * 
	 * @precondition none
	 * @param status the status
	 * @return the status as a string
	 */
	public static String statusAsString(PlayerStatus status) {
		String returnString = "Error";

		switch (status) {
			case Normal:
				returnString = "Normal";
				break;
			case Unconscious:
				returnString = "Unconscious";
				break;
			case Unresponsive:
				returnString = "Unresponsive";
				break;
			case Disconnected:
				returnString = "Disconnected";
				break;
			default:
				throw new IllegalArgumentException("Invalid PlayerStatus.");
		}

		return returnString;
	}
}
