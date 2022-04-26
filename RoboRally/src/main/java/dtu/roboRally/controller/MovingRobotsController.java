package dtu.roboRally.controller;

import java.util.ArrayList;

import dtu.roboRally.Game;
import dtu.roboRally.Player;
import dtu.roboRally.view.MovingRobotsView;
import javafx.stage.Stage;

public class MovingRobotsController {

    private RoboRallyController application;
    private MovingRobotsView view;
    private Stage primaryStage;
    private ArrayList<String> playerNames;

    public MovingRobotsController(RoboRallyController application, Stage primaryStage, ArrayList<String> playerNames) {
        this.application = application;
        this.primaryStage = primaryStage;
        this.playerNames = playerNames;

        view = new MovingRobotsView(this);
        display();
    }

    public void display() {
        primaryStage.setScene(view.initGUI());
      
    }

    public ArrayList<String> getPlayerNames(){
        return playerNames;
    }
    
    public void updateView() {
    	
    	primaryStage.setScene(view.updateRobotsAndLives());
    	//view = new MovingRobotsView(this);
    }
    
    public ArrayList<Integer> getLivesOfRobots() {
    	return application.getLivesOfRobots();
    }
    
    public void resumeGame() {
    	application.managePlayerTurn(primaryStage, 0);
    }

}
