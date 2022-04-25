package dtu.roboRally.controller;

import dtu.roboRally.Game;
import dtu.roboRally.Player;
import dtu.roboRally.view.SetStartingPositionsView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SetStartingPositionsController {

    private RoboRallyController application;
    private SetStartingPositionsView view;
    private Stage primaryStage;

    private ArrayList<Player> players;
    private ArrayList<String> playerNames;

    public SetStartingPositionsController(RoboRallyController application, Stage primaryStage, ArrayList<String> playerNames){
        this.application = application;
        this.primaryStage = primaryStage;
        this.playerNames = playerNames;
        players = Game.getInstance().getPlayers();

        nextPlayer();
    }

    public void nextPlayer(){
        if(!allPlayerInitialised()){
            int playerID = getRandomPlayer();
            view = new SetStartingPositionsView(this, playerID, playerNames.get(playerID));
            primaryStage.setScene(view.initGUI());
        }else{
            application.managePlayerTurn(primaryStage, 0);
        }
    }

    public int getRandomPlayer(){

        int random = (int)Math.floor(Math.random()*players.size());

        if(players.get(random).getRobot() != null){ //the player already has a robot
            return getRandomPlayer();
        }else{
            return random;
        }
    }

    public boolean allPlayerInitialised(){
        for(Player p: players){
            if(p.getRobot() == null){
                return false;
            }
        }
        return true;
    }

    public void addRobot(int playerID, int x, int y){
        application.initializeRobot(players.get(playerID), x,y);
    }
}
