package dtu.roboRally.view;

import dtu.roboRally.controller.SetGameLayoutController;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class SetGameLayoutView {
	
	private SetGameLayoutController controller;
	
	public SetGameLayoutView(SetGameLayoutController controller) {
		this.controller = controller;
	}
	
	public Scene initGUI() {
		return new Scene(new Label("Temporary label"));
	}
	
}
