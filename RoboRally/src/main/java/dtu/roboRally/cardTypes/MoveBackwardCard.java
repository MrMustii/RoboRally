package dtu.roboRally.cardTypes;

import dtu.roboRally.Card;
import dtu.roboRally.Position;

public class MoveBackwardCard extends Card {
	public MoveBackwardCard() {
		super(3);
	}
	
	public Position useCard(Position position) {
		switch (position.getOrientation()) {
		case 0: return new Position(position.getX(), position.getY()-1, position.getOrientation());
		case 1: return new Position(position.getX()-1, position.getY(), position.getOrientation());
		case 2: return new Position(position.getX(), position.getY()+1, position.getOrientation());
		case 3: return new Position(position.getX()+1, position.getY(), position.getOrientation());
		}
		return null;
	}
}