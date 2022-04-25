package dtu.roboRally.view;

import java.util.ArrayList;

import dtu.roboRally.Game;
import dtu.roboRally.controller.SetPlayerNamesController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SetPlayerNamesView {
	
	private SetPlayerNamesController controller;
	
	public SetPlayerNamesView(SetPlayerNamesController controller) {
		this.controller = controller;
	}
	
	public Scene initGUI() {
		
		VBox playerNamesBox = new VBox();
        playerNamesBox.setSpacing(50);
                
        for(int i = 0; i<Game.getInstance().getPlayers().size(); i++) {
        	TextField t = new TextField();
        	t.setText("Player" + String.valueOf(i+1));
        	controller.addPlayerName(i, t.getText());
        	
        	int playerIndex = i;
        	t.textProperty().addListener((observable, oldValue, newValue) -> {
        		controller.removePlayerName(playerIndex);
        		controller.addPlayerName(playerIndex, newValue);
        	});
        	
        	playerNamesBox.getChildren().add(new HBox(t));

        }
                
        Button confirmButton = new Button("CONFIRM");
        confirmButton.setFont(Font.font("Courier New", FontWeight.BOLD, 27));
		confirmButton.setOnAction(value ->  {
			
			controller.pickStartingPositions();
		        
		    });
		
        playerNamesBox.getChildren().add(confirmButton);
        playerNamesBox.setPadding(new Insets(150, 0, 0, 500));
        
        return new Scene(playerNamesBox, 400, 200);
		
	}
	
}
