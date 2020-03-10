package edu.westga.cs4225.project1.group6.game.roles;

import edu.westga.cs4225.project1.group6.game.roles.moves.healer.GreaterHeal;
import edu.westga.cs4225.project1.group6.game.roles.moves.healer.MinorHeal;

/**
 * This is the support role. A healer can not deal damage to enemies. Instead,
 * they focus on keeping their team-mates alive.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class Healer extends Role {

	private static final double HEALTH_MULTIPLIER = 0.5;
	private static final double MANA_MULTIPLIER = 4.5;

	/**
	 * Creates a new healer role object.
	 */
	public Healer() {
		super(HEALTH_MULTIPLIER, MANA_MULTIPLIER, new MinorHeal(), new GreaterHeal());
	}

}
