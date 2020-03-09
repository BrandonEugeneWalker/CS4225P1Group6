package edu.westga.cs4225.project1.group6.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Maintains a connection with a particular client.
 * Is capable of sending and receiving messages to the 
 * client.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class ClientConnectionPort implements Runnable {

	private int port;
	private ServerSocket server;
	
	/**
	 * Creates a new ClientConnection that is bound to the 
	 * specified port.
	 * 
	 * @precondition port > 0
	 * @postcondition none
	 * 
	 * @param port the port at which the private client-server connection is created.
	 */
	public ClientConnectionPort(int port) {
		if (port <= 0) {
			throw new IllegalArgumentException("port should not be less than or equal to 0.");
		}
		
		this.port = port;
	}
	
	@Override
	public void run() {
		this.attemptToListen();
	}

	private void attemptToListen() {
		try {
			this.startListening();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void startListening() throws IOException {
		this.server = new ServerSocket(this.port);
		while (!this.server.isClosed()) {
			Socket client = this.server.accept();
			try (ObjectInputStream in = new ObjectInputStream(client.getInputStream());
					ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream())) {
				// Read client input from in. Write back to the client with out.
				// A ClientConnectionPort will be created for every player that is playing
				// the game. There will be a thread for each player (executing this runnable).
				// On each thread, a new ServerSocket will be opened on a new port. This listening
				// port will solely be used to communicate with ONE client.
			}
		}
	}
}
