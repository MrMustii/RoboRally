package dtu.roboRally.cardTypes;

import dtu.roboRally.Card;
import dtu.roboRally.Position;
import dtu.roboRally.Robot;

/**
 * A card that makes a robot perform a u-turn
 */
public class UTurnCard extends Card {
	public UTurnCard() {
		super();
	}
	
	public Position useCard(Position position) {
		return new Position(position.getX(), position.getY(), 
				(position.getOrientation()+2)%4);
	}
}
