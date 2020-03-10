package edu.westga.cs4225.project1.group6.game.roles.moves;

import edu.westga.cs4225.project1.group6.game.Entity;

/**
 * Represents a generic move. Each move has an accuracy value, a damage value,
 * and a manaCost value.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public abstract class Move {

	private double accuracy;

	private int damage;
	private int manaCost;

	/**
	 * Accuracy values should be between 0 < accuracy <= 1. An accuracy of 1 will
	 * always hit a target unless there are some other factors preventing the damage
	 * to go through. The damage is the base damage that this move does assuming
	 * there are no other factors reducing the damage. The manacost is the cost to
	 * perform the move. Mana cost can be zero.
	 * 
	 * @precondition accuracy > 0 && accuracy <= 1 && manaCost >= 0
	 * @postcondition getAccuracy() == accuracy && getDamage() == damage &&
	 *                getManaCost() == manaCost
	 * 
	 * @param accuracy the move's accuracy.
	 * @param damage   the move's damage.
	 * @param manaCost the move's mana cost.
	 */
	protected Move(double accuracy, int damage, int manaCost) {
		if (accuracy <= 0 || accuracy > 1) {
			throw new IllegalArgumentException("accuracy should not be less than or equal to zero or greater than 1.");
		}
		if (manaCost < 0) {
			throw new IllegalArgumentException("manaCost should not be less than zero.");
		}

		this.accuracy = accuracy;
		this.damage = damage;
		this.manaCost = manaCost;
	}

	/**
	 * Performs this move on the specified target.
	 * 
	 * @precondition source != null && target != null
	 * @postcondition none
	 * 
	 * @param source the entity performing the move.
	 * @param target the entity to perform the move on.
	 */
	public abstract void performMove(Entity source, Entity target);

	/**
	 * Gets this move's accuracy.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return this move's accuracy.
	 */
	public double getAccuracy() {
		return this.accuracy;
	}

	/**
	 * Gets this move's damage value.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return this move's damage value.
	 */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * Gets this move's mana cost.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return this move's mana cost.
	 */
	public int getManaCost() {
		return this.manaCost;
	}

}
