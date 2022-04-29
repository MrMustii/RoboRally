package dtu.roboRally.utils;

import dtu.roboRally.Game;
import dtu.roboRally.Position;
import javafx.scene.control.ProgressBar;

/**
 * Custom ProgressBar used in the game
 */
public class ProgressBarPlayer extends ProgressBar {

    /**
     * Constructor
     * @param robotPosition (Position)
     * @param startPosition (Position)
     * @param endPosition (Position)
     */
    public ProgressBarPlayer(Position robotPosition, Position startPosition, Position endPosition){
        super();
        
        setPrefWidth(50*Game.getInstance().getBoard().getCols());
        
        double totalDist = computeDistance(startPosition, endPosition);
        double currentDist = computeDistance(robotPosition, endPosition);

        double progress = 1-currentDist/totalDist;
        if(progress<0) progress=0;
        setProgress(progress);
    }

    /**
     * helper to compute distance between two positions
     * @param start (Position)
     * @param end (Position)
     * @return (double)
     */
    private double computeDistance(Position start, Position end){

        int x = end.getX() - start.getX();
        int y = end.getY() - start.getY();

        return Math.sqrt(Math.pow(x,2) + Math.pow(y, 2));
    }
}
