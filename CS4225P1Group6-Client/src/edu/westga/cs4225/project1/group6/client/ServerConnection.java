package edu.westga.cs4225.project1.group6.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import edu.westga.cs4225.project1.group6.model.GamePlayer;
import edu.westga.cs4225.project1.group6.model.MoveType;
import edu.westga.cs4225.project1.group6.model.TurnResults;

/**
 * Handles interactions with the server.
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class ServerConnection {

	private Socket activeSocket;
	private int privatePort;
	
	/**
	 * Creates a new ServerConnection object. This object will be created when the 
	 * client connects to the server. It will automatically handle getting its own private
	 * port to communicate with the server.
	 * 
	 * @precondition player != null && host != null && port >= 0
	 * @postcondition none
	 * 
	 * @param player needed to send to the server.
	 * @param host the server host.
	 * @param port the server login port.
	 * @throws CouldNotConnectException if the connection was not made succesfully.
	 */
	public ServerConnection(GamePlayer player, String host, int port) throws CouldNotConnectException {
		if (player == null) {
			throw new IllegalArgumentException("player should not be null.");
		}
		if (host == null) {
			throw new IllegalArgumentException("host should not be null.");
		}
		if (port < 0) {
			throw new IllegalArgumentException("port should not be negative.");
		}
		
		this.attemptToInitializeConnection(player, host, port);
	}
	
	/**
	 * Attempts to send the specified move type to the server. The response will be
	 * returned once the server has sent it back (when all players have submitted moves).
	 * 
	 * @precondition type != null
	 * @postcondition none
	 * 
	 * @param type the type of move the player wants to make.
	 * @return the end of turn results.
	 * 
	 * @throws IOException if a connection error occurs.
	 * @throws ClassNotFoundException (unlikely) if the object could not be deserialized.
	 */
	public TurnResults sendMove(MoveType type) throws IOException, ClassNotFoundException {
		if (type == null) {
			throw new IllegalArgumentException("type should not be null.");
		}
		
		TurnResults results = null;
		try (ObjectOutputStream out = new ObjectOutputStream(this.activeSocket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(this.activeSocket.getInputStream())) {
			System.out.println("Sending move: " + type);
			out.writeObject(type);
			
			System.out.println("Awaiting turn results..");
			results = (TurnResults) in.readObject();
		}
		return results;
	}
	
	/**
	 * Attempts to close this connection. If the connection is
	 * already closed, then nothing happens.
	 * 
	 * @precondition none
	 * @postcondition sendMove(MoveType) will throw an exception.
	 */
	public void attemptToCloseConnection() {
		try {
			this.activeSocket.close();
		} catch (IOException e) {
			System.out.println("Socket Already Closed.");
		}
	}
	
	private void attemptToInitializeConnection(GamePlayer player, String host, int port) {
		try {
			this.initializeConnection(player, host, port);
		} catch (ClassNotFoundException | IOException e) {
			throw new CouldNotConnectException(e.getMessage());
		}
	}
	
	private void initializeConnection(GamePlayer player, String host, int port) throws IOException, ClassNotFoundException {
		Socket loginSocket = new Socket(host, port);
		try (ObjectOutputStream out = new ObjectOutputStream(loginSocket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(loginSocket.getInputStream())) {
			System.out.println("Attempting Login..");
			out.writeObject(player);
			
			this.privatePort = (Integer) in.readObject();
			System.out.println("Connection Successful: Starting private connection to port " + this.privatePort);
			
			this.activeSocket = new Socket(host, this.privatePort);
		} finally {
			loginSocket.close();
		}
	}
}
