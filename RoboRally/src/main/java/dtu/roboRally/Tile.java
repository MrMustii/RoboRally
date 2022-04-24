package dtu.roboRally;

import java.util.Random;

public class Tile {

	//initialize class attributes
	private int damage;
	private String label; //TODO: delete when GUI is implemented
	
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

// TODO: implement laser as an advanced obstacle
//class Laser extends Tile{
//	public Laser() {
//		super("L",2);
//	}
//}


