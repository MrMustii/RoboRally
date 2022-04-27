package dtu.roboRally.view;

import dtu.roboRally.controller.MovingRobotsController;
import dtu.roboRally.utils.BoardTilePane;
import dtu.roboRally.utils.PlayerStatusPanel;
import dtu.roboRally.utils.RoboRallyGridPane;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * view for the moving robot scene
 */
public class MovingRobotsView {

    private MovingRobotsController controller;

    /**
     * Constructor that initialises the board
     * @param controller (RoboRallyController)
     */
    public MovingRobotsView(MovingRobotsController controller){
        this.controller = controller;
    }

    /**
     * called whenever the controller gets notified that a card has been played
     * updates the board and the playerStatusPanel
     * @return (Scene)
     */
    public RoboRallyGridPane initGUI() {
    	BoardTilePane board = new BoardTilePane();
    	PlayerStatusPanel panel = new PlayerStatusPanel(controller.getPlayerNames());
    	RoboRallyGridPane layout = new RoboRallyGridPane(panel, board);
    	layout.add(createContinueButton(), 1, 1, 1, 1);
    	
    	return layout;
    	
    }
    
    public Button createContinueButton() {
    	Button button = new Button("CONTINUE");
    	button.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
    	
    	button.setOnAction(value -> {
        	controller.manageEndOfRound();
    		
    	});
    	
    	return button;
    }

}
