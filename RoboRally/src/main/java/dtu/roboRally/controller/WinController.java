package dtu.roboRally.controller;

import dtu.roboRally.view.WinView;
import javafx.stage.Stage;

public class WinController {
	
	private RoboRallyController application;
	private Stage primaryStage;
	private WinView view;
	
	public WinController(RoboRallyController application, Stage primaryStage, String nameOfWinner) {
		this.application = application;
		this.primaryStage = primaryStage;
		
		view = new WinView(this, nameOfWinner);
	}
	
	public void newGame() {
		try {
			application.start(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void display() {
		primaryStage.setScene(view.initGUI());
	}
	
	
}
