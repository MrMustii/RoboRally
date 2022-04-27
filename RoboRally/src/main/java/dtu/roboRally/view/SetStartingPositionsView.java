package dtu.roboRally.view;

import java.util.ArrayList;
import java.util.Arrays;

import dtu.roboRally.Board;
import dtu.roboRally.Game;
import dtu.roboRally.Position;
import dtu.roboRally.controller.SetStartingPositionsController;
import dtu.roboRally.utils.BoardTilePane;
import dtu.roboRally.utils.ImageViewLoader;
import dtu.roboRally.utils.PlayerStatusPanel;
import dtu.roboRally.utils.RoboRallyGridPane;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

/**
 * View for the starting positions
 */
public class SetStartingPositionsView {

    private SetStartingPositionsController controller;
       
    private GridPane layout;
    private BoardTilePane boardGUI;
    private Label instructions;
    private PlayerStatusPanel playerStatusPanel;

    private int playerID;
    private ArrayList<String> playerNames;
    private String playerName;

    /**
     * Constructor that retrieves relevant data, creates the nodes and add listeners to the starting positions
     * @param controller (RoboRallyController)
     * @param playerID (int)
     * @param playerNames (ArrayList<String>)
     */
    public SetStartingPositionsView(SetStartingPositionsController controller, int playerID, ArrayList<String> playerNames){
    	//layout = new GridPane();
        this.controller = controller;
        this.playerID = playerID;
        this.playerNames = playerNames;
        playerName = playerNames.get(playerID);
        
        boardGUI = new BoardTilePane();
        playerStatusPanel = new PlayerStatusPanel(playerNames);

        layout = new RoboRallyGridPane(playerStatusPanel, boardGUI);

        addListeners();
    }

    /**
     * creates a Scene for this view to display it with the updated board
     * @return (Scene)
     */
    public Scene initGUI(){
        addLabel();

        layout = new RoboRallyGridPane(playerStatusPanel, boardGUI);
        return new Scene(layout);
    }

    /**
     * helper to add the listeners to the starting positions
     * listeners asks the controller to set the robot, and update the board with the image of the robot
     */
    public void addListeners() {
    	for(int i=0; i<boardGUI.getChildren().size(); i++) {
    		Node sp = boardGUI.getChildren().get(i);
    		int x = i% boardGUI.getPrefColumns();
    		int y = (int)Math.floor((i-x)/(double)boardGUI.getPrefColumns());
    		
    		int idx = i;
    		if(sp.getId().equals("S ")) {
    			sp.setOnMouseClicked(value -> {
    				if(((StackPane)boardGUI.getChildren().get(idx)).getChildren().size() < 2){
    					int id = getCurrentPlayerID();
    					controller.addRobot(id, x, y);
    					addRobotImage(id, idx);
    					
    					controller.nextPlayer();
    				}
    				
    			});
    		}
    	}
    }

    /**
     * helper method to create the label
     */
    public void addLabel(){
        instructions = new Label(playerName + ", please pick a starting position by clicking on it");
        layout.add(instructions, 0, 1);
    }

    /**
     * adds the robot image of the given player to the board on the given tile
     * @param playerID (int)
     * @param index (int) tile index
     */
    public void addRobotImage(int playerID, int index) {
    	ImageView robotImageView = ImageViewLoader.loadFile("src/main/resources/robotImages/robot"+playerID+".png", 50, 50);
		robotImageView.setRotate(90);
    	StackPane stack = (StackPane)boardGUI.getChildren().get(index);
    	stack.getChildren().add(robotImageView);
    }

    /**
     * getter for the listener to take the current player thats picking the SS
     * @return (int)
     */
    public int getCurrentPlayerID() {
    	return playerID;
    }

    /**
     * called by the controller
     * changes the current player infos and updates the label
     * @param nextPlayerID (int)
     */
    public void nextPlayerChooseStart(int nextPlayerID) {
    	playerID = nextPlayerID;
    	playerName = playerNames.get(playerID);
    	addLabel();
    	
    }

}
