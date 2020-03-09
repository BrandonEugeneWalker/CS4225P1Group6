package edu.westga.cs4225.project1.group6.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is the game's server class. It 
 * continuously listens for connections and
 * handles them when it receives them.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class GameServer {

	private static final int NUMBER_OF_THREADS = 6;

	private ServerSocket server;
	private int port;

	private ExecutorService pool;
	
	/**
	 * Creates a new GameServer. The server is bound
	 * to the specified port. 
	 * 
	 * @precondition port > 0
	 * @postcondition none
	 * 
	 * @param port the server port.
	 */
	public GameServer(int port) {
		if (port <= 0) {
			throw new IllegalArgumentException("port should not be less than or equal to 0.");
		}
		
		this.server = null;
		this.port = port;
		this.pool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
	}
	
	/**
	 * Starts the GameServer to listen for incoming transmissions.
	 * 
	 * @precondition none
	 * @postcondition the server starts
	 * 
	 * @throws IOException if an I/O error occurs.
	 */
	public void start() throws IOException {
		this.server = new ServerSocket(this.port);
		int clientPort = this.port + 1;
		while (!this.server.isClosed()) {
			Socket client = this.server.accept();
			ClientConnectionPort clientConnection = new ClientConnectionPort(clientPort);
			this.pool.execute(clientConnection);
			
			try (ObjectInputStream in = new ObjectInputStream(client.getInputStream());
					ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream())) {
				// Receive GamePlayer object from ObjectInputStream in. Once we receive,
				// give it to the clientConnection. Probably should separate the GamePlayer data
				// from the ClientConnectionPort class though. They should be linked somehow though
				// to maintain the relationship.
				
				// TODO: Uncomment below once GamePlayer is copied to the server side.
				// GamePlayer player = (GamePlayer) in.readObject();
				
				// Next occurs after the GamePlayer object is received.
				// We are going to send a number back to the client so that they know which
				// new port to connect to for their own private client-server interaction with the server.
				out.writeObject(clientPort);
			}
			clientPort++;
		}
	}

	/**
	 * Closes this GameServer.
	 * 
	 * @precondition none
	 * @postcondition the server is closed.
	 */
	public void close() {
		try {
			this.server.close();
			this.pool.shutdown();
		} catch (IOException e) {
			System.out.println("The server is already closed.");
		}
	}
}
