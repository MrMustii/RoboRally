package dtu.roboRally;

public class Robot {
	private Position position;
	private Position startPosition;
	private int lives;
	private boolean isDead;
	private boolean shielded;

	//final values same for all robot
	private final int startingLives = 5;
	
	/**
	 * Constructor for creating a robot with an orientation and a position
	 * @param o = orientation (int)
	 * @param startx (int)
	 * @param starty (int)
	 */
	public Robot(int o, int startx, int starty) {
		startPosition = new Position(startx, starty, o);
		setPosition(startPosition.clone());
		this.lives = startingLives;
		isDead = false;
		shielded = false;
		Tile currentTile = Game.getInstance().getBoard().getTile(startx, starty);
		currentTile.setOccupied(true);
		currentTile.setOccupidRobot(this);
		
	}

	/**
	 * Moving the robot into the neighbouring cell if possible
	 * @param board (Board)
	 * @param newPosition (Position)
	 * @return A position
	 */
	public boolean move(Board board, Position newPosition) {
		
		int deltaX = newPosition.getX() - position.getX();
		int deltaY = newPosition.getY() - position.getY();
		int deltaO = newPosition.getOrientation() - position.getOrientation();
		
		// if rotation
		if (deltaO != 0) {
			position = newPosition;
			return true;
		}
		
		// to set movement direction
		int dir = 1;
		if(deltaX<0 || deltaY<0) dir = -1;
		
		//changing position
		int delta = 0;
		
		boolean onX;
		if (deltaX != 0) {
			delta = deltaX;
			onX = true;
		} else if (deltaY != 0) {
			delta = deltaY;
			onX = false;
		} else {
			onX = false;
			System.out.println("error on computing delta moving in move robot");
		}
		
		for(int i = 0; i<Math.abs(delta); i++) {
			if(isDead) return false;
			if(moveOne(dir, onX, board)) {
				Tile currentTile = board.getTile(position.getX(), position.getY());
				currentTile.interact(this);
			} else {
				return false;
			}
		}
		return true;
	}
	
	private boolean moveOne(int direction, boolean onXaxis, Board board) {
		
		int nextTileX;
		int nextTileY;
		Tile nextTile;
		
		Tile currentTile = board.getTile(position.getX(), position.getY());
		
		if(onXaxis) {
			nextTileX = position.getX() + 1*direction;
			nextTileY = position.getY();
		} else {
			nextTileX = position.getX();
			nextTileY = position.getY() + 1*direction;
		}
		
		if(board.isTileOnBoard(nextTileX, nextTileY)) {
			nextTile = board.getTile(nextTileX, nextTileY);
		} else {
			System.out.println("Robot cannot move out of board");
			return false;
		}
		
		//find orientation of the move
		int moveOrientation;
		if(!onXaxis && direction < 0) {
			moveOrientation = 0;
		} else if(onXaxis && direction > 0) {
			moveOrientation = 1;
		} else if(!onXaxis && direction > 0) {
			moveOrientation = 2;
		} else {
			moveOrientation = 3;
		}
		
		boolean moveOut = currentTile.canMoveOut(moveOrientation);
		boolean moveIn = nextTile.canMoveIn(moveOrientation);
		
		if(!moveOut || !moveIn) {
			return false;
		}
	
		//boolean canMoveIn = true;
		if (nextTile.isOccupied()) {
			Position prePushRobotPosition = nextTile.getOccupidRobot().getPosition();
			Position postPushRobotPosition = prePushRobotPosition.clone();
			
			switch(moveOrientation) {
			case 0: postPushRobotPosition.setY(prePushRobotPosition.getY()-1); break;
			case 1: postPushRobotPosition.setX(prePushRobotPosition.getX()+1); break;
			case 2: postPushRobotPosition.setY(prePushRobotPosition.getY()+1); break;
			case 3: postPushRobotPosition.setX(prePushRobotPosition.getX()-1); break;
			}
			moveIn = nextTile.getOccupidRobot().move(board, postPushRobotPosition);
		}
		
		if(moveIn) {
			position.setX(nextTileX);
			position.setY(nextTileY);
			currentTile.setOccupied(false);
			nextTile.setOccupied(true);
			nextTile.setOccupidRobot(this);
		}
		
		return true;
		
	}

	/**
	 * Damaging the robot. If the robot runs out of lives, it dies
	 * @param damage (int)
	 */
	public void damage(int damage){
		if (!shielded) {
			lives -= damage;

			if(lives <= 0){
				isDead = true;
				respawn();
			}
		} else {
			shielded = false;
		}
	}

	/**
	 * If a robot encounters a repair, it gains a life.
	 */
	public void repair() {
		lives +=1;
		if(lives > 5) lives = 5;
	}

	/**
	 * If the robot dies, it respawns at the start-position
	 */
	public void respawn() {
		setPosition(startPosition.clone());
		setLives(startingLives);
//		isDead=false;
	}
	
	public void setIsDead(boolean isDead) {
		this.isDead = isDead;
	}

	/**
	 * Setting the amount of lives
	 * @param lives (int)
	 */
	public void setLives(int lives){
		this.lives = lives;
	}

	/**
	 * Getter method to get the amount of lives
	 * @return (int)
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * Getter method to get the position
	 * @return (Position)
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Getter method to get the startposition
	 * @return (Position)
	 */
	public Position getStartPosition(){
		return startPosition;
	}

	/**
	 * Setting the currect position
	 * @param position (Position)
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Checks if the robot is dead
	 * @return (boolean)
	 */
	public boolean isDead() {
		return isDead;
	}

	/**
	 * Checks if shield has been set
	 * @return (boolean)
	 */
	public boolean getShielded() {
		return shielded;
	}

	/**
	 * Sets a shield for the shield card
	 * @param bool (boolean)
	 */
	public void setShielded(boolean bool) {
		shielded=bool;
	}
}
