package mainPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatLightLaf;

public class FileChooserTest extends JFrame {
	public static final String version = "0.1.5";
  
  int num = 0;
  OpenDirectory op;
  JPanel p,p2;
  Container cp;
  JButton refreshButton;

  public FileChooserTest() {
    p = new JPanel();
    p2 = new JPanel();
    cp = getContentPane();

    try {
      UIManager.setLookAndFeel( new FlatLightLaf() );
    } catch (UnsupportedLookAndFeelException e1) {
      e1.printStackTrace();
    }
    SwingUtilities.updateComponentTreeUI(p);
    
    try {
        ReadFile.getFile();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    
    p2.setLayout(new GridLayout(1,2,0,10));
    JLabel nome = new JLabel(" Nome ");
    nome.setHorizontalAlignment(SwingConstants.CENTER);
    p2.add(nome);
    p2.setAlignmentY(CENTER_ALIGNMENT);
    p2.add(new JLabel("  "));
    cp.add(p2);

    try{
      num = ReadFile.getArray().size();
      p.setLayout(new GridLayout(num,2,0,15));
      p.setLocation(getLocation().x+1,getLocation().y+1);

      // aggiungo al JPanel la label e il pulsante APRI
      for(Viewer viewer : ReadFile.getArray()){ 
          JLabel label = new JLabel(viewer.label);
          label.setHorizontalAlignment(SwingConstants.CENTER);
          //label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
          p.add(label);
          
          JButton button = new JButton("APRI"); //viewer.openDirectory
          button.setHorizontalAlignment(SwingConstants.CENTER);
          button.addActionListener(new OpenLine(viewer.openDirectory));
          p.add(button);
      }
      
      cp.add(p);
      refreshButton = new JButton("AGGIORNA");
      refreshButton.addActionListener(new RefreshWindow());
      cp.add(refreshButton, BorderLayout.SOUTH);
      cp.validate();

    } catch (NullPointerException np){
      np.getCause();
    }
    
    if (num == 0) {
    	//to be added "prova ad aggiornare la pagina!"
    	p.setLayout(new FlowLayout());
    	JTextField emp = new JTextField("Non ci sono Documenti da Firmare!",30);
    	emp.setToolTipText("Aspettare che si aggiorni oppure chiudere e riaprire l'applicazione");
    	emp.setAlignmentY(CENTER_ALIGNMENT);
    	emp.setAlignmentX(CENTER_ALIGNMENT);
    	emp.setMinimumSize(p.getMaximumSize());
	    //da migliorare la presentazione!
	    //emp.setLocation((int)p.getBounds().getCenterX(),(int)p.getBounds().getCenterY());
    	emp.setEditable(false);
    	p.add(emp);
    }

    //dir.setEditable(false);
    //filename.setEditable(false);
  }

  class OpenLine implements ActionListener {
    public String path;

    public OpenLine(String path){
      this.path = path;
    }

    public void actionPerformed(ActionEvent e) {
      //controllo file
      new OpenFile().OpenSingleFile(path);
    }
  }

  class RefreshWindow implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      //p.removeAll();
      //cp.removeAll();
      ReadFile.array.clear();
      dispose();
      //main(new String[0]);
      run(new FileChooserTest(), 450, 220);
    }
  }

  public static void main(String[] args) {
    FlatLightLaf.setup();
    run(new FileChooserTest(), 450, 220);
  }

  public static void run(JFrame frame, int width, int height) { 
	frame.setName("File Checker" + version);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(width, height);
    frame.setLocationRelativeTo(null);  
    frame.setVisible(true);
  }
}