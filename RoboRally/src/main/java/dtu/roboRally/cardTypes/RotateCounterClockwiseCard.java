package dtu.roboRally.cardTypes;

import dtu.roboRally.Card;
import dtu.roboRally.Position;

public class RotateCounterClockwiseCard extends Card {
	public RotateCounterClockwiseCard() {
		super(2);
	}
	
	public Position useCard(Position position) {
		return new Position(position.getX(), position.getY(), 
				(position.getOrientation()+3)%4);
	}
}
