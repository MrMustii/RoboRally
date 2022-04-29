package dtu.roboRally.controller;

import java.util.ArrayList;

import dtu.roboRally.*;
import dtu.roboRally.view.PickCardsView;
import dtu.roboRally.view.WaitingView;
import javafx.stage.Stage;

/**
 * Scene to pick cards for corresponding player
 * Is instantiated one time for every player
 * either calls a next player to start their turn or makes the robots move
 */
public class PickCardsController{
	
	private RoboRallyController application;
	private Stage primaryStage;
	private PickCardsView view;
	private WaitingView waitingView;

	private ArrayList<String> playerNames;
	private int playerIndex;
	private Player player;

	/**
	 * Constructor to retrieve relevant data from application and instantiate view
	 * @param application (RoboRallyController)
	 * @param primaryStage (Stage)
	 * @param playerNames (ArrayList<String>)
	 * @param playerIndex (int) player whose turn it is
	 */
	public PickCardsController(RoboRallyController application, Stage primaryStage, ArrayList<String> playerNames, int playerIndex) {
		this.playerNames = playerNames;
		this.application = application;
		this.primaryStage = primaryStage;
		this.playerIndex = playerIndex;
		player = Game.getInstance().getPlayers().get(playerIndex);
		this.view = new PickCardsView(this, player.getHand());

		waitingView = new WaitingView();
	}

	/**
	 * set the corresponding Scene to the primary stage
	 */
	public void display() {
		primaryStage.setScene(view.initGUI());
	}

	/**
	 * sets the waiting view while phase2 is computing stuff
	 */
	public void displayWaiting(){
		primaryStage.setScene(waitingView.initGUI());
	}

	/**
	 * called when the user clicks on a card in the hand
	 * adds the card to the card in play of the player
	 * @param card (Card)
	 */
	public void addCardInPlay(Card card) {
		player.getCardsInPlay().add(card);
	}

	/**
	 * called when the user clicks on a card in the cardInPlay
	 * removes this card from the player's cardInPlay
	 * @param card (Card)
	 */
	public void removeCardInPlay(Card card) {
		player.getCardsInPlay().remove(card);
	}

	/**
	 * asks the application to switch Scene, either next player to pick cards either moving robots
	 */
	public void nextPlayer() {
		application.managePlayerTurn(primaryStage, playerIndex+1);
	}

	/**
	 * getter for the view to get data about the robot and start positions
	 * used to compute the progress bar
	 * @return (Position[])
	 */
	public Position[] extractPosition(){
		Position[] positions = new Position[2];
		positions[0] = player.getRobot().getPosition();
		positions[1] = player.getRobot().getStartPosition();

		/*
		//@TODO: change that with looking for the new label thingy, iterate on boardGUI instead of actual board (to that in the view?)
		Board board = Game.getInstance().getBoard();
		for(int j=0; j<board.getRows(); j++){
			for(int i=board.getCols()-4; i<board.getCols(); i++){ //loop trough the last 3 cols
				if(board.getTile(i,j).getLabel().equals("E ")){
					positions[2] = new Position(i,j,0); //dont care about orientation
					return positions;
				}
			}
		}

		 */
		return positions;
		/*
		System.out.println("error while searching for endPosition in PickCardsController.extractPosition()");
		return null;
		 */
	}

	/**
	 * getter for the view to retrieve the player names
	 * @return ArrayList<String>)
	 */
	public ArrayList<String> getPlayerNames() {
		return playerNames;
	}
}
