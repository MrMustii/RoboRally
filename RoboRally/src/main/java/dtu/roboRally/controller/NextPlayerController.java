package dtu.roboRally.controller;

import dtu.roboRally.view.NextPlayerView;
import javafx.stage.Stage;

/**
 * Controller for the scene to switch players
 * calls the controller for the next player to pick cards (PickCardsController)
 */
public class NextPlayerController {

	private RoboRallyController application;
	private Stage primaryStage;
	private NextPlayerView view;

	private int playerIndex;

	/**
	 * Constructor that retrieve relevant data from application and instantiates the view
	 * @param application (RoboRallyController)
	 * @param primaryStage (Stage)
	 * @param playerName (String)
	 * @param playerIndex (int)
	 */
	public NextPlayerController(RoboRallyController application, Stage primaryStage, String playerName, int playerIndex) {
		this.application = application;
		this.primaryStage = primaryStage;
		this.playerIndex = playerIndex;

		view = new NextPlayerView(this, playerName);
	}

	/**
	 * sets the scene to the primaryStage
	 */
	public void display() {
		primaryStage.setScene(view.initGUI());
	}

	/**
	 * called when clicked on this scene
	 * asks the application to start PickCardsController for this player
	 */
	public void pickCards() {
		application.pickCards(primaryStage, playerIndex);
	}

}
