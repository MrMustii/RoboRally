package GUIJavaFX;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import dtu.roboRally.Board;
import dtu.roboRally.Card;
import dtu.roboRally.Game;
import dtu.roboRally.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class App extends Application {
	
	private int nbOfPlayers;
	private Game game;
	private ArrayList<String> playerNames;
	private Board board;
	
	@Override
    public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("My First JavaFX App");
    primaryStage.setTitle("RoboRally");
    primaryStage.setWidth(1250);
    primaryStage.setHeight(800);
    
    primaryStage.setScene(createStartScene(primaryStage));
    
    primaryStage.show();
	}
	
	
	public Scene createStartScene(Stage primaryStage) {
		
		Slider slider = new Slider(2, 6, 0);
	    slider.setMajorTickUnit(1.0);
	    slider.setMinorTickCount(0);
	    slider.setSnapToTicks(true);
	    slider.setShowTickMarks(true);
	    slider.setShowTickLabels(true);
	    
	    Button startGameButton = new Button("START GAME");
	    Font font = Font.font("Courier New", FontWeight.BOLD, 28);
	    startGameButton.setFont(font);
	    startGameButton.setPrefSize(250, 80);
	    startGameButton.setOnAction(value ->  {
	    	
	        nbOfPlayers = (int) slider.getValue();
	        primaryStage.setScene(createPlayerNamesScene(primaryStage));
	        
	     });
	    
	    VBox vbox = new VBox(slider, startGameButton);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setSpacing(50);
	    VBox.setMargin(slider, new Insets(0, 400, 0, 400));
	    
	    return new Scene(vbox);
	}
	
	public Scene createPlayerNamesScene(Stage primaryStage) {
		
		VBox playerNamesBox = new VBox();
        playerNamesBox.setSpacing(50);
        
        ArrayList<TextField> fields = new ArrayList<>();
        
        for(int i = 0; i<nbOfPlayers; i++) {
        	TextField t = new TextField();
        	t.setText("Player" + String.valueOf(i+1));
        	fields.add(t);
        	playerNamesBox.getChildren().add(new HBox(t));

        }
        playerNames = new ArrayList<>();
        
        for(TextField field : fields) {
        	playerNames.add(field.getText());
        }
        
        Button confirmButton = new Button("CONFIRM");
        confirmButton.setFont(Font.font("Courier New", FontWeight.BOLD, 27));
		confirmButton.setOnAction(value ->  {
		     
			System.out.println(playerNames); //does not work
//			game = new Game(nbOfPlayers);
			primaryStage.setScene(createGameScene(primaryStage));
		        
		    });
		
        playerNamesBox.getChildren().add(confirmButton);
        playerNamesBox.setPadding(new Insets(150, 0, 0, 500));
        
        return new Scene(playerNamesBox, 400, 200);
	}
	
	public Scene createGameScene(Stage primaryStage) {
		
		GridPane gameLayout = new GridPane();
		gameLayout.setVgap(20);
		gameLayout.setHgap(20);
		gameLayout.setPadding(new Insets(40, 0, 0, 40));
		
		VBox playersStatus = new VBox();
		playersStatus.setSpacing(60);
		for(String player : playerNames) {
			Label l = new Label(player);
			l.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
			playersStatus.getChildren().add(l);
		}
		gameLayout.add(playersStatus, 0, 0, 1, 3);
		
		int rows = 9;
		int cols = 12;
		
		Board board = new Board(rows, cols, nbOfPlayers);
//		board = game.getBoard();
		
		TilePane boardGUI = new TilePane();
		boardGUI.setPrefColumns(cols);
		boardGUI.setPrefRows(rows);
		
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < cols; i++) {
				FileInputStream tiles = null;
				try {
					tiles = new FileInputStream("src/main/resources/tileImages/"+board.getTile(i, j).getLabel()+".png");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Image tileImage = new Image(tiles);
				ImageView tileImageView = new ImageView(tileImage);
				tileImageView.setFitHeight(50);
				tileImageView.setFitWidth(50);
				boardGUI.getChildren().add(tileImageView);
			}
		}
		
		gameLayout.add(boardGUI, 1, 0, 1, 1);
		
		ProgressBar progressBar = new ProgressBar();
		progressBar.setPrefWidth(600);
		
		gameLayout.add(progressBar, 1, 1, 1, 1);
		
//		for(int playerIndex = 0; playerIndex<game.getPlayers().size();) {
		
			TilePane hand = new TilePane();
			hand.setPrefColumns(3);
			hand.setPrefRows(3);
	//		hand.setHgap(0);
			hand.setVgap(45);
			
			HBox cardsPlayed = new HBox();
			cardsPlayed.setSpacing(24);
			
			Player player = new Player();
			player.drawHand();
					
			for(int i = 0; i<player.getHand().size(); i++) {
				String tileName = player.getHand().get(i).getClass().getSimpleName();
				Card card = player.getHand().get(i);
				FileInputStream input = null;
				try {
					input = new FileInputStream("src/main/resources/cardImages/"+tileName+".png");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Image image = new Image(input);
				
				ImageView imageView = new ImageView(image);
				imageView.setId(tileName);
				imageView.setFitHeight(140);
				imageView.setFitWidth(100);
	//			imageView.setPreserveRatio(true);
				
				ImageView imageViewCopy = new ImageView(image);
				imageView.setId(tileName);
				imageViewCopy.setFitHeight(140);
				imageViewCopy.setFitWidth(100);
	//			imageViewCopy.setPreserveRatio(true);
				
				imageView.setOnMouseClicked(value -> {
					if(cardsPlayed.getChildren().size()<5) {
						hand.getChildren().remove(imageView);
						cardsPlayed.getChildren().add(imageViewCopy);
						player.getCardsInPlay().add(card);
					}
				});
				
				imageViewCopy.setOnMouseClicked(value -> {
					cardsPlayed.getChildren().remove(imageViewCopy);
					hand.getChildren().add(imageView);
					player.getCardsInPlay().remove(card);
				});
				
				hand.getChildren().add(imageView);
			}
			
			gameLayout.add(cardsPlayed, 1, 2, 1, 3);
			gameLayout.add(hand, 2, 0, 1, 3);
			
			Button confirmCardsButton = new Button("CONFIRM CARDS");
			confirmCardsButton.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
	//		gameLayout.add(confirmCardsButton, 1, 3, 1, 1); // does not look nice
			playersStatus.getChildren().add(confirmCardsButton);
			confirmCardsButton.setOnAction(value -> {
				if(!(cardsPlayed.getChildren().size()<5)) {
					System.out.println(player.getCardsInPlay()); //TODO: delete this
					
					Label nextPlayer = new Label("Turn of next player... Click anywhere to continue");
					nextPlayer.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
					HBox hb = new HBox(nextPlayer);
					hb.setPadding(new Insets(40, 0, 0, 40));
					Scene nextPlayerScene = new Scene(hb);
					nextPlayerScene.setOnMouseClicked(val -> {
//						playerIndex++; // does not work
					});
					primaryStage.setScene(nextPlayerScene);
				}
			});
//		}
		
		return new Scene(gameLayout);
		
	}
	
//	public static 
	
}
