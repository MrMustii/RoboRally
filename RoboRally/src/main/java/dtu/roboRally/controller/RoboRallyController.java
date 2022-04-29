package dtu.roboRally.controller;

import dtu.roboRally.Game;
import dtu.roboRally.Player;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RoboRallyController extends Application {
	
	private SetNumberOfPlayersController SNOPC;
	private SetPlayerNamesController SPNC;
	private SetStartingPositionsController SSPC;
	private PickCardsController PCC;
	private NextPlayerController NPC;
	private MovingRobotsController MRC;
	private WinController WC;

	private boolean hasWinner;
	private String nameOfWinner;
	
	private ArrayList<String> playerNames = new ArrayList<String>();
	
	@Override
    public void start(Stage primaryStage) throws Exception {

		setStage(primaryStage);
		
		RoboRallyController app = new RoboRallyController();
		app.startApplication(primaryStage);
		
		primaryStage.show();
	}

	/**
	 * gives initial value and initialises the primary Stage used all long
	 * @param primaryStage (Stage)
	 */
	public void setStage(Stage primaryStage) {

		primaryStage.setTitle("RoboRally");
		primaryStage.setWidth(1300);
		primaryStage.setHeight(830);
	}

	/**
	 * Creates a new SetNumberOfPlayerController and displays the 
	 * corresponding view
	 * this controller will call instantiateGame and setPlayerNames
	 * @param primaryStage (Stage)
	 */
	public void startApplication(Stage primaryStage) {
		hasWinner = false;
		SNOPC = new SetNumberOfPlayersController(this, primaryStage);
		SNOPC.display();
	}

	/**
	 * called by SetNumberOfPlayerController.setPlayerNames()
	 * @param nbOfPlayers (int)
	 */
	public void instantiateGame(int nbOfPlayers) {
		Game.getInstance(this, nbOfPlayers);
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
		SSPC.display();
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
	 * called by setStartingPositionsController.nextPlayer() or PickCardsController.nextPlayer() or resumeGame button in MRC
	 * manages if the next scene is a player picking card or if the robot should be moved
	 * calls nextPlayer if there is still a player to play
	 * instantiates a new MovingRobotsController if it was the last player
	 * starts the phase 2 of the game
	 * @param primaryStage (Stage)
	 * @param playerIndex (int) the index of the next player to pick its card(if smaller than the number of players)
	 */
	public void managePlayerTurn(Stage primaryStage, int playerIndex) {
		if(playerIndex < playerNames.size() && !hasWinner) {
			nextPlayer(primaryStage, playerNames.get(playerIndex), playerIndex);
		} else {
			PCC.displayWaiting();
			MRC = new MovingRobotsController(this, primaryStage, playerNames);
			Game.getInstance().phase2();
			MRC.display();
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
	 * @param primaryStage (Stage)
	 * @param playerIndex (int)
	 */
	public void pickCards(Stage primaryStage, int playerIndex) {
		Game.getInstance().getPlayers().get(playerIndex).drawHand();
		PCC = new PickCardsController(this, primaryStage, playerNames, playerIndex);
		PCC.display();
	}

	/**
	 * called by the observer in Game and Tiles
	 * tells the MRC to stack a Pane for this set of the board
	 */
	public void notifyRobotMove() {
		MRC.addBoardToList();
	}

	/**
	 * called by the observer in Game
	 * saves the name of the winner
	 * @param playerIndex (int) id of the winner
	 */
	public void notifyWin(int playerIndex) {
		nameOfWinner = playerNames.get(playerIndex);
		hasWinner = true;
	}

	/**
	 * instantiates a new WinnerController and displays it
	 * @param primaryStage
	 */
	public void crownWinner(Stage primaryStage) {
		WC = new WinController(this, primaryStage, nameOfWinner);
		WC.display();
	}

	/**
	 * getter for the MRC to know if the game is finished
	 * @return (boolean)
	 */
	public boolean hasWinner() {
		return hasWinner;
	}
	
}
