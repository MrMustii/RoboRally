package dtu.roboRally;

public class Position {
	private int x, y;
	private int orientation;
	
	
	public Position(int x, int y, int orientation) {
		this.setX(x);
		this.setY(y);
		this.setOrientation(orientation);
	}
	
	@Override
	public Position clone() {
		return new Position(x,y,orientation);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	

}
