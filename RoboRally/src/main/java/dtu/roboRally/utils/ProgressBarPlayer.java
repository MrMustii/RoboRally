package dtu.roboRally.utils;

import dtu.roboRally.Position;
import javafx.scene.control.ProgressBar;

public class ProgressBarPlayer extends ProgressBar {

    public ProgressBarPlayer(Position robotPosition, Position startPosition, Position endPosition){
        super();

        double totalDist = computeDistance(startPosition, endPosition);
        double currentDist = computeDistance(robotPosition, endPosition);

        double progress = currentDist/totalDist;
        setProgress(progress);
    }

    private double computeDistance(Position start, Position end){

        int x = end.getX() - start.getX();
        int y = end.getY() - start.getY();

        return Math.sqrt(Math.pow(x,2) + Math.pow(y, 2));
    }
}
