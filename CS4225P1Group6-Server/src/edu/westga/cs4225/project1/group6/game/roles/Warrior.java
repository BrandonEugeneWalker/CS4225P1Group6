package edu.westga.cs4225.project1.group6.game.roles;

import edu.westga.cs4225.project1.group6.game.roles.moves.warrior.MeleeAttack;
import edu.westga.cs4225.project1.group6.game.roles.moves.warrior.TwoHandedSwing;

/**
 * This is the melee role. A warrior has a higher health multiplier than the
 * other roles. Warriors do not use mana.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class Warrior extends Role {

	private static final double HEALTH_MULTIPLIER = 1.1;
	private static final double MANA_MULTIPLIER = 0;

	/**
	 * Creates a new Warrior role object.
	 */
	public Warrior() {
		super(HEALTH_MULTIPLIER, MANA_MULTIPLIER, new MeleeAttack(), new TwoHandedSwing());
	}
}
