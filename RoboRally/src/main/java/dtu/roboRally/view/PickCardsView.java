package dtu.roboRally.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import dtu.roboRally.Board;
import dtu.roboRally.Card;
import dtu.roboRally.Game;
import dtu.roboRally.controller.PickCardsController;
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

public class PickCardsView {
	
	private PickCardsController controller;
	private GridPane layout = new GridPane();
	private ArrayList<Card> hand; 
	HBox cardsPlayed = new HBox();
	
	public PickCardsView(PickCardsController controller, ArrayList<Card> hand) {
		this.controller = controller;
		this.hand = hand;
	}
	
	public Scene initGUI() {
		
		layout.setVgap(20);
		layout.setHgap(20);
		layout.setPadding(new Insets(40, 0, 0, 40));	
		
		addPlayerStatusColumn();
		
		addBoardGUI();
		
		addProgressBar();
		
		addCardsGUI();
		
		addConfirmCardsButton();
		
		return new Scene(layout);
	}
	
	public void addPlayerStatusColumn() {
		
		VBox playersStatus = new VBox();
		playersStatus.setSpacing(60);
		for(String playerName : controller.getPlayerNames()) {// TODO: add player lives to the status
			Label name = new Label(playerName);
			name.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
			playersStatus.getChildren().add(name);
		}
		layout.add(playersStatus, 0, 0, 1, 3);
	}
	
	public void addBoardGUI() {
		
		Board board = Game.getInstance().getBoard();
		
		int rows = board.getRows();
		int cols = board.getCols();
		
		TilePane boardGUI = new TilePane();
		boardGUI.setPrefColumns(cols);
		boardGUI.setPrefRows(rows);
		
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < cols; i++) {
				FileInputStream tiles = null;
				try {
					tiles = new FileInputStream("src/main/resources/tileImages/"+board.getTile(i, j).getLabel()+".png");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				Image tileImage = new Image(tiles);
				ImageView tileImageView = new ImageView(tileImage);
				tileImageView.setFitHeight(50);
				tileImageView.setFitWidth(50);
				boardGUI.getChildren().add(tileImageView);
			}
		}
		
		layout.add(boardGUI, 1, 0, 1, 1);
	}
	
	public void addProgressBar() {
		
		ProgressBar progressBar = new ProgressBar(); //TODO: implement progress in the game. maybe create custom progressbar Class with calculateProgress(pos rob, pos end)
		progressBar.setPrefWidth(600);
		
		layout.add(progressBar, 1, 1, 1, 1);
	}
	
	public void addCardsGUI() {
		
		TilePane handGUI = new TilePane();
		
		handGUI.setPrefColumns(3);
		handGUI.setPrefRows(3);
//		hand.setHgap(0);
		handGUI.setVgap(45);
		
		cardsPlayed.setSpacing(24);
		System.out.println(hand.size());
		for(int i = 0; i<hand.size(); i++) {
			
			String cardName = hand.get(i).getClass().getSimpleName();
			Card card = hand.get(i);
			
			FileInputStream input = null;
			
			try {
				input = new FileInputStream("src/main/resources/cardImages/"+cardName+".png");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Image image = new Image(input);
			
			ImageView imageViewHand = new ImageView(image);
			imageViewHand.setId(cardName);
			imageViewHand.setFitHeight(140);
			imageViewHand.setFitWidth(100);
//			imageView.setPreserveRatio(true);
			
			ImageView imageViewCardsPlayed = new ImageView(image);
			imageViewHand.setId(cardName);
			imageViewCardsPlayed.setFitHeight(140);
			imageViewCardsPlayed.setFitWidth(100);
//			imageViewCopy.setPreserveRatio(true);
			
			imageViewHand.setOnMouseClicked(value -> {
				
				if(cardsPlayed.getChildren().size()<5) {
					handGUI.getChildren().remove(imageViewHand);
					cardsPlayed.getChildren().add(imageViewCardsPlayed);
					
					controller.addCardInPlay(card);
				}
			});
			
			imageViewCardsPlayed.setOnMouseClicked(value -> {
				
				cardsPlayed.getChildren().remove(imageViewCardsPlayed);
				handGUI.getChildren().add(imageViewHand);
				
				controller.removeCardInPlay(card);
//				player.getCardsInPlay().remove(card);
			});
			
			handGUI.getChildren().add(imageViewHand);
		}
		
		layout.add(handGUI, 2, 0, 1, 3);
		layout.add(cardsPlayed, 1, 2, 1, 3);
	}
	
	public void addConfirmCardsButton() {
		
		Button confirmCardsButton = new Button("CONFIRM CARDS");
		confirmCardsButton.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
		layout.add(confirmCardsButton, 1, 3, 1, 1); // does not look nice
//		playersStatus.getChildren().add(confirmCardsButton);
		
		confirmCardsButton.setOnAction(value -> {
			if(!(cardsPlayed.getChildren().size()<5)) {
				
				controller.nextPlayer();
			}
		});
	}
}
