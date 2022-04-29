package dtu.roboRally.utils;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 * helper class in order to have the same layout for each view and reduce code duplicates
 * WARNING does not add the layout for CardGUI if used in PickCardView, add it afterwards!
 */
public class RoboRallyGridPane extends GridPane {

    /**
     * constructor that sets the wanted layout
     * @param psp (PlayerStatusPanel)
     * @param btp (BoardTilePane)
     * @param extraNode (Node) Button or ProgressBar (can be empty)
     */
    public RoboRallyGridPane(PlayerStatusPanel psp, BoardTilePane btp, Node... extraNode){
        super();

        //sets general layout
        setVgap(20);
        setHgap(20);
        setPadding(new Insets(10, 0, 0, 40));
        
        Label title = new Label("RoboRally");
        title.setFont(Font.font("Arial Black", 40));
        add(title, 0, 0, 3, 1);
        
        //add playerStatusPanel and Board that we have in each view
        add(psp, 0, 1, 1, 3);
        add(btp, 1, 1, 1, 1);

        //handles the potential button and progressbar
        for(Node n: extraNode){
            if(n instanceof Button){
                add(n, 2,4,1,1);
            }
            if(n instanceof ProgressBarPlayer){
                add(n, 1,2,1,1);
            }
        }
    }
}
