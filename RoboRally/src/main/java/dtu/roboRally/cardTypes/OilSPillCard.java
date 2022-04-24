package dtu.roboRally.cardTypes;


import dtu.roboRally.Card;
import dtu.roboRally.Game;
import dtu.roboRally.Position;
import dtu.roboRally.Robot;
public class OilSPillCard extends Card{
		public OilSPillCard() {
		super(3);
	}

		@Override
		public Position useCard(Robot robot, Position position) {
		
			
			Position oldPosition=new Position(position.getX(),position.getY(),position.getOrientation());
			int x=position.getX();
			int y=position.getY();

			switch (position.getOrientation()) {
			case 0: y-=1;break;
			case 1: x-=1;break;
			case 2: y+=1;break;
			case 3: x+=1;break;
			}

			if (Game.getInstance().getBoard().getTile(x, y).getLabel()=="0 ") {
				Game.getInstance().getBoard().setOilTile(x, y);
				}
			return oldPosition;
		}
		
}


