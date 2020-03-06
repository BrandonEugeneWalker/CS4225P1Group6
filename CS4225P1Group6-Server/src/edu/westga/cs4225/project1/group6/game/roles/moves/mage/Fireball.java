package edu.westga.cs4225.project1.group6.game.roles.moves.mage;

import java.util.Random;

import edu.westga.cs4225.project1.group6.game.Entity;
import edu.westga.cs4225.project1.group6.game.roles.moves.Move;

/**
 * The primary attack for the mage role.
 * 
 * @author Luke Whaley
 *
 */
public class Fireball extends Move {

	private static final double ACCURACY = 1;
	private static final int DAMAGE = 30;
	private static final int MANA_COST = 15;
	
	/**
	 * Creates a new Fireball object. This is the primary
	 * attack for the mage role. There is a ten percent chance
	 * that this move will deal an additional ten percent of the 
	 * targets remaining health.
	 */
	public Fireball() {
		super(ACCURACY, DAMAGE, MANA_COST);
	}

	@Override
	public void performMove(Entity source, Entity target) {
		Random accuracyRoller = new Random();
		double roll = accuracyRoller.nextDouble();
		if (roll <= this.getAccuracy()) {
			int damage = this.getDamage();
			if (accuracyRoller.nextDouble() <= 0.1) {
				int tenPercentTargetRemainingHealth = (int) 0.1 * target.getHealthRemaining();
				damage += tenPercentTargetRemainingHealth;
			}
			target.takeDamage(damage);
		}
	}

}
