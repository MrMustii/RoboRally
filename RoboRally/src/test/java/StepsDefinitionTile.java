import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import dtu.roboRally.*;
import io.cucumber.java.en.*;

public class StepsDefinitionTile {

	Tile tile;
	Robot robot;
	Robot robot2;
	Game game = Game.getInstance();
	Board board;
	

	
	
	@Given("the robot has {int} lives and moves to a tile")
	public void the_robot_has_lives_and_moves_to_a_tile(int int1) {
		robot = new Robot(1,1,0);
	    robot.setLives(int1);
	}
	@When("the robot interacts with the tile with {int}")
	public void the_robot_interacts_with_the_tile_with(int int1) {
		Tile tile;
		Game.getInstance().getBoard().setAcidTile(0, 0);
		tile=Game.getInstance().getBoard().getTile(0, 0);
		tile.setDamage(int1);
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

		game.getBoard().getTile(x1,y1).interact(robot);
		
	}
	@Then("the robot will move down the series of conveyor belts and end on position {int} and {int}")
	public void the_robot_will_move_down_the_series_of_conveyor_belts_and_end_on_position_and(int x4, int y4) {
		Position position = robot.getPosition();
		x4 = 1;
		y4 = 4;
	    assertEquals(x4, position.getX());
	    assertEquals(y4, position.getY());
	}

	@Given("two robots with {int} lives")
	public void two_robots_with_lives(int lives) {
		robot = new Robot(1,1,1);
		robot2 = new Robot(0,3,3);
		lives = 5;
		robot.setLives(lives);
	    
	}
	@Given("two conveyer belts on position {int} and {int}  and {int} and {int} with orientation {int} and {int}")
	public void two_conveyer_belts_on_position_and_and_and_with_orientation_and(int x1, int y1, int x2, int y2, int ori1, int ori2) {
		x1 = 1;
	    y1 = 1;
	    ori1 = 2;
		game.getBoard().setConveyorBelt(x1, y1, ori1);
		
		x2 = 3;
	    y2 = 3;
	    ori1 = 0;
		game.getBoard().setConveyorBelt(x2, y2, ori2);
	}
	@When("the robots interacts with the conveyer belts on position {int} and {int} and {int} and {int}")
	public void the_robots_interacts_with_the_conveyer_belts_on_position_and_and_and(int x1, int y1, int x2, int y2) {
		game.getBoard().getTile(x1,y1).interact(robot);
		
		
		game.getBoard().getTile(x2,y2).interact(robot2);
		
		
	}
	@Then("the robot will be pushed to position {int} and {int} and {int} and {int}")
	public void the_robot_will_be_pushed_to_position_and_and_and(int newx1, int newy1, int newx2, int newy2) {
		newx1 = 1; newy1 = 2;
		assertEquals(newx1,robot.getPosition().getX());
		assertEquals(newy1,robot.getPosition().getY());
		
		newx2 = 3; newy2 = 2;
		assertEquals(newx2,robot2.getPosition().getX());
		assertEquals(newy2,robot2.getPosition().getY());
	}
	@Then("the tile is a damaging tile with position {int} and {int}")
	public void the_tile_is_a_damaging_tile(int newx1, int newy1) {
		game.getBoard().setAcidTile(newx1,newy1);
	
	}
	@Then("the robot will recieve corresponding damage on position {int} and {int}")
	public void the_robot_will_recieve_corresponding_damage(int newx1, int newy1) {
		game.getBoard().getTile(newx1, newy1).interact(robot);
		assertEquals(3, robot.getLives());
	}
	//
	@Given("a robot on position with {int} lives")
	public void a_robot_on_position_with_lives(int lives) {
	    robot = new Robot(1,1,1);
	    robot.setLives(3);
	}
	@Given("a repair kit on position {int} and {int}")
	public void a_repair_kit_on_position_and(int x, int y) {
	    x = 1; y = 2;
	    game.getBoard().setRepair(x, y);
	}
	@When("the robot interacts with the repair kit on position {int} and {int}")
	public void the_robot_interacts_with_the_repair_kit_on_position_and(int x, int y) {
	    game.getBoard().getTile(x, y).interact(robot);
	}
	@Then("the robot should have {int} lives")
	public void the_robot_should_have_lives(int newlives) {
		newlives = 4;
	    assertEquals(newlives, robot.getLives());
	}
	//
	@Given("a robot on position {int} and {int} with orienation {int}")
	public void a_robot_on_position_and_with_orienation(int x, int y, int ori) {
		x = 1; y = 1; ori = 1;
	    robot = new Robot(x,y,ori);
	}
	@Given("a laser shooter tile on position {int} and {int} with orientation {int}")
	public void a_laser_shooter_tile_on_position_and_with_orientation(int xl, int yl, int oril) {
		xl = 2; yl = 1; oril = 2;
	    game.getBoard().setLaserShooter(xl, yl, oril);
	}
	@When("the robot tries to move onto the laser shooter tile")
	public void the_robot_tries_to_move_onto_the_laser_shooter_tile() {
		robot.move(game.getBoard(), new Position(2,1,1));
		
	}
	@Then("the robot will stay at the same position {int} and {int}")
	public void the_robot_will_stay_at_the_same_position_and(int x, int y) {

		assertEquals(x,robot.getPosition().getX());
		assertEquals(y,robot.getPosition().getY());
	}
	@Given("conveyer belts on position {int} and {int} with orientation {int} and position {int} and {int} with orientation {int}, and position {int} and {int} with orientation {int}")
	public void conveyer_belts_on_position_and_with_orientation_and_position_and_with_orientation_and_position_and_with_orientation(int x1, int y1, int ori1, int x2, int y2, int ori2, int x3, int y3, int ori3) {
	    x1 = 1; y1 = 1; ori1 = 1; x2 = 2; y2 = 1; ori2 = 0; x3 = 2; y3 = 0; ori3 = 3;
	    game.getBoard().setConveyorBelt(x1, y1, ori1);
	    game.getBoard().setConveyorBelt(x2, y2, ori2);
	    game.getBoard().setConveyorBelt(x3, y3, ori3);
	}
	@Then("the robot will move to position {int} and {int}")
	public void the_robot_will_move_to_position_and(int newx, int newy) {
	    newx = 1;newy=0;
		assertEquals(newx,robot.getPosition().getX());
		assertEquals(newy,robot.getPosition().getY());
	}
}
