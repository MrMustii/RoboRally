import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class StepsDefinition {
	
	Card c= Card.MOVE_BACKWARDS_ONE;
	ArrayList<Card> hand = new ArrayList<Card>();
	ArrayList<Card> inPlay = new ArrayList<Card>();
	
	
	@Given("player draws a card")
	public void player_draws_a_card() {
	    Card c= Card.MOVE_BACKWARDS_ONE;
	}

	@Then("determain that contant of the card")
	public void determain_that_contant_of_the_card() {
		assertEquals(3,c.getInitiative());
		assertEquals(Card.MOVE_BACKWARDS_ONE,c);
	}
	
	@Then("adds it to the hand")
	public void adds_it_to_the_hand() {
	    hand.add(c);
	    hand.add(c);
	    hand.add(c);
	    for (int i = 0; i < hand.size();i++) 
	      { 		      
	          System.out.println(hand.get(i)); 		
	      }
}
	
	///////////////////////////////////////////////////////////////////////
	@Given("player wants to draw random cards")
	public void player_wants_to_draw_cards() {
		boolean playerTurn=true;
	    
	}
	@Then("the player will discard his hand")
	public void the_player_will_discard_his_hand() {
		hand.removeAll(hand);
		assertEquals(hand.size(),0);
	}
	@Then("draws cards")
	public void draws_cards() {
		for (int i =0; i<9;i++) {
			double chance =Math.random();
			if (chance>=0 && chance <0.1) {
				hand.add(Card.ROTATE_CLOCKWISE);
			}else if(chance>=0.1 && chance <0.2) {
				hand.add(Card.ROTATE_ANTI_CLOCKWISE);
			}else if(chance>=0.2 && chance <0.5) {
				hand.add(Card.MOVE_FORWARD_ONE);
			}else if(chance>=0.5 && chance <0.65) {
				hand.add(Card.MOVE_FORWARD_TWO);;
			}else if(chance>=0.65 && chance <0.70) {
				hand.add(Card.MOVE_FORWARD_THREE);
			}else if(chance>=0.70 && chance <0.85) {
				hand.add(Card.MOVE_BACKWARDS_ONE);
			}else if(chance>=0.85 && chance <=1) {
				hand.add(Card.U_TURN);
			}
			}
		assertEquals(9,hand.size());
		  for (int i = 0; i < hand.size();i++) 
	      {
			  System.out.print("["+i+"] ");
	          System.out.println(hand.get(i)); 		
	      }
	}
	//////////////////////////////////
	
}