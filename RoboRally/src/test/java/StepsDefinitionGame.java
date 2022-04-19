import static org.junit.Assert.assertEquals;

import dtu.roboRally.Card;
import dtu.roboRally.Game;
import dtu.roboRally.Player;
import dtu.roboRally.cardTypes.MoveBackwardCard;
import dtu.roboRally.cardTypes.MoveForwardOneCard;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsDefinitionGame {
	@Given("the user choses a {int} players")
	public void the_user_choses_a_players(Integer int1) {
	    
	}
	@When("creation instances of {int} players")
	public void creation_instances_of_players(int p) {
		Game newgame=Game.getInstance(p);
	}
	@Then("the number of players should be set to {int}")
	public void the_number_of_players_should_be_set_to(int p){
		Game newgame=Game.getInstance(p);
	    assertEquals(3, newgame.getPlayers().size());
	}
	////
	@When("The game wants to check the number of players for the phases")
	public void the_game_wants_to_check_the_number_of_players_for_the_phases() {
	    newgame.numberOfPlayers();
	}
	@Then("the number of players should be returned to {int}")
	public void the_number_of_players_should_be_returned_to(Integer int1) {
	    assertEquals(3, newgame.numberOfPlayers());
	}
	////
	Game newgame=Game.getInstance(3);
	@Given("that the players dont have full a hand")
	public void that_the_players_dont_have_full_a_hand() {
		for(Player p:newgame.getPlayers()) {
			p.getHand().removeAll(p.getHand());
	    }
	}
	@When("They draw thier hand, thier hand is showen and pick cards")
	public void they_draw_thier_hand_thier_hand_is_showen_and_pick_cards() {
	    newgame.phase1();
	}
	@Then("all the players have picked cards")
	public void all_the_players_have_picked_cards() {
		for(Player p:newgame.getPlayers()) {
			assertEquals(9, p.getHand().size());
		}
	}
	/////
	@Given("That the players have picked cards")
	public void that_the_players_have_picked_cards() {
		for(Player p:newgame.getPlayers()) {
			for (int i=0;i<5;i++) {
			p.getCardsInPlay().add( new MoveForwardOneCard());
			}
	    }

	}
	@Given("they have robots on the board")
	public void they_have_robots_on_the_board() {
		for(int i=0;i<newgame.numberOfPlayers();i++) {
			newgame.getPlayers().get(i).setRobot(0, i,0);
		}
		for(Player p:newgame.getPlayers()) {
			assertEquals(5, p.getCardsInPlay().size());
		}
	}
	@When("robot has to move accordingly")
	public void robot_has_to_move_accordingly() {
	    newgame.phase2();
	}
	@Then("The robots have new psoitions")
	public void the_robots_have_new_psoitions() {
		for(Player p:newgame.getPlayers()) {
			int i=(p.getRobot().getPosition()).getY();
			assertEquals(5,i);
		}
	}
	/////////
	
	
}
