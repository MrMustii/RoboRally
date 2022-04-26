package dtu.roboRally.controller;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import dtu.roboRally.Game;
import dtu.roboRally.Player;
import javafx.application.Application;
import javafx.stage.Stage;


public class RoboRallyController extends Application {
	
	private SetNumberOfPlayersController SNOPC;
	private SetPlayerNamesController SPNC;
	private SetStartingPositionsController SSPC;
	private PickCardsController PCC;
	private NextPlayerController NPC;
	private MovingRobotsController MRC;
	
	private ArrayList<String> playerNames = new ArrayList<String>();
	
	@Override
    public void start(Stage primaryStage) throws Exception {
		
		setStage(primaryStage);
		
		RoboRallyController app = new RoboRallyController();
		app.startApplication(primaryStage);
		
		primaryStage.show();
	}

	/**
	 * gives initial value and initializes the primary Stage used all long
	 * @param primaryStage (Stage)
	 */
	public void setStage(Stage primaryStage) {

		primaryStage.setTitle("RoboRally");
		primaryStage.setWidth(1250);
		primaryStage.setHeight(800);
	}

	/**
	 * Creates a new SetNumberOfPlayerController and displays the 
	 * corresponding view
	 * this controller will call instantiateGame and setPlayerNames
	 * @param primaryStage (Stage)
	 */
	public void startApplication(Stage primaryStage) {
		
		SNOPC = new SetNumberOfPlayersController(this, primaryStage);
		SNOPC.display();
	}

	/**
	 * called by SetNumberOfPlayerController.setPlayerNames()
	 * @param nbOfPlayers (int)
	 */
	public void instantiateGame(int nbOfPlayers) {
		Game.getInstance(this, nbOfPlayers);
		//TODO: should it be in the same method?
	}

	/**
	 * called by SetNumberOfPlayerController.setPlayerNames()
	 * instantiates a new SetPlayerNamesController that will modify playerNames, and call pickStartingPositions
	 * @param primaryStage (Stage)
	 */
	public void setPlayerNames(Stage primaryStage) {
		SPNC = new SetPlayerNamesController(this, primaryStage, playerNames);
		SPNC.display();
	}

	/**
	 * called by SetPlayerNamesController.pickStartingPositions()
	 * instantiates a new SetStartingPositionsController that will call initialiseRobot and managePlayerTurn.
	 * @param primaryStage (Stage)
	 */
	public void pickStartingPositions(Stage primaryStage){
		SSPC = new SetStartingPositionsController(this, primaryStage, playerNames);
		//display is made in the controller
	}

	/**
	 * called by SetStartingPositionsController.addRobot()
	 * initializes the robot of the given player to the given position, with orientation RIGHT
	 * @param player (Player)
	 * @param x (int)
	 * @param y (int)
	 */
	public void initializeRobot(Player player, int x, int y){
		player.initializeRobot(1,x,y);
	}

	/**
	 * called by setStartingPositionsController.nextPlayer() or PickCardsController.nextPlayer()
	 * manages if the next scene is a player picking card or if the robot should be moved
	 * calls nextPlayer if there is still a player to play
	 * instantiates a new MovingRobotsController if it was the last player TODO: what it calls
	 * @param primaryStage (Stage)
	 * @param playerIndex (int) the index of the next player to pick its card(if smaller than the number of players)
	 */
	public void managePickCards(Stage primaryStage, int playerIndex) {
		if(playerIndex < playerNames.size()) {
			nextPlayer(primaryStage, playerNames.get(playerIndex), playerIndex);
		} else {
			MRC = new MovingRobotsController(this, primaryStage, playerNames);
			Game.getInstance().phase2();
			//when phase2 is done, call playerTurnManager again
		}
	}

	/**
	 * called by managePlayerTurn
	 * calls a NextPlayerController to show a screen for the next player to come
	 * this NPC will call pickCards
	 * @param primaryStage (Stage)
	 * @param playerName (String)
	 * @param playerIndex (int) id of the player to pass down to pickCards
	 */
	public void nextPlayer(Stage primaryStage, String playerName, int playerIndex) {

		NPC = new NextPlayerController(this, primaryStage, playerName, playerIndex);
		NPC.display();
	}

	/**
	 * called by NextPlayerController.pickCards()
	 * completes phase1 of the game: the given player will draw hand, and pick cardsInPlay through the PickCardsController
	 * instantiates a new PickCardsController that will modify the players cardsInPlay and calls managePlayerTurn
	 * @param primaryStage
	 * @param playerIndex
	 */
	public void pickCards(Stage primaryStage, int playerIndex) {
		Game.getInstance().getPlayers().get(playerIndex).drawHand();
		PCC = new PickCardsController(this, primaryStage, playerNames, playerIndex);
		PCC.display();
		
	}
	
	public void updateMovingRobotsView() {
		
//		Timer t = new Timer();
//    	TimerTask tt = new TimerTask() {
//    		@Override
//    		public void run() {
//    			MRC.updateView();
//    		}
//    	};
//    	
//    	t.schedule(tt, 100);
		MRC.updateView();
	}
	
	public ArrayList<Integer> getLivesOfRobots() {
		
		ArrayList<Integer> robotLives = new ArrayList<>();
    	
    	for(Player player : Game.getInstance().getPlayers()) {
    		robotLives.add(player.getRobot().getLives());
    	}
    	return robotLives;
	}
	
}
