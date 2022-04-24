package dtu.roboRally.view;

import dtu.roboRally.controller.SetNumberOfPlayersController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SetNumberOfPlayersView {
	
	private SetNumberOfPlayersController controller;
	private int nbOfPlayers;
	
	public SetNumberOfPlayersView(SetNumberOfPlayersController controller) {
		this.controller = controller;
	}
	
	public Scene initGUI() {
		
		Slider slider = new Slider(2, 6, 0);
	    slider.setMajorTickUnit(1.0);
	    slider.setMinorTickCount(0);
	    slider.setSnapToTicks(true);
	    slider.setShowTickMarks(true);
	    slider.setShowTickLabels(true);
	    
	    Button startGameButton = new Button("START GAME");
	    Font font = Font.font("Courier New", FontWeight.BOLD, 28);
	    startGameButton.setFont(font);
	    startGameButton.setPrefSize(250, 80);
	    startGameButton.setOnAction(value ->  {
	    	
	        nbOfPlayers = (int) slider.getValue();
	        controller.setPlayerNames(nbOfPlayers);
	        
	     });
	    
	    VBox vbox = new VBox(slider, startGameButton);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setSpacing(50);
	    VBox.setMargin(slider, new Insets(0, 400, 0, 400));
	    
	    return new Scene(vbox);
	}
	
}
