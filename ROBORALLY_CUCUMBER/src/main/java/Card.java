enum Card{
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
}




