package dtu.roboRally.controller;

import dtu.roboRally.view.WinView;
import javafx.stage.Stage;

/**
 * controller for the Win scene
 */
public class WinController {
	
	private RoboRallyController application;
	private Stage primaryStage;
	private WinView view;

	/**
	 * Constructor that retrieves relevant data and instantiates the view
	 * @param application (RoboRallyController)
	 * @param primaryStage (Stage)
	 * @param nameOfWinner (String)
	 */
	public WinController(RoboRallyController application, Stage primaryStage, String nameOfWinner) {
		this.application = application;
		this.primaryStage = primaryStage;
		
		view = new WinView(this, nameOfWinner);
	}

	/**
	 * called by 'new game' button of the view
	 * tells the application to start a new game
	 */
	public void newGame() {
		application.startApplication(primaryStage);
	}

	/**
	 * sets the scene on the primary stage
	 */
	public void display() {
		primaryStage.setScene(view.initGUI());
	}
	
	
}
