import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import dtu.roboRally.*;
import io.cucumber.java.en.*;

public class StepsDefinitionTile {

	Tile tile;
	Robot robot;
	Game game = Game.getInstance();
	Board board;
	

	
	
	@Given("the robot has {int} lives and moves to a tile")
	public void the_robot_has_lives_and_moves_to_a_tile(int int1) {
		robot = new Robot(1,1,0);
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
	    Board b=new Board(12,12,3, null);
	    b.setOilTile(2, 1);
		b.getTile(2, 1).interact(robot);
	}
	@Then("the oriantation changes")
	public void the_oriantation_changes() {
	    assertNotEquals(1, robot.getPosition().getOrientation());
	}
	
	@Given("a robot")
	public void a_robot() {
		robot = new Robot(0,1,1);
	}
	@Given("an empty board")
	public void an_empty_board() {
		game.getBoard().emptyTheBoard();

	}
	@Given("two teleporter tiles at x- and y positions {int} and {int}, and {int} and {int}")
	public void two_teleporter_tiles_at_x_and_y_positions_and_and_and(int x1, int y1, int x2, int y2) {

		x1 = 1;
		y1 = 1;
		x2 = 4;
		y2 = 4;
		game.getBoard().setTeleporter(x1, y1, x2, y2);
		game.getBoard().setTeleporter(x2, y2, x1, y1);

		
		
	}
	@When("the robot interacts with the teleporter tile at position {int} and {int}")
	public void the_robot_interacts_with_the_teleporter_tile_at_position_and(int x1, int y1) {

	    game.getBoard().getTile(x1,y1).interact(robot);

	}
	@Then("the robot will teleport to the other teleporter at position {int} and {int}")
	public void the_robot_will_teleport_to_the_other_teleporter_at_position_and(int x2, int y2) {
		

		assertEquals(y2,robot.getPosition().getX());
		assertEquals(x2,robot.getPosition().getY());

	}
	
	@Given("two new teleporter tiles at x- and y positions {int} and {int}, and {int} and {int}")
	public void two_new_teleporter_tiles_at_x_and_y_positions_and_and_and(int x3, int y3, int x4, int y4) {
		robot.setPosition(new Position(2,2,0));
		x3 = 2;
		y3 = 2;
		x4 = 5;
		y4 = 5;
		game.getBoard().setTeleporter(x3, y3, x4, y4);
		game.getBoard().setTeleporter(x4, y4, x3, y3);

	}
	@When("the robot interacts with the new teleporter tile at position {int} and {int}")
	public void the_robot_interacts_with_the_new_teleporter_tile_at_position_and(int x3, int y3) {
		game.getBoard().getTile(x3,y3).interact(robot);
		
	}
	@Then("the robot will teleport to the other new teleporter at position {int} and {int}")
	public void the_robot_will_teleport_to_the_other_new_teleporter_at_position_and(int x4, int y4) {
		assertEquals(y4,robot.getPosition().getX());
		assertEquals(x4,robot.getPosition().getY());
	}

	
	@Given("a series of conveyer belts on positions {int} and {int}, {int} and {int}, {int} and {int} and all have orientation {int}")
	public void a_series_of_conveyer_belts_on_positions_and_and_and_and_all_have_orientation(int x1, int y1, int x2, int y2, int x3, int y3, int orientation) {
		orientation = 2;
		x1 = 1;
		y1 = 1;
		game.getBoard().setConveyorBelt(x1, y1, orientation);

		
		
		x2 = 1;
		y2 = 2;
		game.getBoard().setConveyorBelt(x2, y2, orientation);

		
		x3 = 1;
		y3 = 3;
		game.getBoard().setConveyorBelt(x3, y3, orientation);

		
		
	}
	@When("the robot interacts with the conveyor belt on position {int} and {int}")
	public void the_robot_interacts_with_the_conveyor_belt_on_position_and(int x1, int y1) {
		System.out.println("");
		game.getBoard().printBoard();
		game.getBoard().getTile(x1,y1).interact(robot);
		System.out.println("");
		game.getBoard().printBoard();
	}
	@Then("the robot will move down the series of conveyor belts and end on position {int} and {int}")
	public void the_robot_will_move_down_the_series_of_conveyor_belts_and_end_on_position_and(int x4, int y4) {
		Position position = robot.getPosition();
		x4 = 1;
		y4 = 4;
	    assertEquals(x4, position.getX());
	    assertEquals(y4, position.getY());
	}
	@Given("a robot with {int} lives")
	public void a_robot_with_lives(int lives) {
		robot = new Robot(1,1,1);
		lives = 5;
		robot.setLives(lives);
	}
	
	@Given("a conveyer belt on position {int} and {int} with orientation {int}")
	public void a_conveyer_belt_on_position_and_with_orientation(int x, int y, int ori) {
	    x = 1;
	    y = 1;
	    ori = 2;
		game.getBoard().setConveyorBelt(x, y, ori);
	    
	}
	@When("the robot interacts with the conveyer belt on position {int} and {int}")
	public void the_robot_interacts_with_the_conveyer_belt_on_position_and(int x, int y) {
		game.getBoard().getTile(x,y).interact(robot);
	}
	@Then("the robot will be pushed to position {int} and {int}")
	public void the_robot_will_be_pushed_to_position_and(int newx, int newy) {
		newx = 1; newy = 2;
		assertEquals(newx,robot.getPosition().getX());
		assertEquals(newy,robot.getPosition().getY());
	}
	@Then("the tile is a damaging tile with position {int} and {int}")
	public void the_tile_is_a_damaging_tile(int newx, int newy) {
		game.getBoard().setAcidTile(newx,newy);
	}
	@Then("the robot will recieve corresponding damage on position {int} and {int}")
	public void the_robot_will_recieve_corresponding_damage(int newx, int newy) {
		game.getBoard().getTile(newx, newy).interact(robot);
		assertEquals(3, robot.getLives());
	}
}
