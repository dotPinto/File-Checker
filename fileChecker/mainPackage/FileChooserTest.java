package mainPackage;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.DimensionUIResource;

public class FileChooserTest extends JFrame {
  private static final long serialVersionUID = 1L;

private JTextField filename = new JTextField(), dir = new JTextField();

  //private JButton open = new JButton("Apri");
  private JButton open = new JButton("Apri");

  //public static JFileChooser c = new JFileChooser();
  
  JPanel p;
  
  public static FileChooserTest fct = new FileChooserTest();

  public static ReadFile rf;

  int num = 0;
  
  public FileChooserTest() {
    
    mainPackage.FileChecker.main(null);
    p = new JPanel();
    //open.addActionListener(new OpenL());
    //p.add(open);
    Container cp = getContentPane();
    cp.add(p, BorderLayout.CENTER);

    try{
      num = ReadFile.getArray().size();
      p = new JPanel();
      p.setLayout(new GridLayout(num,2,5,5));
      for(Viewer viewer : ReadFile.getArray()){
        //System.err.println(viewer.label + " " + viewer.openDirectory);
        
        JLabel label = new JLabel(viewer.label);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        p.add(label);

        JButton button = new JButton("APRI"); //viewer.openDirectory
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.addActionListener(new OpenLine(viewer.openDirectory));
        p.add(button);

      }

      cp.add(p, BorderLayout.NORTH);
    } catch (NullPointerException np){
      np.getCause();
    }
    
    if (num == 0) {
    	//to be added "prova ad aggiornare la pagina!"
    	JTextField emp = new JTextField("Non ci sono Documenti da Firmare!");
    	emp.setAlignmentY(SwingConstants.CENTER);
    	emp.setAlignmentX(CENTER_ALIGNMENT);
      //da migliorare la presentazione!
      emp.setLocation((int)p.getBounds().getCenterX(),(int)p.getBounds().getCenterY());
    	emp.setEditable(false);
    	p.add(emp);
    }

    //dir.setEditable(false);
    //filename.setEditable(false);

  }
  
  /*
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
      }
    }
  }
  */

  class OpenLine implements ActionListener {
    public String path;

    public OpenLine(String path){
      this.path = path;
    }

    public void actionPerformed(ActionEvent e) {
      //apertura directory con path
      OpenFile op;
      new OpenFile().OpenSingleFile(path);
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
}