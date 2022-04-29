package dtu.roboRally.controller;

import java.util.ArrayList;

import dtu.roboRally.view.SetPlayerNamesView;
import javafx.stage.Stage;

/**
 * Controller for the second Scene: asks the player names
 */
public class SetPlayerNamesController {
	
	private RoboRallyController application;
	private Stage primaryStage;
	private SetPlayerNamesView view;
	private ArrayList<String> playerNames;

	/**
	 * Controller that retrieves needed data from the application and instantiates the view
	 * gets an empty playerNames array and update it with the new names
	 * @param application (RoboRallyController)
	 * @param primaryStage (Stage)
	 * @param playerNames (ArrayList<String>)
	 */
	public SetPlayerNamesController(RoboRallyController application, Stage primaryStage, ArrayList<String> playerNames) {
		this.application = application;
		this.primaryStage = primaryStage;
		this.playerNames = playerNames;
		this.view = new SetPlayerNamesView(this);
	}

	/**
	 * called by the button 'confirm'
	 * asks the application to pass to the next Scene, to pick the starting positions
	 */
	public void pickStartingPositions() {
		application.pickStartingPositions(primaryStage);
	}

	/**
	 * called when a textField is changed, and adds the playerNames list
	 * @param nameIndex (int) which player name to update
	 * @param name (String) new name
	 */
	public void addPlayerName(int nameIndex, String name) {
		playerNames.add(nameIndex, name);
	}

	/**
	 * sets the corresponding scene to the primary stage
	 */
	public void display() {
		primaryStage.setScene(view.initGUI());
	}

	/**
	 * removes the player name at that given id (in order to update it)
	 * @param nameIndex (int)
	 */
	public void removePlayerName(int nameIndex) {
		playerNames.remove(nameIndex);
	}

	/**
	 * goes back to pickPlayerNumber
	 */
	public void goBack(){
		application.startApplication(primaryStage);
	}
}
