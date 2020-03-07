package edu.westga.cs4225.project1.group6.server;

import java.io.IOException;
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
		while (true) {
			Socket client = this.server.accept();
			this.pool.execute(new ClientConnection(client));
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
