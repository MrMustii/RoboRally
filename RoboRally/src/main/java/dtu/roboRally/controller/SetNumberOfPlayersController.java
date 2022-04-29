package dtu.roboRally.controller;

import dtu.roboRally.view.SetNumberOfPlayersView;
import javafx.stage.Stage;

/**
 * Controller for the first Scene that sets the number of player
 */
public class SetNumberOfPlayersController {
	
	private RoboRallyController application;
	private SetNumberOfPlayersView view;
	private Stage primaryStage;

	private GameRulesController GRC;

	/**
	 * Constructor that instantiates the SetNumberOfPlayersView
	 * @param application (RoboRallyController)
	 * @param primaryStage (Stage)
	 */
	public SetNumberOfPlayersController(RoboRallyController application, Stage primaryStage) {
		this.application = application;
		this.primaryStage = primaryStage;
		view = new SetNumberOfPlayersView(this);

		GRC = new GameRulesController(this, primaryStage);
	}

	/**
	 * called when pushing the button 'start game'
	 * asks the application to instantiate the game with the correct number of player
	 * asks the application to call the next Scene
	 * @param nbOfPlayers (int)
	 */
	public void setPlayerNames(int nbOfPlayers) {
		application.instantiateGame(nbOfPlayers);
		application.setPlayerNames(primaryStage);
	}

	/**
	 * sets the Scene corresponding to this view on the primary Stage
	 */
	public void display() {
		primaryStage.setScene(view.initGUI());
	}

	/**
	 * to call the rules controller
	 */
	public void displayRules(){
		GRC.display();
	}
	
}
