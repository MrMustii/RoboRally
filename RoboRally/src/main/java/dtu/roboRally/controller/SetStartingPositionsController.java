package dtu.roboRally.controller;

import java.util.ArrayList;

import dtu.roboRally.Game;
import dtu.roboRally.Player;
import dtu.roboRally.view.SetStartingPositionsView;
import javafx.stage.Stage;

public class SetStartingPositionsController {

    private RoboRallyController application;
    private SetStartingPositionsView view;
    private Stage primaryStage;

    private ArrayList<Player> players;

    /**
     * Constructor retrieves relevant data and instantiates the view
     * picks the first random player to pick their SS
     * @param application (RoboRallyController)
     * @param primaryStage (Stage)
     * @param playerNames (ArrayList<String>)
     */
    public SetStartingPositionsController(RoboRallyController application, Stage primaryStage, ArrayList<String> playerNames){
        this.application = application;
        this.primaryStage = primaryStage;
        players = Game.getInstance().getPlayers();
        
        int playerID = getRandomPlayer();
        view = new SetStartingPositionsView(this, playerID, playerNames);
    }

    /**
     * sets the Scene to the primary stage
     */
    public void display() {
    	primaryStage.setScene(view.initGUI());
    }

    /**
     * called when the player picked his starting position
     * either asks the next player to pick theirs, either starts the pickCard phase
     */
    public void nextPlayer(){
        
        if(!allPlayersInitialised()){
            int playerID = getRandomPlayer();
            view.nextPlayerChooseStart(playerID);
            display();
        } else {
            application.managePlayerTurn(primaryStage, 0);
        }
    }

    /**
     * picks a random player that still has to choose their SS
     * @return (int)
     */
    public int getRandomPlayer(){
        int random = (int)Math.floor(Math.random()*players.size());
        if(players.get(random).getRobot() != null){ //the player already has a robot
            return getRandomPlayer();
        } else {
            return random;
        }
    }

    /**
     * checks if all player got their SS
     * @return (boolean)
     */
    public boolean allPlayersInitialised(){
        for(Player p: players){
            if(p.getRobot() == null){
                return false;
            }
        }
        return true;
    }

    /**
     * asks the application to initialize the player's robot on the chosen SS
     * @param playerID (int)
     * @param x (int)
     * @param y (int)
     */
    public void addRobot(int playerID, int x, int y){
        application.initializeRobot(players.get(playerID), x, y);
    }
}
