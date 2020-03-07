package edu.westga.cs4225.project1.group6.model;

/**
 * Enum type for describing the status of a player. The player status is either
 * normal or unconscious depending on how the fight went. If a player fails to
 * make a turn selection in time their status should become unresponsive. If a
 * player is unresponsive for two turns in a row they become disconnected which
 * is equal to unconscious to the game.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public enum PlayerStatus {
	Normal, Unconscious, Unresponsive, Disconnected
}
