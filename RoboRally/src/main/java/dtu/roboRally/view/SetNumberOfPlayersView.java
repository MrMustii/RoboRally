package dtu.roboRally.view;

import dtu.roboRally.controller.SetNumberOfPlayersController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * view for the SetNumberOfPlayer scene
 */
public class SetNumberOfPlayersView {
	
	private SetNumberOfPlayersController controller;
	private int nbOfPlayers;

	/**
	 * Constructor
	 * @param controller (SetNumberOfPlayersController)
	 */
	public SetNumberOfPlayersView(SetNumberOfPlayersController controller) {
		this.controller = controller;
	}

	/**
	 * creates the Scene with a slider and a button to ask the controller to change Scene
	 * @return Scene
	 */
	public Scene initGUI() {

		Label label = new Label("Choose no. of players:");
		label.setFont(Font.font("Courier New", 20));
		
		//set slider
		Slider slider = new Slider(1, 4, 0);
	    slider.setMajorTickUnit(1.0);
	    slider.setMinorTickCount(0);
	    slider.setSnapToTicks(true);
	    slider.setShowTickMarks(true);
	    slider.setShowTickLabels(true);

	    //set Button
	    Button startGameButton = new Button("START GAME");
	    startGameButton.setStyle("-fx-background-color: #32CD32; -fx-border-color: #228B22; -fx-border-width: 7; -fx-text-fill: #FFFFFF");
		startGameButton.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
		startGameButton.setPrefSize(250, 80);

	    startGameButton.setOnAction(value ->  {
	    	
	        nbOfPlayers = (int) slider.getValue();
	        controller.setPlayerNames(nbOfPlayers);
	        
	     });

	    //add nodes to the layout
	    VBox vbox = new VBox(label, slider, startGameButton);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setSpacing(50);
	    VBox.setMargin(slider, new Insets(0, 400, 0, 400));
	    
	    return new Scene(vbox);
	}
	
}
