package dtu.roboRally.view;

import dtu.roboRally.controller.MovingRobotsController;
import dtu.roboRally.utils.BoardTilePane;
import dtu.roboRally.utils.PlayerStatusPanel;
import dtu.roboRally.utils.RoboRallyGridPane;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * view for the moving robot scene
 */
public class MovingRobotsView {
	
	BoardTilePane boardGUI = new BoardTilePane();
	
    private MovingRobotsController controller;

    /**
     * Constructor
     * @param controller (RoboRallyController)
     */
    public MovingRobotsView(MovingRobotsController controller){
        this.controller = controller;
    }

    /**
     * called whenever the controller gets notified that change occurred on the board
     * updates the board and the playerStatusPanel
     * @return (Scene)
     */
    public RoboRallyGridPane initGUI() {
//    	boardGUI.deleteRobots();
//    	boardGUI.setRobots();
//    	BoardTilePane b = (BoardTilePane) boardGUI.clone();
    	BoardTilePane board = new BoardTilePane();
    	PlayerStatusPanel panel = new PlayerStatusPanel(controller.getPlayerNames());

    	RoboRallyGridPane layout = new RoboRallyGridPane(panel, board);

    	layout.add(createContinueButton(), 1, 2, 1, 1);
    	
    	return layout;
    }

	/**
	 * Button to press when all scene have been displayed
	 * @return (Button)
	 */
	public Button createContinueButton() {
    	Button button = new Button("CONTINUE");
    	button.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
	    button.setStyle("-fx-background-color: #32CD32; -fx-border-color: #228B22; -fx-border-width: 7; -fx-text-fill: #FFFFFF");
    	
    	button.setOnAction(value -> {
        	controller.manageEndOfRound();
    		
    	});
    	
    	return button;
    }

}
