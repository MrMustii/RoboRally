package dtu.roboRally;

public class Robot {
	private Position position;
	private Position startPosition;
	private int lives;
	private boolean isDead;
	private boolean shielded;

	//final values same for all robot
	private final int startingLives = 5;
	
	public Robot(int o, int startx, int starty) {
		startPosition = new Position(startx, starty, o);
		setPosition(startPosition.clone());
		this.lives = startingLives;
		isDead = false;
		shielded = false;
		Game.getInstance().getBoard().getTile(startx, starty).setOccupied(true);
	}
	
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
		} else {
			
			//if moving on X axis
			for (int i=0; i<Math.abs(deltaX); i++) {
				//check if next cell is on board
				int nextTileX = position.getX()+(i+1)*n;
				if(nextTileX < 0 || nextTileX>= board.getCols()){
					System.out.println("robot cannot move out of board");
					return false;
				}else {
					Tile currentCell = board.getTile(position.getX() + i * n, position.getY());
					Tile nextCell = board.getTile(nextTileX, position.getY());
					//checks if it is possible to moveOut of the current cell
					boolean moveOut = currentCell.canMoveOut(position.getOrientation());
					//checks if it possible to moveIn the next cell
					boolean moveIn = nextCell.canMoveIn(position.getOrientation());
					if (!moveOut || !moveIn|| (nextCell.getOccupied()==true && nextTileX+1>=Game.getInstance().getBoard().getCols())) {
						//move not possible, change to best position
						position = new Position(position.getX() + i * n, position.getY(), position.getOrientation());
						return false;
					}
					if(nextCell.getOccupied()==true) {
						Position pushedTo=new Position(position.getX()+(i+2)*n, position.getY(), nextCell.getOccupidRobot().getPosition().getOrientation());
						nextCell.getOccupidRobot().move(board, pushedTo);
					}
					//if move possible, interact with the cell
					nextCell.interact(this);
				}
			}
			//moving on Y axis
			for (int i=0; i<Math.abs(deltaY); i++) {
				int nextTileY = position.getY()+(i+1)*n;
				if(nextTileY<0 || nextTileY>=board.getRows()){
					System.out.println("Robot cannot move out of board");
					return false;
				}
				Tile currentCell = board.getTile(position.getX(), position.getY()+i*n);
				Tile nextCell = board.getTile(position.getX(), nextTileY);
				boolean moveOut = currentCell.canMoveOut(position.getOrientation());
				boolean moveIn = nextCell.canMoveIn(position.getOrientation());
				if(!moveOut || ! moveIn || (nextCell.getOccupied()==true && nextTileY+1>=Game.getInstance().getBoard().getCols())) {
					position = new Position(position.getX(), position.getY()+i*n, position.getOrientation());
					return false;
				}
				if(nextCell.getOccupied()==true) {
					Position pushedTo=new Position(position.getX(),position.getY()+(i+2)*n , nextCell.getOccupidRobot().getPosition().getOrientation());

					 nextCell.getOccupidRobot().move(board, pushedTo);

				}
				nextCell.interact(this);
			}
		
			position = newPosition;
			board.getTile(position.getX(), position.getY()).setOccupied(true);
			board.getTile(position.getX(), position.getY()).setOccupidRobot(this);
			return true;
		}	
		
	}
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
	
	public void respawn() {
		setPosition(startPosition.clone());
		setLives(startingLives);
		isDead=false;
	}
	
//	public void resurrect() {
//		isDead = false;
//	}

	public void setLives(int lives){
		this.lives = lives;
	}

	public int getLives() {
		return lives;
	}
	
	public Position getPosition() {
		return position;
	}

	public Position getStartPosition(){
		return startPosition;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	public boolean isDead() {
		return isDead;
	}
	public boolean getShielded() {
		return shielded;
	}
	public void setShielded(boolean bool) {
		shielded=bool;
	}
	public void repair() {
		lives +=1;
		if(lives > 5) lives = 5;
	}
	
}
