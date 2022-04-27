import static org.junit.Assert.*;

import org.junit.Test;

import dtu.roboRally.Game;
import dtu.roboRally.Position;
import dtu.roboRally.Robot;
import dtu.roboRally.controller.RoboRallyController;

public class Junit_Robot {
	Game game=Game.getInstance(new RoboRallyController(), 3);
	Robot robot=new Robot(1,1,1);
	@Test
	public void testGetShielded() {
		assertEquals(false, robot.getShielded());
	}

	@Test
	public void testSetShielded() {
		robot.setShielded(true);
		assertEquals(true, robot.getShielded());
	}
	
	@Test
	public void testMoveOutofBoundsX() {
		robot.move(Game.getInstance().getBoard(), new Position(99, 1, 1));
		assertEquals(99, robot.getPosition().getX());
		assertEquals(1, robot.getPosition().getY());
		assertEquals(1, robot.getPosition().getOrientation());
	}
	@Test
	public void testMoveOutofBoundsY() {
		robot.move(Game.getInstance().getBoard(), new Position(1,99, 1));
		assertEquals(1, robot.getPosition().getX());
		assertEquals(99, robot.getPosition().getY());
		assertEquals(1, robot.getPosition().getOrientation());
	}
	@Test
	public void testMoveToWall() {
		robot.move(Game.getInstance().getBoard(), new Position(1,99, 1));
		assertEquals(1, robot.getPosition().getX());
		assertEquals(99, robot.getPosition().getY());
		assertEquals(1, robot.getPosition().getOrientation());
	}
	@Test
	public void testGetStarterPosition() {
		Position position=robot.getStartPosition();
		assertEquals(1, position.getX());
		assertEquals(1, position.getY());
		assertEquals(1, position.getOrientation());
	}
	
}
