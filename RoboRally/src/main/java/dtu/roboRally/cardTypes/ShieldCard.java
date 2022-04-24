package dtu.roboRally.cardTypes;

import dtu.roboRally.Card;
import dtu.roboRally.Position;
import dtu.roboRally.Robot;

public class ShieldCard extends Card {
	
	public ShieldCard() {
		super(0);
	}
	
	@Override
	public Position useCard(Robot robot, Position position) {
		robot.setShielded(true);
		return position;
	}

}
