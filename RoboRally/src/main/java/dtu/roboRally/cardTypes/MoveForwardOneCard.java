package dtu.roboRally.cardTypes;

import dtu.roboRally.Card;
import dtu.roboRally.Position;
import dtu.roboRally.Robot;

/**
 * A card that makes a robot move one tile forward
 */
public class MoveForwardOneCard extends Card {
	public MoveForwardOneCard() {
		super();
	}
	
	public Position useCard(Position position) {
		switch (position.getOrientation()) {
		case 0: return new Position(position.getX(), position.getY()-1, position.getOrientation());
		case 1: return new Position(position.getX()+1, position.getY(), position.getOrientation());
		case 2: return new Position(position.getX(), position.getY()+1, position.getOrientation());
		case 3: return new Position(position.getX()-1, position.getY(), position.getOrientation());
		}
		return null;
	}
}
