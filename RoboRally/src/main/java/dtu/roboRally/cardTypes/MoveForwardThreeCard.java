package dtu.roboRally.cardTypes;

import dtu.roboRally.Card;
import dtu.roboRally.Position;
import dtu.roboRally.Robot;

public class MoveForwardThreeCard extends Card {
	public MoveForwardThreeCard() {
		super(2);
	}
	
	public Position useCard(Robot robot, Position position) {
		switch (position.getOrientation()) {
		case 0: return new Position(position.getX(), position.getY()-3, position.getOrientation());
		case 1: return new Position(position.getX()+3, position.getY(), position.getOrientation());
		case 2: return new Position(position.getX(), position.getY()+3, position.getOrientation());
		case 3: return new Position(position.getX()-3, position.getY(), position.getOrientation());
		}
		return null;
	}
}
