package dtu.roboRally.XGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import dtu.roboRally.Game;
import dtu.roboRally.Player;

public class MainGUI extends Container {
	
	Game game = Game.getInstance();
	ArrayList<Player> players = game.getPlayers();
	
	protected void makePanel(GridBagLayout gridbag, GridBagConstraints c, Color color) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        gridbag.setConstraints(panel, c);
        add(panel);
    }
	
	protected void makeComponent(GridBagLayout gridbag, GridBagConstraints c, Component comp) {
        JPanel panel = new JPanel();
        gridbag.setConstraints(panel, c);
        panel.add(comp);
        add(panel);
    }
	
	protected void makeProgressBar(GridBagLayout gridbag, GridBagConstraints c) {
		// Sets color of the string on the progress bar
		UIManager.put("ProgressBar.selectionBackground",Color.WHITE);
		UIManager.put("ProgressBar.selectionForeground",Color.WHITE);

		JProgressBar bar = new JProgressBar();
		gridbag.setConstraints(bar, c);
		bar.setMaximum(100);
		bar.setMinimum(0);
		bar.setIndeterminate(true);
		bar.setPreferredSize(new Dimension(800, 30));
		bar.setStringPainted(true);
		bar.setForeground(new Color(204,0,0));
		bar.setBackground(Color.GRAY);
		bar.setBorderPainted(false);
		
		add(bar);
	}
	
	protected void makeHand(GridBagLayout gridbag, GridBagConstraints c, Component comp) {
		JPanel panel = new JPanel();
        gridbag.setConstraints(panel, c);
        panel.add(comp);
        add(panel);
	}
	
	public void init() {
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints c = new GridBagConstraints();
	
	    setFont(new Font("SansSerif", Font.PLAIN, 14));
	    setLayout(gridbag);
	    
	  //Status
        c.fill = GridBagConstraints.BOTH;
//        c.weighty = 1;
//        c.weightx = 0.1;
        c.gridheight = 3;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        makeComponent(gridbag, c, new PlayerGUI());

        //Board
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
//        c.weighty = 0;
//        c.weightx = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 0;
        //c.gridwidth = GridBagConstraints.RELATIVE;
        makeComponent(gridbag, c, new BoardGUI());

        //progressBar
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
//        c.weighty = 0.1;
//        c.weightx = 0.7;
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        makeProgressBar(gridbag, c);

        //hand
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
//        c.weighty = 0.3;
//        c.weightx = 0.7;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        makePanel(gridbag, c, Color.YELLOW);

        //cards
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
//        c.weighty = 1;
//        c.weightx = 0.2;
        c.gridheight = 3;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 0;
        makeComponent(gridbag, c, new HandGUI(players.get(1)));
        
        setSize(300, 100);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame("GridBag Layout Example");
        MainGUI ex1 = new MainGUI();

        ex1.init();

        f.add(ex1);
        f.pack();
        f.setSize(1300,800);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
