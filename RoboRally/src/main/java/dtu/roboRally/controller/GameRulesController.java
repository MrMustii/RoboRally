package dtu.roboRally.controller;

import dtu.roboRally.view.GameRulesView;
import javafx.stage.Stage;

/**
 * Controller for the games rule
 */
public class GameRulesController {

    private SetNumberOfPlayersController SNOPC;
    private GameRulesView view;
    private Stage primaryStage;

    /**
     * constructor
     * @param application (SetNumberOfPlayersController)
     * @param primaryStage (Stage)
     */
    public GameRulesController(SetNumberOfPlayersController application, Stage primaryStage) {
        this.SNOPC = application;
        this.primaryStage = primaryStage;

        view = new GameRulesView(this);

    }

    /**
     * sets the scene on the stage
     */
    public void display(){
        primaryStage.setScene(view.initGUI());
    }

    /**
     * displays the SetNumberOfPlayerView
     */
    public void exit(){
        SNOPC.display();
    }
}
