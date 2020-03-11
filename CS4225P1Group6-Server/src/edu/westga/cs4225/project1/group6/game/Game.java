package edu.westga.cs4225.project1.group6.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import edu.westga.cs4225.project1.group6.game.entities.Boss;
import edu.westga.cs4225.project1.group6.game.entities.Player;
import edu.westga.cs4225.project1.group6.game.roles.Warrior;
import edu.westga.cs4225.project1.group6.game.roles.moves.NotEnoughManaException;
import edu.westga.cs4225.project1.group6.model.GameLog;
import edu.westga.cs4225.project1.group6.model.EntityInformation;
import edu.westga.cs4225.project1.group6.model.MoveType;

/**
 * This class handles the main game logic. It also is intended to be run on its
 * own thread.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class Game implements Runnable {

	private static final int PLAYER_LIMIT = 2;
	private static final Object LOCK = new Object();

	private List<Player> players;
	private Queue<Player> turnQueue;

	private Entity enemy;

	private GameLog log;
	private int turnCount;

	/**
	 * Creates a new Game.
	 * 
	 * @precondition none
	 * @postcondition getPlayers().size() == 0
	 */
	public Game() {
		this.players = new ArrayList<Player>();
		this.turnQueue = new LinkedList<Player>();
		this.enemy = new Boss(new Warrior());
		this.log = new GameLog();
		this.turnCount = 0;
	}

	@Override
	public void run() {
		while (this.enemy.isAlive()) {
			this.turnCount++;

			this.log.appendLine("Starting Turn " + this.turnCount);
			this.performPlayerTurns();
			this.performBossTurn();
			this.log.appendLine("End of Turn " + this.turnCount + System.lineSeparator());

			this.sendTurnResults();
		}
	}

	private void performPlayerTurns() {
		while (!this.turnQueue.isEmpty()) {
			Player currentPlayer = this.getNextPlayer();
			MoveType type = currentPlayer.waitForMove();
			int initialHealth = this.enemy.getHealthRemaining();
			try {
				if (type == MoveType.REGULAR) {
					currentPlayer.performPrimaryMove(this.enemy);
					int endHealth = this.enemy.getHealthRemaining();
					int damageDealt = initialHealth - endHealth;
					this.log.appendLine(currentPlayer.getName() + " used a primary move dealing " + damageDealt + " damage to the enemy.");
				} else if (type == MoveType.SPECIAL) {
					currentPlayer.performSpecialMove(this.enemy);
					int endHealth = this.enemy.getHealthRemaining();
					int damageDealt = initialHealth - endHealth;
					this.log.appendLine(currentPlayer.getName() + " used a special move dealing " + damageDealt + " damage to the enemy.");
				} else {
					this.log.appendLine(currentPlayer.getName() + " did not make a move.");
				}
			} catch (NotEnoughManaException e) {
				this.log.appendLine(currentPlayer.getName() + " did not have enough mana to make the move.");
			}
		}
		this.repopulateTurnQueue();
	}

	private void performBossTurn() {
		Random generator = new Random();
		int randomIndex = generator.nextInt(this.players.size());
		Player target = this.players.get(randomIndex);
		int initalHealth = target.getHealthRemaining();
		this.enemy.performPrimaryMove(target);
		int finalHealth = target.getHealthRemaining();
		this.log.appendLine("The boss targeted " + target.getName());
		this.log.append(target.getName() + " Has lost " + (initalHealth - finalHealth) + " Health.");
	}

	private void sendTurnResults() {
		ArrayList<EntityInformation> info = new ArrayList<EntityInformation>();
		for (Player player : this.players) {
			EntityInformation currentInformation = new EntityInformation(player.getName(), player.getRole());
			currentInformation.setPlayerHealth(player.getHealthRemaining());
			currentInformation.setPlayerMana(player.getManaRemaining());
			info.add(currentInformation);
		}
		EntityInformation enemyInformation = new EntityInformation("Boss", this.enemy.getRole());
		enemyInformation.setPlayerHealth(this.enemy.getHealthRemaining());
		enemyInformation.setPlayerMana(this.enemy.getManaRemaining());
		for (Player player : this.players) {
			player.sendResults(this.log, info, enemyInformation);
		}
	}

	/**
	 * Adds the player to the game.
	 * 
	 * @precondition newPlayer != null
	 * @postcondition getPlayers().size() == getPlayers().size()@prev + 1
	 * 
	 * @param newPlayer the player to add to the game.
	 * 
	 * @throws ServerFullException if the server is already full.
	 */
	public void addPlayer(Player newPlayer) throws ServerFullException {
		if (newPlayer == null) {
			throw new IllegalArgumentException("newPlayer should not be null.");
		}
		synchronized (LOCK) {
			if (this.players.size() >= PLAYER_LIMIT) {
				throw new ServerFullException("The server is full.");
			}

			this.players.add(newPlayer);
			this.turnQueue.add(newPlayer);
		}
	}

	/**
	 * Determines if the game is ready to be started.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true if the game is ready to be started; false otherwise.
	 */
	public boolean isReady() {
		synchronized (LOCK) {
			return this.players.size() == PLAYER_LIMIT;
		}
	}

	/**
	 * Determines if the game lobby is full.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true if the game lobby is full. False otherwise.
	 */
	public boolean isFull() {
		synchronized (LOCK) {
			return this.players.size() >= PLAYER_LIMIT;
		}
	}

	/**
	 * Gets all of the players in the game. This collection is unmodifiable.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return an unmodifiable collection of all the players in the game.
	 */
	public Collection<Player> getPlayers() {
		return Collections.unmodifiableCollection(this.players);
	}

	/**
	 * Gets the enemy in the game.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the enemy.
	 */
	public Entity getEnemy() {
		return this.enemy;
	}

	private Player getNextPlayer() {
		synchronized (LOCK) {
			return this.turnQueue.remove();
		}
	}

	private void repopulateTurnQueue() {
		synchronized (LOCK) {
			this.players.forEach(player -> this.turnQueue.add(player));
		}
	}
}
