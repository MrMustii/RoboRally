import static org.junit.Assert.*;

import org.junit.Test;

import dtu.roboRally.Robot;

public class Junit_Robot {
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

}
