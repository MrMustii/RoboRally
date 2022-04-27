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
    private GridPane layout;
    private int playerID;
    private ArrayList<String> playerNames;
    private String playerName;
        
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
        
      //list of 5 lives because robots are not initialized at that point
        ArrayList<Integer> lives = new ArrayList<>();
        for(int i=0; i<playerNames.size(); i++) {
        	lives.add(5);
        }
        playerStatusPanel = new PlayerStatusPanel(playerNames, lives);
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

//    public void addBoardGUI() {
//
//        Board board = Game.getInstance().getBoard();
//
//        int rows = board.getRows();
//        int cols = board.getCols();
//
//        TilePane boardGUI = new TilePane();
//        boardGUI.setPrefColumns(cols);
//        boardGUI.setPrefRows(rows);
//
//        for (int j = 0; j < rows; j++) {
//            for (int i = 0; i < cols; i++) {
//                ImageView tileImageView = ImageViewLoader.loadFile("src/main/resources/tileImages/"+board.getTile(i, j).getLabel()+".png");
//                tileImageView.setFitHeight(50);
//                tileImageView.setFitWidth(50);
//
//                if(board.getTile(i,j).getLabel().equals("S ")){
//                    int x = i;
//                    int y = j;
//                    tileImageView.setOnMouseClicked(value -> {
//                    	int index = y*cols + x;
//                    	StackPane stack = (StackPane) boardGUI.getChildren().get(index);
//                    	
//                    	if(stack.getChildren().size()<2) {
//                    		controller.addRobot(playerID, x, y);
//                			stack.getChildren().add(ImageViewLoader.loadFile("src/main/resources/robotImages/robot"+playerID+".png"));
//                            //TODO: add picture robot
//                            //TODO: check if tile occupied?
//                            controller.nextPlayer();
//                    	}
//                    });
//                }
//                boardGUI.getChildren().add(new StackPane(tileImageView));
//            }
//        }
//
//        layout.add(boardGUI, 0, 0, 1, 1);
//    }
    
    public void addRobotImage(int playerID, int index) {
    	ImageView robotImageView = ImageViewLoader.loadFile("src/main/resources/robotImages/robot"+playerID+".png");
    	robotImageView.setFitHeight(50);
		robotImageView.setFitWidth(50);
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
