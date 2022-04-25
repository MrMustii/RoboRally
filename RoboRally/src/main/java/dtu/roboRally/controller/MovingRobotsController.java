package dtu.roboRally.controller;

import java.util.ArrayList;

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
    	primaryStage.setScene(view.updateRobots());
    	//view = new MovingRobotsView(this);
    }

}
