package fileChecker;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FileChooserTest extends JFrame {
  private static final long serialVersionUID = 1L;

private JTextField filename = new JTextField(), dir = new JTextField();

  private JButton open = new JButton("Apri");//, save = new JButton("Save");
  
  private JTextField label = new JTextField("Cerca nel tuo computer il file che contiene le directory");

  public static JFileChooser c = new JFileChooser();
  
  JPanel p;
  
  public static FileChooserTest fct = new FileChooserTest();
  
  public FileChooserTest() {
    p = new JPanel();
    
    open.addActionListener(new OpenL());
    p.add(open);
    Container cp = getContentPane();
    cp.add(p, BorderLayout.SOUTH);
    dir.setEditable(false);
    filename.setEditable(false);
    label.setEditable(false);
    p = new JPanel();
    p.setLayout(new GridLayout(3, 1));
    p.add(label);
    p.add(filename);
    p.add(dir);
    cp.add(p, BorderLayout.NORTH);
  }
  
  public void setFile(JFileChooser c) {
	  c = this.c;
  }
  
  public static String getFile() {
	  return c.getSelectedFile().getAbsolutePath();
  }

  class OpenL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      int rVal = c.showOpenDialog(FileChooserTest.this);
      if (rVal == JFileChooser.APPROVE_OPTION) {
        c.getSelectedFile().getName();
        c.getCurrentDirectory().toString();
        setFile(c);
        fct.dispose();
        
        fileChecker.FileChecker.main(null);
      }
    }
  }

  public static void main(String[] args) {
    run(fct, 450, 220);
  }

  public static void run(JFrame frame, int width, int height) {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(width, height);
    frame.setVisible(true);
  }
} ///:~