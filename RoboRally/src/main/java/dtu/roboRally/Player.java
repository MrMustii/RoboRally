package dtu.roboRally;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	private ArrayList<Card> hand=new ArrayList();
	private ArrayList<Card> cardsInPlay=new ArrayList();
	private boolean myTurn=true;
	private Robot robot;
	
	public void drawHand(){
		
		hand.removeAll(hand);
		for (int i =0; i<9;i++) {
			
			switch (Card.random()) {
			case "ROTATE_CLOCKWISE":
				hand.add(Card.ROTATE_CLOCKWISE);
				break;
			case "ROTATE_ANTI_CLOCKWISE":
				hand.add(Card.ROTATE_ANTI_CLOCKWISE);
				break;
			case "MOVE_FORWARD_ONE":
				hand.add(Card.MOVE_FORWARD_ONE);
				break;
			case "MOVE_FORWARD_TWO":
				hand.add(Card.MOVE_FORWARD_TWO);
				break;
			case "MOVE_FORWARD_THREE":
				hand.add(Card.MOVE_FORWARD_THREE);
				break;
			case "MOVE_BACKWARDS_ONE":
				hand.add(Card.MOVE_BACKWARDS_ONE);
				break;
			case "U_TURN":
				hand.add(Card.U_TURN);
				break;
			}
			
	}
	}
	public void showHand() {
		for (int i = 0; i < hand.size();i++) 
	      {
			  System.out.print("["+i+"] ");
	          System.out.println(hand.get(i)); 		
	      }
	}
	
	public void pickCards() {
		for(int i =0;i<5;i++) {
		showHand();
		System.out.println("pick a card number");
		Scanner obj=new Scanner(System.in);
		int index=obj.nextInt();
		cardsInPlay.add(hand.get(index));
		hand.remove(index);
		}
	}
	public ArrayList getHand() {
		return hand;
	}
	
	public ArrayList getCardsInPlay() {
		return cardsInPlay;
	}
	public void playerNumber() {
		
	}
	public boolean isMyturn() {
		return myTurn;
	}
	
	public void use(Card card) {
		
		switch(card) {
		case ROTATE_CLOCKWISE:
			robot.rotateClockwise();
			break;
		case ROTATE_ANTI_CLOCKWISE:
			robot.rotateCounterClockwise();
			break;
		case MOVE_FORWARD_ONE:
			robot.moveForward();
			break;
		case MOVE_FORWARD_TWO:
			robot.moveForward();
			robot.moveForward(); // TODO: implement interact with obstacle
			break;
		case MOVE_FORWARD_THREE:
			robot.moveForward();
			robot.moveForward();
			robot.moveForward();
			break;
		case MOVE_BACKWARDS_ONE:
			robot.moveBackward();
			break;
		case U_TURN:
			robot.rotateClockwise();
			robot.rotateClockwise();
			break;
		}
		
	}
	
	public void createRobot(int x, int y, Orientation orientation) {
		robot = new Robot(orientation, x, y);
	}
	
	public Robot getRobot() {
		return robot;
	}
	
}