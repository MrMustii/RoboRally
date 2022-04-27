package dtu.roboRally.XGUI;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import dtu.roboRally.Board;
import dtu.roboRally.Game;
import dtu.roboRally.Tile;

public class BoardGUI extends JPanel {
	
	Game game = Game.getInstance();
	Board board = game.getBoard();
	TileGUI[][] boardGUI;
	
	int rows, cols;
	
	public BoardGUI() {
		rows = board.getRows();
		cols = board.getCols();
		boardGUI = new TileGUI[rows][cols];
		
		setLayout(new GridLayout(rows, cols));
		
		setMinimumSize(new Dimension(cols * TileGUI.PIXEL_SIZE, rows * TileGUI.PIXEL_SIZE));
		setMaximumSize(getMinimumSize());
		setPreferredSize(getMinimumSize());
		
		loadBoard();
	}
	
	private void loadBoard() {
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < cols; i++) {
				TileGUI t = new TileGUI(board.getTile(i, j).getLabel());
				boardGUI[j][i] = t;
				add(t);
			}
		}
	}
	
}
