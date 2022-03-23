import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsDefinition {
	
	Card c= Card.MOVE_BACKWARDS_ONE;
	ArrayList<Card> hand = new ArrayList<Card>();
	ArrayList<Card> inPlay = new ArrayList<Card>();
	Player a=new Player();
	
	@Given("it is players turn")
	public void player_draws_a_card() {
	    Card c= Card.MOVE_BACKWARDS_ONE;
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
}