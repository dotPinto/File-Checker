package mainPackage;

import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;

public class Viewer implements Iterable {
    public String openDirectory;
    public String label;

    public Viewer(String label,String open){
        this.openDirectory = open;
        this.label = label;
    };

    public String getLabel(){
        return this.label;
    } 

    public String getOpenDirectory(){
        return this.openDirectory;
    }

    public void setLabel(String name){
        label = name;
    }

    public void setOpenDirectory(String dir){
        openDirectory = dir;
    }

    @Override
    public Iterator iterator() {
        // TODO Auto-generated method stub
        return null;
    }
}
