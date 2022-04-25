package dtu.roboRally.view;

import dtu.roboRally.controller.MovingRobotsController;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class MovingRobotsView {

    private MovingRobotsController controller;
    private GridPane layout;


    public MovingRobotsView(MovingRobotsController controller){
        this.controller = controller;
        layout = new GridPane();
    }

    public Scene initGUI(){

        layout.add(new PlayerStatusPanel(controller.getPlayerNames()), 0, 0, 1, 3);
        boardGUI();
        // i need some kind of listeners here, either on robot positions, either on cards?
        //not robot caus it still should update if shield or oil
        //maybe when player.use(card) repaint board? --> which makes that we just have to call phase2() in application

        // need to update the board everytime a card is played
        //okay maybe have a board object and reupload it everytime ? HOW
        return new Scene(layout);
    }

    public void boardGUI(){
        //do stuff
    }

}
