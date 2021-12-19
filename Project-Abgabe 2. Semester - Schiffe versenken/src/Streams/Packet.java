package Streams;

import java.io.Serializable;

import SchiffeVersenken.SchiffeVersenken;

/**
 * 
 * Used to send the needed information through the pipe
 *
 */

public class Packet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 254349219740160271L;

	/*
	 * player One
	 */
	private  SchiffeVersenken p1;
	/*
	 * player two
	 */
	private  SchiffeVersenken p2;
	/*
	 * active player
	 * 
	 * @code true = player one
	 * 
	 * @code false = player two
	 */
	boolean activePlayer;

	// initialize
	public Packet(SchiffeVersenken p1, SchiffeVersenken p2, boolean activePlayer) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.activePlayer = activePlayer;
	}

	///////////////////Getters
	
	public SchiffeVersenken getP1() {
		return p1;
	}

	public SchiffeVersenken getP2() {
		return p2;
	}

	public boolean getActivePlayer() {
		return activePlayer;
	}
	public boolean setActivePlayer(boolean p) {
		return this.activePlayer = p;
	}
	
}
