package dtu.roboRally.view;

import dtu.roboRally.controller.WinController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class WinView {
	
	private WinController controller;
	private String nameOfWinner;
	
	public WinView(WinController controller, String nameOfWinner) {
		this.controller = controller;
		this.nameOfWinner = nameOfWinner;
	}
	
	public Scene initGUI() {
		
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		layout.setSpacing(40);
		
		Label label = new Label("Congratulations! " + nameOfWinner + " has won the game.");
		label.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
		
		Button exit = new Button("EXIT GAME");
		exit.setFont(Font.font("Courier New", FontWeight.BOLD, 28));

		exit.setOnAction(value -> {
			System.exit(0);
		});
		
		Button playAgain = new Button("PLAY AGAIN");
		playAgain.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
		exit.setOnAction(value -> {
			controller.newGame();
		});
		
		layout.getChildren().addAll(label, exit, playAgain);
		
		return new Scene(layout);
		
	}
	
}
