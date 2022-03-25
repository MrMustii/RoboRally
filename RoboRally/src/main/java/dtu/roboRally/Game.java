package dtu.roboRally;

import java.util.ArrayList;

public class Game {

	ArrayList<Player> players=new ArrayList();
	
	
	
	public Game (int i) {
		if (i>=2) {
			players.add(new Player());
			players.add(new Player());
		if (i==3) {
			players.add(new Player());
		}else if(i==4){
			players.add(new Player());
			players.add(new Player());
		}
		}
	}
	
	public int numberOfPlayers() {
		return players.size();
	}
	
}
