package dtu.roboRally.view;

import dtu.roboRally.Game;
import dtu.roboRally.controller.SetPlayerNamesController;
import dtu.roboRally.utils.RoboRallyButton;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * View to choose the player names
 */
public class SetPlayerNamesView {
	
	private SetPlayerNamesController controller;

	/**
	 * Constructor
	 * @param controller (SetPlayerNamesController)
	 */
	public SetPlayerNamesView(SetPlayerNamesController controller) {
		this.controller = controller;
	}

	/**
	 * creates a new Scene with corresponding elements : correct number of text fields, Button to continue
	 *
	 * @return
	 */
	public Scene initGUI() {

		//layout
		VBox playerNamesBox = new VBox();
        playerNamesBox.setSpacing(50);

        //add text fields and their listeners
        for(int i = 0; i<Game.getInstance().getPlayers().size(); i++) {
        	TextField t = new TextField();
        	t.setText("Player" + (i + 1));
        	controller.addPlayerName(i, t.getText());
        	
        	int playerIndex = i;
        	t.textProperty().addListener((observable, oldValue, newValue) -> {
        		controller.removePlayerName(playerIndex); 
        		controller.addPlayerName(playerIndex, newValue);
        	});
        	
        	playerNamesBox.getChildren().add(new HBox(t));

        }

        //add button and listener
        RoboRallyButton confirmButton = new RoboRallyButton("CONFIRM");
		confirmButton.setOnAction(value -> controller.pickStartingPositions());
		
        playerNamesBox.getChildren().add(confirmButton);
        playerNamesBox.setPadding(new Insets(150, 0, 0, 500));
        
        return new Scene(playerNamesBox, 400, 200);
	}
}
