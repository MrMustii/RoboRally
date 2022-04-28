package dtu.roboRally.controller;

import dtu.roboRally.utils.RoboRallyGridPane;
import dtu.roboRally.view.MovingRobotsView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Controller for the scene when the robots are moving
 */
public class MovingRobotsController {

    private RoboRallyController application;
    private MovingRobotsView view;
    private Stage primaryStage;
    private ArrayList<String> playerNames;
    private ArrayList<RoboRallyGridPane> layouts = new ArrayList<>();
    private Pane root = new Pane();
    private Scene scene = new Scene(root);

    /**
     * constructor that retrieves relevant data and instantiates the view
     * @param application (RoboRallyController)
     * @param primaryStage (Stage)
     * @param playerNames (ArrayList<String>)
     */
    public MovingRobotsController(RoboRallyController application, Stage primaryStage, ArrayList<String> playerNames) {
        this.application = application;
        this.primaryStage = primaryStage;
        this.playerNames = playerNames;

        view = new MovingRobotsView(this);
        
//        MovingRobotsView lagView = new MovingRobotsView(this);
//        primaryStage.setScene(new Scene(lagView.initGUI()));
//        display(); // TODO: is it needed?
    }

    /**
     * creates a timeline to display every RoboRallyGridPane scene by scene, one per second
     */
    public void display() {
    	Timeline timeline = new Timeline();
    	timeline.setCycleCount(layouts.size());
    	
    	KeyFrame kf = new KeyFrame(Duration.seconds(1), event -> scene.setRoot(layouts.remove(0)));
    	
    	timeline.getKeyFrames().add(kf);
    	timeline.play();
    	
    	primaryStage.setScene(scene);
    }

    /**
     * called by the application whenever a card is played or a tile is interacted with
     * creates a new RoboRallyGridPane for the move that was just made, and save it in the list layouts
     * layouts will be displayed when all the robots moves were made
     */
    public void addBoardToList() {
    	layouts.add(view.initGUI());    	
    }

    /**
     * manager called by clicking on the 'continue' button
     * either starts a new phase of picking card, either declare winner to application
     */
    public void manageEndOfRound() {
    	if(!application.hasWinner()) {
    		application.managePlayerTurn(primaryStage, 0);
    	} else {
    		application.crownWinner(primaryStage);
    	}
    }

    /**
     * getter for the view to get the player names
     * @return (ArrayList<String>)
     */
    public ArrayList<String> getPlayerNames(){
        return playerNames;
    }
}
