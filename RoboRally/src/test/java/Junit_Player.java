import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import dtu.roboRally.Card;
import dtu.roboRally.Player;
import io.cucumber.java.Before;

public class Junit_Player {
	private Player p=new Player();
	
	@Before
	public void newplayer(){
		p=new Player();
	}
	
	
	@Test
	public void testGetRobot() {
		p.setRobot(0, 0, 0);
		assertEquals(0,p.getRobot().getPosition().getOrientation());
		assertEquals(0,p.getRobot().getPosition().getY());
		assertEquals(0,p.getRobot().getPosition().getX());
	}

	@Test
	public void testGetHand() {
		assertEquals(0,p.getHand().size());
		p.getHand().add(Card.random());
		assertEquals(1,p.getHand().size());
	}

	@Test
	public void testGetCardsInPlay() {
		assertEquals(0,p.getCardsInPlay().size());
		p.getCardsInPlay().add(Card.random());
		assertEquals(1,p.getCardsInPlay().size());
	}

}
