package dtu.roboRally;

import dtu.roboRally.controller.RoboRallyController;

import java.util.Random;

public class Board {

	//initialize class attributes
	private int rows;
	private int cols;

	private RoboRallyController observer; //to give to the tiles
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	//initializing a matrix consisting of elements of 'Tile' type
	private Tile[][] board;
	

	
	//boolean for checking if board is running in StepsDefinition
//	private boolean isRunning = false;
	private Random rnd = new Random();
	
	//Board constructor
	public Board(int newRows, int newCols, int numberOfPlayers, RoboRallyController observer) {
//		this.isRunning = true;
		this.rows = newRows;
		this.cols = newCols;
		this.board = new Tile[rows][cols];

		this.observer = observer;
		
		loadObstacles();
		loadStartPosition(numberOfPlayers);
		loadEndPosition();
		loadLasers();
		loadBelts();
		loadTeleporter();
		loadTeleporter();

	}
	
	// Method for robot.move testing
	public void emptyTheBoard() {
		this.board = new Tile[rows][cols];
		
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < cols;i++) {
				Floor t = new Floor();
				board[j][i] = t;
			}
		}
	}
	// Method for robot.move testing
	public void addWall(int x, int y, int o) {
		board[y][x] = new Wall(o);
	}
	
	//parse through the empty matrix and assign random tile types
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
	
	public void loadStartPosition(int numberOfPlayers) {
		for(int i=0; i<numberOfPlayers; i++) {
			int x,y;
			do {
				x = (int) Math.floor(Math.random()*3);
				y = (int) Math.floor(Math.random()*rows);
			} while(! (board[y][x] instanceof Floor));
			
			board[y][x] = new StartPosition();
			
			// checks the x-position, and removes obstacles in the x-direction
			if (x == 0) {
				if (! (board[y][x+1] instanceof StartPosition)) {
				board[y][x+1] = new Floor();
				}
			}
			else {
				if (! (board[y][x+1] instanceof StartPosition)) {
				board[y][x+1] = new Floor();
				}
				if (! (board[y][x-1] instanceof StartPosition)) {
				board[y][x-1] = new Floor();
				}
			}
			// check for y-position and removes obstacles in the y-direction
			if (y == 0) {
				if (! (board[y+1][x] instanceof StartPosition)) {
				board[y+1][x] = new Floor();
				}
			}
			else if (y == rows-1) {
				if (! (board[y-1][x] instanceof StartPosition)) {
					board[y-1][x] = new Floor();
					}
			}
			else {
				if (! (board[y-1][x] instanceof StartPosition)) {
				board[y-1][x] = new Floor();
				}
				if (! (board[y+1][x] instanceof StartPosition)) {
					board[y+1][x] = new Floor();
				}
			}


		}
	}
	
	public void loadEndPosition() {
		int x,y;
		do {
			x = (int) Math.floor(cols - Math.random()*3);
			y = (int) Math.floor(Math.random()*rows);
		} while(! (board[y][x] instanceof Floor));
		
		board[y][x] = new EndPosition();
		
		// check x-position and removes obstacles around the endposition in x-direction
		if (x == cols - 1) {
			board[y][x-1] = new Floor();
			
		}
		else {
			board[y][x-1] = new Floor();
			board[y][x+1] = new Floor();
			
		}
		// checks y-position and removes obstacles around the endposition in y-direction
		if (y == 0) {
			board[y+1][x] = new Floor();
		}
		else if (y == rows - 1) {
			board[y-1][x] = new Floor();
		}
		else {
			board[y-1][x] = new Floor();
			board[y+1][x] = new Floor();
		}
	}
	
