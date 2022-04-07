package mainPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

import javax.crypto.spec.PBEKeySpec;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Desktop;

public class FileChecker {
    static OpenFile op = new OpenFile();
    ReadFile rf;
    public static void main(String[] args) {
        //JFrame frame = new JFrame("FileChecker");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            ReadFile.getFile();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
