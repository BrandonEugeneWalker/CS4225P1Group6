package edu.westga.cs4225.project1.group6.game.roles;

import edu.westga.cs4225.project1.group6.game.roles.moves.Move;

/**
 * Represents a generic role that a player or enemy can
 * be. Each role has its own health multiplier, mana multiplier,
 * primaryMove, and specialMove.
 * 
 * @author Luke Whaley
 *
 */
public abstract class Role {

	private double healthMultiplier;
	private double manaMultiplier;
	
	private Move primaryMove;
	private Move specialMove;
	
	/**
	 * The multipliers are intended to be multiplied against a player or enemies
	 * health/mana pool. A large multiplier will increase the health/mana pool while
	 * a value less than 1 will decrease it. Mana multipliers can be zero for roles that
	 * do not utilize mana.
	 * 
	 * @precondition healthMultiplier > 0 && manaMultiplier >= 0 && primaryMove != null && specialMove != null
	 * @postcondition getHealthMultiplier() == healthMultiplier && getManaMultiplier() == manaMultiplier
	 * 				 && getPrimaryMove().equals(primaryMove) && getSpecialMove().equals(specialMove)
	 * 
	 * @param healthMultiplier the factor which increases or decreases health.
	 * @param manaMultiplier the factor which increases or decreases mana.
	 * @param primaryMove the role's primary move.
	 * @param specialMove the role's special move.
	 */
	protected Role(int healthMultiplier, double manaMultiplier, Move primaryMove, Move specialMove) {
		if (healthMultiplier <= 0) {
			throw new IllegalArgumentException("healthMultiplier should not be less than or equal to zero.");
		}
		if (manaMultiplier < 0) {
			throw new IllegalArgumentException("manaMultiplier should not be less than zero.");
		}
		if (primaryMove == null) {
			throw new IllegalArgumentException("primaryMove should not be null.");
		}
		if (specialMove == null) {
			throw new IllegalArgumentException("specialMove should not be null.");
		}
		
		this.healthMultiplier = healthMultiplier;
		this.manaMultiplier = manaMultiplier;
		this.primaryMove = primaryMove;
		this.specialMove = specialMove;
	}

	/**
	 * Gets this role's health multiplier.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return this role's health multiplier.
	 */
	public double getHealthMultiplier() {
		return this.healthMultiplier;
	}

	/**
	 * Gets this role's mana multiplier.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return this role's mana multiplier.
	 */
	public double getManaMultiplier() {
		return this.manaMultiplier;
	}

	/**
	 * Gets this role's primary move.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return this role's primary move.
	 */
	public Move getPrimaryMove() {
		return this.primaryMove;
	}

	/**
	 * Gets this role's special move.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return this role's special move.
	 */
	public Move getSpecialMove() {
		return this.specialMove;
	}

}