// Load teleporter
	
	public void loadTeleporter() {

		int x1,y1;
		int x2,y2;
		do {
			x1 = (int) ((int) 3+ Math.floor(Math.random()*(cols-6)));
			y1 = (int) Math.floor(Math.random()*rows);
		} while(! (board[y1][x1] instanceof Floor));
		do {
			x2 = (int) ((int) 3+ Math.floor(Math.random()*(cols-6)));
			y2 = (int) Math.floor(Math.random()*rows);
		} while((! (board[y2][x2] instanceof Floor)) && (x1!=x2 || y1!=y2));

		Teleporter t1 = new Teleporter(x2, y2);
		Teleporter t2 = new Teleporter(x1, y1);
		t1.setObserver(observer);
		t2.setObserver(observer);

		board[y1][x1] = t1;
		board[y2][x2] = t2;

		
		
	}
	
	// return random tile type method
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
			return new Radiation();
		}
	}
	
	public Tile getTile(int x, int y) {
		return board[y][x];
	}
	
	//prints the board, parses through the Tile matrix
	public void printBoard() {
		for (Tile[] row : board) {
			for (Tile col : row) {
				System.out.print(" " + col);
			}
			System.out.println("");
		}
	}

	//setter for Tile
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
	public boolean isTpLsCb(int x, int y) {
		if (board[y][x] instanceof Teleporter || board[y][x] instanceof LaserShooter || board[y][x] instanceof ConveyorBelt) {
			return true;
		}
		else {
			return false;
		}
	}
	

	public void loadLasers() {
		int x,y;
		do {
			x = (int) ((int) 3+ Math.floor(Math.random()*(cols-6)));
			y = (int) Math.floor(Math.random()*(rows-4));
		} while(!( board[y][x] instanceof Floor));
		board[y][x] = new LaserShooter(2);
		board[y+1][x] = new LaserBeam();
		board[y+2][x] = new LaserBeam();
		board[y+3][x] = new LaserBeam();
		board[y+4][x] = new LaserShooter(0);
		do {
			x = (int) ((int) 3+ Math.floor(Math.random()*(cols-6)));
			y = (int) Math.floor(Math.random()*(rows-4));
		} while((isTpLsCb(x,y)||isTpLsCb(x,y+1)||isTpLsCb(x,y+2)||isTpLsCb(x,y+3)||isTpLsCb(x,y+4)));
		board[y][x] = new LaserShooter(2);
		board[y+1][x] = new LaserBeam();
		board[y+2][x] = new LaserBeam();
		board[y+3][x] = new LaserBeam();
		board[y+4][x] = new LaserShooter(0);
	}

	public void loadBelts(){
		for(int i=0; i<3; i++){
			loadOneBelt();
		}
	}
	
	public void loadOneBelt() {

		int x,y, orientation;
		//first cb
		do { //checks if the tile is available
			x = (int) ((int) 3 + Math.floor(Math.random()*(cols-6)));
			y = (int) ((int) 3 + Math.floor(Math.random()*(rows-7)));
			orientation = (int) Math.floor(Math.random()*2);
			if (orientation == 1) {
				orientation = 2;
			}
		}while((orientation==2&&(isTpLsCb(x,y)||isTpLsCb(x,y+1)||isTpLsCb(x,y+2))
				||(orientation==0)&&(isTpLsCb(x,y)||isTpLsCb(x,y-1)||isTpLsCb(x,y-2))));

		//sets the belt
		int nextY;
		if (orientation == 2) {
			nextY = y+1;
		}else {
			nextY = y-1;
		}

		ConveyorBelt cb1 = new ConveyorBelt(2);
		cb1.setObserver(observer);
		board[y][x] = cb1;
		ConveyorBelt cb2 = new ConveyorBelt(2);
		cb2.setObserver(observer);
		board[nextY][x] = cb2;

		/*
		//second cb
		do {
			x = (int) ((int) 3 + Math.floor(Math.random()*(cols-6)));
			y = (int) ((int) 2 + Math.floor(Math.random()*(rows-4)));
			orientation = (int) Math.floor(Math.random()*2);
			if (orientation == 1) {
				orientation = 2;
			}
		}while((orientation==2&&(isTpLsCb(x,y)||isTpLsCb(x,y+1)||isTpLsCb(x,y+2))
				||(orientation==0)&&(isTpLsCb(x,y)||isTpLsCb(x,y-1)||isTpLsCb(x,y-2))));

		if (orientation == 2) {
			board[y][x] = new ConveyorBelt(2);
			board[y+1][x] = new ConveyorBelt(2);
		}
		else {
			board[y][x] = new ConveyorBelt(2);
			board[y-1][x] = new ConveyorBelt(2);
		}

		//third cb
		do {
			x = (int) ((int) 3 + Math.floor(Math.random()*(cols-6)));
			y = (int) ((int) 2 + Math.floor(Math.random()*(rows-4)));
			orientation = (int) Math.floor(Math.random()*2);
			if (orientation == 1) {
				orientation = 2;
			}
			
		}while((orientation==2&&(isTpLsCb(x,y)||isTpLsCb(x,y+1)||isTpLsCb(x,y+2))
				||(orientation==0)&&(isTpLsCb(x,y)||isTpLsCb(x,y-1)||isTpLsCb(x,y-2))));
		if (orientation == 2) {
			board[y][x] = new ConveyorBelt(2);
			board[y+1][x] = new ConveyorBelt(2);

			
		}
		else {
			board[y][x] = new ConveyorBelt(2);
			board[y-1][x] = new ConveyorBelt(2);

		}

		 */
	}

	public boolean isTileOnBoard(int x, int y){
		boolean xOnBoard = (x>=0) && (x<cols);
		boolean yOnBoard = (y>=0) && (y<rows);
		return xOnBoard&&yOnBoard;
	}

    public void setWall(int x, int y, int o) {
        board[y][x]=new Wall(o);
    }
}
