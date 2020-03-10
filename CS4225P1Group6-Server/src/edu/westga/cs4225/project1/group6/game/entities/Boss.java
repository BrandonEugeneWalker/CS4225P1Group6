package edu.westga.cs4225.project1.group6.game.entities;

import edu.westga.cs4225.project1.group6.game.Entity;
import edu.westga.cs4225.project1.group6.game.roles.Role;

/**
 * The Boss enemy entity.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class Boss extends Entity {

	/**
	 * Creates a new Boss entity.
	 * 
	 * @precondition role != null
	 * @postcondition none
	 * 
	 * @param role the boss' role.
	 */
	public Boss(Role role) {
		super(role);
		int currentHealth = this.getHealthRemaining();
		this.takeDamage(-2 * currentHealth);
	}

}
