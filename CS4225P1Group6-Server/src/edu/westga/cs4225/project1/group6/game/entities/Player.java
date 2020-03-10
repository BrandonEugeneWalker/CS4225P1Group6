package edu.westga.cs4225.project1.group6.game.entities;

import java.util.ArrayList;

import edu.westga.cs4225.project1.group6.game.Entity;
import edu.westga.cs4225.project1.group6.game.roles.Role;
import edu.westga.cs4225.project1.group6.model.GameLog;
import edu.westga.cs4225.project1.group6.model.GamePlayer;
import edu.westga.cs4225.project1.group6.model.MoveType;
import edu.westga.cs4225.project1.group6.model.TurnResults;
import edu.westga.cs4225.project1.group6.server.ClientConnectionPort;

/**
 * A player class. These will represent the clients.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class Player extends Entity {

	private String name;
	private ClientConnectionPort connection;
	
	private volatile MoveType selectedMove;
	
	/**
	 * Creates a new Player object.
	 * 
	 * @precondition role != null && name != null && connection != null
	 * @postcondition none
	 * 
	 * @param name the Player's name.
	 * @param role the player's role.
	 * @param connection the player's connection service.
	 */
	public Player(String name, Role role, ClientConnectionPort connection) {
		super(role);
		if (name == null) {
			throw new IllegalArgumentException("name should not be null.");
		}
		if (connection == null) {
			throw new IllegalArgumentException("connection should not be null.");
		}
		
		this.name = name;
		this.connection = connection;
		this.connection.setOnMoveRead(move -> this.selectedMove = move);
		this.selectedMove = null;
	}

	/**
	 * Sends the results to the player connection.
	 * 
	 * @precondition log != null && players != null && enemy != null
	 * @postcondition none
	 * 
	 * @param log the game log.
	 * @param players the players in the game.
	 * @param enemy the enemy in the game.
	 */
	public void sendResults(GameLog log, ArrayList<GamePlayer> players, GamePlayer enemy) {
		TurnResults results = new TurnResults(log, players, enemy);
		this.connection.setCurrentResult(results);
	}
	
	/**
	 * Waits for the player to make a move.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the player's selected move.
	 */
	public MoveType waitForMove() {
		long startingTime = System.currentTimeMillis();
		while (this.selectedMove == null) {
			long currentTime = System.currentTimeMillis();
			long difference = currentTime - startingTime;
			// Can use difference to enforce timeout.
		}
		
		MoveType move = this.selectedMove;
		this.selectedMove = null;
		return move;
	}

	/**
	 * Gets this player's name.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return this player's name.
	 */
	public String getName() {
		return this.name;
	}
	
}
