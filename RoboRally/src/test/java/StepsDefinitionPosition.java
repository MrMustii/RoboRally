import static org.junit.Assert.assertEquals;

import dtu.roboRally.Position;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsDefinitionPosition {
	
	Position position;
	
	@Given("a position with coordinates \\({int}, {int}) and orientation {int}")
	public void a_position_with_coordinates_and_orientation(int x, int y, int o) {
	    position = new Position(x,y,o);
	}
	@When("the position changes to upwards adjacent tile")
	public void the_position_changes_to_upwards_adjacent_tile() {
	    position.setY(2);
	}
	@Then("the position has coordinates \\({int}, {int})")
	public void the_position_has_coordinates(int x, int y) {
	    assertEquals(x,position.getX());
	    assertEquals(y,position.getY());
	}
	@When("the position changes to right adjacent tile")
	public void the_position_changes_to_right_adjacent_tile() {
	    position.setX(2);
	}
	@When("the orientation of the position changes to right")
	public void the_orientation_of_the_position_changes_to_right() {
	    position.setOrientation(1);
	}
	@Then("the position has orientation {int}")
	public void the_position_has_orientation(int o) {
	    assertEquals(o,position.getOrientation());
	}

}
