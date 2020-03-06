package edu.westga.cs4225.project1.group6.game.roles.moves.warrior;

import java.util.Random;

import edu.westga.cs4225.project1.group6.game.Entity;
import edu.westga.cs4225.project1.group6.game.roles.moves.Move;

/**
 * The special attack for the Warrior role.
 * 
 * @author Luke Whaley
 *
 */
public class TwoHandedSwing extends Move {

	private static final double ACCURACY = 0.7;
	private static final int DAMAGE = 55;
	private static final int MANA_COST = 0;
	
	/**
	 * Creates a new TwoHandedSwing object. This is the
	 * warrior role's special attack. If this move hits,
	 * then the warrior loses five percent of his health.
	 */
	public TwoHandedSwing() {
		super(ACCURACY, DAMAGE, MANA_COST);
	}

	@Override
	public void performMove(Entity source, Entity target) {
		Random accuracyRoller = new Random();
		double roll = accuracyRoller.nextDouble();
		if (roll <= this.getAccuracy()) {
			int fivePercentRemainingHealth = (int) 0.05 * source.getHealthRemaining();
			target.takeDamage(this.getDamage());
			source.takeDamage(fivePercentRemainingHealth);
		}
	}

}
