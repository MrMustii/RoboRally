package dtu.roboRally.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * utils to easily import an image and create an ImageView out of it
 */
public class ImageViewLoader {

    /**
     * constructor that imports the file, sets it as a ImageView and scales it
     * @param path (String) where to find the image
     * @param height (int)
     * @param width (int)
     * @return (ImageView)
     */
    public static ImageView loadFile(String path, int height, int width){
        FileInputStream input = null;
        try {
            input = new FileInputStream(path);
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }

        ImageView image = new ImageView(new Image(input));
        image.setFitHeight(height);
        image.setFitWidth(width);
        return image;
    }


}
