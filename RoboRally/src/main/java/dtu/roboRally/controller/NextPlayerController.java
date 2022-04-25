package dtu.roboRally.controller;

import dtu.roboRally.view.NextPlayerView;
import javafx.stage.Stage;

public class NextPlayerController {
	
	private RoboRallyController application;
	private int playerIndex;
	private String playerName;
	private Stage primaryStage;
	
	private NextPlayerView view;
	
	public NextPlayerController(RoboRallyController application, Stage primaryStage, String playerName, int playerIndex) {
		this.application = application;
		this.primaryStage = primaryStage;
		this.playerIndex = playerIndex;
		this.playerName = playerName;
		
		view = new NextPlayerView(this, playerName);
	}
	
	public void display() {
		primaryStage.setScene(view.initGUI());
	}
	
	public void pickCards() {
		application.pickCards(primaryStage, playerIndex);
	}

}
