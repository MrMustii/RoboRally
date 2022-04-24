package GUIJavaFX;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Slider slider;
	private int nbOfPlayers;
	
	@FXML
	private VBox container;
	
	public void switchToPlayerNumberScene(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("PlayerNameScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void getSliderValue() {
		nbOfPlayers = (int) slider.getValue();
	}
	
	public void makeTextFields() {
		
		for(int i = 0; i<nbOfPlayers; i++) {
			TextField t = new TextField();
        	t.setText("Player" + String.valueOf(i+1));
        	container.getChildren().add(new HBox(t));
		}
		
	}
	
}
