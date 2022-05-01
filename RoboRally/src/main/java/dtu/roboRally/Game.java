package dtu.roboRally;

import java.util.ArrayList;

import dtu.roboRally.controller.RoboRallyController;

/**
 * This class creates a game with a board-layout where robots moves accord to the played cards
 */
public class Game {
	private ArrayList<Player> players = new ArrayList<>();
	private static Game instance;
	private Board board;
	private Player winner;
	private RoboRallyController observer;
	private boolean testing=false;

	/**
	 * Creating a board-layout and adding the chosen amount of players
	 * @param observer (RoboRallyController)
	 * @param numberOfPlayers (int)
	 */
	private Game(RoboRallyController observer, int numberOfPlayers) {
		board = new Board(9, 14, numberOfPlayers,observer); //maybe change size
		for(int i = 0; i<numberOfPlayers; i++) {
			players.add(new Player());
		}
		if(!testing) {this.observer = observer;}
	}
	
	/**
	 * Creating one instance of the game 
	 * @param observer (RoboRallyController)
	 * @param nbPlayers (int)
	 * @return (Game)
	 */
	public static Game getInstance(RoboRallyController observer, int nbPlayers) {
		if(instance == null) {
			instance = new Game(observer, nbPlayers);
		}
		return instance;
	}
	
	/**
	 * Checking if the game is instantiated
	 * @return (Game)
	 */
	public static Game getInstance() {
		if(instance==null) {
			System.out.println("Game not initialised; call getInstance(observer, int)");
		}
		//setNumberPlayers(nbPlayers);
		return instance;
	}
		
	/**
	 * Runs the game. This method makes sure that a robot moves accordingly to the played cards
	 * it picks which player plays their card first given on the priorities
	 * it checks if a player won at the end of the flow
	 * it notifies the GUI when  a card has been played, that they should update the view
	 */
	public void gameFlow() {
		// number of cards in play
		int nbOfCards = instance.getPlayers().get(0).getCardsInPlay().size();		
		
		// loop for each player to program their robot with their cards
		for (int round=0; round < nbOfCards; round++) {
			
			ArrayList<Integer> priorities = new ArrayList<>();
			
			// creates list of n'th card priorities according to each player index
			for(Player p:instance.getPlayers()) {
				if(!p.getCardsInPlay().isEmpty()) {
					priorities.add(p.getCardsInPlay().get(0).getPriority());
				}else {
					priorities.add(-1);
				}
			}
			// loop for robot to move according to the n'th card of each player
			for(int playerIndex=0; playerIndex<numberOfPlayers(); playerIndex++) {
				// loop for getting max priority and index of max priority
				int maxPriority = 0;
				int indexMaxPriority = 0;
				for (int i=0; i < priorities.size(); i++) {
					if (priorities.get(i) > maxPriority) {
						maxPriority = priorities.get(i);
						indexMaxPriority = i;
					}
				}
				// use of card (robot behaves accordingly)
				Player currPlayer = instance.getPlayers().get(indexMaxPriority);
				
				if (currPlayer.getRobot().isDead()) {
					currPlayer.getCardsInPlay().clear();
					currPlayer.getRobot().setIsDead(false);
				} else {
					if(priorities.get(indexMaxPriority)>0 && !currPlayer.getCardsInPlay().isEmpty()) {
						currPlayer.use(currPlayer.getCardsInPlay().remove(0));
						if(!testing) {observer.notifyRobotMove();}
						priorities.set(indexMaxPriority, 0);
					}
				}
				//check if player won
			}
		}
		for(int i=0;i<players.size();i++) {
			Position p= players.get(i).getRobot().getPosition();
			if (board.getTile(p.getX(), p.getY() ) instanceof EndPosition) {
				instance.hasWon(i);

			}
	    }
	}
	
	/**
	 * Checking if the end-position has been reached. If so then a winner has been found
	 * @param playerIndex (int)
	 */
	public void hasWon(int playerIndex) {
		Position positionRobot = instance.getPlayers().get(playerIndex).getRobot().getPosition();
		if(board.getTile(positionRobot.getX(), positionRobot.getY()) instanceof EndPosition) {
		if(!testing) {observer.notifyWin(playerIndex);}
		winner = players.get(playerIndex);
		}
	}

	/**
	 * resets the game in case we're playing a new game
	 */
	public static void endGame() {
		instance=null;
	}

	/**
	 * Gets the number of players
	 * @return (int)
	 */
	public int numberOfPlayers() {
		return players.size();
	}
	
	/**
	 * Getter method to get all of the players
	 * @return (ArrayList<Player>)
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	/**
	 * Getter method to get the board
	 * @return (Board)
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Sets the board 
	 */
	public void setBoard() {
		this.board = new Board(12,12,instance.numberOfPlayers(), null);
	}
	
	/**
	 * Getter method to get the winner
	 * @return (Player)
	 */
	public Player getWinner() {
		return winner;
	}
}