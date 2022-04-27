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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class SetStartingPositionsView {

    private SetStartingPositionsController controller;
    private int playerID;
    private ArrayList<String> playerNames;
    private String playerName;
       
    private GridPane layout;
    private BoardTilePane boardGUI;
    private Label instructions;
    private PlayerStatusPanel playerStatusPanel;


    public SetStartingPositionsView(SetStartingPositionsController controller, int playerID, ArrayList<String> playerNames){
    	layout = new GridPane();
        this.controller = controller;
        this.playerID = playerID;
        this.playerNames = playerNames;
        playerName = playerNames.get(playerID);
        
        boardGUI = new BoardTilePane();
        layout.add(boardGUI, 1, 0, 1, 1);
        
      
        playerStatusPanel = new PlayerStatusPanel(playerNames);
        layout.add(playerStatusPanel , 0, 0, 1, 3);
        
        addListeners();
    }

    public Scene initGUI(){
    	layout = new GridPane();
    	layout.setPadding(new Insets(40, 0, 0, 40));
        addLabel();
        layout.add(boardGUI, 1, 0, 1, 1);
        layout.add(playerStatusPanel,  0,  0, 1, 3);
        //boardGUI = new BoardTilePane();
        //layout.add(boardGUI, 1, 0, 1, 1);
        //layout.add(playerStatusPanel , 0, 0, 1, 3);
        
        return new Scene(layout);
    }

    public void addLabel(){
        instructions = new Label(playerName + ", please pick a starting position by clicking on it");
        layout.add(instructions, 0, 1);
    }
    
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
    
    public void addRobotImage(int playerID, int index) {
    	ImageView robotImageView = ImageViewLoader.loadFile("src/main/resources/robotImages/robot"+playerID+".png");
    	robotImageView.setFitHeight(50);
		robotImageView.setFitWidth(50);
		robotImageView.setRotate(90);
    	StackPane stack = (StackPane)boardGUI.getChildren().get(index);
    	stack.getChildren().add(robotImageView);
    }
    
    public int getCurrentPlayerID() {
    	return playerID;
    }
    
    public void nextPlayerChooseStart(int nextPlayerID) {
    	playerID = nextPlayerID;
    	playerName = playerNames.get(playerID);
    	addLabel();
    	
    }

}
