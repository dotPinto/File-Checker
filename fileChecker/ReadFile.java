package fileChecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
    public String[] getFile(){
        try {
            File directories = new File("fileChecker/directories.txt");
            Scanner reader = new Scanner(directories);
            int i = 0;
            while (reader.hasNextLine()){
                String data = reader.nextLine();
                i++;
            }
            reader.close();
            reader = new Scanner(directories);
            String[] array = new String[i];
            i=0;
            while (reader.hasNextLine()){
                String data = reader.nextLine();
                array[i] = data;
            }
            reader.close();
            System.err.println(array);
            return array;
        } catch (FileNotFoundException e){
            System.out.println("File \'directories.txt\' non trovato, controllare se si trova nella stessa cartella di questo eseguibile");
            e.printStackTrace();
            return new String[0];
        }
    }
}

