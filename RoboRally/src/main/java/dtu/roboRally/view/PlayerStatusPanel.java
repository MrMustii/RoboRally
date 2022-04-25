package dtu.roboRally.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class PlayerStatusPanel  extends VBox{

    public PlayerStatusPanel(ArrayList<String> playerNames){
        super();
        setSpacing(60);
        for(String playerName : playerNames) {// TODO: add player lives to the status
            Label name = new Label(playerName);
            name.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
            getChildren().add(name);
        }
    }



}
