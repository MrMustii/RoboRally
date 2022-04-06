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
	
public boolean move(Board board, Position newPosition) {
		
		int deltaX = newPosition.getX() - position.getX();
		int deltaY = newPosition.getY() - position.getY();
		int deltaO = newPosition.getOrientation() - position.getOrientation();
		
		// to set movement direction
		int n = 1;
		if(deltaX<0 || deltaY<0) n = -1;
		
		//if rotation
		if (deltaO != 0) {
			position = newPosition;
			return true;
		} else {
			
			//if moving on X axis
			for (int i=0; i<Math.abs(deltaX); i++) {
				Tile currentCell = board.getTile(position.getX()+i*n, position.getY());
				Tile nextCell = board.getTile(position.getX()+(i+1)*n, position.getY());
				//checks if it is possible to moveOut of the current cell
				boolean moveOut = currentCell.canMoveOut(position.getOrientation());
				//checks if it possible to moveIn the next cell
				boolean moveIn = nextCell.canMoveIn(position.getOrientation());
				if(!moveOut || ! moveIn) {
					//move not possible, change to best position
					position = new Position(position.getX()+i*n, position.getY(), position.getOrientation());
					return false;
				}
				//if move possible, interact with the cell
				if(moveIn) nextCell.interact(this);
			}
			
			for (int i=0; i<Math.abs(deltaY); i++) {
				Tile currentCell = board.getTile(position.getX(), position.getY()+i*n);
				Tile nextCell = board.getTile(position.getX(), position.getY()+(i+1)*n);
				boolean moveOut = currentCell.canMoveOut(position.getOrientation());
				boolean moveIn = nextCell.canMoveIn(position.getOrientation());
				if(!moveOut || ! moveIn) {
					position = new Position(position.getX(), position.getY()+i*n, position.getOrientation());
					return false;
				}
				if(moveIn) nextCell.interact(this);
			}
		
			position = newPosition;
			return true;
		}	
	}
	
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
