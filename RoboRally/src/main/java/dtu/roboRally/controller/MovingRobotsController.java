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
    	
//    	Task<Void> sleeper = new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                }
//                return null;
//            }
//        };
//        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent event) {
//            	primaryStage.setScene(view.updateRobots());
//            }
//        });
//        new Thread(sleeper).start();
    	
    	primaryStage.setScene(view.updateRobotsAndLives());
    	//view = new MovingRobotsView(this);
    }
    
    public ArrayList<Integer> getLivesOfRobots() {
    	return application.getLivesOfRobots();
    }

}
