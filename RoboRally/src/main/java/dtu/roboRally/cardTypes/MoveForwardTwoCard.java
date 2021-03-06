package dtu.roboRally.cardTypes;

import dtu.roboRally.Card;
import dtu.roboRally.Position;
import dtu.roboRally.Robot;

/**
 * A card that makes a robot move two tiles forward
 */
public class MoveForwardTwoCard extends Card {
	public MoveForwardTwoCard() {
		super();
	}
	
	public Position useCard(Position position) {
		switch (position.getOrientation()) {
		case 0: return new Position(position.getX(), position.getY()-2, position.getOrientation());
		case 1: return new Position(position.getX()+2, position.getY(), position.getOrientation());
		case 2: return new Position(position.getX(), position.getY()+2, position.getOrientation());
		case 3: return new Position(position.getX()-2, position.getY(), position.getOrientation());
		}
		return null;
	}
}
