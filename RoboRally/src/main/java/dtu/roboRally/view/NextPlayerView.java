package dtu.roboRally.view;

import dtu.roboRally.controller.NextPlayerController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NextPlayerView {
	
	private String playerName;
	private NextPlayerController controller;
	
	public NextPlayerView(NextPlayerController controller, String playerName) {
		this.controller = controller;
		this.playerName = playerName;
	}
	
	public Scene initGUI() {
		
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
