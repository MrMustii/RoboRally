package dtu.roboRally.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import dtu.roboRally.Board;
import dtu.roboRally.Game;
import dtu.roboRally.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class BoardTilePane extends TilePane {
	
	Board board;
	private int rows;
	private int cols;
	
	ArrayList<Player> players;

		
	public BoardTilePane() {
		super();
		board = Game.getInstance().getBoard();
		rows = board.getRows();
		cols = board.getCols();
		
		players = Game.getInstance().getPlayers(); //TODO: pass this data from controller
		createTilePane();
		if(players.get(0).getRobot()!=null) { //we don't want to setRobot for SetStartingPositionView
			setRobots();
		}
	}
	
	public void createTilePane() {	
		
		setPrefColumns(cols);
		setPrefRows(rows);
		
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < cols; i++) {
				String tileID = board.getTile(i, j).getLabel();
				ImageView tileImageView = ImageViewLoader.loadFile("src/main/resources/tileImages/"+ tileID +".png");
				tileImageView.setFitHeight(50);
				tileImageView.setFitWidth(50);
				
				StackPane sp = new StackPane();
				sp.setId(tileID);
				sp.getChildren().add(tileImageView);
				
				getChildren().add(sp);
			}
		}
	}
	
	public void setRobots() {
		
		for(int i = 0; i<players.size(); i++) {
			int x = players.get(i).getRobot().getPosition().getX();
			int y = players.get(i).getRobot().getPosition().getY();
			int o = players.get(i).getRobot().getPosition().getOrientation();
			
			int index = y*cols + x;
			
			ImageView robotImageView = ImageViewLoader.loadFile("src/main/resources/robotImages/robot"+i+".png");
			robotImageView.setFitHeight(50);
			robotImageView.setFitWidth(50);
			robotImageView.setRotate(90*o);
			
			StackPane stack = (StackPane) getChildren().get(index);
			stack.getChildren().add(robotImageView);
		}
	}
	
	public void deleteRobots() {
 		for(int i = 0; i<getChildren().size(); i++) {
 			StackPane tile = (StackPane) getChildren().get(i);
			if(tile.getChildren().size() > 1) {
				tile.getChildren().remove(1);
			}
		}
	}
}
