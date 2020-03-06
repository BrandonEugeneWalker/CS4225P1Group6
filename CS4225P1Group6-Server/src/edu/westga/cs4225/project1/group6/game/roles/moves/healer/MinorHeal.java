package edu.westga.cs4225.project1.group6.game.roles.moves.healer;

import java.util.Random;

import edu.westga.cs4225.project1.group6.game.Entity;
import edu.westga.cs4225.project1.group6.game.roles.moves.Move;

/**
 * The primary attack for the healer role.
 * 
 * @author Luke Whaley
 *
 */
public class MinorHeal extends Move {

	private static final double ACCURACY = 1;
	private static final int DAMAGE = -30;
	private static final int MANA_COST = 30;
	
	/**
	 * Creates a new MinorHeal move object. This is the
	 * primary attack for the healer role. No matter the target,
	 * the healer always heals itself one half of what it heals
	 * the target.
	 */
	public MinorHeal() {
		super(ACCURACY, DAMAGE, MANA_COST);
	}

	@Override
	public void performMove(Entity source, Entity target) {
		Random accuracyRoller = new Random();
		double roll = accuracyRoller.nextDouble();
		if (roll <= this.getAccuracy()) {
			target.takeDamage(this.getDamage());
			source.takeDamage(this.getDamage() / 2);
		}
	}

}
