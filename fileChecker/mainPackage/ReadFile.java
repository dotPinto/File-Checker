package mainPackage;

import java.awt.Desktop;
import java.awt.Window;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

//import javax.swing.DesktopManager;
//import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.plaf.FileChooserUI;

public class ReadFile {
    private static List<Viewer> array = new ArrayList<>();

    public static List<Viewer> getArray(){
        return array;
    }

    public static void getFile() throws FileNotFoundException{
        JFrame frame = new JFrame("FileChecker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        OpenDirectory op;
        String patternString = "^[a-zA-Z0-9]* --- ";
        try {
            op = new OpenDirectory();
            int i;
            File directories = new File(Paths.get(System.getProperty("user.dir")+"/directories.txt").toString());
            i = (int) Files.lines(Paths.get(System.getProperty("user.dir")+"/directories.txt")).count();
            
            Scanner reader = new Scanner(directories);
            
            i = 0;
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                String temp = data.replaceFirst(patternString,"");
                if (!(op.isEmpty(Paths.get(temp))) && Files.exists(Paths.get(temp),LinkOption.NOFOLLOW_LINKS)){
                    System.err.println((Paths.get(data.replaceFirst(patternString,"")))+" Esiste!!");
                    try{
                        String name[] = data.split(" --- ");
                        //System.err.println(name[0]+" "+name[1]);
                        array.add(new Viewer(name[0],name[1]));
                        i++;
                    } catch (ArrayIndexOutOfBoundsException e){
                        System.err.println(e + " - Nome della cartella o della directory non presente o non trovato");
                    }
                }
                
            }
            reader.close();

        } catch (IOException e){
            JLabel label = new JLabel("File \'directories.txt\' non trovato. \n controllare se si trova nella stessa cartella di questo eseguibile \n Mettere il proprio file in: \n" + System.getProperty("user.dir"));
            frame.setType(Window.Type.POPUP);
            frame.add(label);
            frame.pack();
            frame.setLocation(200, 200);
            frame.setVisible(true);
        }

    }
}

