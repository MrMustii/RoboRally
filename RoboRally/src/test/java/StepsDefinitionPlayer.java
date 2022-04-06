import static org.junit.Assert.assertEquals;

import dtu.roboRally.Card;
import dtu.roboRally.Player;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsDefinitionPlayer {
	Player player;
	@Given("a player with a hand")
	public void a_player_with_a_hand() {
		 player =new Player();
	}
	@When("a player draws a card")
	public void a_player_draws_a_card() {
	    player.getHand().add(Card.random());
	}
	@Then("he has a card")
	public void he_has_a_card() {
	    assertEquals(1, player.getHand().size());
	}
//////////////////////
	
	@Given("a player with a hand of no cards")
	public void a_player_with_a_hand_of_no_cards() {
	 player =new Player();
	 assertEquals(true, player.getHand().isEmpty());
	}
	@When("a player draws a hand")
	public void a_player_draws_a_hand() {
	    player.drawHand();
	}
	@Then("he has {int} cards")
	public void he_has_cards(int int1) {
	    assertEquals(int1, player.getHand().size());
	}
	////
	@When("picks the first cards to play")
	public void picks_the_first_cards_to_play() {
		player.getCardsInPlay().add(player.getHand().get(1));
    	player.getHand().remove(1);
	}
	@Then("he has {int} card in play")
	public void he_has_card_in_play(int cards) {
	    assertEquals(cards, player.getCardsInPlay().size());
	}
	////////// 
	@Given("a player with a hand of cards")
	public void a_player_with_a_hand_of_cards() {
		 player =new Player();
		 player.drawHand();
	}
	@When("a player picks the first {int} cards in his hand to play")
	public void a_player_picks_the_first_cards_in_his_hand_to_play(int max) {
	    for (int i=0;i<max;i++) {
	    	player.getCardsInPlay().add(player.getHand().get(i));
	    	player.getHand().remove(i);
	    }
	}
	@Then("a player has {int} cards picked to play")		
	public void a_player_has_cards_picked_to_play(int cards) {
	    assertEquals(cards, player.getCardsInPlay().size());
	    assertEquals(4, player.getHand().size());
	}


///////////this will change with GUI addtion

@Then("the game prints his hand")
public void the_game_prints_his_hand() {
	player.showHand();
}
}