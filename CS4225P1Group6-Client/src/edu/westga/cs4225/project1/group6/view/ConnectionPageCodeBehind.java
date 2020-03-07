package edu.westga.cs4225.project1.group6.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Code behind for ./fxml/ConnectionPage.fxml
 * 
 * @author Luke Whaley
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
	private ComboBox<String> classComboBox;

	@FXML
	void onConnectButtonAction(ActionEvent event) {

	}

	@FXML
	void initialize() {
		this.classComboBox.getItems().removeAll(this.classComboBox.getItems());
		this.classComboBox.getItems().addAll("Warrior", "Mage", "Healer");
		this.classComboBox.getSelectionModel().select("Warrior");
	}
}
