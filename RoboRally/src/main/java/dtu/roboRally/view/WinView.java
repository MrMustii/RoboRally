package dtu.roboRally.view;

import dtu.roboRally.controller.WinController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * View for the win scene
 */
public class WinView {
	
	private WinController controller;
	private String nameOfWinner;

	/**
	 * constructor that retrieves relevant data
	 * @param controller (RoboRallyController)
	 * @param nameOfWinner (String)
	 */
	public WinView(WinController controller, String nameOfWinner) {
		this.controller = controller;
		this.nameOfWinner = nameOfWinner;
	}

	/**
	 * creates the scene with a 'new game' button and an 'exit' button
	 * @return (Scene)
	 */
	public Scene initGUI() {
		
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);


		VBox buttons = new VBox();
		//VBox.setMargin(buttons, new Insets(80, 200, 0, 200));
		buttons.setSpacing(40);
		
		Label label = new Label("Congratulations! " + nameOfWinner + " has won the game.");
		label.setFont(Font.font("Courier New", FontWeight.BOLD, 28));

		Button exit = new Button("EXIT GAME");
		exit.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
		exit.setStyle("-fx-background-color: #32CD32; -fx-border-color: #228B22; -fx-border-width: 7; -fx-text-fill: #FFFFFF");
		exit.setAlignment(Pos.CENTER);
		exit.setPrefSize(250, 20);
		
		exit.setOnAction(value -> {
			System.exit(0);
		});
		
		Button playAgain = new Button("PLAY AGAIN");
		playAgain.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
	    playAgain.setStyle("-fx-background-color: #32CD32; -fx-border-color: #228B22; -fx-border-width: 7; -fx-text-fill: #FFFFFF");
	    playAgain.setAlignment(Pos.CENTER);
		playAgain.setPrefSize(250, 20);


		playAgain.setOnAction(value -> {
			controller.newGame();
		});

		buttons.getChildren().addAll(playAgain, exit);
		buttons.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(label, buttons);
		layout.setSpacing(80);
		
		//layout.getChildren().addAll(label,);
		
		return new Scene(layout);
	}
}
