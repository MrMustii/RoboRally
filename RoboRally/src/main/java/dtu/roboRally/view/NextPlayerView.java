package dtu.roboRally.view;

import dtu.roboRally.controller.NextPlayerController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * view for the next player scene
 */
public class NextPlayerView {
	
	private String playerName;
	private NextPlayerController controller;

	/**
	 * controller that retrieves relevant data
	 * @param controller (NextPlayercontroller)
	 * @param playerName (String)
	 */
	public NextPlayerView(NextPlayerController controller, String playerName) {
		this.controller = controller;
		this.playerName = playerName;
	}

	/**
	 * creates a Scene with a label and a listener to switch Scenes
	 * @return (Scene)
	 */
	public Scene initGUI() {
		//set label
		Label nextPlayer = new Label("Is is now " + playerName + "'s turn... Click anywhere to continue");
		nextPlayer.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
		HBox hb = new HBox(nextPlayer);
		hb.setPadding(new Insets(40, 0, 0, 40));
		
		Scene nextPlayerScene = new Scene(hb);
		
		nextPlayerScene.setOnMouseClicked(val -> {
			controller.pickCards();
		});
		
		return nextPlayerScene;
	}

}
