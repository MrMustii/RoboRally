import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import dtu.roboRally.Board;
import dtu.roboRally.Game;
import dtu.roboRally.Position;
import dtu.roboRally.Robot;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsDefinitionRobot {
	Robot robot;
	Robot robot5 = new Robot(1,1,1);
	Board board = Game.getInstance().getBoard();
	boolean canMove;
	
	@Given("A robot with {int} lives")
	public void a_robot_with_lives(int lives) {
	    robot = new Robot(1, 1, 1);
	    robot.setLives(lives);
	}
	@When("the robot takes {int} damage")
	public void the_robot_takes_damage(int damage) {
	    robot.damage(damage);
	}
	@Then("it has {int} lives")
	public void it_has_lives(int lives) {
	    assertEquals(lives, robot.getLives());
	}
	@Given("A robot with {int} life and start position \\({int}, {int}, {int})")
	public void a_robot_with_life_and_start_position(int lives, int o, int x, int y) {
	    robot = new Robot(o, x, y);
	    robot.setLives(lives);
	}
	@Then("it is dead")
	public void it_is_dead() {
	    assertEquals(true, robot.isDead());
	}
	@Then("it respawns at the starting point \\({int}, {int}, {int})")
	public void it_respawns_at_the_starting_point(int o, int x, int y) {
	    assertEquals(o, robot.getPosition().getOrientation());
	    assertEquals(x, robot.getPosition().getX());
	    assertEquals(y, robot.getPosition().getY());
	}
	@Given("a robot with a position with coordinates \\({int}, {int}) and orientation {int}")
	public void a_robot_with_a_position_with_coordinates_and_orientation(int x, int y, int o) {
	    robot = new Robot(o, x, y);
	}
	@Given("a board without obstacles")
	public void a_board_without_obstacles() {
	    board.emptyTheBoard();
	}
	@When("the robot gets a new position {int}, {int}, {int} by moving or rotating")
	public void the_robot_gets_a_new_position_by_moving_or_rotating(int x, int y, int o) {
	    robot.move(board, new Position(x,y,o));
	}
	@Then("the robot has the position {int}, {int}, {int}")
	public void the_robot_has_the_position(int x, int y, int o) {
	    assertEquals(x, robot.getPosition().getX());
	    assertEquals(y, robot.getPosition().getY());
	    assertEquals(o, robot.getPosition().getOrientation());
	}
	@Given("a board with a wall at position {int}, {int}, {int}")
	public void a_board_with_a_wall_at_position(int x, int y, int o) {
	    board.emptyTheBoard();
	    board.setWall(x, y, o);
	}
	@Given("A dead robot and start position \\({int}, {int}, {int})")
	public void a_dead_robot_and_start_position(int x, int y, int o) {
		robot = new Robot(o, x, y);
	    robot.damage(5);
	}
	@When("the robot respawns")
	public void the_robot_respawns() {
	    robot.respawn();
	}
	@When("the robot moves")
	public void the_robot_moves() {
	    robot.move(Game.getInstance().getBoard(), new Position(3, 4, 0));
	}
	@Then("the old tile is unoccupied")
	public void the_old_tile_is_unoccupied() {
	    assertEquals(false, Game.getInstance().getBoard().getTile(3, 3).isOccupied());
	}
	@Then("new tile is occupied")
	public void new_tile_is_occupied() {
		assertEquals(true, Game.getInstance().getBoard().getTile(3, 4).isOccupied());
	}
	@Given("it is shielded")
	public void it_is_shielded() {
	    robot.setShielded(true);
	}

	@Given("a robot with {int} lives that steps on a repair tile")
	public void a_robot_with_lives_that_steps_on_a_repair_tile(int lives) {
		lives = 5;
	    
	    robot5.setLives(lives);
	}
	@When("the robot gets repaired")
	public void the_robot_gets_repaired() {
	    robot5.repair();
	}
	@Then("the robot will still have {int} lives")
	public void the_robot_will_still_have_lives(int lives) {
	    assertEquals(lives,robot5.getLives());
	}
	@Given("a robot that is shielded")
	public void a_robot_that_is_shielded() {
	    robot5.setShielded(true);
	}
	@Given("a start position tile on position {int} and {int}")
	public void a_start_position_tile_on_position_and(int x, int y) {
		x = 1; y = 1;
	    board.setStart(x,y);
	}
	@When("the robot interacts with the start posoition tile on position {int} and {int}")
	public void the_robot_interacts_with_the_start_posoition_tile_on_position_and(int x, int y) {
		
	    board.getTile(x, y).interact(robot5);
	}
	@Then("shielded status is still true")
	public void shielded_status_is_still_true() {
	    assertEquals(true, robot5.getShielded());
	}

	@Given("a board with a oil tile at position {int}, {int}")
	public void a_board_with_a_oil_tile_at_position(int x, int y) {
		Game.getInstance().getBoard().emptyTheBoard();
		Game.getInstance().getBoard().setOilTile(x, y);
	}
	@When("the robot moves over oil tile to {int}, {int}, {int}")
	public void the_robot_moves_over_oil_tile_to_final(int x, int y, int o) {
		robot.move(Game.getInstance().getBoard(), new Position(x, y, o));
	}
	@Then("the robot does not have the position {int}, {int}, {int}")
	public void the_robot_does_not_have_the_position_final(int x, int y, int o) {
		int a=robot.getPosition().getX();
	    int b=robot.getPosition().getY();
	    int c=robot.getPosition().getOrientation();
		assertNotEquals(o,c);
		
		System.out.println("this is the x "+a);
		System.out.println("this is the y "+b);
		System.out.println("this is the o "+c);
		
		assertTrue( ((x!=a) || (y!=b)));
	}

	/////


}
	
