package dtu.roboRally.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import dtu.roboRally.Board;
import dtu.roboRally.Game;
import dtu.roboRally.controller.SetStartingPositionsController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;

public class SetStartingPositionsView {

    private SetStartingPositionsController controller;
    private GridPane layout = new GridPane();
    private int playerID;
    private String playerName;


    public SetStartingPositionsView(SetStartingPositionsController controller, int playerID, String playerName){
        this.controller = controller;
        this.playerID = playerID;
        this.playerName = playerName;
    }

    public Scene initGUI(){
    	
    	layout.setPadding(new Insets(40, 0, 0, 40));
        addLabel();
        addBoardGUI();

        return new Scene(layout);
    }

    public void addLabel(){
        Label playerID = new Label(playerName + ", please pick a starting position by clicking on it");
        layout.add(playerID, 0, 1);
    }

    public void addBoardGUI() {

        Board board = Game.getInstance().getBoard();

        int rows = board.getRows();
        int cols = board.getCols();

        TilePane boardGUI = new TilePane();
        boardGUI.setPrefColumns(cols);
        boardGUI.setPrefRows(rows);

        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                FileInputStream tiles = null;
                try {
                    tiles = new FileInputStream("src/main/resources/tileImages/"+board.getTile(i, j).getLabel()+".png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Image tileImage = new Image(tiles);
                ImageView tileImageView = new ImageView(tileImage);
                tileImageView.setFitHeight(50);
                tileImageView.setFitWidth(50);

                if(board.getTile(i,j).getLabel().equals("S ")){
                    int x = i;
                    int y = j;
                    tileImageView.setOnMouseClicked(value -> {
                        controller.addRobot(playerID, x, y);
                        //TODO: add picture robot
                        //TODO: check if tile occupied?
                        controller.nextPlayer();
                    });
                }
                boardGUI.getChildren().add(tileImageView);
            }
        }

        layout.add(boardGUI, 0, 0, 1, 1);
    }

}
