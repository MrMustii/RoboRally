package dtu.roboRally.view;

import dtu.roboRally.utils.ImageViewLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;



public class WaitingView {

    public Scene initGUI(){

        HBox hb = new HBox();

        Label label = new Label("Robots connecting...");
        ImageView wifi = ImageViewLoader.loadFile("src/main/resources/images/wifi.png", "wifi",100, 100);

        hb.getChildren().addAll(label, wifi);

        return new Scene(hb);
    }
}
