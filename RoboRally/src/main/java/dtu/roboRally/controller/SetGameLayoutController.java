package dtu.roboRally.controller;

import dtu.roboRally.view.SetGameLayoutView;
import javafx.stage.Stage;

public class SetGameLayoutController {
	
	private RoboRallyController application;
	private Stage primaryStage;
	private SetGameLayoutView view;
	
	public SetGameLayoutController(RoboRallyController application, Stage primaryStage) {
		this.application = application;
		this.primaryStage = primaryStage;
		this.view = new SetGameLayoutView(this);
	}
	
	public void display() {
		primaryStage.setScene(view.initGUI());
	}
	
}
