package dtu.roboRally.cardTypes;

import dtu.roboRally.Card;
import dtu.roboRally.Position;
import dtu.roboRally.Robot;

/**
 * A card that activates a robot-shield. Then the robot can block one damage
 */
public class ShieldCard extends Card {
	
	public ShieldCard() {
		super(1);
	}
	
	@Override
	public Position useCard(Robot robot, Position position) {
		robot.setShielded(true);
		return position.clone();
	}

}
