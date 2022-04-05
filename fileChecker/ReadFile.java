package fileChecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
    public String[] getFile(){
        try {
            int i;
            File directories = new File("fileChecker/directories.txt");
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
            System.out.println("File \'directories.txt\' non trovato, controllare se si trova nella stessa cartella di questo eseguibile");
            e.printStackTrace();
            return new String[0];
        }
    }
}

