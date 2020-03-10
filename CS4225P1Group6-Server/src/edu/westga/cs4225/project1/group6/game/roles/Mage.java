package edu.westga.cs4225.project1.group6.game.roles;

import edu.westga.cs4225.project1.group6.game.roles.moves.mage.Fireball;
import edu.westga.cs4225.project1.group6.game.roles.moves.mage.MeteorStrike;

/**
 * This is the ranged role. A mage has a smaller health multiplier, but has a
 * much higher mana multiplier.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class Mage extends Role {

	private static final double HEALTH_MULTIPLIER = 0.7;
	private static final double MANA_MULTIPLIER = 2.5;

	/**
	 * Creates a new Mage role object.
	 */
	public Mage() {
		super(HEALTH_MULTIPLIER, MANA_MULTIPLIER, new Fireball(), new MeteorStrike());
	}

}
