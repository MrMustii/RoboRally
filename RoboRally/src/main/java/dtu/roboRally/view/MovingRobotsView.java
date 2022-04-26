package dtu.roboRally.view;

import dtu.roboRally.controller.MovingRobotsController;
import dtu.roboRally.utils.BoardTilePane;
import dtu.roboRally.utils.PlayerStatusPanel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MovingRobotsView {

    private MovingRobotsController controller;
    //private GridPane layout = new GridPane();
    private BoardTilePane board;


    public MovingRobotsView(MovingRobotsController controller){
        this.controller = controller;
        board = new BoardTilePane();
    }

    public Scene initGUI(){
    	
    	GridPane layout = createLayout();
        layout.add(new PlayerStatusPanel(controller.getPlayerNames(), controller.getLivesOfRobots()), 0, 0, 1, 3);
		layout.add(board, 1, 0, 1, 1);
		layout.add(createResumeGameButton(), 0, 1, 1, 1);
        
        return new Scene(layout);
    }
    
    public Scene updateRobotsAndLives() {
    	board.deleteRobots();
    	board.setRobots();
    	
    	GridPane layout = createLayout();
        layout.add(new PlayerStatusPanel(controller.getPlayerNames(), controller.getLivesOfRobots()), 0, 0, 1, 3);
		layout.add(board, 1, 0, 1, 1);
		layout.add(createResumeGameButton(), 0, 1, 1, 1);
    	
    	return new Scene(layout);
    }
    
    public Button createResumeGameButton() {
    	Button button = new Button("RESUME GAME");
    	button.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
    	
    	button.setOnAction(value -> {
    		controller.resumeGame();
    	});
    	
    	return button;
    }
    
    public GridPane createLayout() {
      	
    	GridPane layout = new GridPane();
    	layout.setVgap(20);
		layout.setHgap(20);
		layout.setPadding(new Insets(40, 0, 0, 40));
    	
		return layout;
    }

}
