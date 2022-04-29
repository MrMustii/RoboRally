import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import dtu.roboRally.Card;
import dtu.roboRally.Game;
import dtu.roboRally.Player;
import dtu.roboRally.Position;
import dtu.roboRally.Robot;
import dtu.roboRally.cardTypes.MoveBackwardCard;
import dtu.roboRally.cardTypes.MoveForwardOneCard;
import dtu.roboRally.cardTypes.MoveForwardTwoCard;
import dtu.roboRally.cardTypes.RotateClockwiseCard;
import dtu.roboRally.cardTypes.RotateCounterClockwiseCard;
import dtu.roboRally.cardTypes.ShieldCard;
import dtu.roboRally.controller.RoboRallyController;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsDefinitionGame {
	@Given("there is no game")
	public void there_is_no_game() {
		Game.endGame();
		assertEquals(null, Game.getInstance());
	    
	}
	@When("creation instances of {int} players")
	public void creation_instances_of_players(int p) {
		Game newgame=Game.getInstance(null,p);
	}
	@Then("the number of players should be set to {int}")
	public void the_number_of_players_should_be_set_to(int p){
		Game newgame=Game.getInstance(null,p);
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
	Game newgame=Game.getInstance(new RoboRallyController(),3);
	
	@Given("that the players dont have full a hand")
	public void that_the_players_dont_have_full_a_hand() {
		for(Player p:newgame.getPlayers()) {
			p.getHand().removeAll(p.getHand());
	    }
	}
	
	@When("They draw thier hand, thier hand is showen and pick cards")
	public void they_draw_thier_hand_thier_hand_is_showen_and_pick_cards() {
		for(Player player : Game.getInstance().getPlayers()) {
			player.drawHand();
		}
		
//	    newgame.phase1();
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
			p.getCardsInPlay().clear();
			for (int i=0;i<5;i++) {
			p.getCardsInPlay().add( new RotateClockwiseCard());
			}
	    }

	}
	@Given("they have robots on the board")
	public void they_have_robots_on_the_board() {
		for(int i=0;i<newgame.numberOfPlayers();i++) {
			newgame.getPlayers().get(i).setRobot(0, i, 0);
		}
		
	}
	@When("robot has to move accordingly")
	public void robot_has_to_move_accordingly() {
	    newgame.phase2();
	}
	@Then("The robots have new psoitions")
	public void the_robots_have_new_psoitions() {
		for(int i=0;i<newgame.numberOfPlayers();i++) {
			assertEquals(1,newgame.getPlayers().get(i).getRobot().getPosition().getOrientation());
		}
	}
	/////////
	@Given("they have a dead robot")
	public void they_have_a_dead_robot() {
		
	    newgame.getPlayers().get(0).getRobot().damage(5);
	    assertEquals(true, newgame.getPlayers().get(0).getRobot().isDead());
	    
	}
	@Given("That the players have picked {int} card")
	public void that_the_players_have_picked_card(int a) {
		for(Player p:newgame.getPlayers()) {
			for (int i=0;i<a;i++) {

				p.getCardsInPlay().add( new RotateCounterClockwiseCard());
			}
		}
	    
	}
	@Then("dead robot does not move")
	public void dead_robot_does_not_move() {
	    int j=newgame.getPlayers().get(0).getRobot().getPosition().getOrientation();
	    assertEquals(0, j);
	    
	}
	
	
	@Then("the living robot have new positions")
	public void the_living_robot_have_new_positions() {
	    
		for(int i=1;i<newgame.numberOfPlayers();i++) {
	    	int j=newgame.getPlayers().get(i).getRobot().getPosition().getOrientation();
	    	
	    	System.out.println(newgame.getPlayers().get(i).getRobot().getPosition().getOrientation());
	    	
	    	assertEquals(3,j);
	    }
	}
/////
	@Given("robot is near end posistion")
	public void robot_is_near_end_posistion() {
		newgame.setBoard();
	    ArrayList<Position> endPosition=new ArrayList<>();
		//finds all end position
	    for (int rows=0;rows<newgame.getBoard().getRows();rows++) {
	    	for (int cols=0;cols<newgame.getBoard().getCols();cols++) {
	    		if(newgame.getBoard().getTile(cols, rows).getLabel()=="E ") {
	    		endPosition.add(new Position(cols,rows,1));
	    		}
	    	}
	    }
	    int x= endPosition.get(0).getX();
		int y= endPosition.get(0).getY();
		int o= endPosition.get(0).getOrientation();
		newgame.getPlayers().get(0).getRobot().setPosition(new Position(x-1, y, 1));
	}
	@When("robot moves to a end position")
	public void robot_moves_to_a_end_position() {
		newgame.getPlayers().get(0).use(new MoveForwardOneCard());
		int x=newgame.getPlayers().get(0).getRobot().getPosition().getX();
		int y=newgame.getPlayers().get(0).getRobot().getPosition().getY();
		assertEquals("E ", newgame.getBoard().getTile(x, y).getLabel());
		newgame.hasWon(0);
	}
	@Then("player wins")
	public void player_wins() {
	    assertNotEquals(null, newgame.getWinner());
	}
	/////
	
	@Given("a clear board")
	public void a_clear_board() {
	    newgame.getBoard().emptyTheBoard();
	}
	@Given("players with shielded robots")
	public void players_with_shielded_robots() {
		for(Player p:newgame.getPlayers()) {
			p.use(new ShieldCard());
		}
	}
	@When("phase {int} starts")
	public void phase_starts(Integer int1) {
		for(Player p:newgame.getPlayers()) {
			p.getRobot().damage(1);
		}
	}
	@Then("the robot are unshielded")
	public void the_robot_are_unshielded() {
		for(Player p:newgame.getPlayers()) {
			assertEquals(false, p.getRobot().getShielded());
		}
	}
	@Given("a robot with a position with coordinates {int}, {int} and orientation {int}")
	public void a_robot_with_a_position_with_coordinates_and_orientation(int a, int b, int c) {
	    newgame.getPlayers().get(0).setRobot(c, a, b);
	    newgame.getBoard().getTile(a, b).setOccupidRobot(newgame.getPlayers().get(0).getRobot());
	}
	@Given("another robot with a position with coordinates \\({int}, {int}) and orientation {int}")
	public void another_robot_with_a_position_with_coordinates_and_orientation(int x, int y, int z) {
		 newgame.getPlayers().get(1).setRobot(z, x, y);
		 newgame.getBoard().getTile(x, y).setOccupidRobot(newgame.getPlayers().get(1).getRobot());
		 

	}
	@When("the robot moves to {int}, {int}, {int}")
	public void the_robot_moves_to(int x, int y, int o ) {
		newgame.getPlayers().get(0).getRobot().move(newgame.getBoard(), new Position(x, y, o));
	    
	}
	@Then("one robot is pushed  {int}, {int}, {int} and {int}, {int}, {int}")
	public void one_robot_is_pushed_and(int anew, int bnew, int cnew, int xnew, int ynew, int znew) {
		Robot robot=newgame.getPlayers().get(0).getRobot();
		Robot bot=newgame.getPlayers().get(1).getRobot();

		
		assertEquals(anew, robot.getPosition().getX());
		assertEquals(bnew, robot.getPosition().getY());
		assertEquals(cnew, robot.getPosition().getOrientation());
		assertEquals(xnew, bot.getPosition().getX());
		assertEquals(ynew, bot.getPosition().getY());
		//assertEquals(znew, bot.getPosition().getOrientation());
	}
	@Given("a third robot with a position with coordinates \\({int}, {int}) and orientation {int}")
	public void a_third_robot_with_a_position_with_coordinates_and_orientation(int i, int j, int k) {
		newgame.getPlayers().get(2).setRobot(k, i, j);
		newgame.getBoard().getTile(i, j).setOccupidRobot(newgame.getPlayers().get(2).getRobot());
	}
	@Then("then the new positions for all robots are \\({int}, {int}, {int}), \\({int}, {int}, {int}), and \\({int}, {int}, {int})")
	public void then_the_new_positions_for_all_robots_are_and(int a, int b, int c,  int x, int y, int z, int i,int j, int k) {
		Robot robot=newgame.getPlayers().get(0).getRobot();
		Robot bot=newgame.getPlayers().get(1).getRobot();
		Robot rob=newgame.getPlayers().get(2).getRobot();
		
		assertEquals(a, robot.getPosition().getX());
		assertEquals(b, robot.getPosition().getY());
		assertEquals(c, robot.getPosition().getOrientation());
		assertEquals(x, bot.getPosition().getX());
		assertEquals(y, bot.getPosition().getY());
		assertEquals(z, bot.getPosition().getOrientation());
		assertEquals(i, rob.getPosition().getX());
		assertEquals(j, rob.getPosition().getY());
		assertEquals(k, rob.getPosition().getOrientation());
	}
	@Given("setting a wall at {int}, {int}, {int}")
	public void setting_a_wall_at(int x,int y,int o) {
	    newgame.getBoard().setWall(x, y, o);
	}
	@Then("then the new positions for all robots are \\({int}, {int}, {int}), \\({int}, {int}, {int})")
	public void then_the_new_positions_for_all_robots_are(int a, int b, int c, int x, int y,int z) {
		Robot robot=newgame.getPlayers().get(0).getRobot();
		Robot bot=newgame.getPlayers().get(1).getRobot();
		
		assertEquals(a, robot.getPosition().getX());
		assertEquals(b, robot.getPosition().getY());
		assertEquals(c, robot.getPosition().getOrientation());
		assertEquals(x, bot.getPosition().getX());
		assertEquals(y, bot.getPosition().getY());
		assertEquals(z, bot.getPosition().getOrientation());
	}

}
