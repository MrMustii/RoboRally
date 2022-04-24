import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import dtu.roboRally.*;
import io.cucumber.java.en.*;

public class StepsDefinitionTile {

	Tile tile;
	Robot robot;
	Game game = Game.getInstance();
	Board board;
//	Board board=Game.getInstance().getBoard();
	
	
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
//<<<<<<< Updated upstream
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
//=======
	
	@Given("a robot")
	public void a_robot() {
		robot = new Robot(0,1,1);
	}
	@Given("an empty board")
	public void an_empty_board() {
		game.getBoard().emptyTheBoard();
//		Game game = Game.getInstance();
//		Board board = Game.getInstance().getBoard();
		
//		board = new Board(12,12,1);
//	    this.board = board.emptyTheBoard();
	}
	@Given("two teleporter tiles at x- and y positions {int} and {int}, and {int} and {int}")
	public void two_teleporter_tiles_at_x_and_y_positions_and_and_and(int x1, int y1, int x2, int y2) {
//		Game game = Game.getInstance();
		x1 = 1;
		y1 = 1;
		game.getBoard().setTeleporter(x1, y1);
		game.getBoard().getTile(x1, y1).setLabel("T1");
		
		
		x2 = 4;
		y2 = 4;
		game.getBoard().setTeleporter(x2, y2);
		game.getBoard().getTile(x2, y2).setLabel("T2");
		
		
	}
	@When("the robot interacts with the teleporter tile at position {int} and {int}")
	public void the_robot_interacts_with_the_teleporter_tile_at_position_and(int x1, int y1) {
//		Game game = Game.getInstance();
	    game.getBoard().getTile(x1,y1).interact(robot);

	}
	@Then("the robot will teleport to the other teleporter at position {int} and {int}")
	public void the_robot_will_teleport_to_the_other_teleporter_at_position_and(int x2, int y2) {
		

		assertEquals(y2,robot.getPosition().getX());
		assertEquals(x2,robot.getPosition().getY());
//>>>>>>> Stashed changes
	}
}
