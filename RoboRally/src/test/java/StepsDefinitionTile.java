import static org.junit.Assert.assertEquals;

import dtu.roboRally.*;
import io.cucumber.java.en.*;

public class StepsDefinitionTile {

	Tile tile;
	Robot robot = new Robot(1,1,0);
	
	@Given("the robot has {int} lives and moves to a tile")
	public void the_robot_has_lives_and_moves_to_a_tile(int int1) {
	    robot.setLives(int1);
	}
	@When("the robot interacts with the {string} with {int}")
	public void the_robot_interacts_with_the_with(String string, int int1) {
		tile = new Tile(string, int1);
		tile.interact(robot);
	}
		
	@Then("the robot takes damage and has {int} left")
	public void the_robot_takes_damage_and_has_left(int int1) {
	    assertEquals(int1, robot.getLives());
	}
}
