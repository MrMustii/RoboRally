package dtu.roboRally.controller;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;

public class RoboRallyController extends Application {
	
	private SetNumberOfPlayersController SNOPC;
	private SetPlayerNamesController SPNC;
	private SetGameLayoutController SGLC;
	
	@Override
    public void start(Stage primaryStage) throws Exception {
		
		setStage(primaryStage);
		
		RoboRallyController app = new RoboRallyController();
		app.startGame(primaryStage);
		
		primaryStage.show();
	}
	
	public void startGame(Stage primaryStage) {
		
		SNOPC = new SetNumberOfPlayersController(this, primaryStage);
		SNOPC.display();
	}
	
	public void setStage(Stage primaryStage) {
		
	    primaryStage.setTitle("RoboRally");
	    primaryStage.setWidth(1250);
	    primaryStage.setHeight(800);
	}
	
	public void setPlayerNames(Stage primaryStage) {
		
		SPNC = new SetPlayerNamesController(this, primaryStage);
		SPNC.display();
	}
	
	public void setGameLayout(Stage primaryStage, ArrayList<String> playerNames) {
		
		SGLC = new SetGameLayoutController(this, primaryStage);
		SGLC.display();
	}
	
}
