package dtu.roboRally.view;

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
	 * @return (Scene)
	 */
	public Scene initGUI() {

		//layout
		VBox playerNamesBox = new VBox();
        playerNamesBox.setSpacing(40);
        
        //add text fields and their listeners
        for(int i = 0; i<Game.getInstance().getPlayers().size(); i++) {
        	TextField t = new TextField();
        	t.setText("Player" + (i + 1));
        	setTextLimit(t, 8);
        	controller.addPlayerName(i, t.getText());
        	
        	int playerIndex = i;
        	t.textProperty().addListener((observable, oldValue, newValue) -> {
        		controller.removePlayerName(playerIndex); 
        		controller.addPlayerName(playerIndex, newValue);
        	});
        	
        	playerNamesBox.getChildren().add(new HBox(t));

        }

        //add button and listener
        Button confirmButton = new Button("CONFIRM");
		confirmButton.setStyle("-fx-background-color: #32CD32; -fx-border-color: #228B22; -fx-border-width: 7; -fx-text-fill: #FFFFFF");
		confirmButton.setFont(Font.font("Courier New", FontWeight.BOLD, 23));
		confirmButton.setPrefSize(150, 40);
		confirmButton.setOnAction(value -> controller.pickStartingPositions());
		confirmButton.setDefaultButton(true);

		Button backButton = new Button("BACK");
		backButton.setStyle("-fx-background-color: #32CD32; -fx-border-color: #228B22; -fx-border-width: 7; -fx-text-fill: #FFFFFF");
		backButton.setFont(Font.font("Courier New", FontWeight.BOLD, 23));
		backButton.setPrefSize(150, 40);
		backButton.setOnAction(value -> {
			controller.goBack();
		});
		
        playerNamesBox.getChildren().addAll(confirmButton, backButton);
        playerNamesBox.setPadding(new Insets(150, 0, 0, 550));
        
        return new Scene(playerNamesBox, 400, 200);
	}

	/**
	 * helper function to limit the length of the player name input, for layout
	 * @param textField
	 * @param length
	 */
	public static void setTextLimit(TextField textField, int length) {
		textField.setOnKeyTyped(event -> {
			String string = textField.getText();

			if (string.length() > length) {
				textField.setText(string.substring(0, length));
				textField.positionCaret(string.length());
			}
		});
	}
}
