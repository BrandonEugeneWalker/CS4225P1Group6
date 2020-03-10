package edu.westga.cs4225.project1.group6.controller;

import java.util.ArrayList;

import edu.westga.cs4225.project1.group6.client.RpgServerConnection;
import edu.westga.cs4225.project1.group6.model.GameLog;
import edu.westga.cs4225.project1.group6.model.GamePlayer;

/**
 * Handles interaction between the GamePage and the model logic.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class GamePageController {

	private GameLog rpgGameLog;
	private ArrayList<GamePlayer> rpgGamePlayers;
	private RpgServerConnection rpgServerConnection;
	private GamePlayer localPlayer;

	/**
	 * Creates a new isntance of the GamePageController
	 * 
	 * @precondition the local player and server cannot be null
	 * @postcondition the game controller is created
	 * @param localPlayer         the player playing the game on this client
	 * @param rpgServerConnection the connection to the server
	 */
	public GamePageController(GamePlayer localPlayer, RpgServerConnection rpgServerConnection) {
		if (localPlayer == null) {
			throw new IllegalArgumentException("Cannot play the game without a player!");
		}
		if (rpgServerConnection == null) {
			throw new IllegalArgumentException("Cannot play the game without a server connection!");
		}
		this.localPlayer = localPlayer;
		this.rpgServerConnection = rpgServerConnection;
		this.rpgGameLog = new GameLog();
		this.rpgGamePlayers = new ArrayList<GamePlayer>();
	}

	/**
	 * Gets the local player.
	 * 
	 * @precondition none
	 * @return the local player
	 */
	public GamePlayer getLocalPlayer() {
		return this.localPlayer;
	}

	/**
	 * Gets the non-local players.
	 * 
	 * @precondition none
	 * @return the non-local players
	 */
	public ArrayList<GamePlayer> getRpgGamePlayers() {
		return this.rpgGamePlayers;
	}

	/**
	 * Sets the local game player.
	 * 
	 * @precondition player cannot be null or have a different name.
	 * @postcondition the local player is set
	 * @param player the player to set
	 */
	public void setLocalGamePlayer(GamePlayer player) {
		if (player == null) {
			throw new IllegalArgumentException("The player to set cannot be null!");
		}
		if (!this.localPlayer.getPlayerName().equals(player.getPlayerName())) {
			throw new IllegalArgumentException("Cannot replace a local player with a player of a different name!");
		}
		this.localPlayer = player;
	}

	/**
	 * Gets the game log.
	 * 
	 * @precondition none
	 * @return the log
	 */
	public GameLog getRpgGameLog() {
		return this.rpgGameLog;
	}

	/**
	 * Gets the game log string.
	 * 
	 * @precondition none
	 * @return the game log string
	 */
	public String getRpgGameLogString() {
		return this.rpgGameLog.getGameLog();
	}

	/**
	 * Sets the game log.
	 * 
	 * @precondition the log cannot be null
	 * @postcondition the log is set
	 * @param updatedLog the log to set
	 */
	public void setRpgGameLog(GameLog updatedLog) {
		if (updatedLog == null) {
			throw new IllegalArgumentException("The updated game log cannot be null!");
		}
		this.rpgGameLog = updatedLog;
	}

	
	public void submitMove() {
		// TODO
	}

	public void updateRpgGameLog() {
		// TODO
	}

	public void updateRpgGamePlayers() {
		// TODO
	}

	public void updateLocalPlayer() {
		// TODO
	}

	public void transmitQuitMessage() {
		// TODO
	}

}
