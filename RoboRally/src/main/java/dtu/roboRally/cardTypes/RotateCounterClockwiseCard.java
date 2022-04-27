package dtu.roboRally.cardTypes;

import dtu.roboRally.Card;
import dtu.roboRally.Position;
import dtu.roboRally.Robot;

/**
 * A card that rotates a robot counterclockwise from its current orientation
 */
public class RotateCounterClockwiseCard extends Card {
	public RotateCounterClockwiseCard() {
		super(2);
	}
	
	public Position useCard(Robot robot, Position position) {
		return new Position(position.getX(), position.getY(), 
				(position.getOrientation()+3)%4);
	}
}
