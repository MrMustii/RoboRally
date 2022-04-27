package dtu.roboRally.controller;

import dtu.roboRally.view.MovingRobotsView;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Controller for the scene when the robots are moving
 */
public class MovingRobotsController {

    private RoboRallyController application;
    private MovingRobotsView view;
    private Stage primaryStage;
    private ArrayList<String> playerNames;

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

    /**
     * sets the Scene to the primary stage
     */
    public void display() {
        primaryStage.setScene(view.initGUI());
    }

    /**
     * called by the application whenever a card is played
     * updates the scene and sets it on the primary stage
     */
    public void updateView() {
    	
    	primaryStage.setScene(view.updateRobotsAndLives());
    	//view = new MovingRobotsView(this);
    }

    /**
     * getter for the view to have the lives of the robots
     * @return (ArrayList<Integer>)
     */
    public ArrayList<Integer> getLivesOfRobots() {
    	return application.getLivesOfRobots();
    }
    
    public void resumeGame() {
    	application.managePlayerTurn(primaryStage, 0);
    }

    /**
     * getter for the view to get the player names
     * @return (ArrayList<String>)
     */
    public ArrayList<String> getPlayerNames(){
        return playerNames;
    }

}
