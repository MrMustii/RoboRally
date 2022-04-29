package dtu.roboRally.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * utils to easily import an image and create an ImageView out of it
 */
public class ImageViewLoader {
	
	private static ArrayList<Image> images = new ArrayList<>();
	private static ArrayList<String> ids = new ArrayList<>();

    /**
     * constructor that imports the file, sets it as a ImageView and scales it
     * @param path (String) where to find the image
     * @param height (int)
     * @param width (int)
     * @return (ImageView)
     */
    public static ImageView loadFile(String path, String id, int height, int width){
    	ImageView imageView;
    	if(ids.contains(id)) {
    		int index = ids.indexOf(id);
    		imageView = new ImageView(images.get(index));
    	} else {
    		FileInputStream input = null;
    	    try {
	            input = new FileInputStream(path);
    	     } catch (FileNotFoundException e) {
	            e.printStackTrace();
	         }
    	    Image image = new Image(input);
    	    images.add(image);
    	    ids.add(id);
	        imageView = new ImageView(image);
    	}
    	
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        return imageView;
    }


}
