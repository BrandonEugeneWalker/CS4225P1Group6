package edu.westga.cs4225.project1.group6.game.roles.moves.warrior;

import java.util.Random;

import edu.westga.cs4225.project1.group6.game.Entity;
import edu.westga.cs4225.project1.group6.game.roles.moves.Move;

/**
 * The primary attack for the Warrior role.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class MeleeAttack extends Move {

	private static final double ACCURACY = 0.9;
	private static final int DAMAGE = 10;
	private static final int MANA_COST = 0;

	/**
	 * Creates a new MeleeAttack object. This attack is the warrior role's primary
	 * attack.
	 * 
	 * @precondition none
	 * @postcondition the action is performed
	 */
	public MeleeAttack() {
		super(ACCURACY, DAMAGE, MANA_COST);
	}

	/**
	 * Performs the given action on the given entity.
	 * 
	 * @precondition none
	 * @postcondition the action is done
	 */
	@Override
	public void performMove(Entity source, Entity target) {
		Random accuracyRoller = new Random();
		double roll = accuracyRoller.nextDouble();
		if (roll <= this.getAccuracy()) {
			target.takeDamage(this.getDamage());
		}
	}

}
