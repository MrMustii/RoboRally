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
		Game.getInstance().getBoard().getTile(startx, starty).setOccupied(true);
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
		
		board.getTile(position.getX(), position.getY()).setOccupied(false);
		//board.getTile(position.getX(), position.getY()).setOccupidRobot(null);
		
		// to set movement direction
		int n = 1;
		if(deltaX<0 || deltaY<0) n = -1;
		
		//if rotation
		if (deltaO != 0) {
			position = newPosition;
			return true;
		}
		//changing position
		int delta = 0;
		//if moving on X axis:
		if (deltaX > 0) {
			delta = deltaX;
		} else if (deltaY > 0) {
			delta = deltaY;
		} else {
			System.out.println("error on computing delta moving in move robot");
		}

		for (int i = 0; i < Math.abs(delta); i++) {
			//position of the cell we want to move in
			int nextTileX;
			int nextTileY;
			Tile currentCell;
			Tile nextCell;
			if (deltaX > 0) {
				nextTileX = position.getX() + (i + 1) * n;
				nextTileY = position.getY();
				currentCell = board.getTile(position.getX() + i * n, position.getY());
				nextCell = board.getTile(nextTileX, nextTileY);
			} else { //deltaY
				nextTileX = position.getX();
				nextTileY = position.getY() + (i + 1) * n;
				currentCell = board.getTile(position.getX(), position.getY() + i * n);
				nextCell = board.getTile(position.getX(), nextTileY);
			}


			//checks if it is possible to moveOut of the current cell
			boolean moveOut = currentCell.canMoveOut(position.getOrientation());

			//checks if it possible to moveIn the next cell
			boolean moveIn = nextCell.canMoveIn(position.getOrientation());
			moveIn = moveIn && board.isTileOnBoard(nextTileX, position.getY());

			if (!moveOut || !moveIn) {
				//move not possible, change to best position
				if (deltaX > 0) {
					position = new Position(position.getX() + i * n, position.getY(), position.getOrientation());
				} else {
					position = new Position(position.getX() + i * n, position.getY(), position.getOrientation());
				}
				return false;
			}
			if (nextCell.getOccupied()) {
				Position pushedTo;
				if (deltaX > 0) {
					pushedTo = new Position(position.getX() + (i + 2) * n, position.getY(), nextCell.getOccupidRobot().getPosition().getOrientation());
				} else {
					pushedTo = new Position(position.getX(), position.getY() + (i + 2) * n, nextCell.getOccupidRobot().getPosition().getOrientation());
				}
				nextCell.getOccupidRobot().move(board, pushedTo);
			}

			//if move possible, interact with the cell
			nextCell.interact(this);
		}
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


            position = newPosition;
            board.getTile(position.getX(), position.getY()).setOccupied(true);
            board.getTile(position.getX(), position.getY()).setOccupidRobot(this);
            return true;
    }

    public void respawn() {
        setPosition(startPosition.clone());
        setLives(startingLives);
        isDead = false;
    }

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
		isDead=false;
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
