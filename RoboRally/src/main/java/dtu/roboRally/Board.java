package dtu.roboRally;

import java.util.Random;

public class Board {

	//initialize class attributes
	private int rows;
	private int cols;
	
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
	public Board(int newRows, int newCols, int numberOfPlayers) {
//		this.isRunning = true;
		this.rows = newRows;
		this.cols = newCols;
		this.board = new Tile[rows][cols];
		
		loadObstacles();
		loadStartPosition(numberOfPlayers);
		loadEndPosition();
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
	
	//getter for board
	public Tile[][] getBoard() {
		return board;
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
}
