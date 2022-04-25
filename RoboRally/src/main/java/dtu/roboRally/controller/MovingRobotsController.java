package dtu.roboRally.controller;

import dtu.roboRally.view.MovingRobotsView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MovingRobotsController {

    private RoboRallyController application;
    private MovingRobotsView view;
    private Stage primaryStage;
    private ArrayList<String> playerNames;

    public MovingRobotsController(RoboRallyController application, Stage primaryStage, ArrayList<String> playerNames){
        this.application = application;
        this.primaryStage = primaryStage;
        this.playerNames = playerNames;

        view = new MovingRobotsView(this);
    }

    public void display() {
        primaryStage.setScene(view.initGUI());
    }

    public ArrayList<String> getPlayerNames(){
        return playerNames;
    }

}
