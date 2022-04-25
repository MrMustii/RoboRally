package dtu.roboRally.controller;

import java.util.ArrayList;

import dtu.roboRally.view.SetPlayerNamesView;
import javafx.stage.Stage;

public class SetPlayerNamesController {
	
	private RoboRallyController application;
	private Stage primaryStage;
	private SetPlayerNamesView view;
	private ArrayList<String> playerNames;
	
	public SetPlayerNamesController(RoboRallyController application, Stage primaryStage, ArrayList<String> playerNames) {
		this.application = application;
		this.primaryStage = primaryStage;
		this.playerNames = playerNames;
		this.view = new SetPlayerNamesView(this);
	}
	
	public void pickCards() {
		application.managePlayerTurn(primaryStage, 0);
	}
	
	public void addPlayerName(int nameIndex, String name) {
		playerNames.add(nameIndex, name);
	}
	
	public void removePlayerName(int nameIndex) {
		playerNames.remove(nameIndex);
	}
	
	public void display() {
		primaryStage.setScene(view.initGUI());
	}
	
	public ArrayList<String> getPlayerNames() {
		return playerNames;
	}
}
