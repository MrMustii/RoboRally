package dtu.roboRally.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dtu.roboRally.Game;
import dtu.roboRally.Player;

public class PlayerGUI extends JPanel {
	
	Game game = Game.getInstance(2);
	ArrayList<Player> players = game.getPlayers();
	ArrayList<String> names = new ArrayList<>(Arrays.asList("Julie", "Alberte"));
	ArrayList<Integer> lives = new ArrayList<>();
	
	BufferedImage imageHeart;
	BufferedImage imageEmptyHeart;
	
	
	public void updateLives() {
		lives.clear();
		for(int i = 0; i<players.size(); i++) {
			lives.add(players.get(i).getRobot().getLives());
		}
	}
	
	public void drawHearts(JPanel panel, int lives) {
		for(int i=0; i<lives; i++) {
			JLabel heart = new JLabel(new ImageIcon(imageHeart));
			heart.setPreferredSize(new Dimension(10,10));
			panel.add(heart);
		}
		for(int i=lives; i<5; i++) {
			JLabel emptyHeart = new JLabel(new ImageIcon(imageEmptyHeart));
			emptyHeart.setPreferredSize(new Dimension(10,10));
			panel.add(emptyHeart);
		}
		
	}
	
	public PlayerGUI() {
		super();
		
		try {
			imageHeart = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/heart.png"));
			imageEmptyHeart = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/emptyHeart.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		

		for(int i=0; i<players.size(); i++) {
			lives.add(5);
			JPanel playerName = new JPanel();
			JPanel playerHearts = new JPanel();
			
			JLabel name = new JLabel(names.get(i));
			name.setFont(new Font("Arial", Font.PLAIN, 25));
			playerName.add(name, BorderLayout.WEST);
			
			
			drawHearts(playerHearts, lives.get(i));
			add(playerName, BorderLayout.WEST);
			add(playerHearts, BorderLayout.WEST);
		}
		
	}
	
//	@Override
//	public void paint(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D g2d = (Graphics2D) g;
//		
//		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		g2d.drawImage(imageHeart, 0, 0, null);
//	}
	
}
