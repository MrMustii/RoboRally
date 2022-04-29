import static org.junit.Assert.*;

import org.junit.Test;

import dtu.roboRally.Game;
import dtu.roboRally.Position;
import dtu.roboRally.Robot;
import dtu.roboRally.controller.RoboRallyController;

public class Junit_Robot {
	Game game=Game.getInstance(new RoboRallyController(), 3);
	Robot r=new Robot(1,1,1);
	@Test
	public void testGetShielded() {
		assertEquals(false, r.getShielded());
	}

	@Test
	public void testSetShielded() {
		r.setShielded(true);
		assertEquals(true, r.getShielded());
	}
	
	@Test
	public void testMoveOutofBoundsX() {
		game.getBoard().emptyTheBoard();
		r.move(Game.getInstance().getBoard(), new Position(99, 1, 1));
		assertEquals(13, r.getPosition().getX());
		assertEquals(1, r.getPosition().getY());
		assertEquals(1, r.getPosition().getOrientation());
	}
	@Test
	public void testMoveOutofBoundsY() {
		r.move(Game.getInstance().getBoard(), new Position(1,99, 1));
		assertEquals(1, r.getPosition().getX());
		assertEquals(9, r.getPosition().getY());
		assertEquals(1, r.getPosition().getOrientation());
	}
	@Test
	public void testGetStarterPosition() {
		Position position=r.getStartPosition();
		assertEquals(1, position.getX());
		assertEquals(1, position.getY());
		assertEquals(1, position.getOrientation());
	}
	
}
