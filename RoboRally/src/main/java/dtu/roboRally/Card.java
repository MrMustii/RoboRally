package dtu.roboRally;

import dtu.roboRally.cardTypes.MoveBackwardCard;
import dtu.roboRally.cardTypes.MoveForwardOneCard;
import dtu.roboRally.cardTypes.MoveForwardThreeCard;
import dtu.roboRally.cardTypes.MoveForwardTwoCard;
import dtu.roboRally.cardTypes.OilSPillCard;
import dtu.roboRally.cardTypes.RotateClockwiseCard;
import dtu.roboRally.cardTypes.RotateCounterClockwiseCard;
import dtu.roboRally.cardTypes.ShieldCard;
import dtu.roboRally.cardTypes.UTurnCard;

public abstract class Card {
	
	private final int priority;
	
	public Card(int priority) {
		this.priority = priority;
	}
	
	public abstract Position useCard(Robot robot, Position position);
	
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
		}else if(chance>=0.95 && chance <=1) {
			return new ShieldCard();
		}
		return null;
	}
	
	public int getPriority() {
		return priority;
	}
}


