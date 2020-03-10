package edu.westga.cs4225.project1.group6.view;

import edu.westga.cs4225.project1.group6.client.ServerConnection;
import edu.westga.cs4225.project1.group6.controller.GamePageController;
import edu.westga.cs4225.project1.group6.model.GamePlayer;
import edu.westga.cs4225.project1.group6.model.PlayerRole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
		GamePlayer player = new GamePlayer(this.nameTextField.getText(), this.classComboBox.getSelectionModel().getSelectedItem());
		ServerConnection connection = new ServerConnection(player, this.hostTextField.getText(), Integer.parseInt(this.portTextField.getText()));
		GamePageController.initialize(player, connection);
		
		// TODO: Transition to main application screen here.
	}

	@FXML
	void initialize() {
		this.classComboBox.getItems().removeAll(this.classComboBox.getItems());
		this.classComboBox.getItems().addAll(PlayerRole.Warrior, PlayerRole.Mage, PlayerRole.Healer);
		this.classComboBox.getSelectionModel().select(PlayerRole.Warrior);
	}
}
