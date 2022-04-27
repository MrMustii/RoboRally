package dtu.roboRally.utils;

import dtu.roboRally.Game;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

/**
 * Custom VBox that we use in several views
 */
public class PlayerStatusPanel  extends VBox {

    /**
     * Constructor of the box
     * @param playerNames (ArrayList<String>)
     */
    public PlayerStatusPanel(ArrayList<String> playerNames) {
        super();
        setSpacing(60);
        
        for(int playerIndex = 0; playerIndex<playerNames.size(); playerIndex++) {

            //set names
        	Label name = new Label(playerNames.get(playerIndex));
            name.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
            
            getChildren().add(name);

            //set lives
            HBox livesHB = new HBox();
            livesHB.setSpacing(10);

            int lives = 5; //in case the player did not choose the SS yet, it doesnt have a robot yet but it has 5 lives
            if(Game.getInstance().getPlayers().get(playerIndex).getRobot()!=null) {
                lives = Game.getInstance().getPlayers().get(playerIndex).getRobot().getLives();
            }
            
            for(int i = 0; i<lives; i++) {
            	ImageView heartImageView = ImageViewLoader.loadFile("src/main/resources/images/heart.png", 20, 20);
            	livesHB.getChildren().add(heartImageView);
            }
            getChildren().add(livesHB);
        }
    }

}
