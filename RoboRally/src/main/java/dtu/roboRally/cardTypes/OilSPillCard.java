package dtu.roboRally.cardTypes;

import dtu.roboRally.Card;
import dtu.roboRally.Game;
import dtu.roboRally.Position;
import dtu.roboRally.Robot;

/**
 * A card that makes a robot drop one oil stain in a free neighbouring tile
 */
public class OilSPillCard extends Card{
		public OilSPillCard() {
		super(3);
	}

		@Override
		public Position useCard(Robot robot, Position position) {

			int x=position.getX();
			int y=position.getY();

			if (Game.getInstance().getBoard().getTile(x, y).getLabel().equals("0 ")) {
				Game.getInstance().getBoard().setOilTile(x, y);
				}
			return position;
		}
		
}


