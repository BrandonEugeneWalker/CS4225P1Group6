package edu.westga.cs4225.project1.group6.game.roles.moves.healer;

import java.util.Random;

import edu.westga.cs4225.project1.group6.game.Entity;
import edu.westga.cs4225.project1.group6.game.roles.moves.Move;

/**
 * This is the special attack for the healer role.
 * 
 * @author Luke Whaley
 *
 */
public class GreaterHeal extends Move {

	private static final double ACCURACY = 1;
	private static final int DAMAGE = -100;
	private static final int MANA_COST = 100;
	
	private static final int RECOIL_DAMAGE = 10;
	
	/**
	 * Creates a new GreaterHeal move object. This is the 
	 * special attack for the healer role. This move has a
	 * tiring effect on the healer, it deals recoil damage to
	 * the healer when it is performed.
	 */
	public GreaterHeal() {
		super(ACCURACY, DAMAGE, MANA_COST);
	}

	@Override
	public void performMove(Entity source, Entity target) {
		Random accuracyRoller = new Random();
		double roll = accuracyRoller.nextDouble();
		if (roll <= this.getAccuracy()) {
			target.takeDamage(this.getDamage());
		}
		source.takeDamage(RECOIL_DAMAGE);
	}

}
