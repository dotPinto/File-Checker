package mainPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.awt.Desktop;

public class OpenFile {
    OpenDirectory op;
    
    /*
    public void OpenAllFile() throws FileNotFoundException{
        //dire a questa classe che file aprire e non aprirli tutti
        ReadFile read = new ReadFile();
            
        String[] paths = new String[read.getFile().length];
        Viewer[] viewers;

        viewers = read.getFile();
        
        String[] fullDirs = new String[paths.length];
        op = new OpenDirectory();

        int i = 0;
        for (String path : paths){
            try {
                if(!(op.isEmpty(Paths.get(path)))){
                    fullDirs[i] = path;
                    //QUA DEVO AGGIUNGERE IL METODO PER APIRE I FILE CONTENUTI DENTO QUESTA CARTELLA
                    try{
                        //Desktop.getDesktop().open(new File(path));
                    } catch (Exception e){
                        e.getMessage();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    */

    public void OpenSingleFile(String string){
        op = new OpenDirectory();
        try {
            if(!(op.isEmpty(Paths.get(string)))){
                try{
                    Desktop.getDesktop().open(new File(string));
                } catch (Exception e){
                    e.getMessage();
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
