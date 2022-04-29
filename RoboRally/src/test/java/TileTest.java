import static org.junit.Assert.*;

import org.junit.Test;

import dtu.roboRally.Tile;
import dtu.roboRally.controller.RoboRallyController;




public class TileTest {

	
	@Test
	public void testSetAndGetLabel() {
		Tile tile;
		tile = new Tile("A ",1);
		tile.setLabel("R ");
		assertEquals("R ", tile.getLabel());
		
	}

	@Test
	public void testSetAndGetDamage() {
		Tile tile;
		tile = new Tile("A ",1);
		tile.setDamage(2);
		assertEquals(2, tile.getDamage());
	}
	
	@Test
	public void testGetTileOrientation() {
		Tile wall = new Tile("W0",0);
		
		assertEquals(0,wall.getTileOrientation());

	}
}
