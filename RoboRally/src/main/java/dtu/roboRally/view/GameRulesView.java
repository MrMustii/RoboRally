package dtu.roboRally.view;

import dtu.roboRally.controller.GameRulesController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;

/**
 * view for the rules
 */
public class GameRulesView {

    private GameRulesController controller;

    /**
     * constructor
     * @param controller (GameRulesController)
     */
    public GameRulesView(GameRulesController controller){
        this.controller = controller;
    }

    /**
     * creates the scene with the rules and the exit button on a scrollpane
     * @return
     */
    public Scene initGUI(){

        VBox vb = new VBox();

        Text title1 = new Text("Rules of the game \n");
        title1.setFont(Font.font("Arial", 40));
        title1.setTextAlignment(TextAlignment.CENTER);


        Text text1 = new Text("The aim of the game is to be the first to bring your robot on the end tile of the board, " +
                "by programming your robots move with the movement cards you pick. Each rounds are divided in two phases," +
                " the programming of the robots, and the robots moving on the board.\n \n");
        text1.setFont(Font.font("Arial", 16));
        text1.setTextAlignment(TextAlignment.JUSTIFY);

        Text title2 = new Text("Set up of the game \n");
        title2.setFont(Font.font("Arial", 25));

        Text text2 = new Text("The first screen let you choose the number of players. After pressing the start game button," +
                " you can then choose the names of the players, or you can keep the default options.\nPlayers are then" +
                " asked to pick their starting positions in a random order. To do that, click on one of the starting position" +
                " tiles, which will place your robot on it, facing towards the right. You cannot undo this choice, " +
                "so pick wisely! Your robot is of the color your name is written in.\nOnce all players picked their" +
                " starting positions, the game can start.\n \n");
        text2.setFont(Font.font("Arial", 16));
        text2.setTextAlignment(TextAlignment.JUSTIFY);


        Text title3 = new Text("Programming the robots\n");
        title3.setFont(Font.font("Arial", 25));

        Text text3 = new Text("The players will then be asked to program their robots with the cards they picked. The " +
                "other players shouldn’t be looking at the choices their adversaries are doing.\nYou can see on the " +
                "right side of the screen the nine cards you have been dealt. You have to pick five on them, in a selected " +
                "order. To do so, you click on the card in your hand, which will move it below the board. You can undo " +
                "your choices by clicking on the cards below the game at anytime.\nWhen you are happy of your choice, " +
                "click the ’confirm cards’ button to let the next player pick their cards, or to launch the second phase if" +
                " all players picked their cards.\n \n");
        text3.setFont(Font.font("Arial", 16));
        text3.setTextAlignment(TextAlignment.JUSTIFY);


        Text title4 = new Text("Robots moving\n");
        title4.setFont(Font.font("Arial", 25));

        Text text4 = new Text("The robots will all move accordingly to their cards. The priorities on which robots use" +
                " their card first is given by the priorities on the cards (which are kept secret). You can then " +
                "excitingly watch the robots move on the board, interact with the objects, or push each other." +
                "\nAt any point of this phase, you can press the ’continue’ button to pass to the next phase " +
                "(programming the robots again).\n \n");
        text4.setFont(Font.font("Arial", 16));
        text4.setTextAlignment(TextAlignment.JUSTIFY);


        Text title5 = new Text("Winning the game\n");
        title5.setFont(Font.font("Arial", 25));

        Text text5 = new Text("To win the game, you have to end you turn on the end tile. Be careful about the other " +
                "robots pushing you out of it! If you do manage to do so, when clicking the continue button at the end " +
                "of the second phase, a new screen will show up to announce the winner and the end of the game. You can" +
                " then exit the game or play again.\n \n");
        text5.setFont(Font.font("Arial", 16));
        text5.setTextAlignment(TextAlignment.JUSTIFY);


        Text title6 = new Text("Description of obstacles\n");
        title6.setFont(Font.font("Arial", 25));

        Text text6 = new Text("Floor: does nothing.\nStart Position: Starting position of the robot. Each player have" +
                " their own starting position that their robot will respawn to if they loose all lives.\nEnd " +
                "Position: Tile to end your turn on to win the game.\nWall: Robot cannot pass the wall. It can" +
                " get on the tile as long as it’s not trying to pass through the wall.\nAcid: Robot loses two " +
                "lives.\nOil: Robot gets spinned in a random orientation. \nPit: Robot dies and respawn.\nRepair: " +
                "Robots gets an extra life. (maximum of 5 lives) Laser shooter: robot cannot move on this tile. Laser" +
                " beam: Robot loses three lives if gets on this tile.\nTeleporter: Robot gets teleported to the " +
                "other teleporter paired with this one. You cannot know which is the paired teleporter before someone" +
                " uses it and discover it.\nConveyor belt: Pushes the robot along the conveyor belt until the robot" +
                " falls out of it.\n \n" );
        text6.setFont(Font.font("Arial", 16));

        Text title7 = new Text("Description of the cards\n");
        title7.setFont(Font.font("Arial", 25));

        Text text7 = new Text("Move forward (1, 2 or 3) : moves the robot in the direction it is facing. \nMove backwards:" +
                " moves the robot in the opposite direction it is facing. \nRotate (clockwise or counterclockwise): rotates" +
                " the robot of 90 degrees. \nU-turn: rotates the robot of 180 degrees.\nShield: robot gets a protection" +
                " until the next obstacle. Only lasts for one damaging obstacle.\n \n");
        text7.setFont(Font.font("Arial", 16));

        TextFlow rules = new TextFlow(title1, text1, title2, text2, title3, text3, title4, text4,
                title5, text5, title6, text6, title7, text7);

        Button exitButton = new Button("EXIT");
        exitButton.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        exitButton.setStyle("-fx-background-color: #32CD32; -fx-border-color: #228B22; -fx-border-width: 7; -fx-text-fill: #FFFFFF");
        exitButton.setPrefSize(250, 80);
        exitButton.setOnAction(value -> {
            controller.exit();
        });

        vb.getChildren().addAll(rules, exitButton);
        vb.setAlignment(Pos.CENTER);
        VBox.setMargin(rules, new Insets(50, 200, 0, 200));
        VBox.setMargin(exitButton, new Insets( 0, 0, 100, 0));

        ScrollPane sb = new ScrollPane();
        sb.setFitToWidth(true);
        sb.setContent(vb);

        return new Scene(sb);
    }
}
