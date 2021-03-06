package dtu.roboRally;

import java.util.Random;

import dtu.roboRally.controller.RoboRallyController;



public abstract class Tile {

	//initialize class attributes
	private int damage;
	private String label;
	private boolean occupied;
	private Robot occupiedRobot;
	protected int orientation;
	protected boolean testing = false;

	private RoboRallyController observer;
	
	
	/**
	 * Constructor for Tile, which takes in the "name" of a tile and the amount of damage the specific tile afflicts
	 * 
	 * @param label (String)
	 * @param damage (int)
	 */
	//Tile constructor to assign attributes to subclass tiles
	public Tile(String label, int damage) {
		this.label = label;
		this.damage = damage;
	}

	public void setObserver(RoboRallyController observer){
		if (!testing) {this.observer = observer;}
	}

	public RoboRallyController getObserver(){
		return observer;
	}

	
	/** 
	 * Base version of the interact method, which only checks how much damage a tile deals. 
	 * Gets overridden in the necessary tile subclasses which implement additional functionality (fx. conveyor belts)
	 * 
	 * @param robot (Robot)
	 */
	public void interact(Robot robot) {
		robot.damage(damage);
	}
	
	/**
	 * Method which takes the robots orientation as input, to be overridden in certain tiles to check if it is possible to
	 * to move either onto the tile depending on on the tile's orientation or move onto it at all
	 * 
	 * @param robotOrientation (int)
	 * @return (boolean) whether you can move in or not
	 */
	public boolean canMoveIn(int robotOrientation) {
		return true;
	}
	
	/**
	 * Similar to the canMoveIn method, but this one takes the tile's orientation as input to check if the robot can move out of a tile if
	 * both the tile's and the robot's orientation are equal. Overridden in the wall subclass.
	 * 
	 * @param orientation (int)
	 * @return (boolean)
	 */
	public boolean canMoveOut(int orientation) {
		return true;
	}

	/**
	 * Setter for label, so certain tiles can be assigned a specific label fx. in theloadTeleporter method, where two separate teleporter
	 * labels are made to distinguish between the two teleporters that are loaded.
	 * @param label (String)
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Getter for the label, returns a tiles label
	 * @return (String)
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Setter for damage, for if a tile's damage-given changes during the game
	 * @param damage (int)
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	/**
	 * Gets the damage of a tile
	 * @return (int) damage
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 * Overridden method for printing of the board for testing
	 * @return (String) label
	 */
	@Override
	public String toString() {
		return label;
	}
	
	/**
	 * Getter for if tile is occupied
	 * @return boolean
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * Setter for tile being occupied
	 * @param bool (boolean)
	 */
	public void setOccupied(boolean bool) {
		occupied=bool;
	}

	/**
	 * Getter for seeing if tile is occupied by robot
	 * @return occupiedRobot
	 */
	public Robot getOccupiedRobot() {
		return occupiedRobot;
	}
	
	/**
	 * Assigns a tile with the attribute of being occupied by a robot
	 * @param occupiedRobot (Robot)
	 */
	public void setOccupiedRobot(Robot occupiedRobot) {
		this.occupiedRobot = occupiedRobot;
	}

	/**
	 * Getter for the tile's orientation, needed for multiple tiles fx. conveyor belt.
	 * @return orientation 
	 */
	public int getTileOrientation() {
		return orientation;
	}
}

// Tile subclasses that will make up the board 'matrix'

class Floor extends Tile {
	public Floor() {
		//calling the parent class constructor 'Tile(String label, int damage)' to assign 
		//label and damage properties
		super("0 ",0);
	}
	
