import static org.junit.Assert.*;

import org.junit.Test;

import dtu.roboRally.Game;
import dtu.roboRally.Tile;
import io.cucumber.java.Before;



public class TileTest {

	
	@Test
	public void testSetAndGetLabel() {
		Tile tile;
		Game.getInstance().getBoard().setAcidTile(0, 0);
		tile=Game.getInstance().getBoard().getTile(0, 0);
		tile.setLabel("R ");
		assertEquals("R ", tile.getLabel());
		
	}



	@Test
	public void testSetAndGetDamage() {
		Tile tile;
		Game.getInstance().getBoard().setAcidTile(0, 0);
		tile=Game.getInstance().getBoard().getTile(0, 0);
		tile.setDamage(2);
		assertEquals(2, tile.getDamage());
	}
	
	@Test
	public void testGetTileOrientation() {
		Tile tile;
		Game.getInstance().getBoard().setWall(0, 0, 0);
		tile = Game.getInstance().getBoard().getTile(0, 0);
		assertEquals(0,tile.getTileOrientation());
		

	}
		
		

		

}
