package fileChecker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Desktop;

public class FileChecker {
    
    public static void main(String[] args) {
        //JFrame frame = new JFrame("FileChecker");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ReadFile read = new ReadFile();
        
        String[] paths = new String[read.getFile().length];

        paths = read.getFile();
        
        String[] fullDirs = new String[paths.length];
        OpenDirectory op = new OpenDirectory();

        int i = 0;
        for (String path : paths){
            try {
                if(!(op.isEmpty(Paths.get(path)))){
                    fullDirs[i] = path;
                    //QUA DEVO AGGIUNGERE IL METODO PER APIRE I FILE CONTENUTI DENTO QUESTA CARTELLA
                    try{
                        Desktop.getDesktop().open(new File(path));
                    } catch (IllegalArgumentException iae){
                        iae.getMessage();
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

                //JLabel label = new JLabel(e.getMessage());
                //frame.add(label);
                //frame.pack();
                //frame.setVisible(true);
            }
        }
    }
}
