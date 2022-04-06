package dtu.roboRally;

public enum Card{
	ROTATE_CLOCKWISE(2),
	ROTATE_ANTI_CLOCKWISE(2),
	MOVE_FORWARD_ONE(4),
	MOVE_FORWARD_TWO(3),
	MOVE_FORWARD_THREE(1),
	MOVE_BACKWARDS_ONE(3),
	U_TURN(3);
	

	private final int initiative;

	Card(int initiative) {
		this.initiative=initiative;
	}
	public int getInitiative() {
		return initiative;
}
	public static String random() {
		double chance =Math.random();
		if (chance>=0 && chance <0.1) {
		return "ROTATE_CLOCKWISE";
	}else if(chance>=0.1 && chance <0.2) {
		return "ROTATE_ANTI_CLOCKWISE";
	}else if(chance>=0.2 && chance <0.5) {
		return "MOVE_FORWARD_ONE";
	}else if(chance>=0.5 && chance <0.65) {
		return "MOVE_FORWARD_TWO";
	}else if(chance>=0.65 && chance <0.70) {
		return "MOVE_FORWARD_THREE";
	}else if(chance>=0.70 && chance <0.85) {
		return "MOVE_BACKWARDS_ONE";
	}else if(chance>=0.85 && chance <=1) {
		return "U_TURN";
	}
		return null;
}
}





