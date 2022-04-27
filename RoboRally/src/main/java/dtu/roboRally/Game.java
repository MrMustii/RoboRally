package dtu.roboRally;


import java.util.ArrayList;

import dtu.roboRally.controller.RoboRallyController;

public class Game {

	private ArrayList<Player> players = new ArrayList<>();
	private static Game instance;
	private Board board;
	private Player winner;
	private RoboRallyController observer;
	
	private Game(RoboRallyController observer, int numberOfPlayers) {
		board = new Board(9, 12, numberOfPlayers); //maybe change size
		for(int i = 0; i<numberOfPlayers; i++) {
			players.add(new Player());
		}
		this.observer = observer;
	}
	
	public static Game getInstance(RoboRallyController observer, int nbPlayers) {
		if(instance == null) {
			instance = new Game(observer, nbPlayers);
		}
		//setNumberPlayers(nbPlayers);
		return instance;
	}
	
	public static Game getInstance() {
		if(instance==null) {
			System.out.println("Game not initialised; call getInstance(int)");
		}
		//setNumberPlayers(nbPlayers);
		return instance;
	}
	
	public void phase1() {
		for(Player p:instance.getPlayers()) {
			p.drawHand();
			p.showHand();
			p.pickCardsInPlay();
		}
		//respawn at the start of phase 2
//		for(Player p:instance.getPlayers()) {
//			if (p.getRobot().isDead()){
//				p.getRobot().respawn();
//				}
//		}
	}
	
	public void phase2() {		
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
//					priorities.set(indexMaxPriority, 0);
					currPlayer.getRobot().respawn();
				} else {
					if(priorities.get(indexMaxPriority)>0 && !currPlayer.getCardsInPlay().isEmpty()) {
						currPlayer.use(currPlayer.getCardsInPlay().remove(0));
						observer.notifyRobotMove();
						priorities.set(indexMaxPriority, 0);
					}
				}
				//check if player won
				instance.hasWon(indexMaxPriority);

			}
		}
	}
	
	public void hasWon(int playerIndex) {
		Position positionRobot = instance.getPlayers().get(playerIndex).getRobot().getPosition();
		if(board.getTile(positionRobot.getX(), positionRobot.getY()) instanceof EndPosition) {
		observer.notifyWin(playerIndex);
		winner = players.get(playerIndex);
		
		//players.clear();
		//players.add(winner);
		
		}
	}
	
	public int numberOfPlayers() {
		return players.size();
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	//getter for board
	public Board getBoard() {
		return board;
	}
	public void setBoard() {
		this.board = new Board(12,12,instance.numberOfPlayers());
	}
	public Player getWinner() {
		return winner;
	}
	
}