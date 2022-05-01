package dtu.roboRally;

import java.util.Random;

import dtu.roboRally.controller.RoboRallyController;

public class Board {

	//initialize class attributes
	private int rows;
	private int cols;

	private RoboRallyController observer; //to give to the tiles
	
	//initializing a matrix consisting of elements of 'Tile' type
	private Tile[][] board;
	
	//boolean for checking if board is running in StepsDefinition
	private Random rnd = new Random();
	
	/**
	 * Creates a columns*rows sized board with same number of starting positions as number of players, 
	 * one endposition and various obstacles. The observer is passed to some of the advanced obstacles to update
	 * the GUI.
	 * @param (int) rows
	 * @param (int) cols
	 * @param (int) numberOfPlayers
	 * @param (RoboRallyController) observer
	 */
	public Board(int rows, int cols, int numberOfPlayers, RoboRallyController observer) {
		this.rows = rows;
		this.cols = cols;
		this.board = new Tile[rows][cols];

		this.observer = observer;
		
		loadObstacles();
		loadStartPosition(numberOfPlayers);
		loadEndPosition();
		loadLasers();
		loadBelts();
		loadTeleporterPair();
		loadTeleporterPair();

	}
	
	/**
	 * Loops through the entire board matrix and assigns random tile types.
	 */
	public void loadObstacles() {
		Tile t;
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < cols;i++) {
				t = getRandomTileType();
				t.setObserver(observer);
				board[j][i] = t;
			}
		}
	}
	/**
	 * Loads starting positions according to number of players in the first three columns of the board.
	 * Then removes all obstacles in 4 surrounding tiles (to make sure robot are not trapped).
	 * @param (int) numberOfPlayers
	 */
	public void loadStartPosition(int numberOfPlayers) {
		for(int i=0; i<numberOfPlayers; i++) {
			int x,y;
			do {
				x = (int) Math.floor(Math.random()*3);
				y = (int) Math.floor(Math.random()*rows);
			} while(! (board[y][x] instanceof Floor));
			
			board[y][x] = new StartPosition();
			
			if(isTileOnBoard(x, y+1)) board[y+1][x] = new Floor();
			if(isTileOnBoard(x, y-1)) board[y-1][x] = new Floor();
			board[y][x+1] = new Floor(); //this tile is always on the board
			if(isTileOnBoard(x-1, y)) board[y][x-1] = new Floor();
			
//			// checks the x-position, and removes obstacles in the x-direction
//			if (x == 0) {
//				if (! (board[y][x+1] instanceof StartPosition)) {
//				board[y][x+1] = new Floor();
//				}
//			}
//			else {
//				if (! (board[y][x+1] instanceof StartPosition)) {
//				board[y][x+1] = new Floor();
//				}
//				if (! (board[y][x-1] instanceof StartPosition)) {
//				board[y][x-1] = new Floor();
//				}
//			}
//			// check for y-position and removes obstacles in the y-direction
//			if (y == 0) {
//				if (! (board[y+1][x] instanceof StartPosition)) {
//				board[y+1][x] = new Floor();
//				}
//			}
//			else if (y == rows-1) {
//				if (! (board[y-1][x] instanceof StartPosition)) {
//					board[y-1][x] = new Floor();
//					}
//			}
//			else {
//				if (! (board[y-1][x] instanceof StartPosition)) {
//				board[y-1][x] = new Floor();
//				}
//				if (! (board[y+1][x] instanceof StartPosition)) {
//					board[y+1][x] = new Floor();
//				}
//			}

		}
	}
	/**
	 * Loads an endposition in the last three columns of the board. Removes obstacles in the 4 surrounding
	 * tiles to make sure the endposition is actually reachable.
	 */
	public void loadEndPosition() {
		int x,y;
		do {
			x = (int) Math.floor(cols - Math.random()*3);
			y = (int) Math.floor(Math.random()*rows);
		} while(! (board[y][x] instanceof Floor));
		
		board[y][x] = new EndPosition();
		
		if(isTileOnBoard(x,y+1)) board[y+1][x] = new Floor();
		if(isTileOnBoard(x,y-1)) board[y-1][x] = new Floor();
		if(isTileOnBoard(x+1,y)) board[y][x+1] = new Floor();
		board[y][x-1] = new Floor(); //this tile is always on the board.
		
//		// check x-position and removes obstacles around the endposition in x-direction
//		if (x == cols - 1) {
//			board[y][x-1] = new Floor();
//			
//		} else {
//			board[y][x-1] = new Floor();
//			board[y][x+1] = new Floor();
//			
//		}
//		// checks y-position and removes obstacles around the endposition in y-direction
//		if (y == 0) {
//			board[y+1][x] = new Floor();
//		} else if (y == rows - 1) {
//			board[y-1][x] = new Floor();
//		} else {
//			board[y-1][x] = new Floor();
//			board[y+1][x] = new Floor();
//		}
	}
	
	/**
	 * Loads a pair of teleporters. A pair of teleporters are linked and will transport the robot to each other.
	 * The observer notifies the GUI to update with the moves that the tiles inflict.
	 */
	public void loadTeleporterPair() {
		
		int x1,y1;
		int x2,y2;
		do {
			x1 = (int) (3+ Math.floor(Math.random()*(cols-6)));
			y1 = (int) Math.floor(Math.random()*rows);
		} while(! (board[y1][x1] instanceof Floor));
		do {
			x2 = (int) (3+ Math.floor(Math.random()*(cols-6)));
			y2 = (int) Math.floor(Math.random()*rows);
		} while((! (board[y2][x2] instanceof Floor)) && (x1!=x2 || y1!=y2));

		Teleporter t1 = new Teleporter(x2, y2);
		Teleporter t2 = new Teleporter(x1, y1);
		t1.setObserver(observer);
		t2.setObserver(observer);

		board[y1][x1] = t1;
		board[y2][x2] = t2;
	}
	
	/**
	 *
	 * @return (Tile) random tile type
	 */
	public Tile getRandomTileType() {
		double val = rnd.nextDouble();
		if (val < 0.80) {
			return new Floor();
		} else if (val < 0.84) {
			return new Repair();
		} else if (val < 0.88) {
			return new Wall((int)Math.floor(Math.random()*4));
		} else if (val < 0.92) {
			return new Pit();
		} else if (val < 0.96) {
			return new Acid();
		} else {
			Oil oil = new Oil();
			oil.setObserver(observer);
			return oil;
		}
	}
	/**
	 * 
	 * @param (int) x
	 * @param (int) y
	 * @return (Tile) returns the tile at position (x, y)
	 */
	public Tile getTile(int x, int y) {
		return board[y][x];
	}
	
	/**
	 * Prints the board labels in a 2d array. Used for testing.
	 */
	public void printBoard() {
		for (Tile[] row : board) {
			for (Tile col : row) {
				System.out.print(" " + col);
			}
			System.out.println("");
		}
	}

	// Setters of tiles for testing
	public void setOilTile(int x,int y) {
		board[y][x]=new Oil();
	}
	public void setTeleporter(int x,int y, int otherX, int otherY) {
        board[y][x]=new Teleporter(otherX, otherY);
    }
	public void setConveyorBelt(int x, int y, int orientation) {
		board[y][x] = new ConveyorBelt(orientation);
	}
	public void setAcidTile (int x, int y) {
		board[y][x] = new Acid();
	}
	public void setLaserShooter(int x, int y, int orientation) {
		board[y][x] = new LaserShooter(orientation);
	}
	public void setRepair(int x, int y) {
		board[y][x] = new Repair();
	}
	public void setWall(int x, int y, int o) {
		board[y][x] = new Wall(o);
	}
	
	/**
	 * Checks if the position (x, y) is an advanced obstacle. Used for generating multiple advanced obstacles
	 * on the board. Used to make sure that advanced obstacles are not generated within each other.
	 * @param (int) x
	 * @param (int) y
	 * @return (boolean)
	 */
	public boolean isAdvancedObstacle(int x, int y) {
		return board[y][x] instanceof Teleporter || board[y][x] instanceof LaserShooter
				|| board[y][x] instanceof ConveyorBelt || board[y][x] instanceof LaserBeam;
	}
	
	/**
	 * Loads laser shooters and beams, which span five vertical tiles. Can not be 
	 * loaded in the first or last three columns or on other advanced obstacles.
	 */
	public void loadLasers() {
		for(int n = 0; n<2; n++) {
			int x,y;
			do {
				x = (int) (3+ Math.floor(Math.random()*(cols-6)));
				y = (int) Math.floor(Math.random()*(rows-4));
			} while((isAdvancedObstacle(x,y)|| isAdvancedObstacle(x,y+1)|| isAdvancedObstacle(x,y+2)|| isAdvancedObstacle(x,y+3)|| isAdvancedObstacle(x,y+4)));
			
			board[y][x] = new LaserShooter(2);
			for(int i = 1; i<=3; i++) {
				board[y+i][x] = new LaserBeam();
			}
			board[y+4][x] = new LaserShooter(0);
		}
	}
	
	/**
	 * Loads conveyor belts on the board. Conveyor belts span 2 tiles vertically or horizontally. Vertical belts will either
	 * be oriented up (0) or down (2), and horizontal belts will be oriented right (1) or left (3). The orientation is
	 * the direction of transportation.
	 * The observer notifies the GUI to update with the moves that the belts inflict on the robot.
	 */
	public void loadBelts() {
		for(int i=0; i<3; i++) {
			int x,y, orientation;
			int nextY =0;
			int nextX=0;

			do { //checks if the tile is available
				x = (int) (3+ Math.floor(Math.random() * (cols-6)));
				y = (int) (Math.floor(Math.random() * rows));

				orientation = (int) Math.floor(Math.random() * 4);

				switch (orientation) {
					case 0:
						nextX = x;
						nextY = y - 1;
						break;
					case 1:
						nextX = x + 1;
						nextY = y;
						break;
					case 2:
						nextX = x;
						nextY = y + 1;
						break;
					case 3:
						nextX = x - 1;
						nextY = y;
				}
			} while(!isTileOnBoard(nextX, nextY) || isAdvancedObstacle(x,y) || isAdvancedObstacle(nextX, nextY));

			ConveyorBelt cb1 = new ConveyorBelt(orientation);
			cb1.setObserver(observer);
			board[y][x] = cb1;

			ConveyorBelt cb2 = new ConveyorBelt(orientation);
			cb2.setObserver(observer);
			board[nextY][nextX] = cb2;
		}
	}
	
	/**
	 * Checks whether the tile in position (x, y) is within the columns*rows specified board size.
	 * @param (int) x
	 * @param (int) y
	 * @return (boolean)
	 */
	public boolean isTileOnBoard(int x, int y){
		boolean xOnBoard = (x>=0) && (x<cols);
		boolean yOnBoard = (y>=0) && (y<rows);
		return xOnBoard && yOnBoard;
	}
	
	/**
	 * 
	 * @return (int)
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * 
	 * @return (int)
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * Removes all obstacles in the board. Used for testing.
	 */
	public void emptyTheBoard() {
		this.board = new Tile[rows][cols];

		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < cols;i++) {
				Floor t = new Floor();
				board[j][i] = t;
			}
		}
	}
}
