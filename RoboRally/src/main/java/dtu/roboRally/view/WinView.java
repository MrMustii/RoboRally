package dtu.roboRally.view;

import dtu.roboRally.controller.WinController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
		layout.setPadding(new Insets(40, 0, 0, 40));
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
		
		playAgain.setOnAction(value -> {
			controller.newGame();
		});
		
		layout.getChildren().addAll(label, exit, playAgain);
		
		return new Scene(layout);
	}
}
