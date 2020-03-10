package edu.westga.cs4225.project1.group6.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.westga.cs4225.project1.group6.model.GamePlayer;

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
	 * @throws ClassNotFoundException 
	 */
	public void start() throws IOException, ClassNotFoundException {
		System.out.println("Server Running - Port: " + this.port);
		this.server = new ServerSocket(this.port);
		int clientPort = this.port + 1;
		while (!this.server.isClosed()) {
			Socket client = this.server.accept();
			ClientConnectionPort clientConnection = new ClientConnectionPort(clientPort);
			this.pool.execute(clientConnection);
			
			try (ObjectInputStream in = new ObjectInputStream(client.getInputStream());
					ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream())) {
				
				GamePlayer player = (GamePlayer) in.readObject();
				// TODO: Add this player to the game somehow
				System.out.println(player.getPlayerDescription());
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
