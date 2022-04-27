package dtu.roboRally.utils;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;

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
        setPadding(new Insets(40, 0, 0, 40));

        //add playerStatusPanel and Board that we have in each view
        add(psp, 0, 0, 1, 3);
        add(btp, 1, 0, 1, 1);

        //handles the potential button and progressbar
        for(Node n: extraNode){
            if(n instanceof Button){
                add(n, 2,3,1,1);
            }
            if(n instanceof ProgressBarPlayer){
                add(n, 1,1,1,1);
            }
        }
    }
}
