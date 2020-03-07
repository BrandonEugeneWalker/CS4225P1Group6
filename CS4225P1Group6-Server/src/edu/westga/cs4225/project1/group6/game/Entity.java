package edu.westga.cs4225.project1.group6.game;

import edu.westga.cs4225.project1.group6.game.roles.Role;
import edu.westga.cs4225.project1.group6.game.roles.moves.Move;
import edu.westga.cs4225.project1.group6.game.roles.moves.NotEnoughManaException;

/**
 * This abstraction represents any entity in the game.
 * This could be a player or an enemy. Different concrete
 * implementations can partially override or fully override
 * the behavior that entities have. For example, bosses could override
 * the take damage method to always take reduced damage. Other 
 * modifications to the concrete implementations are up to ones 
 * imagination.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public abstract class Entity {

	public static final int BASE_HEALTH = 100;
	public static final int BASE_MANA = 100;
	
	private Role role;
	
	private int healthRemaining;
	private int manaRemaining;
	
	/**
	 * Entities all start with a base health value and a 
	 * base mana value. They also all have a particular role.
	 * The role given to an entity to determines the moveset that
	 * it has as well as what multipliers are given to health 
	 * and mana values.
	 * 
	 * @precondition role != null
	 * @postcondition getHealthRemaining() == Entity.BASE_HEALTH * role.getHealthMultiplier() &&
	 * 				  getManaRemaining() == Entity.BASE_MANA * role.getManaMultiplier() &&
	 * 				  isAlive() && !isDead() &&
	 * 				  if role.getManaMultiplier() > 0, then !isOutOfMana() &&
	 * 				  if role.getManaMultiplier() == 0, then isOutOfMana()
	 * 
	 * @param role this entity's designated role.
	 */
	protected Entity(Role role) {
		if (role == null) {
			throw new IllegalArgumentException("role should not be null.");
		}
		
		this.role = role;
		this.healthRemaining = BASE_HEALTH;
		this.manaRemaining = BASE_MANA;
		
		this.initializeRoleSpecificStats();
	}
	
	/**
	 * Performs this entity's primary move. The primary move
	 * stems from this entity's role. 
	 * 
	 * If the move succeeds, then the amount of damage done by the move is subtracted
	 * from the target's remaining health. Whether or not it succeeds depends on
	 * the moves accuracy value. A random number is generated between 0 <= number < 1.
	 * If that number is less than or equal to the move's accuracy value, then the move
	 * succeeds; otherwise, it fails.
	 * 
	 * It does not matter if the move hits or not, the mana cost is subtracted from this 
	 * entity's remaining mana. If this entity does not have enough mana
	 * to perform the move, then an exception is thrown.
	 * 
	 * @precondition target != null
	 * @postcondition getManaRemaining() <= getManaRemaining()@prev
	 * 
	 * @throws NotEnoughManaException if this entity does not have enough mana to perform the move.
	 * 
	 * @param target the entity to perform the move on.
	 * @return true if the move killed the target; false otherwise.
	 */
	public boolean performPrimaryMove(Entity target) {
		return this.performMove(this.role.getPrimaryMove(), target);
	}
	
	/**
	 * Performs this entity's special move. The special move
	 * stems from this entity's role. 
	 * 
	 * For more information about how this method works, read
	 * the documentation for the performPrimaryMove(Entity). 
	 * 
	 * @precondition target != null
	 * @postcondition getManaRemaining() <= getManaRemaining()@prev
	 * 
	 * @see edu.westga.cs4225.project1.group6.game.Entity#performPrimaryMove(Entity)
	 * @throws NotEnoughManaException if this entity does not have enough mana to perform the move.
	 * 
	 * @param target the entity to perform the move on.
	 * @return true if the move killed the target; false otherwise.
	 */
	public boolean performSpecialMove(Entity target) {
		return this.performMove(this.role.getSpecialMove(), target);
	}
	
	/**
	 * Makes this entity take the specified amount of damage. A negative
	 * damage value will increase the health remaining of this entity.
	 * 
	 * @precondition none
	 * @postcondition getHealthRemaining() == getHealthRemaining()@prev - damage &&
	 * 				  if damage >= getHealthRemaining(), then isDead()
	 * 
	 * @param damage the amount of damage for this entity to take.
	 * @return true if the damage killed this entity; false otherwise.
	 */
	public boolean takeDamage(int damage) {
		this.healthRemaining -= damage;
		return this.isDead();
	}
	
	/**
	 * Gets whether or not this entity is dead.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true if this entity is dead; false otherwise.
	 */
	public boolean isDead() {
		return this.healthRemaining <= 0;
	}
	
	/**
	 * Gets whether or not this entity is alive.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true if this entity is alive; false otherwise.
	 */
	public boolean isAlive() {
		return !this.isDead();
	}
	
	/**
	 * Gets whether or not this entity is out of mana.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true if this entity is out of mana; false otherwise.
	 */
	public boolean isOutOfMana() {
		return this.manaRemaining <= 0;
	}
	
	/**
	 * Gets the health that this entity has remaining.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the health that this entity has remaining.
	 */
	public int getHealthRemaining() {
		return this.healthRemaining;
	}

	/**
	 * Gets the mana that this entity has remaining.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the mana that this entity has remaining.
	 */
	public int getManaRemaining() {
		return this.manaRemaining;
	}

	protected boolean performMove(Move move, Entity target) {
		if (move == null) {
			throw new IllegalArgumentException("move should not be null.");
		}
		if (target == null) {
			throw new IllegalArgumentException("target should not be null.");
		}
		if (this.manaRemaining < move.getManaCost()) {
			throw new NotEnoughManaException(this, move);
		}
		
		this.manaRemaining -= move.getManaCost();
		move.performMove(this, target);
		
		return target.isDead();
	}
	
	private void initializeRoleSpecificStats() {
		this.healthRemaining *= this.role.getHealthMultiplier();
		this.manaRemaining *= this.role.getManaMultiplier();
	}
	
}
