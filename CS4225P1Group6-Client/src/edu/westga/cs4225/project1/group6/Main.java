package edu.westga.cs4225.project1.group6;


import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Starting point for the application.
 * 
 * @author Luke Whaley
 *
 */
public class Main extends Application {
	
	private static final String TITLE = "Multiplayer RPG";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Path connectionPath = Paths.get(".", "view","fxml","ConnectionPage.fxml");
			FXMLLoader loader = new FXMLLoader(Main.class.getResource(connectionPath.toString()));
			Pane pane = loader.load();
			Scene scene = new Scene(pane);
			primaryStage.setTitle(TITLE);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Starting point for the application.
	 * 
	 * @param args not used.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
