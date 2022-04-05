package fileChecker;

import java.awt.Desktop;
import java.awt.Window;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.DesktopManager;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ReadFile {
    public String[] getFile(){
        JFrame frame = new JFrame("FileChecker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            int i;
            File directories = new File(Paths.get(System.getProperty("user.dir")+"/fileChecker/directories.txt").toString());
            System.err.println(Paths.get(System.getProperty("user.dir")+"/fileChecker/directories.txt").toString());
            Scanner reader = new Scanner(directories);
            for (i=0;reader.hasNextLine();i++){
                String data = reader.nextLine();
            }
            reader.close();
            reader = new Scanner(directories);
            String[] array = new String[i];
            
            for(i=0;reader.hasNextLine();i++){
                String data = reader.nextLine();
                array[i] = data;
            }
            reader.close();
            return array;
        } catch (FileNotFoundException e){
            JLabel label = new JLabel("File \'directories.txt\' non trovato, controllare se si trova nella stessa cartella di questo eseguibile"+"\n"+"Mettere il proprio file in:" + System.getProperty("user.dir"));
            frame.setType(Window.Type.POPUP);
            frame.add(label);
            frame.pack();
            frame.setLocation(500, 500);
            frame.setVisible(true);
            //System.out.println("File \'directories.txt\' non trovato, controllare se si trova nella stessa cartella di questo eseguibile");
            //e.printStackTrace();
            return new String[0];
        }
    }
}

