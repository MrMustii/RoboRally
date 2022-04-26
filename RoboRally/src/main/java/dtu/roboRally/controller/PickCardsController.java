package dtu.roboRally.controller;

import java.util.ArrayList;

import dtu.roboRally.*;
import dtu.roboRally.view.PickCardsView;
import javafx.stage.Stage;

public class PickCardsController{
	
	private RoboRallyController application;
	private Stage primaryStage;
	private PickCardsView view;
	private ArrayList<String> playerNames;
	private int playerIndex;
	private Player player;
	
	public PickCardsController(RoboRallyController application, Stage primaryStage, ArrayList<String> playerNames, int playerIndex) {
		this.playerNames = playerNames;
		this.application = application;
		this.primaryStage = primaryStage;
		this.playerIndex = playerIndex;
		player = Game.getInstance().getPlayers().get(playerIndex);
		this.view = new PickCardsView(this, player.getHand());
	}
	
	public void display() {
		primaryStage.setScene(view.initGUI());
	}
	
	public ArrayList<String> getPlayerNames() {
		return playerNames;
	}
	
	public void addCardInPlay(Card card) {
		player.getCardsInPlay().add(card);
	}
	
	public void removeCardInPlay(Card card) {
		player.getCardsInPlay().remove(card);
	}
	
	public void nextPlayer() {
		application.managePlayerTurn(primaryStage, playerIndex+1);
	}
	
	public ArrayList<Integer> getLivesOfRobots() {
		return application.getLivesOfRobots();
	}

	public Position[] extractPosition(){
		Position[] positions = new Position[3];
		positions[0] = player.getRobot().getPosition();
		positions[1] = player.getRobot().getStartPosition();

		Board board = Game.getInstance().getBoard();
		for(int j=0; j<board.getRows(); j++){
			for(int i=board.getCols()-4; i<board.getCols(); i++){ //loop trough the last 3 cols
				if(board.getTile(i,j).getLabel().equals("E ")){
					positions[2] = new Position(i,j,0); //dont care about orientation
					return positions;
				}
			}
		}
		System.out.println("error while searching for endPosition in PickCardsController.extractPosition()");
		return null;
	}
	
}
