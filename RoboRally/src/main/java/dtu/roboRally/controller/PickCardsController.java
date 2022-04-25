package dtu.roboRally.controller;

import java.util.ArrayList;

import dtu.roboRally.Card;
import dtu.roboRally.Game;
import dtu.roboRally.Player;
import dtu.roboRally.view.PickCardsView;
import javafx.stage.Stage;

public class PickCardsController {
	
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
	
}
