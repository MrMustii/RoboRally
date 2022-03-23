package dtu.roboRally;

public class Robot {
	private Orientation orientation;
	private int x, y;
	private int startx, starty;
	
	public Robot(Orientation o, int startx, int starty) {
		orientation = o;
		this.startx = startx;
		this.starty = starty;
		setPosition(startx, starty);
	}
	
	public void setOrientation(Orientation o) {
		orientation = o;
	}
	
	public Orientation getOrientation() {
		return orientation;
	}
	
	public void rotateClockwise() {
		switch(orientation) {
		case UP:
			setOrientation(Orientation.RIGHT);
			break;
		case RIGHT: 
			setOrientation(Orientation.DOWN);
			break;
		case DOWN:
			setOrientation(Orientation.LEFT);
			break;
		case LEFT:
			setOrientation(Orientation.UP);
			break;
		}
	}
	
	public void rotateCounterClockwise() {
		switch(orientation) {
		case UP:
			setOrientation(Orientation.LEFT);
			break;
		case RIGHT: 
			setOrientation(Orientation.UP);
			break;
		case DOWN:
			setOrientation(Orientation.RIGHT);
			break;
		case LEFT:
			setOrientation(Orientation.DOWN);
			break;
		}
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void moveForward() {
		switch(orientation) {
		case UP:
			y += 1;
			break;
		case RIGHT: 
			x += 1;
			break;
		case DOWN:
			y -= 1;
			break;
		case LEFT:
			x -= 1;
			break;
		}
	}
	
	public void moveBackward() {
		switch(orientation) {
		case UP:
			y -= 1;
			break;
		case RIGHT: 
			x -= 1;
			break;
		case DOWN:
			y += 1;
			break;
		case LEFT:
			x += 1;
			break;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void respawn() {
		setPosition(startx, starty);
	}
	
}
