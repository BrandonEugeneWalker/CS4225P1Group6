package edu.westga.cs4225.project1.group6.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

import edu.westga.cs4225.project1.group6.model.MoveType;
import edu.westga.cs4225.project1.group6.model.TurnResults;

/**
 * Maintains a connection with a particular client. Is capable of sending and
 * receiving messages to the client.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class ClientConnection implements Runnable {

	private static final int TIMEOUT_MS = 300000;

	private int port;
	private ServerSocket server;

	private Consumer<MoveType> onMoveRead;
	private volatile TurnResults currentResult;

	/**
	 * Creates a new ClientConnection that is bound to the specified port.
	 * 
	 * @precondition port > 0
	 * @postcondition none
	 * 
	 * @param port the port at which the private client-server connection is
	 *             created.
	 */
	public ClientConnection(int port) {
		if (port <= 0) {
			throw new IllegalArgumentException("port should not be less than or equal to 0.");
		}

		this.port = port;
		this.currentResult = null;
	}
	
	/**
	 * Closes this client connection.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void close() {
		try {
			this.server.close();
		} catch (IOException e) {
			System.out.println("Already closed.");
		}
	}

	/**
	 * Sets the onMoveRead callback that will respond to the on move read event.
	 * 
	 * @precondition onMoveRead != null
	 * @postcondition none
	 * 
	 * @param onMoveRead the callback that will execute when a move is read.
	 */
	public void setOnMoveRead(Consumer<MoveType> onMoveRead) {
		if (onMoveRead == null) {
			throw new IllegalArgumentException("onMoveRead should not be null.");
		}

		this.onMoveRead = onMoveRead;
	}

	/**
	 * Sets the results to send back to the client.
	 * 
	 * @precondition results != null
	 * @postcondition none
	 * 
	 * @param results the results to send back to the client.
	 */
	public void setCurrentResult(TurnResults results) {
		if (results == null) {
			throw new IllegalArgumentException("results should not be null.");
		}

		this.currentResult = results;
	}

	/**
	 * Runs the server.
	 * 
	 * @precondition none
	 * @postcondition the server is listening.
	 */
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
				if (this.onMoveRead != null) {
					this.onMoveRead.accept(type);
				}

				long startingTime = System.currentTimeMillis();
				while (this.currentResult == null) {
					long currentTime = System.currentTimeMillis();
					long difference = currentTime - startingTime;
					if (difference >= TIMEOUT_MS) {
						throw new RuntimeException("5 Minute Shutdown.");
					}
				}

				out.writeObject(this.currentResult);
				this.currentResult = null;
			}
		}
	}
}
