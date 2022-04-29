package dtu.roboRally.controller;

import dtu.roboRally.view.GameRulesView;
import dtu.roboRally.view.MovingRobotsView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameRulesController {

    private SetNumberOfPlayersController SNOPC;
    private GameRulesView view;
    private Stage primaryStage;

    public GameRulesController(SetNumberOfPlayersController application, Stage primaryStage) {
        this.SNOPC = application;
        this.primaryStage = primaryStage;

        view = new GameRulesView(this);

    }

    public void display(){
        primaryStage.setScene(view.initGUI());
    }

    public void exit(){
        SNOPC.display();
    }
}
