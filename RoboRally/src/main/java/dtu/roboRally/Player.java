package dtu.roboRally;

import java.util.ArrayList;

import dtu.roboRally.cardTypes.ShieldCard;

/**
 * Class for player actions such as drawing cards, using cards and setting the player's robot on the board
 */
public class Player {
	private ArrayList<Card> hand = new ArrayList<>();
	private ArrayList<Card> cardsInPlay = new ArrayList<>();
	private Robot robot;
	
	/**
	 * Drawing the nine initial cards
	 */
	public void drawHand() {
		hand.clear();
		for (int i = 0; i<9;i++) {
			hand.add(Card.random());
		}
	}
	
	/**
	 * Showing the drawn cards
	 */
	public void showHand() {
		for (int i = 0; i < hand.size(); i++) {
			 System.out.print("["+i+"] ");
	         System.out.println(hand.get(i)); 		
	    }
	}

	/**
	 * Playing the chosen cards so the robot behaves accordingly
	 * @param card (Card)
	 */
	public void use(Card card) {
		Board board = Game.getInstance().getBoard();
		if(card instanceof ShieldCard) {
			robot.setShielded(true);
		}else{
			Position newPosition = card.useCard(robot.getPosition());
			robot.move(board, newPosition);
		}
	}
	
	/**
	 * Checking for starting-positions for the robot in order for the player to choose a starting-position
	 * @param orientation (int)
	 * @param x (int)
	 * @param y (int)
	 */
	public void initializeRobot(int orientation, int x, int y) {
		Board board = Game.getInstance().getBoard();
		if (board.getTile(x, y).getLabel().equals("S ")) {
			robot = new Robot(orientation, x, y);
		}
	}
	
	/**
	 * Getter method for the robot being used by the player
	 * @return (Robot) 
	 */
	public Robot getRobot() {
		return robot;
	}
	
	/**
	 * Sets the robot on the board with a position and an orientation 
	 * @param o orientation (int)
	 * @param x (int)
	 * @param y	(int
	 */
	public void setRobot(int o, int x, int y) {
		robot = new Robot(o,x,y);
	}
	
	/**
	 * Getter method to get the hand of drawn cards
	 * @return (ArrayList<Card>) 
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	/**
	 * Getter method to get the player's chosen cards
	 * @return (ArrayList<Card>) 
	 */
	public ArrayList<Card> getCardsInPlay() {
		return cardsInPlay;
	}
}