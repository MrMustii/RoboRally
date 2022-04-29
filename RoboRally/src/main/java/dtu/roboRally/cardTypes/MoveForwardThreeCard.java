package dtu.roboRally.cardTypes;

import dtu.roboRally.Card;
import dtu.roboRally.Position;
import dtu.roboRally.Robot;

/**
 * A card that makes a robot move three tiles forward
 */
public class MoveForwardThreeCard extends Card {
	public MoveForwardThreeCard() {
		super(2);
	}
	
	public Position useCard(Position position) {
		switch (position.getOrientation()) {
		case 0: return new Position(position.getX(), position.getY()-3, position.getOrientation());
		case 1: return new Position(position.getX()+3, position.getY(), position.getOrientation());
		case 2: return new Position(position.getX(), position.getY()+3, position.getOrientation());
		case 3: return new Position(position.getX()-3, position.getY(), position.getOrientation());
		}
		return null;
	}
}
