package dtu.roboRally.controller;

import java.util.ArrayList;

import dtu.roboRally.Game;
import javafx.application.Application;
import javafx.stage.Stage;

public class RoboRallyController extends Application {
	
	private SetNumberOfPlayersController SNOPC;
	private SetPlayerNamesController SPNC;
	private PickCardsController SGLC;
	private NextPlayerController NPC;
	
	private ArrayList<String> playerNames = new ArrayList<String>();
	
	@Override
    public void start(Stage primaryStage) throws Exception {
		
		setStage(primaryStage);
		
		RoboRallyController app = new RoboRallyController();
		app.startApplication(primaryStage);
		
		primaryStage.show();
	}
	
	public void startApplication(Stage primaryStage) {
		
		SNOPC = new SetNumberOfPlayersController(this, primaryStage);
		SNOPC.display();
	}
	
	public void instantiateGame(int nbOfPlayers) {
		Game.getInstance(nbOfPlayers);
	}
	
	public void setStage(Stage primaryStage) {
		
	    primaryStage.setTitle("RoboRally");
	    primaryStage.setWidth(1250);
	    primaryStage.setHeight(800);
	}
	
	public void setPlayerNames(Stage primaryStage) {
		
		SPNC = new SetPlayerNamesController(this, primaryStage, playerNames);
		SPNC.display();
	}
	
	public void pickCards(Stage primaryStage, int playerIndex) {
		Game.getInstance().getPlayers().get(playerIndex).drawHand();
		SGLC = new PickCardsController(this, primaryStage, playerNames, playerIndex);
		SGLC.display();
		
	}
	
	public void managePlayerTurn(Stage primaryStage, int playerIndex) {
		if(playerIndex < playerNames.size()) {
			nextPlayer(primaryStage, playerNames.get(playerIndex), playerIndex);
		}else {
			//play the game
		}
	}
	
	public void nextPlayer(Stage primaryStage, String playerName, int playerIndex) {
		
		NPC = new NextPlayerController(this, primaryStage, playerName, playerIndex);
		NPC.display();
	}
	
}
