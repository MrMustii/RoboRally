import static org.junit.Assert.assertEquals;

import dtu.roboRally.Orientation;
import dtu.roboRally.Robot;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsDefinition {
	
	Robot robot = new Robot(Orientation.UP);
	
	// rotate fetaure
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
	

}
