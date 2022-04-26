package dtu.roboRally.view;

import dtu.roboRally.Game;
import dtu.roboRally.controller.MovingRobotsController;
import dtu.roboRally.utils.BoardTilePane;
import dtu.roboRally.utils.PlayerStatusPanel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class MovingRobotsView {

    private MovingRobotsController controller;
    //private GridPane layout = new GridPane();
    private BoardTilePane board;


    public MovingRobotsView(MovingRobotsController controller){
        this.controller = controller;
        board = new BoardTilePane();
    }

    public Scene initGUI(){
    	
    	GridPane layout = new GridPane();
    	layout.setVgap(20);
		layout.setHgap(20);
		layout.setPadding(new Insets(40, 0, 0, 40));

        layout.add(new PlayerStatusPanel(controller.getPlayerNames(), controller.getLivesOfRobots()), 0, 0, 1, 3);
		layout.add(board, 1, 0, 1, 1);

        // i need some kind of listeners here, either on robot positions, either on cards?
        //not robot caus it still should update if shield or oil
        //maybe when player.use(card) repaint board? --> which makes that we just have to call phase2() in application

        // need to update the board everytime a card is played
        //okay maybe have a board object and reupload it everytime ? HOW
        
        
        return new Scene(layout);
    }
    
    public Scene updateRobotsAndLives() {
    	board.deleteRobots();
    	board.setRobots();
    	
    	GridPane layout = new GridPane();
        layout.add(new PlayerStatusPanel(controller.getPlayerNames(), controller.getLivesOfRobots()), 0, 0, 1, 3);
		layout.add(board, 1, 0, 1, 1);
    	
    	return new Scene(layout);
    }

}
