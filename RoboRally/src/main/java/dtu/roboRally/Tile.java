package dtu.roboRally;

import java.util.Random;

public class Tile {

	//initialize class attributes
	private int damage;
	private String label; //TODO: delete when GUI is implemented
	Board board;
	private boolean occupied;
	private Robot occupidRobot;

	
	//Tile constructor to assign attributes to subclass tiles
	public Tile(String label, int damage) {
		this.label = label;
		this.damage = damage;
	}
	
	public void interact(Robot robot) {
		robot.damage(damage);
	
	}
	
	public boolean canMoveIn(int robotOrientation) {
		return true;
	}
	
	public boolean canMoveOut(int orientation) {
		return true;
	}
	
	//setter and getter methods for Tile labels
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	//setter and getter methods for tile damage
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public int getDamage() {
		return damage;
	}
	
	//toString to print the board
	@Override
	public String toString() {
		return label;
	}
	public boolean getOccupied() {
		return occupied;
	}
	public void setOccupied(boolean bool) {
		occupied=bool;
	}

	public Robot getOccupidRobot() {
		return occupidRobot;
	}

	public void setOccupidRobot(Robot occupidRobot) {
		this.occupidRobot = occupidRobot;
	}
}

// Tile subclasses that will make up the board 'matrix'
class Floor extends Tile {
	public Floor() {
		//calling the parent class constructor 'Tile(String label, int damage)' to assign 
		//label and damage properties
		super("0 ",0);
	}
}

class Pit extends Tile {
	
	public Pit() {
		super("P ",5);
	}
}

class Acid extends Tile {
	public Acid() {
		super("A ",2);
	}
}

class Radiation extends Tile {
	public Radiation() {
		super("R ",1);
	}
}


//changes the orientation of the robot
class Oil extends Tile {
	public Oil() {
		super("O ",0);

	}

public void interact(Robot robot) {
	Random rnd = new Random();

	int oldOri = robot.getPosition().getOrientation();
	
	
	int newOri = rnd.nextInt(3);
	
	while (oldOri == newOri) {
		newOri = rnd.nextInt(3);
	} 
	robot.getPosition().setOrientation(newOri);
	
	}
}


class StartPosition extends Tile {
	//additional input parameters can be added to this constructor to specify fixed starting position I believe
	public StartPosition() {
		super("S ", 0);
		
	}
}

class EndPosition extends Tile {
	public EndPosition() {
		super("E ",0);
	}
}

class Repair extends Tile {
	public Repair() {
		super("+ ",-3);	
	}
	public boolean canMoveOut(int robotOrientation) {
		return false;
	}
}


class Wall extends Tile {
	
	int orientation;
	public Wall(int orientation) {
		super("W"+orientation,0);
		this.orientation = orientation;
	}
	
	@Override
	public boolean canMoveIn(int robotOrientation) {
		return Math.abs(orientation-robotOrientation) != 2;
	}
	
	@Override
	public boolean canMoveOut(int robotOrientation) {
		return orientation != robotOrientation;
	}
}

class Teleporter extends Tile {
	public Teleporter() {
		super("T ", 0);
	}
	@Override
	public void interact(Robot robot) {
//		board = Game.getInstance().getBoard();
		Position position;
		String label;
		Game game = Game.getInstance();
		
		// initialize x and y variables to get the position of the robot and check if its on a teleporter tile
		int x,y;
		position = robot.getPosition();
		x = position.getX();
		y = position.getY();
		
		//checks if teleporter is the first or second teleporter
		if (game.getBoard().getTile(x,y) instanceof Teleporter) {
			label = game.getBoard().getTile(x,y).getLabel();
			
			if (label == "T1") {
				//parse for T2
				for (int rows=0;rows<game.getBoard().getRows();rows++) {
			    	for (int cols=0;cols<game.getBoard().getCols();cols++) {
			    		if(game.getBoard().getTile(cols, rows).getLabel() == "T2") {
			    			robot.setPosition(new Position(cols, rows, position.getOrientation()));

			    		}
			    	}
			    }
			}
//				for (Tile[] row : board) {
//					for (Tile col : row) {
//						if (label == "T2") {
//							robot.setPosition(new Position(i, j, position.getOrientation()));
//							
//						}
//					j++;
//					}
//				j = 0;
//				i++;
//				}
				
			
			else { //if label == "T2"
				
				//parse for T1
				for (int rows=0;rows<game.getBoard().getRows();rows++) {
			    	for (int cols=0;cols<game.getBoard().getCols();cols++) {
			    		if(game.getBoard().getTile(cols, rows).getLabel() == "T1") {
			    			robot.setPosition(new Position(cols, rows, position.getOrientation()));
//			    		endPosition.add(new Position(cols,rows,1));
			    		}
			    	}
			    }
			}
//				for (Tile[] row : board) {
//					for (Tile col : row) {
//						if (label == "T1") {
//								robot.setPosition(new Position(i, j, position.getOrientation()));
//						}
//					j++;
//					}
//				j = 0;	
//				i++;
//				}
			}
		}
		
		
	}
	

// TODO: implement laser as an advanced obstacle
//class Laser extends Tile{
//	public Laser() {
//		super("L",2);
//	}
//}


