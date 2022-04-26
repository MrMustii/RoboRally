package dtu.roboRally.view;

import dtu.roboRally.controller.SetNumberOfPlayersController;
import dtu.roboRally.utils.RoboRallyButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

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

		//set slider
		Slider slider = new Slider(2, 6, 0);
	    slider.setMajorTickUnit(1.0);
	    slider.setMinorTickCount(0);
	    slider.setSnapToTicks(true);
	    slider.setShowTickMarks(true);
	    slider.setShowTickLabels(true);

	    //set Button
	    RoboRallyButton startGameButton = new RoboRallyButton("START GAME");

	    startGameButton.setOnAction(value ->  {
	    	
	        nbOfPlayers = (int) slider.getValue();
	        controller.setPlayerNames(nbOfPlayers);
	        
	     });

	    //add nodes to the layout
	    VBox vbox = new VBox(slider, startGameButton);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setSpacing(50);
	    VBox.setMargin(slider, new Insets(0, 400, 0, 400));
	    
	    return new Scene(vbox);
	}
	
}
