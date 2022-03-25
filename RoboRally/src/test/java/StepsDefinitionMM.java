import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import dtu.roboRally.Card;
import dtu.roboRally.Game;
import dtu.roboRally.Player;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsDefinitionMM {
	
	Card c = Card.MOVE_BACKWARDS_ONE;
	ArrayList<Card> hand = new ArrayList<Card>();
	ArrayList<Card> inPlay = new ArrayList<Card>();
	Player a=new Player();
	Game newgame = Game.getInstance(3);
	
	
	@Given("it is players turn")
	public void player_draws_a_card() {
	    a.setTurn(true);
	}
	@When("player draws a card")
	public void player_draws_a_card1() {
	    hand.add(c);
	}
	@Then("determain that contant of the card")
	public void determain_that_contant_of_the_card() {
		assertEquals(3,hand.get(0).getInitiative());
		assertEquals(hand.get(0),c);
	}
	


	
	///////////////////////////////////////////////////////////////////////
	@Given("its players turn to draw")
	public void player_wants_to_draw_cards() {
		a.setTurn(true);
		assertEquals(true, a.isMyturn());
		
	    
	}
	@When("players can draw cards")
	public void players_can_draw_cards() {
	    a.drawHand();
	    
	}
	@Then("add them to his hand")
	public void draws_cards() {
		a.drawHand();
			
		assertEquals(9,a.getHand().size());
		 a.showHand();

	}
	//////////////////////////////////
	@Given("has a hand")
	public void has_a_hand() {
		a.drawHand();
	}
	@When("he choses {int} cards to play")
	public void he_choses_cards_to_play(Integer int1) {
	   for (int i=0;i<5;i++) {
		   	a.showHand();
			System.out.println("pick a card number");
			a.getCardsInPlay().add(a.getHand().get(i));
			a.getHand().remove(i);
	   }
	}
	@Then("he has an order of cards to play")
	public void he_has_an_order_of_cards_to_play() {
		assertEquals(5,a.getCardsInPlay().size());
		assertEquals(4,a.getHand().size());
	}
	/////////////////////////
	///Game set up
	//////////////////////////////////
	@Given("the user choses a number of players")
	public void the_user_choses_a_number_of_players() {
	    System.out.println("Chose a number of players");
	    int number = 3;
	}
	@When("creation instances of the number of players")
	public void creation_instances_of_the_number_of_players() {
		Game newgame = Game.getInstance(3);
	}
	@Then("the number of players should be set")
	public void the_number_of_players_should_be_set() {
		assertEquals(3,newgame.numberOfPlayers());
	}
	
	///////////////////////////////////////////
	@Given("that a player ends the turn")
	public void that_a_player_picks_his_cards() {
		((Player) newgame.getPlayers().get(0)).getCardsInPlay().add(c);
		((Player) newgame.getPlayers().get(0)).setTurn(false);
		
		assertEquals(false,((Player) newgame.getPlayers().get(0)).isMyturn());
	}
	@When("the game sets the next players turn")
	public void a_player_picks_his_cards() {
		for (int i=0;i<newgame.numberOfPlayers();i++) {
			if (((Player) newgame.getPlayers().get(i)).getCardsInPlay().isEmpty() ==true) {
				((Player) newgame.getPlayers().get(i)).setTurn(true);
			} else {
				((Player) newgame.getPlayers().get(i)).setTurn(false);
			}
		}
	}
	@Then("the next player can draw cards")
	public void the_next_player_can_draw_cards() {
		for (int i=0;i<newgame.numberOfPlayers();i++) {
			if (((Player) newgame.getPlayers().get(i)).isMyturn()==true) {
				((Player)newgame.getPlayers().get(i)).drawHand();
				assertEquals(9,((Player)newgame.getPlayers().get(i)).getHand().size());
				
				
			}
		}
		
	}
}