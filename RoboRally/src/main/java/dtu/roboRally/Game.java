package dtu.roboRally;


import java.util.ArrayList;

public class Game {

	private ArrayList<Player> players=new ArrayList();
	private static Game instance;
	
	
	private Game () {}
	private Game (int i) {
		for(int j=0;j<i;j++) {
			players.add(new Player());
		}
		
	}
	
	public static Game getInstance(int i) {
		if (instance==null) {
			instance=new Game(i);
		}
		return instance;
	}
	
	
	
	public int numberOfPlayers() {
		return players.size();
	}
	public ArrayList getPlayers() {
		return players;
	}

	public void nextTurn(Game game) {
			for (int i=0;i<instance.numberOfPlayers();i++) {
				if (((Player) instance.getPlayers().get(i)).getCardsInPlay().isEmpty() ==true) {
					((Player) instance.getPlayers().get(i)).setTurn(true);
				} else {
					((Player) instance.getPlayers().get(i)).setTurn(false);
				}
			}		
	}
	public void drawPhase() {
		for (int i=0;i<instance.numberOfPlayers();i++) {
			if (((Player) instance.getPlayers().get(i)).isMyturn()==true) {
				((Player)instance.getPlayers().get(i)).drawHand();
	}
}
}
}