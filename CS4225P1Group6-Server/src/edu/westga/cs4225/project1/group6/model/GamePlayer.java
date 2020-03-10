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

	private PlayerStatus playerStatus;

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
		this.playerStatus = PlayerStatus.Normal;
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
	 * Sets the player's health to the specified value.
	 * 
	 * @precondition none
	 * @postcondition getPlayerHealth() == health
	 * 
	 * @param health the player's new health value.
	 */
	public void setPlayerHealth(int health) {
		this.playerHealth = health;
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
	
	/**
	 * Sets the player's mana to the specified value.
	 * 
	 * @precondition none
	 * @postcondition getPlayerMana() == mana
	 * 
	 * @param mana the player's new mana value.
	 */
	public void setPlayerMana(int mana) {
		this.playerMana = mana;
	}

	/**
	 * Builds and returns a description of a player that is formatted nicely for the
	 * UI.
	 * 
	 * @precondition none
	 * @return the player as a string
	 */
	public String getPlayerDescription() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.playerName + System.lineSeparator());
		stringBuilder.append("Role: " + PlayerRole.roleAsString(this.playerRole) + System.lineSeparator());
		stringBuilder.append("Health: " + this.playerHealth + System.lineSeparator());
		stringBuilder.append("Mana: " + this.playerMana + System.lineSeparator());
		stringBuilder.append("Status: " + PlayerStatus.statusAsString(this.playerStatus));
		return stringBuilder.toString();
	}

}

