package dtu.roboRally;

//test22
public class Robot {
	private Position position;
	private Position startPosition;
	private int lives;

	//final values same for all robot
	private final int startingLives = 5;
	
	public Robot(int o, int startx, int starty) {
		startPosition = new Position(startx, starty, o);
		setPosition(startPosition.clone());
		this.lives = startingLives;
	}
	
//	public void rotateClockwise() {
//		switch(orientation) {
//		case UP:
//			setOrientation(Orientation.RIGHT);
//			break;
//		case RIGHT: 
//			setOrientation(Orientation.DOWN);
//			break;
//		case DOWN:
//			setOrientation(Orientation.LEFT);
//			break;
//		case LEFT:
//			setOrientation(Orientation.UP);
//			break;
//		}
//	}
//	
//	public void rotateCounterClockwise() {
//		switch(orientation) {
//		case UP:
//			setOrientation(Orientation.LEFT);
//			break;
//		case RIGHT: 
//			setOrientation(Orientation.UP);
//			break;
//		case DOWN:
//			setOrientation(Orientation.RIGHT);
//			break;
//		case LEFT:
//			setOrientation(Orientation.DOWN);
//			break;
//		}
//	}
//	
//	public void moveForward() {
//		switch(orientation) {
//		case UP:
//			y += 1;
//			break;
//		case RIGHT: 
//			x += 1;
//			break;
//		case DOWN:
//			y -= 1;
//			break;
//		case LEFT:
//			x -= 1;
//			break;
//		}
//	}
//	
//	public void moveBackward() {
//		switch(orientation) {
//		case UP:
//			y -= 1;
//			break;
//		case RIGHT: 
//			x -= 1;
//			break;
//		case DOWN:
//			y += 1;
//			break;
//		case LEFT:
//			x += 1;
//			break;
//		}
//	}
	
	public void damage(int damage){
		lives -= damage;
		if(lives<=0){
			respawn();
		}
	}
	
	public void respawn() {
		setPosition(startPosition.clone());
		setLives(startingLives);
	}

	public void setLives(int lives){
		this.lives = lives;
	}

	public int getLives() {
		return lives;
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
