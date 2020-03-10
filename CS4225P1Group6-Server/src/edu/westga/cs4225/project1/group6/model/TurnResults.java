package edu.westga.cs4225.project1.group6.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Data class that is used to report the end of turn state of all
 * players and the state of the game back to a client.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class TurnResults implements Serializable {

	private static final long serialVersionUID = -112328579945164540L;
	
	private GameLog log;
	private ArrayList<GamePlayer> players;
	private GamePlayer enemy;
	
	/**
	 * Creates a new TurnResults object with the specified log and players.
	 * 
	 * @precondition log != null && players != null && enemy != null
	 * @postcondition getLog().equals(log) && getPlayers().equals(players) && getEnemy().equals(enemy)
	 * 
	 * @param log the game's log.
	 * @param players all of the players in the game.
	 * @param enemy the enemy
	 */
	public TurnResults(GameLog log, ArrayList<GamePlayer> players, GamePlayer enemy) {
		if (log == null) {
			throw new IllegalArgumentException("log should not be null.");
		}
		if (players == null) {
			throw new IllegalArgumentException("players should not be null.");
		}
		if (enemy == null) {
			throw new IllegalArgumentException("enemy should not be null.");
		}
		
		this.log = log;
		this.players = players;
		this.enemy = enemy;
	}

	/**
	 * Gets the game log.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the game log.
	 */
	public GameLog getLog() {
		return this.log;
	}

	/**
	 * Gets the players.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the players.
	 */
	public ArrayList<GamePlayer> getPlayers() {
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
	public GamePlayer getEnemy() {
		return this.enemy;
	}
	
}
