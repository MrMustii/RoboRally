import static org.junit.Assert.*;

import org.junit.Test;

import dtu.roboRally.Tile;



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

}
