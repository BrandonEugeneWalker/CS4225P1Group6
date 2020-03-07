package edu.westga.cs4225.project1.group6.model;

import java.io.Serializable;

/**
 * GamePlayer class is a object representation of a player playing the game.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class GamePlayer implements Serializable {

	private static final long serialVersionUID = -1541419447387431947L;

	private String playerName;

	private PlayerRole playerRole;

	private int playerHealth;

	private int playerMana;

	/**
	 * Creates a new instance of a GamePlayer.
	 * 
	 * @precondition name cannot be empty or null, role cannot be null
	 * @postcondition the GamePlayer is created
	 * @param name the name of the player
	 * @param role the role of the player
	 */
	public GamePlayer(String name, PlayerRole role) {
		if (name == null) {
			throw new IllegalArgumentException("The player's name cannot be null!");
		}
		if (name.equals("")) {
			throw new IllegalArgumentException("The player's name cannot be empty!");
		}
		if (role == null) {
			throw new IllegalArgumentException("The player's role cannot be null!");
		}
		this.playerName = name;
		this.playerRole = role;
		this.playerHealth = 100;
		this.playerMana = 100;
	}

	/**
	 * Gets the players name.
	 * 
	 * @precondition none
	 * @return the player name
	 */
	public String getPlayerName() {
		return this.playerName;
	}

	/**
	 * Gets the players role.
	 * 
	 * @precondition none
	 * @return the players role
	 */
	public PlayerRole getPlayerRole() {
		return this.playerRole;
	}

	/**
	 * Gets the players health.
	 * 
	 * @precondition none
	 * @return the player health
	 */
	public int getPlayerHealth() {
		return this.playerHealth;
	}

	/**
	 * Gets the players mana.
	 * 
	 * @precondition none
	 * @return the player mana
	 */
	public int getPlayerMana() {
		return this.playerMana;
	}

}
