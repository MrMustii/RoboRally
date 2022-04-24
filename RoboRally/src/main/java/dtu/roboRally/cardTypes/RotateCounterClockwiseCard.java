package dtu.roboRally.cardTypes;

import dtu.roboRally.Card;
import dtu.roboRally.Position;
import dtu.roboRally.Robot;

public class RotateCounterClockwiseCard extends Card {
	public RotateCounterClockwiseCard() {
		super(2);
	}
	
	public Position useCard(Robot robot, Position position) {
		return new Position(position.getX(), position.getY(), 
				(position.getOrientation()+3)%4);
	}
}
