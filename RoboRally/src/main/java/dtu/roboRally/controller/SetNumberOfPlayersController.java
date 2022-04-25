package dtu.roboRally.controller;

import dtu.roboRally.Game;
import dtu.roboRally.view.SetNumberOfPlayersView;
import javafx.stage.Stage;

public class SetNumberOfPlayersController {
	
	private RoboRallyController application;
	private SetNumberOfPlayersView view;
	private Stage primaryStage;
	
	public SetNumberOfPlayersController(RoboRallyController application, Stage primaryStage) {
		this.application = application;
		this.primaryStage = primaryStage;
		view = new SetNumberOfPlayersView(this);
	}
	
	public void setPlayerNames(int nbOfPlayers) {
		application.instantiateGame(nbOfPlayers);
		application.setPlayerNames(primaryStage);
	}
	
	public void display() {
		primaryStage.setScene(view.initGUI());
	}
	
}
