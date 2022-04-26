package dtu.roboRally.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileLoader {

    public static FileInputStream loadFile(String path){
        FileInputStream input = null;
        try {
            input = new FileInputStream(path);
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        return input;
    }


}
