package edu.westga.cs4225.project1.group6.server;

import java.net.Socket;

/**
 * Maintains a connection with a particular client.
 * Is capable of sending and receiving messages to the 
 * client.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class ClientConnection implements Runnable {

	private Socket client;
	
	/**
	 * Creates a new ClientConnection that is bound to the 
	 * specified socket.
	 * 
	 * @precondition client != null
	 * @postcondition none
	 * 
	 * @param client the client of this connection.
	 */
	public ClientConnection(Socket client) {
		if (client == null) {
			throw new IllegalArgumentException("client should not be null.");
		}
		
		this.client = client;
	}
	
	@Override
	public void run() {
		
	}

}
