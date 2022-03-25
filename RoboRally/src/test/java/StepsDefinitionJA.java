import static org.junit.Assert.assertEquals;

import dtu.roboRally.Orientation;
import dtu.roboRally.Robot;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsDefinitionJA {
	
	Robot robot = new Robot(Orientation.UP, 1, 1);
	
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
		robot.isDamaged(int1);
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
}