package dtu.roboRally.GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TileGUI extends JPanel {
	
	public static final int PIXEL_SIZE = 50;
	private BufferedImage image;
	
	public TileGUI(String type) {
		super(true);
		
		try {
			this.image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tileImages/"+ type + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setMinimumSize(new Dimension(PIXEL_SIZE, PIXEL_SIZE));
		setMaximumSize(getMinimumSize());
		setPreferredSize(getMinimumSize());
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(image, 0, 0, null);
	}
}

