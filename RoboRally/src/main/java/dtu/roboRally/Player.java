package dtu.roboRally;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	private ArrayList<Card> hand = new ArrayList<>();
	private ArrayList<Card> cardsInPlay = new ArrayList<>();
	private Robot robot;
	
	public void drawHand() {
		hand.removeAll(hand);
		
		for (int i = 0; i<9;i++) {
			hand.add(Card.random());
		}
	}
	
	public void showHand() {
		for (int i = 0; i < hand.size(); i++) {
			 System.out.print("["+i+"] ");
	         System.out.println(hand.get(i)); 		
	    }
	}
	
	public void pickCardsInPlay() {
//		for (int i = 0; i<5; i++) {
//			showHand();
//			System.out.println("pick a card number");
//			Scanner obj = new Scanner(System.in);
//			int index = obj.nextInt();
//			cardsInPlay.add(hand.get(index));
//			hand.remove(index);
//		}
//		myTurn = false;
	}
	
	
	public void use(Card card) {
		Board board = Game.getInstance().getBoard();
		Position newPosition = card.useCard(robot.getPosition());
		robot.move(board, newPosition);
	}
	
	public boolean initializeRobot(int orientation, int x, int y) {
		Board board = Game.getInstance().getBoard();
		if (board.getTile(x, y).getLabel()=="S ") {
			robot = new Robot(orientation, x, y);
			return true;
		} else {
			return false;
		}
	}
	
	public Robot getRobot() {
		return robot;
	}
	
	public void setRobot(int o, int x, int y) {
		robot = new Robot(o,x,y);
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public ArrayList<Card> getCardsInPlay() {
		return cardsInPlay;
	}

}