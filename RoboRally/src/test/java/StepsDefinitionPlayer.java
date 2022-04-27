import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import dtu.roboRally.Card;
import dtu.roboRally.Game;
import dtu.roboRally.Player;
import dtu.roboRally.Position;
import dtu.roboRally.cardTypes.MoveBackwardCard;
import dtu.roboRally.cardTypes.MoveForwardOneCard;
import dtu.roboRally.cardTypes.MoveForwardThreeCard;
import dtu.roboRally.cardTypes.MoveForwardTwoCard;
import dtu.roboRally.cardTypes.OilSPillCard;
import dtu.roboRally.cardTypes.RotateClockwiseCard;
import dtu.roboRally.cardTypes.RotateCounterClockwiseCard;
import dtu.roboRally.cardTypes.ShieldCard;
import dtu.roboRally.cardTypes.UTurnCard;
import dtu.roboRally.controller.RoboRallyController;
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
	/////////////
	@Given("the player does not have a robot")
	public void the_player_does_not_have_a_robot() {
	    assertEquals(null, player.getRobot());
	}
	@When("the player sets a robot on the board")
	public void the_player_sets_a_robot_on_the_board() {
	    player.setRobot(0, 0, 0);
	}
	@Then("the robot has a position")
	public void the_robot_has_a_position() {
		
		int o=player.getRobot().getPosition().getOrientation();
		int x=player.getRobot().getPosition().getX();
		int y=player.getRobot().getPosition().getY();
		assertEquals(0, o);
		assertEquals(0, x);
		assertEquals(0, y);
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
		game.getInstance().getBoard().emptyTheBoard();
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
	//player initialize 
	Game game=Game.getInstance(new RoboRallyController(),3);
	@Given("there us a board")
	public void there_us_a_board() {
	    Game game=Game.getInstance(new RoboRallyController(),3);
	    game.setBoard();
	}
	@When("a player initialize on the starting tile")
	public void a_player_initialize_on_the_starting_tile() {
		//get the starting positions
	    ArrayList<Position> startingPosition=new ArrayList<>();
		for (int rows=0;rows<game.getBoard().getRows();rows++) {
	    	for (int cols=0;cols<game.getBoard().getCols();cols++) {
	    		if(game.getBoard().getTile(cols, rows).getLabel()=="S ") {
	    		startingPosition.add(new Position(cols,rows,1));
	    		}
	    	}
	    }
		//initialize
		int x= startingPosition.get(0).getX();
		int y= startingPosition.get(0).getY();
		int o= startingPosition.get(0).getOrientation();
		player.initializeRobot(o, x, y);
		


	}
	@Then("the robot has a position of the starting tile")
	public void the_robot_has_a_position_of_the_starting_tile() {
		
	    assertEquals("S ", game.getBoard().getTile(player.getRobot().getPosition().getX(), player.getRobot().getPosition().getY()).getLabel());
	}
	//////////////
	@When("the player plays the shield card")
	public void the_player_plays_the_shield_card() {
	    player.use(new ShieldCard());
	}
	@Then("the robot is shielded")
	public void the_robot_is_shielded() {
	assertEquals(true, player.getRobot().getShielded());    
	}
	@Then("it does not take damage")
	public void it_does_not_take_damage() {
		int lives=player.getRobot().getLives();
	    player.getRobot().damage(3);;
	    assertEquals(lives, player.getRobot().getLives());
	}
	
}



