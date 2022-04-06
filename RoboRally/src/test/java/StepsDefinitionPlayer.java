import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import dtu.roboRally.Card;
import dtu.roboRally.Player;
import dtu.roboRally.cardTypes.MoveBackwardCard;
import dtu.roboRally.cardTypes.MoveForwardOneCard;
import dtu.roboRally.cardTypes.MoveForwardThreeCard;
import dtu.roboRally.cardTypes.MoveForwardTwoCard;
import dtu.roboRally.cardTypes.RotateClockwiseCard;
import dtu.roboRally.cardTypes.RotateCounterClockwiseCard;
import dtu.roboRally.cardTypes.UTurnCard;
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
	
	///////////////////// test player.use
	
	@Given("a player")
	public void a_player() {
	    player = new Player();
	}
	@Given("robot with position \\({int}, {int}, {int})")
	public void robot_with_position(int x, int y, int o) {
	    player.setRobot(o, x, y);
	}
	@When("the player uses a {string}")
	public void the_player_uses_a(String string) {
//		System.out.println((player.getRobot()));
	    switch (string) {
	    case "forward 1": player.use(new MoveForwardOneCard());break;
	    case "forward 2": player.use(new MoveForwardTwoCard());break;
	    case "forward 3": player.use(new MoveForwardThreeCard());break;
	    case "backward": player.use(new MoveBackwardCard());break;
	    case "rotate_c": player.use(new RotateClockwiseCard());break;
	    case "rotate_cc": player.use(new RotateCounterClockwiseCard());break;
	    case "uturn": player.use(new UTurnCard());break;
	    }
	}
	@Then("the robot has position {int}, {int}, {int}")
	public void the_robot_has_position(int x, int y, int o) {
		assertEquals(o, player.getRobot().getPosition().getOrientation());
	    assertEquals(x, player.getRobot().getPosition().getX());
	    assertEquals(y, player.getRobot().getPosition().getY());
	}
}



