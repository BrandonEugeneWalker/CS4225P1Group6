package edu.westga.cs4225.project1.group6.game.roles.moves;

import edu.westga.cs4225.project1.group6.game.Entity;

/**
 * An exception that is thrown when an entity attemps to perform
 * a move without enough mana.
 * 
 * @author Luke Whaley
 *
 */
public class NotEnoughManaException extends RuntimeException {

	private static final long serialVersionUID = 7368352320523547758L;

	/**
	 * Creates a new NotEnoughManaException object.
	 * 
	 * @precondition caster != null && attemptedMove != null
	 * @postcondition none
	 * 
	 * @param caster the entity that tried to perform the move.
	 * @param attemptedMove the move that the entity tried to perform.
	 */
	public NotEnoughManaException(Entity caster, Move attemptedMove) {
		super("Current Mana: " + caster.getManaRemaining() 
			+ ". Move Cost: " + attemptedMove.getManaCost()
			+ ". Not Enough Mana.");
	}
}
