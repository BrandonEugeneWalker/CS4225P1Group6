package edu.westga.cs4225.project1.group6.view;

import java.nio.file.Paths;

import edu.westga.cs4225.project1.group6.client.ServerConnection;
import edu.westga.cs4225.project1.group6.controller.GamePageController;
import edu.westga.cs4225.project1.group6.model.GamePlayer;
import edu.westga.cs4225.project1.group6.model.PlayerRole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Code behind for ./fxml/ConnectionPage.fxml
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class ConnectionPageCodeBehind {

	@FXML
	private AnchorPane pane;

	@FXML
	private TextField hostTextField;

	@FXML
	private TextField portTextField;

	@FXML
	private Button connectButton;

	@FXML
	private TextField nameTextField;

	@FXML
	private ComboBox<PlayerRole> classComboBox;

	@FXML
	void onConnectButtonAction(ActionEvent event) {
		try {
			GamePlayer player = new GamePlayer(this.nameTextField.getText(), this.classComboBox.getSelectionModel().getSelectedItem());
			ServerConnection connection = new ServerConnection(player, this.hostTextField.getText(), Integer.parseInt(this.portTextField.getText()));
			GamePageController.initialize(player, connection);
			
			java.nio.file.Path connectionPath = Paths.get(".", "view","fxml","GamePage.fxml");
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(edu.westga.cs4225.project1.group6.Main.class.getResource(connectionPath.toString()));
			loader.load();
			javafx.scene.Parent parent = loader.getRoot();
			Scene scene = new Scene(parent);
			Stage playListInfoStage = new Stage();
			playListInfoStage.setTitle("Playlist Info");
			playListInfoStage.setScene(scene);
			playListInfoStage.initModality(Modality.APPLICATION_MODAL);
			playListInfoStage.showAndWait();
		}catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR, e.getLocalizedMessage(), ButtonType.OK);
			alert.showAndWait();	
		}	}

	@FXML
	void initialize() {
		this.classComboBox.getItems().removeAll(this.classComboBox.getItems());
		this.classComboBox.getItems().addAll(PlayerRole.Warrior, PlayerRole.Mage, PlayerRole.Healer);
		this.classComboBox.getSelectionModel().select(PlayerRole.Warrior);
	}
}
