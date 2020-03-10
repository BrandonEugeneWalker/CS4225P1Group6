package edu.westga.cs4225.project1.group6.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import edu.westga.cs4225.project1.group6.model.MoveType;
import edu.westga.cs4225.project1.group6.model.TurnResults;

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
		} catch (IOException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void startListening() throws IOException, ClassNotFoundException {
		this.server = new ServerSocket(this.port);
		while (!this.server.isClosed()) {
			Socket client = this.server.accept();
			try (ObjectInputStream in = new ObjectInputStream(client.getInputStream());
					ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream())) {
				MoveType type = (MoveType) in.readObject();
				
				// TODO: Execute the move for this client.
				// Wait until server gives the heads up and execute the following two lines
				// to send results back to the client.
				//TurnResults results = new TurnResults(log, players);
				//out.writeObject(results);
			}
		}
	}
}
