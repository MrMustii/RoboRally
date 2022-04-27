package dtu.roboRally.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import dtu.roboRally.Board;
import dtu.roboRally.Card;
import dtu.roboRally.Game;
import dtu.roboRally.Position;
import dtu.roboRally.controller.PickCardsController;
import dtu.roboRally.utils.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * view for the PickCardScene
 */
public class PickCardsView {
	
	private PickCardsController controller;
	private RoboRallyGridPane layout;
	private ArrayList<Card> hand; 
	HBox cardsPlayed = new HBox();

	/**
	 * Constructor that retrieves relevant data from controller
	 * @param controller (PickCardController)
	 * @param hand (ArrayList<Card>)
	 */
	public PickCardsView(PickCardsController controller, ArrayList<Card> hand) {
		this.controller = controller;
		this.hand = hand;
	}

	/**
	 * creates the Scene from a GridPane with nodes: PlayerStatusPanel, BoardTilePane, ProgressBar, CardGUIs, and a Button
	 * @return (Scene)
	 */
	public Scene initGUI() {

		//sets layout
		PlayerStatusPanel psp = new PlayerStatusPanel(controller.getPlayerNames());
		BoardTilePane btp = new BoardTilePane();
		layout = new RoboRallyGridPane(psp, btp , addProgressBar(), addConfirmCardsButton());

		/*
		layout.setVgap(20);
		layout.setHgap(20);
		layout.setPadding(new Insets(40, 0, 0, 40));	
		
		layout.add(new PlayerStatusPanel(controller.getPlayerNames(), controller.getLivesOfRobots()), 0, 0, 1, 3);
		
		layout.add(new BoardTilePane(), 1, 0, 1, 1);
		
		addProgressBar();

		 */
		
		addCardsGUI();
		
		//addConfirmCardsButton();
		
		return new Scene(layout);
	}
	
	public ProgressBarPlayer addProgressBar() {
		Position[] positions = controller.extractPosition();
		ProgressBarPlayer progressBar = new ProgressBarPlayer(positions[0], positions[1], positions[2]);
		progressBar.setPrefWidth(600);
		return progressBar;

		//layout.add(progressBar, 1, 1, 1, 1);
	}
	
	public void addCardsGUI() {
		
		TilePane handGUI = new TilePane();
		
		handGUI.setPrefColumns(3);
		handGUI.setPrefRows(3);
//		hand.setHgap(0);
		handGUI.setVgap(45);
		
		cardsPlayed.setSpacing(24);
		for (Card item : hand) {

			String cardName = item.getClass().getSimpleName();

			ImageView imageViewHand =  ImageViewLoader.loadFile("src/main/resources/cardImages/" + cardName + ".png");
			imageViewHand.setId(cardName);
			imageViewHand.setFitHeight(140);
			imageViewHand.setFitWidth(100);
//			imageView.setPreserveRatio(true);

			ImageView imageViewCardsPlayed =  ImageViewLoader.loadFile("src/main/resources/cardImages/" + cardName + ".png");
			imageViewHand.setId(cardName);
			imageViewCardsPlayed.setFitHeight(140);
			imageViewCardsPlayed.setFitWidth(100);
//			imageViewCopy.setPreserveRatio(true);

			imageViewHand.setOnMouseClicked(value -> {

				if (cardsPlayed.getChildren().size() < 5) {
					handGUI.getChildren().remove(imageViewHand);
					cardsPlayed.getChildren().add(imageViewCardsPlayed);

					controller.addCardInPlay(item);
				}
			});

			imageViewCardsPlayed.setOnMouseClicked(value -> {

				cardsPlayed.getChildren().remove(imageViewCardsPlayed);
				handGUI.getChildren().add(imageViewHand);

				controller.removeCardInPlay(item);
//				player.getCardsInPlay().remove(card);
			});

			handGUI.getChildren().add(imageViewHand);
		}
		
		layout.add(handGUI, 2, 0, 1, 3);
		layout.add(cardsPlayed, 1, 2, 1, 3);
	}
	
	public RoboRallyButton addConfirmCardsButton() {
		
		RoboRallyButton confirmCardsButton = new RoboRallyButton("CONFIRM CARDS");
		//confirmCardsButton.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
		//layout.add(confirmCardsButton, 1, 3, 1, 1); // does not look nice
//		playersStatus.getChildren().add(confirmCardsButton);
		
		confirmCardsButton.setOnAction(value -> {
			if(!(cardsPlayed.getChildren().size()<5)) {
				
				controller.nextPlayer();
			}
		});

		return confirmCardsButton;
	}
}
