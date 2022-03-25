import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import dtu.roboRally.Card;
import dtu.roboRally.Orientation;
import dtu.roboRally.Player;
import dtu.roboRally.Robot;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsDefinitionJA {
	
	Robot robot = new Robot(Orientation.UP, 1, 1);
	Player player = new Player();
	
	// rotate feature
	@Given("a robot and an orientation {string}")
	public void a_robot_and_an_orientation(String string) {
		robot.setOrientation(Orientation.valueOf(string));
	}
	@When("the robot rotates {string}")
	public void i_use_a_clockwise_rotate_card(String string) {
		if (string.equals("clockwise")) {
			robot.rotateClockwise();
		} else {
			robot.rotateCounterClockwise();
		}
	}	
	@Then("the robot has orientation {string}")
	public void the_robot_has_orientation(String string) {
		assertEquals(Orientation.valueOf(string), robot.getOrientation());
	}
	
	// move feature
	@Given("a robot with an orientation {string}")
	public void a_robot_with_an_orientation(String string) {
	    robot.setOrientation(Orientation.valueOf(string));
	}
	@Given("a start position \\({int}, {int})")
	public void a_start_position(int X, int Y) {
	    robot.setPosition(X, Y);
	}
	@When("the robot moves {string}")
	public void the_robot_moves_forward(String string) {
		if (string.equals("forward")) {
		    robot.moveForward();
		} else {
			robot.moveBackward();
		}
	}
	@Then("the robot has reached {int}, {int}")
	public void the_the_robot_has_reached(int X, int Y) {
	    assertEquals(X, robot.getX());
	    assertEquals(Y, robot.getY());
	}
	
	// death feature
	@Given("A robot and a start position \\({int}, {int})")
	public void a_robot_and_a_start_position(Integer int1, Integer int2) {
		// already instantiated
	}
	@When("the robot dies")
	public void the_robot_dies() {
	    System.out.println("The robot has been murdered");
	}
	@Then("it respawns at the starting point")
	public void it_respawns_at_the_starting_point() {
	    robot.respawn();
	    assertEquals(1, robot.getX());
	    assertEquals(1, robot.getY());
	}

	//lives feature
	//damage
	@Given("A robot and {int} lives")
	public void a_robot_and_lives(Integer int1) {
		robot.setLives(int1);
	}
	@When("the robot takes {int} damage")
	public void the_robot_takes_damage(Integer int1) {
		robot.damage(int1);
	}
	@Then("it has {int} lives")
	public void it_has_lives(int int1) {
		assertEquals(int1, robot.getLives());
	}


	@Given("A robot with starting positions \\({int}, {int})")
	public void a_robot_with_starting_positions(int x, int y) {
		//already have these starting positions
	}

	@Given("{int} live")
	public void live(Integer int1) {
		robot.setLives(int1);
	}
	
	// using cards
	@Given("A player with a card")
	public void a_player_with_a_card() {
	    //player instantiated at the top
	}
	@Given("a robot with starting position \\({int}, {int}) and an orientation {string}")
	public void a_robot_with_starting_position_and_an_orientation(int x, int y, String orientation) {
	    player.createRobot(x, y, Orientation.valueOf(orientation));
	}
	@When("the player uses the {string}")
	public void the_player_uses_the(String card) {
	    player.use(Card.valueOf(card));
	}
	@Then("the robot has position {int}, {int} and {string}")
	public void the_robot_has_position_and(int x, int y, String orientation) {
	    assertEquals(x, player.getRobot().getX());
	    assertEquals(y, player.getRobot().getY());
	    assertEquals(Orientation.valueOf(orientation), player.getRobot().getOrientation());
	}
	
	// picking start positon and orientation
	@Given("a player")
	public void a_player() {
		//player instantiated at the top
	}
	@When("the player picks the starting position \\({int}, {int}) and the orientation {string} for the robot")
	public void the_player_picks_the_starting_position_and_the_orientation_for_the_robot(int x, int y, String orientation) {
	    player.createRobot(x, y, Orientation.valueOf(orientation));
	}
	@Then("the player has a robot")
	public void the_player_has_a_robot() {
	    assertNotNull(player.getRobot());
	}
	@Then("the robot has starting position \\({int}, {int}) and orientation {string}")
	public void the_robot_has_starting_position_and_orientation(int x, int y, String orientation) {
		assertEquals(x, player.getRobot().getX());
	    assertEquals(y, player.getRobot().getY());
	    assertEquals(Orientation.valueOf(orientation), player.getRobot().getOrientation());
	}
	
}