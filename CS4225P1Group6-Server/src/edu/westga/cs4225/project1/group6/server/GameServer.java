package edu.westga.cs4225.project1.group6.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.westga.cs4225.project1.group6.game.Game;
import edu.westga.cs4225.project1.group6.game.entities.Player;
import edu.westga.cs4225.project1.group6.game.roles.Role;
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
	
	private Game game;
	
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
		this.game = new Game();
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
			if (this.game.isFull()) {
				this.sendServerFullNotice(client);
			} else {
				this.sendPrivatePort(client, clientPort);
			}
			clientPort++;
			
			if (this.game.isReady()) {
				this.pool.execute(this.game);
			}
		}
	}
	
	private void sendServerFullNotice(Socket socket) throws IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
			in.readObject();
			System.out.println("Server is full. Sending notice.");
			out.writeObject(-1);
		}
	}
	
	private void sendPrivatePort(Socket socket, int port) throws IOException, ClassNotFoundException {
		ClientConnectionPort clientConnection = new ClientConnectionPort(port);
		this.pool.execute(clientConnection);
		
		try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
			GamePlayer playerInformation = (GamePlayer) in.readObject();
			Player player = new Player(playerInformation.getPlayerName(), Role.createRole(playerInformation.getPlayerRole()), clientConnection);
			this.game.addPlayer(player);
			
			System.out.println(playerInformation.getPlayerDescription());
			out.writeObject(port);
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
