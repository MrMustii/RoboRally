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
		
//		int deltaX = newPosition.getX() - position.getX();
//		int deltaY = newPosition.getY() - position.getY();
//		int deltaO = newPosition.getOrientation() - position.getOrientation();
//		
//		board.getTile(position.getX(), position.getY()).setOccupied(false); //TODO: only do this if not rotate
//		//board.getTile(position.getX(), position.getY()).setOccupidRobot(null);
//		
//		// to set movement direction
//		int n = 1;
//		if(deltaX<0 || deltaY<0) n = -1;
//		
//		//if rotation
//		if (deltaO != 0) {
//			position = newPosition;
//			return true;
//		}
//
//		//changing position
//		int delta = 0;
//		deltaX = Math.abs(deltaX);
//		deltaY = Math.abs(deltaY);
//		//if moving on X axis:
//		if (deltaX != 0) {
//			delta = deltaX;
//		} else if (deltaY != 0) {
//			delta = deltaY;
//		} else {
//			System.out.println("error on computing delta moving in move robot");
//			System.out.println(deltaO);
//			System.out.println(deltaX);
//			System.out.println(deltaY);
//			System.out.println(delta);
//		}
//
//		for (int i = 0; i < delta; i++) {
//			//position of the cell we want to move in
//			int nextTileX;
//			int nextTileY;
//			Tile currentCell;
//			Tile nextCell;
//			if (deltaX != 0) {
//				nextTileX = position.getX() + (i + 1) * n;
//				nextTileY = position.getY();
//				currentCell = board.getTile(position.getX() + i * n, position.getY());
//			} else { //deltaY
//				nextTileX = position.getX();
//				nextTileY = position.getY() + (i + 1) * n;
//				currentCell = board.getTile(position.getX(), position.getY() + i * n);
//			}
//			
//			nextCell = board.getTile(nextTileX, nextTileY);
//
//			//checks if it is possible to moveOut of the current cell
//			boolean moveOut = currentCell.canMoveOut(position.getOrientation());
//
//			//checks if it possible to moveIn the next cell
//			boolean moveIn = nextCell.canMoveIn(position.getOrientation());
//			moveIn = moveIn && board.isTileOnBoard(nextTileX, nextTileY);
//
//			if (!moveOut || !moveIn) {
//				//move not possible, change to best position
//				if (deltaX != 0) {
//					position = new Position(position.getX() + i * n, position.getY(), position.getOrientation());
//				} else {
//					position = new Position(position.getX() + i * n, position.getY(), position.getOrientation());
//				}
//				return false;
//			}
//			if (nextCell.getOccupied()) {
//				Position pushedTo;
//				if (deltaX != 0) {
//					pushedTo = new Position(position.getX() + (i + 2) * n, position.getY(), nextCell.getOccupidRobot().getPosition().getOrientation());
//				} else {
//					pushedTo = new Position(position.getX(), position.getY() + (i + 2) * n, nextCell.getOccupidRobot().getPosition().getOrientation());
//				}
//				nextCell.getOccupidRobot().move(board, pushedTo);
//			}
//
//			//if move possible, interact with the cell
//			nextCell.interact(this);
//		}
        	/*
            //if moving on X axis
            for (int i = 0; i < Math.abs(deltaX); i++) {
            	//position of the cell we want to move in
                int nextTileX = position.getX() + (i + 1) * n;
                int nextTileY = position.getY();

                Tile currentCell = board.getTile(position.getX() + i * n, position.getY());
                Tile nextCell = board.getTile(nextTileX, nextTileY);

                //checks if it is possible to moveOut of the current cell
                boolean moveOut = currentCell.canMoveOut(position.getOrientation());

                //checks if it possible to moveIn the next cell
                boolean moveIn = nextCell.canMoveIn(position.getOrientation());
                moveIn = moveIn && board.isTileOnBoard(nextTileX, position.getY());

                if (!moveOut || !moveIn) {
                    //move not possible, change to best position
                    position = new Position(position.getX() + i * n, position.getY(), position.getOrientation());
                    return false;
                }
                if (nextCell.getOccupied()) {
                    Position pushedTo = new Position(position.getX() + (i + 2) * n, position.getY(), nextCell.getOccupidRobot().getPosition().getOrientation());
                    nextCell.getOccupidRobot().move(board, pushedTo);
                }

                //if move possible, interact with the cell
                nextCell.interact(this);
            }

            //moving on Y axis
            for (int i = 0; i < Math.abs(deltaY); i++) {
                int nextTileY = position.getY() + (i + 1) * n;
                if (nextTileY < 0 || nextTileY >= board.getRows()) {
                    System.out.println("Robot cannot move out of board");
                    return false;
                }
                Tile currentCell = board.getTile(position.getX(), position.getY() + i * n);
                Tile nextCell = board.getTile(position.getX(), nextTileY);
                boolean moveOut = currentCell.canMoveOut(position.getOrientation());
                boolean moveIn = nextCell.canMoveIn(position.getOrientation());
                if (!moveOut || !moveIn || (nextCell.getOccupied() && nextTileY + 1 >= Game.getInstance().getBoard().getCols())) {
                    position = new Position(position.getX(), position.getY() + i * n, position.getOrientation());
                    return false;
                }
                if (nextCell.getOccupied()) {
                    Position pushedTo = new Position(position.getX(), position.getY() + (i + 2) * n, nextCell.getOccupidRobot().getPosition().getOrientation());

                    nextCell.getOccupidRobot().move(board, pushedTo);

                }
                nextCell.interact(this);
            }
            */


//            position = newPosition;
//            board.getTile(position.getX(), position.getY()).setOccupied(true);
//            board.getTile(position.getX(), position.getY()).setOccupidRobot(this);
//            return true;


//	public void resurrect() {
//		isDead = false;
//	}



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
