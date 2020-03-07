package edu.westga.cs4225.project1.group6.model;

/**
 * Enum type for player roles.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public enum PlayerRole {
	Warrior, Mage, Healer;

	/**
	 * Returns the PlayerRole as a string.
	 * 
	 * @precondition none
	 * @param role the role
	 * @return the string version of a role
	 */
	public static String roleAsString(PlayerRole role) {
		String returnString = "Error";

		switch (role) {
		case Warrior:
			returnString = "Warrior";
			break;
		case Mage:
			returnString = "Mage";
			break;
		case Healer:
			returnString = "Healer";
			break;
		}

		return returnString;
	}
}
