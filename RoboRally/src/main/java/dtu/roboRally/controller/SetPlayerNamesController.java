package dtu.roboRally.controller;

import java.util.ArrayList;

import dtu.roboRally.view.SetPlayerNamesView;
import javafx.stage.Stage;

public class SetPlayerNamesController {
	
	private RoboRallyController application;
	private Stage primaryStage;
	private SetPlayerNamesView view;
	
	public SetPlayerNamesController(RoboRallyController application, Stage primaryStage) {
		this.application = application;
		this.primaryStage = primaryStage;
		this.view = new SetPlayerNamesView(this);
	}
	
	public void setGameLayout(ArrayList<String> playerNames) {
		application.setGameLayout(primaryStage, playerNames);
	}
	
	public void display() {
		primaryStage.setScene(view.initGUI());
	}
}
