package dtu.roboRally.cardTypes;

import dtu.roboRally.Card;
import dtu.roboRally.Position;
import dtu.roboRally.Robot;

public class UTurnCard extends Card {
	public UTurnCard() {
		super(3);
	}
	
	public Position useCard(Robot robot, Position position) {
		return new Position(position.getX(), position.getY(), 
				(position.getOrientation()+2)%4);
	}
}
