package dtu.roboRally.cardTypes;

import dtu.roboRally.Card;
import dtu.roboRally.Position;
import dtu.roboRally.Robot;

/**
 * A card that activates a robot-shield. Then the robot can block one damage
 */
public class ShieldCard extends Card {
	
	public ShieldCard() {
		super();
	}
	
	@Override
	public Position useCard(Position position) {
		//robot.setShielded(true);
		return position.clone();
	}

}
