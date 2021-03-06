package dtu.roboRally;

import dtu.roboRally.cardTypes.MoveBackwardCard;
import dtu.roboRally.cardTypes.MoveForwardOneCard;
import dtu.roboRally.cardTypes.MoveForwardThreeCard;
import dtu.roboRally.cardTypes.MoveForwardTwoCard;
import dtu.roboRally.cardTypes.RotateClockwiseCard;
import dtu.roboRally.cardTypes.RotateCounterClockwiseCard;
import dtu.roboRally.cardTypes.ShieldCard;
import dtu.roboRally.cardTypes.UTurnCard;

/**
 * Abstract class for the cards
 * This includes giving cards a priority and a probability to each card
 */
public abstract class Card {
	
	private final int priority;
	
	/**
	 * sets random priority for a card
	 */
	public Card() {
		priority = (int)Math.ceil(Math.random()*100);
	}
	
	
	/**
	 * @param position (Position)
	 * @return The defined action of a card
	 */
	public abstract Position useCard(Position position);
	
	/**
	 * @return a probability for each card to make the dealing of cards random
	 */
	public static Card random() {
		
		double chance =Math.random();
		
		if (chance>=0 && chance <0.1) {
			return new RotateClockwiseCard();
		} else if(chance>=0.1 && chance <0.2) {
			return new RotateCounterClockwiseCard();
		} else if(chance>=0.2 && chance <0.45) {
			return new MoveForwardOneCard();
		} else if(chance>=0.45 && chance <0.60) {
			return new MoveForwardTwoCard();
		} else if(chance>=0.60 && chance <0.65) {
			return new MoveForwardThreeCard();
		} else if(chance>=0.65 && chance <0.80) {
			return new MoveBackwardCard();
		} else if(chance>=0.80 && chance <0.95) {
			return new UTurnCard();
		}else if(chance>=0.95&& chance <=1) {
			return new ShieldCard();
		}
		return null;
	}
	
	/**
	 * Getter method to get the priority
	 * @return (int) 
	 */
	public int getPriority() {
		return priority;
	}
}


