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
	private HBox cardsPlayed = new HBox();
	private BoardTilePane btp;

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
		btp = new BoardTilePane();
		layout = new RoboRallyGridPane(psp, btp , addProgressBar(), addConfirmCardsButton());
		addCardsGUI();

		return new Scene(layout);
	}

	/**
	 * helper to create the progressBar
	 * @return (ProgressBarPlayer)
	 */
	public ProgressBarPlayer addProgressBar() {
		Position[] positions = controller.extractPosition();
		Position endPosition = btp.getEndPosition();

		ProgressBarPlayer progressBar = new ProgressBarPlayer(positions[0], positions[1], endPosition);
		progressBar.setPrefWidth(600);
		return progressBar;
	}

	/**
	 * helper to create the cards hand and cards in play layouts
	 */
	public void addCardsGUI() {

		//hand panel
		TilePane handGUI = new TilePane();
		handGUI.setPrefColumns(3);
		handGUI.setPrefRows(3);
		handGUI.setVgap(45);

		//cards in play
		cardsPlayed.setSpacing(24);

		//looping over the 9 cards in the hand and creating an image for all of them
		for (Card item : hand) {

			String cardName = item.getClass().getSimpleName();

			ImageView imageViewHand =  ImageViewLoader.loadFile("src/main/resources/cardImages/" + cardName + ".png", 140, 100);
			imageViewHand.setId(cardName);

			ImageView imageViewCardsPlayed =  ImageViewLoader.loadFile("src/main/resources/cardImages/" + cardName + ".png", 140, 100);
			imageViewHand.setId(cardName);

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

	/**
	 * helper to add the button to confirm cards
	 * this button tells the controller to manage the next turn and adds the cards to the player
	 * @return (Button)
	 */
	public Button addConfirmCardsButton() {
		
		Button confirmCardsButton = new Button("CONFIRM CARDS");
		confirmCardsButton.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
		
		confirmCardsButton.setOnAction(value -> {
			if(!(cardsPlayed.getChildren().size()<5)) {
				controller.nextPlayer();
			}
		});

		return confirmCardsButton;
	}
}
