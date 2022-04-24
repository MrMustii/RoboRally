import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
	@When("the robot interacts with the oil tile")
	public void the_robot_interacts_with_the_oil_tile() {
	    Board b=new Board(12,12,3);
	    b.setOilTile(2, 1);
		b.getTile(2, 1).interact(robot);
	}
	@Then("the oriantation changes")
	public void the_oriantation_changes() {
	    assertNotEquals(1, robot.getPosition().getOrientation());
	}
}
