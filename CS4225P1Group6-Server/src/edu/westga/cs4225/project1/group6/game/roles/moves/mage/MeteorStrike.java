package edu.westga.cs4225.project1.group6.game.roles.moves.mage;

import java.util.Random;

import edu.westga.cs4225.project1.group6.game.Entity;
import edu.westga.cs4225.project1.group6.game.roles.moves.Move;

/**
 * The special attack for the mage role.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class MeteorStrike extends Move {

	private static final double ACCURACY = 0.8;
	private static final int DAMAGE = 95;
	private static final int MANA_COST = 100;
	
	private static final int CRITICAL_HEALTH_LIMIT = 20;
	
	/**
	 * Creates a new MeteorStrike object. This is the
	 * special attack for the mage role. If the mage's health
	 * is less than or equal to a critical health limit, then
	 * this move does double its normal damage.
	 */
	public MeteorStrike() {
		super(ACCURACY, DAMAGE, MANA_COST);
	}

	@Override
	public void performMove(Entity source, Entity target) {
		Random accuracyRoller = new Random();
		double roll = accuracyRoller.nextDouble();
		if (roll <= this.getAccuracy()) {
			int damage = this.getDamage();
			if (source.getHealthRemaining() <= CRITICAL_HEALTH_LIMIT) {
				damage *= 2;
			}
			target.takeDamage(damage);
		}
	}

}
