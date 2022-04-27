package dtu.roboRally.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import dtu.roboRally.Player;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PlayerStatusPanel  extends VBox {

    public PlayerStatusPanel(ArrayList<String> playerNames, ArrayList<Integer> robotLives) {
        super();
        setSpacing(60);
        for(int playerIndex = 0; playerIndex<playerNames.size(); playerIndex++) {// TODO: add player lives to the status
            
        	Label name = new Label(playerNames.get(playerIndex));
            name.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
            
            getChildren().add(name);
            
            HBox livesHB = new HBox();
            livesHB.setSpacing(10);
            
            for(int i = 0; i<robotLives.get(playerIndex); i++) {
            	FileInputStream heart = FileLoader.loadFile("src/main/resources/images/heart.png");
            	ImageView heartImageView = new ImageView(new Image(heart));
            	heartImageView.setFitHeight(20);
				heartImageView.setFitWidth(20);
            	livesHB.getChildren().add(heartImageView);
            }
            getChildren().add(livesHB);
        }
    }

}
