package dtu.roboRally.XGUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dtu.roboRally.Card;
import dtu.roboRally.Player;
import dtu.roboRally.cardTypes.MoveBackwardCard;
import dtu.roboRally.cardTypes.MoveForwardOneCard;
import dtu.roboRally.cardTypes.MoveForwardTwoCard;
import dtu.roboRally.cardTypes.UTurnCard;

public class HandGUI extends JPanel {
	
	ArrayList<Card> hand;
	ArrayList<BufferedImage> imagesHand;
	
	public HandGUI(Player player) {
		
		imagesHand = new ArrayList<>();
		hand = new ArrayList<>(Arrays.asList(new MoveBackwardCard(), new MoveForwardOneCard(), new MoveForwardTwoCard(), new UTurnCard(), new MoveForwardOneCard(), new MoveForwardTwoCard(), new UTurnCard(), new MoveForwardOneCard(), new MoveForwardTwoCard()));
		
		try {
			for(int i = 0; i<hand.size(); i++) {
				imagesHand.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("cardImages/"+hand.get(i).getClass().getSimpleName()+".png")));
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setHandLayout();
		loadCards();
	}
	
	public void setHandLayout() {
		
		setLayout(new GridLayout(3, 3));
		setMinimumSize(new Dimension(100*3, 170*3));
		setMaximumSize(getMinimumSize());
		setPreferredSize(getMinimumSize());
	}
	
	public void loadCards() {
				
		for(int i = 0; i<hand.size(); i++) {
			JLabel card = new JLabel(new ImageIcon(imagesHand.get(i)));
			add(card);
		}
		
	}
}
