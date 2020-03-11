package edu.westga.cs4225.project1.group6.view;

import java.util.ArrayList;
import java.util.List;

import edu.westga.cs4225.project1.group6.client.RunnableTask;
import edu.westga.cs4225.project1.group6.controller.GamePageController;
import edu.westga.cs4225.project1.group6.model.EntityInformation;
import edu.westga.cs4225.project1.group6.model.MoveType;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * Handles displaying and interaction with the user playing the game.
 * 
 * @author Luke Whaley, Brandon Walker, Kevin Flynn
 *
 */
public class GamePageCodeBehind {

	@FXML
	private TextArea allyOneTextArea;

	@FXML
	private TextArea allyTwoTextArea;

	@FXML
	private TextArea allyThreeTextArea;

	@FXML
	private TextArea monsterStatusTextArea;

	@FXML
	private TextArea playerStatusTextArea;

	@FXML
	private TextArea battleLogTextArea;

	@FXML
	private Button secondaryActionButton;

	@FXML
	private Button primaryActionButton;

	@FXML
	private Button leaveGameButton;
	
	private boolean isGameOver = false;

	@FXML
	void initialize() {
		this.updateNodes();
	}

	@FXML
	void leaveGameClicked(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void primaryActionClicked(MouseEvent event) {
		this.disableAll(true);
		RunnableTask task = new RunnableTask(() -> this.executePrimaryAction(), runnableTask -> {
			if (!this.isGameOver) {
				this.disableAll(false);
			}
		});
		Thread actionThread = new Thread(task);
		actionThread.start();
	}
	
	private void executePrimaryAction() {
		GamePageController.get().submitMove(MoveType.REGULAR);
		this.updateNodes();
	}

	@FXML
	void secondaryActionClicked(MouseEvent event) {
		this.disableAll(true);
		RunnableTask task = new RunnableTask(() -> this.executeSecondaryAction(), runnableTask -> {
			if (!this.isGameOver) {
				this.disableAll(false);
			}
		});
		Thread actionThread = new Thread(task);
		actionThread.start();
	}
	
	private void executeSecondaryAction() {
		GamePageController.get().submitMove(MoveType.SPECIAL);
		this.updateNodes();
	}

	private void disableAll(boolean isDisabled) {
		this.leaveGameButton.setDisable(isDisabled);
		this.primaryActionButton.setDisable(isDisabled);
		this.secondaryActionButton.setDisable(isDisabled);
	}

	private void updateNodes() {
		EntityInformation monster = GamePageController.get().getMonster();
		this.monsterStatusTextArea.textProperty()
				.setValue(monster.getPlayerName() + System.lineSeparator() + "Health: " + monster.getPlayerHealth()
						+ System.lineSeparator() + "Mana: " + monster.getPlayerMana() + System.lineSeparator()
						+ "Role: " + monster.getPlayerRole());

		EntityInformation localPlayer = GamePageController.get().getLocalPlayer();
		this.playerStatusTextArea.textProperty().setValue(localPlayer.getPlayerDescription());

		List<TextArea> allies = new ArrayList<TextArea>();
		allies.add(this.allyOneTextArea);
		allies.add(this.allyTwoTextArea);
		allies.add(this.allyThreeTextArea);

		List<EntityInformation> players = GamePageController.get().getRpgGamePlayers();
		for (int i = 0; i < players.size(); i++) {
			allies.get(i).textProperty().setValue(players.get(i).getPlayerDescription());
		}

		this.battleLogTextArea.textProperty().setValue(GamePageController.get().getRpgGameLog().getGameLog());
		this.battleLogTextArea.appendText("");

		if (monster.getPlayerHealth() <= 0) {
			this.triggerGameOver();
			this.isGameOver = true;
		}
	}

	private void triggerGameOver() {
		var triggerRunnable = new Runnable() {

			@Override
			public void run() {
				primaryActionButton.setDisable(true);
				secondaryActionButton.setDisable(true);

				Alert gameOverAlert = new Alert(AlertType.INFORMATION);
				gameOverAlert.setTitle("Game Over!");
				gameOverAlert.setContentText(
						"The game has ended! You can review the results in the game's log. Hit leave game when you are ready to close the window.");
				gameOverAlert.show();
				
				GamePageController.get().closeServerConnection();
				
			}
			
		};
		Platform.runLater(triggerRunnable);

	}
}
