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
		setRobots();
	}
	
	public void createTilePane() {	
		
		setPrefColumns(cols);
		setPrefRows(rows);
		
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < cols; i++) {
				FileInputStream tiles = null;
				try {
					tiles = new FileInputStream("src/main/resources/tileImages/"+board.getTile(i, j).getLabel()+".png");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				Image tileImage = new Image(tiles);
				ImageView tileImageView = new ImageView(tileImage);
				tileImageView.setFitHeight(50);
				tileImageView.setFitWidth(50);
				getChildren().add(new StackPane(tileImageView));
			}
		}
		
		
		for(int i = 0; i<players.size(); i++) {
			int x = players.get(i).getRobot().getPosition().getX();
			int y = players.get(i).getRobot().getPosition().getY();
			
			int index = y*cols + x;
			
			FileInputStream robotFile = null;
			try {
				robotFile = new FileInputStream("src/main/resources/robotImages/robot"+i+".png");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			ImageView robotImageView = new ImageView(new Image(robotFile));
			robotImageView.setFitHeight(50);
			robotImageView.setFitWidth(50);
			
			
			StackPane stack = (StackPane) getChildren().get(index);
			stack.getChildren().add(robotImageView);
		}
	}
	
	public void setRobots() {
		
		for(int i = 0; i<players.size(); i++) {
			int x = players.get(i).getRobot().getPosition().getX();
			int y = players.get(i).getRobot().getPosition().getY();
			int o = players.get(i).getRobot().getPosition().getOrientation();
			
			int index = y*cols + x;
			
			FileInputStream robotFile = null;
			try {
				robotFile = new FileInputStream("src/main/resources/robotImages/robot"+i+".png");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			ImageView robotImageView = new ImageView(new Image(robotFile));
			robotImageView.setFitHeight(50);
			robotImageView.setFitWidth(50);
			robotImageView.setRotate(90*o);
			
			StackPane stack = (StackPane) getChildren().get(index);
			stack.getChildren().add(robotImageView);
		}
	}
	
	public void deleteRobots() {
				
//		for(int i = 0; i<players.size(); i++) {
//			int x = players.get(i).getRobot().getPosition().getX();
//			int y = players.get(i).getRobot().getPosition().getY();
//			
//			int index = y*cols + x;
//			
//			StackPane stack = (StackPane) getChildren().get(index);
//	
//			if(stack.getChildren().size() == 2) {
//				stack.getChildren().remove(1);
//			}
////			stack.getChildren().remove(stack.getChildren().size()-1);
//			System.out.println(i);
//			System.out.println(stack.getChildren().size());
//			
//		}
 		for(int i = 0; i<getChildren().size(); i++) {
 			StackPane tile = (StackPane) getChildren().get(i);
			if(tile.getChildren().size() > 1) {
				tile.getChildren().remove(1);
			}
		}
		
	}
	
}
