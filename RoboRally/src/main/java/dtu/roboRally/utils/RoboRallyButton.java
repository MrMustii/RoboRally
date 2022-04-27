package dtu.roboRally.utils;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class RoboRallyButton extends Button {

    public RoboRallyButton(String text){
        super();

        Font font = Font.font("Courier New", FontWeight.BOLD, 28);
        setFont(font);
        setPrefSize(250, 80);

    }
}
