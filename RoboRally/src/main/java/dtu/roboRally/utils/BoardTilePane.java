package dtu.roboRally.utils;

import dtu.roboRally.Board;
import dtu.roboRally.Game;
import dtu.roboRally.Player;
import dtu.roboRally.Position;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

/**
 * Custom TilePane that we use in several views of the game
 */
public class BoardTilePane extends TilePane {
	
	Board board;
	private int rows;
	private int cols;
	
	ArrayList<Player> players = Game.getInstance().getPlayers();

	/**
	 * Constructor for the boardTilePane
	 */
	public BoardTilePane() {
		super();
		board = Game.getInstance().getBoard();
		rows = board.getRows();
		cols = board.getCols();
		
		createTilePane();
		if(players.get(0).getRobot()!=null) { //we don't want to setRobot for SetStartingPositionView
			setRobots();
		}
	}

	/**
	 * loads the view of the board and tile images given the Game.board
	 */
	public void createTilePane() {	
		
		setPrefColumns(cols);
		setPrefRows(rows);
		
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < cols; i++) {
				String tileID = board.getTile(i, j).getLabel();
				ImageView tileImageView = ImageViewLoader.loadFile("src/main/resources/tileImages/"+ tileID +".png", 50, 50);

				StackPane sp = new StackPane();
				sp.setId(tileID);
				sp.getChildren().add(tileImageView);
				
				getChildren().add(sp);
			}
		}
	}

	/**
	 * sets the robot on the board view
	 */
	public void setRobots() {
		
		for(int i = 0; i<players.size(); i++) {
			int x = players.get(i).getRobot().getPosition().getX();
			int y = players.get(i).getRobot().getPosition().getY();
			int o = players.get(i).getRobot().getPosition().getOrientation();
			
			int index = y*cols + x;
			
			ImageView robotImageView = ImageViewLoader.loadFile("src/main/resources/robotImages/robot"+i+".png", 50, 50);
			robotImageView.setRotate(90*o+180);
			
			StackPane stack = (StackPane) getChildren().get(index);
			stack.getChildren().add(robotImageView);
		}
	}

	/**
	 * getter for the end position, used for the progress bar to compute distance
	 * @return (Position)
	 */
	public Position getEndPosition(){

		for(int x=cols-4; x<cols; x++){
			for(int y=0; y<rows; y++) {
				int i = y*cols + x;
				StackPane sp = (StackPane) getChildren().get(i);
					if (sp.getId() != null && sp.getId().equals("E ")) {
						return new Position(x,y,0); //dont care about o
					}
			}
		}
		System.out.println("Couldnt find end position");
		return null;
	}

	/**
	 * deletes robots on the stack pane tiles
	 * stopped using because we now create a new board every time
	 */
	@Deprecated
	public void deleteRobots() {
 		for(int i = 0; i<getChildren().size(); i++) {
 			StackPane tile = (StackPane) getChildren().get(i);
			if(tile.getChildren().size() > 1) {
				tile.getChildren().remove(1);
			}
		}
	}


}
