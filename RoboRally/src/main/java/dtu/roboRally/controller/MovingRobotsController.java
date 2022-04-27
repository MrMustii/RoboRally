package dtu.roboRally.controller;

import java.util.ArrayList;

import dtu.roboRally.utils.RoboRallyGridPane;
import dtu.roboRally.view.MovingRobotsView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

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
     * constructor that retrieves relavant data and instantiates the view
     * @param application (RoboRallyController)
     * @param primaryStage (Stage)
     * @param playerNames (ArrayList<String>)
     */
    public MovingRobotsController(RoboRallyController application, Stage primaryStage, ArrayList<String> playerNames) {
        this.application = application;
        this.primaryStage = primaryStage;
        this.playerNames = playerNames;

        view = new MovingRobotsView(this);
        display(); // TODO: is it needed?
    }

    public void display() {
    	Timeline timeline = new Timeline();
    	timeline.setCycleCount(layouts.size());
    	
    	KeyFrame kf = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
    		
    		public void handle(ActionEvent event) {
    			scene.setRoot(layouts.remove(0));
    		}
    	});
    	
    	timeline.getKeyFrames().add(kf);
    	timeline.play();
    	
    	primaryStage.setScene(scene);
    }

    /**
     * called by the application whenever a card is played
     * updates the scene and sets it on the primary stage
     */
    public void addBoardToList() {
    	layouts.add(view.initGUI());    	
    }

    /**
     * getter for the view to have the lives of the robots
     * @return (ArrayList<Integer>)
     */
    public ArrayList<Integer> getLivesOfRobots() {
    	return application.getLivesOfRobots();
    }
    
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
