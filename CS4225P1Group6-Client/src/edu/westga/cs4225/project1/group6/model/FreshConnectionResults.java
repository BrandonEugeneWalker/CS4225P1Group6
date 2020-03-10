package edu.westga.cs4225.project1.group6.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Sent back to the client after a first initial connection.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class FreshConnectionResults implements Serializable {

	private static final long serialVersionUID = 5147776477998742402L;
	
	private int port;
	private ArrayList<EntityInformation> players;
	private EntityInformation enemy;
	
	/**
	 * Creates a new FreshConnectionResults object.
	 * 
	 * @precondition players != null && enemy != null
	 * @postcondition getPort() == port && getPlayers().equals(players) && getEnemy().equals(enemy)
	 * 
	 * @param port the port.
	 * @param players the players.
	 * @param enemy the enemy.
	 */
	public FreshConnectionResults(int port, ArrayList<EntityInformation> players, EntityInformation enemy) {
		if (players == null) {
			throw new IllegalArgumentException("players should not be null.");
		}
		if (enemy == null) {
			throw new IllegalArgumentException("enemy should not be null.");
		}
		
		this.port = port;
		this.players = players;
		this.enemy = enemy;
	}

	/**
	 * Gets the port.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the port.
	 */
	public int getPort() {
		return this.port;
	}

	/**
	 * Gets the players.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the players.
	 */
	public ArrayList<EntityInformation> getPlayers() {
		return this.players;
	}

	/**
	 * Gets the enemy.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the enemy.
	 */
	public EntityInformation getEnemy() {
		return this.enemy;
	}
	
}
