package edu.westga.cs4225.project1.group6.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.westga.cs4225.project1.group6.client.ServerConnection;
import edu.westga.cs4225.project1.group6.model.GameLog;
import edu.westga.cs4225.project1.group6.model.GamePlayer;
import edu.westga.cs4225.project1.group6.model.MoveType;
import edu.westga.cs4225.project1.group6.model.TurnResults;

/**
 * Handles interaction between the GamePage and the model logic.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class GamePageController {

	private GameLog rpgGameLog;
	private List<GamePlayer> rpgGamePlayers;
	private ServerConnection rpgServerConnection;
	private GamePlayer localPlayer;
	private GamePlayer monster;
	
	private static GamePageController singletonController = null;
	
	/**
	 * Gets the global GamePageController.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the GamePageController or null if it has not been initialized yet.
	 */
	public static GamePageController get() {
		return singletonController;
	}
	
	/**
	 * Initializes the global GamePageController. This should only happen once.
	 * A runtime exception will occur if this is called more than once. The rpgGamePlayers
	 * contains all of the players in the game even this client player.
	 * 
	 * @precondition localPlayer != null && rpgServerConnection != null
	 * @postcondition GamePageController.get() != null
	 * 
	 * @param localPlayer the GamePlayer to give the controller.
	 * @param rpgServerConnection the server connection for the player.
	 * @return the created GamePageController.
	 */
	public static GamePageController initialize(GamePlayer localPlayer, ServerConnection rpgServerConnection) {
		if (singletonController != null) {
			throw new RuntimeException("Controller is already initialized. Initialization only happens once.");
		}
		
		singletonController = new GamePageController(localPlayer, rpgServerConnection);
		return singletonController;
	}
	
	/**
	 * Creates a new isntance of the GamePageController
	 * 
	 * @precondition the local player and server cannot be null
	 * @postcondition the game controller is created
	 * @param localPlayer         the player playing the game on this client
	 * @param rpgServerConnection the connection to the server
	 */
	private GamePageController(GamePlayer localPlayer, ServerConnection rpgServerConnection) {
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
	public List<GamePlayer> getRpgGamePlayers() {
		return this.rpgGamePlayers;
	}
	
	public GamePlayer getMonster() {
		return this.monster;
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

	
	public void submitMove(MoveType type) {
		try {
			TurnResults results = this.rpgServerConnection.sendMove(type);
			this.rpgGameLog = results.getLog();
			this.rpgGamePlayers = results.getPlayers();
			this.localPlayer = this.rpgGamePlayers.stream().filter(player -> player.getPlayerName().equals(this.localPlayer.getPlayerName())).collect(Collectors.toList()).get(0);
			this.rpgGamePlayers.removeIf(player -> player.getPlayerName().equals(this.localPlayer.getPlayerName()));
			this.monster = results.getEnemy();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateInformation(ArrayList<GamePlayer> players, GamePlayer enemy) {
		for (GamePlayer player : players) {
			if (player.getPlayerName().equals(localPlayer.getPlayerName())) {
				this.localPlayer = player;
			} else {
				this.rpgGamePlayers.add(player);
			}
		}
		this.monster = enemy;
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
