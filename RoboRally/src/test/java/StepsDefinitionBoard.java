import static org.junit.Assert.assertEquals;

import dtu.roboRally.*;
import io.cucumber.java.en.*;

public class StepsDefinitionBoard {
	Board board;
	
	@Given("a board with {int} rows and {int} columns and {int} players")
	public void a_board_with_rows_and_columns_and_players(Integer int1, Integer int2, Integer int3) {
		board = new Board(12, 12, 4, null);
	}
	@When("the user runs the game")
	public void the_user_runs_the_game() {
		board.printBoard();
	}
	@Then("a board with {int} rows and {int} columns will be generated")
	public void a_board_with_rows_and_columns_will_be_generated(Integer int1, Integer int2) {
	    assertEquals(12, board.getRows());
	    assertEquals(12, board.getCols());
	}
	@Given("the board is empty")
	public void the_board_is_empty() {
	    board.emptyTheBoard();;
	}
	@When("changeing a floor tile to oil tile")
	public void changeing_a_floor_tile_to_oil_tile() {
	    board.setOilTile(0, 0);
	}
	@Then("the floor becomes oil")
	public void the_floor_becomes_oil() {
	    assertEquals("O ", board.getTile(0, 0).getLabel());
	}
}
