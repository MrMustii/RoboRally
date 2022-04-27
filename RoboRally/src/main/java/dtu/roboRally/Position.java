package dtu.roboRally;

/**
 * Class for setting and getting the position of the robot and the obstacles
 */
public class Position {
	private int x, y;
	private int orientation;
	
	/**
	 * Constructor that sets the position
	 * @param x = column (int)
	 * @param y = rows (int)
	 * @param orientation (int)
	 */
	public Position(int x, int y, int orientation) {
		this.setX(x);
		this.setY(y);
		this.setOrientation(orientation);
	}
	
	/**
	 * Clone position for the respawn function
	 */
	@Override
	public Position clone() {
		return new Position(x,y,orientation);
	}
	
	/**
	 * Getter method to get the x-position
	 * @return (int)
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Sets the x-position
	 * @param x (int)
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Getter method for the y-position
	 * @return (int) 
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Sets the y-position
	 * @param y (int)
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Getter method to get the orientation
	 * @return (int)
	 */
	public int getOrientation() {
		return orientation;
	}
	
	/**
	 * Sets the orientation
	 * @param orientation (int)
	 */
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
}
