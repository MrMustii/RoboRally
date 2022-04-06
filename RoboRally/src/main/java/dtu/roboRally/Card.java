package dtu.roboRally;

public abstract class Card {
	
	private final int priority;
	
	public int getPriority() {
		return priority;
}
	
	public Card(int priority) {
		this.priority = priority;
	}
	



	
	
	public static Card random() {
		double chance =Math.random();
		if (chance>=0 && chance <0.1) {
		return new RotateClockwiseCard();
	}else if(chance>=0.1 && chance <0.2) {
		return new RotateCounterClockwiseCard();
	}else if(chance>=0.2 && chance <0.5) {
		return new MoveForwardOneCard();
	}else if(chance>=0.5 && chance <0.65) {
		return new MoveForwardTwoCard();
	}else if(chance>=0.65 && chance <0.70) {
		return new MoveForwardThreeCard();
	}else if(chance>=0.70 && chance <0.85) {
		return new MoveBackwardCard();
	}else if(chance>=0.85 && chance <=1) {
		return new UTurnCard();
	
	}
		return null;
}
}
class RotateClockwiseCard extends Card {
	
	public RotateClockwiseCard() {
		super(2);
	}
	
	public Position useCard(Position position) {
		return new Position(position.getX(), position.getY(), 
							(position.getOrientation()+1)%4);
	}
}

class RotateCounterClockwiseCard extends Card {
	
	public RotateCounterClockwiseCard() {
		super(2);
	}
	
	public Position useCard(Position position) {
		return new Position(position.getX(), position.getY(), 
				(position.getOrientation()-1)%4);
	}
}

class UTurnCard extends Card {
	
	public UTurnCard() {
		super(3);
	}
	
	public Position useCard(Position position) {
		return new Position(position.getX(), position.getY(), 
				(position.getOrientation()+2)%4);
	}
}

class MoveForwardOneCard extends Card {
	
	public MoveForwardOneCard() {
		super(4);
	}
	
	public Position useCard(Position position) {
		switch (position.getOrientation()) {
		case 0: return new Position(position.getY()+1, position.getX(), position.getOrientation());
		case 1: return new Position(position.getY(), position.getX()+1, position.getOrientation());
		case 2: return new Position(position.getY()-1, position.getX(), position.getOrientation());
		case 3: return new Position(position.getY(), position.getX()-1, position.getOrientation());
		}
		return null;
	}
}

class MoveForwardTwoCard extends Card {
	
	public MoveForwardTwoCard() {
		super(3);
	}
	
	public Position useCard(Position position) {
		switch (position.getOrientation()) {
		case 0: return new Position(position.getY()+2, position.getX(), position.getOrientation());
		case 1: return new Position(position.getY(), position.getX()+2, position.getOrientation());
		case 2: return new Position(position.getY()-2, position.getX(), position.getOrientation());
		case 3: return new Position(position.getY(), position.getX()-2, position.getOrientation());
		}
		return null;
	}
}

class MoveForwardThreeCard extends Card {
	
	public MoveForwardThreeCard() {
		super(2);
	}
	
	public Position useCard(Position position) {
		switch (position.getOrientation()) {
		case 0: return new Position(position.getY()+3, position.getX(), position.getOrientation());
		case 1: return new Position(position.getY(), position.getX()+3, position.getOrientation());
		case 2: return new Position(position.getY()-3, position.getX(), position.getOrientation());
		case 3: return new Position(position.getY(), position.getX()-3, position.getOrientation());
		}
		return null;
	}
}

class MoveBackwardCard extends Card {
	
	public MoveBackwardCard() {
		super(3);
	}
	
	public Position useCard(Position position) {
		switch (position.getOrientation()) {
		case 0: return new Position(position.getY()-1, position.getX(), position.getOrientation());
		case 1: return new Position(position.getY(), position.getX()-1, position.getOrientation());
		case 2: return new Position(position.getY()+1, position.getX(), position.getOrientation());
		case 3: return new Position(position.getY(), position.getX()+1, position.getOrientation());
		}
		return null;
	}
}