	@Override
	public void interact(Robot robot) {
		//do nothing when interacting with floor
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

//changes the orientation of the robot
class Oil extends Tile {
	public Oil() {
		super("O ",1);
	}

	/**
	 * Overridden interact method for the oil tile, which changes the orientation of the robot to a different orientation
	 * 
	 * @param robot (Robot)
	 */
	@Override
	public void interact(Robot robot) {
		if(!testing) super.getObserver().notifyRobotMove();
		super.interact(robot);
		Random rnd = new Random();
		
		int oldOri = robot.getPosition().getOrientation();
		int newOri = rnd.nextInt(3);
		
		while (oldOri == newOri) {
			newOri = rnd.nextInt(3);
		} 
		robot.move(Game.getInstance().getBoard(), new Position(robot.getPosition().getX(), robot.getPosition().getY(), newOri));
		if(!testing) super.getObserver().notifyRobotMove();
		}
}


class StartPosition extends Tile {
	//additional input parameters can be added to this constructor to specify fixed starting position I believe
	public StartPosition() {
		super("S ", 0);
	}
	
	@Override
	public void interact(Robot robot) {
		//do nothing when interacting with start position
	}
}

class EndPosition extends Tile {
	public EndPosition() {
		super("E ",0);
	}
	
	@Override
	public void interact(Robot robot) {
		//do nothing when interacting with end position
	}
}

class Repair extends Tile {
	public Repair() {
		super("+ ",0);	
	}
	@Override
	public void interact(Robot robot) {
		robot.repair();
	}
}


class Wall extends Tile {
	
	public Wall(int orientation) {
		super("W"+orientation,0);
		super.orientation = orientation;
	}
	
	@Override
	public void interact(Robot robot) {
		//do nothing when interacting with wall
	}
	
	/**
	 * Overridden method for wall subclass, checking if robot's and tile's orientations match 
	 * 
	 * @param robotOrientation (int)
	 * @return boolean
	 */
	@Override
	public boolean canMoveIn(int robotOrientation) {
		return Math.abs(orientation-robotOrientation) != 2;
	}
	
	/**
	 * Overridden method for wall subclass, checking if robot's and tile's orientations match
	 * 
	 * @param robotOrientation (int)
	 * @return boolean
	 */
	@Override
	public boolean canMoveOut(int robotOrientation) {
		return orientation != robotOrientation;
	}
}

class Teleporter extends Tile {
	int TPxPos, TPyPos;

	/**
	 * Constructor for teleporter
	 * @param x (int)
	 * @param y (int)
	 */
	public Teleporter(int x, int y) {
		super("T ", 0);
		this.TPxPos = x;
		this.TPyPos = y;
	}


	public int getXPos() {
		return this.TPxPos;
	}

	public int getYPos() {
		return this.TPyPos;
	}
	
	/**
	 * Overridden interact method for teleporter tile, to have the robot change position to the other teleporter on the board
	 * 
	 * @param robot (Robot)
	 */
	@Override
	public void interact(Robot robot) {
		Position newPosition =new Position(getXPos(), getYPos(), robot.getPosition().getOrientation());
		robot.setPosition(newPosition);
		if (!testing) {super.getObserver().notifyRobotMove();}
	}
}

class ConveyorBelt extends Tile {
	
	int orientation;
	public ConveyorBelt(int orientation) {
		super("C"+orientation,0);
		this.orientation = orientation;
	}
	
	/**
	 * Overridden interact method for the conveyor belt tiles, moves the robot to the tile in front of the conveyor belt depending on the belt's rotation
	 * 
	 * @param robot (Robot)
	 */
	@Override
	public void interact(Robot robot) {
		if (!testing) {super.getObserver().notifyRobotMove();}
		Position position = robot.getPosition();
		Board board = Game.getInstance().getBoard();
		int x,y,roboOrientation;
		int ynew = 0;
		int xnew = 0;
		x = position.getX();
		y = position.getY();
		roboOrientation = position.getOrientation();
		
		switch(orientation) {
		case 0:
			xnew = x;
			ynew = y-1;
			break;
		case 1:
			xnew = x+1;
			ynew = y;
			break;
		case 2:
			xnew = x;
			ynew = y+1;
			break;
		case 3:
			xnew = x-1;
			ynew = y;
			break;
		}
		
		robot.move(board, new Position(xnew, ynew, roboOrientation));
	}
}
class LaserShooter extends Tile{
	int orientation;
	public LaserShooter(int orientation) {
		super("L" + orientation, 0);
		this.orientation = orientation;
	}
	/**
	 * Overridden canMoveIn method for LaserShooter, returns false as the robot should not be able to move onto the laser shooter
	 * 
	 * @param robotOrientation (int)
	 * @return (boolean)
	 */
	@Override
	public boolean canMoveIn(int robotOrientation) {
		return false;
	}
	
}
class LaserBeam extends Tile{
	public LaserBeam() {
		super("B ", 3);
	}
}




