package dtu.roboRally;

import java.util.ArrayList;

public class Game {

	private ArrayList<Player> players=new ArrayList();
	private static Game instance;
	
	private Game () {}
	private Game (int i) {
		if (i>=2) {
			players.add(new Player());
			players.add(new Player());
			if (i==3) {
				players.add(new Player());
			} else if (i==4) {
				players.add(new Player());
				players.add(new Player());
			}
		}
	}
	
	public static Game getInstance(int i) {
		if (instance==null) {
			instance=new Game(3);
		}
		return instance;
	}
	
	public int numberOfPlayers() {
		return players.size();
	}
	
	public ArrayList getPlayers() {
		return players;
	}

//	public void nextTurn(Game game) {
//			for (int i=0;i<instance.numberOfPlayers();i++) {
//				if (((Player) instance.getPlayers().get(i)).getCardsInPlay().isEmpty() ==true) {
//					((Player) instance.getPlayers().get(i)).setTurn(true);
//				} else {
//					((Player) instance.getPlayers().get(i)).setTurn(false);
//				}
//			}		
//	}
//	public void drawPhase() {
//		for (int i=0;i<instance.numberOfPlayers();i++) {
//			if (((Player) instance.getPlayers().get(i)).isMyturn()==true) {
//				((Player)instance.getPlayers().get(i)).drawHand();
//	}
//}
//}
//	public void pickCardPhase() {
//		for (int i=0;i<instance.numberOfPlayers();i++) {
//			((Player)instance.getPlayers().get(i)).pickCards();;
//		}
//	}
	
//	public void movePhase(){
//		int order=0;
//		int cardsPlayed=0;
//		
//		
//		
//		for(int i=0;i<5;i++) {
//				for (int p=0;p<instance.numberOfPlayers();p++) {
//					if(((Card)(((Player)instance.getPlayers().get(p)).getCardsInPlay().get(i))).getInitiative()==order 
//							&&((Player)instance.getPlayers().get(p)).getCardsInPlay().size()==5-cardsPlayed) {
//						
//						
//						((Player)instance.getPlayers().get(p)).getCardsInPlay().get(i).use(((Card)((Player)instance.getPlayers().get(p)).getCardsInPlay().get(i)));
//						((Player)instance.getPlayers().get(p)).getCardsInPlay().remove(i)));
//					}
//				}
					
			//turn ==1
			//itrate for players get intitve 
			//if turn ==intitve
			//use card
			//else
			//pass
			//after for loop turn++
			

	}
	//move phase