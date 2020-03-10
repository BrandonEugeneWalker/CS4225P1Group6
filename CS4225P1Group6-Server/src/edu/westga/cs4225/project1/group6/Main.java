package edu.westga.cs4225.project1.group6;

import java.io.IOException;

import edu.westga.cs4225.project1.group6.server.GameServer;

/**
 * Starting point for the server.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class Main {

	private static final int PORT = 4225;
	
	/**
	 * Starting point for the server application.
	 * 
	 * @param args program arguments.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) {
		GameServer server = new GameServer(PORT);
		Runtime.getRuntime().addShutdownHook(new Thread(() -> server.close()));
		try {
			server.start();
		} catch (ClassNotFoundException | IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			server.close();
		}
	}

}
