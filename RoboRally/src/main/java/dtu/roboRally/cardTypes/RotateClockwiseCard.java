package dtu.roboRally.cardTypes;

import dtu.roboRally.Card;
import dtu.roboRally.Position;
import dtu.roboRally.Robot;

/**
 * A card that rotates a robot clockwise from its current orientation
 */
public class RotateClockwiseCard extends Card {
	public RotateClockwiseCard() {
		super(2);
	}
	
	public Position useCard(Position position) {
		return new Position(position.getX(), position.getY(), 
							(position.getOrientation()+1)%4);
	}
}
