package dtu.roboRally.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewLoader {

    public static ImageView loadFile(String path){
        FileInputStream input = null;
        try {
            input = new FileInputStream(path);
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
   
        return new ImageView(new Image(input));
    }


}
