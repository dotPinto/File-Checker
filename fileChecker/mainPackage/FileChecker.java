package mainPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatLightLaf;

public class FileChecker extends JFrame {
	public static final String version = "0.1.5";
	public static int HEIGHT = 700 , WIDTH = 500;
  
  int num = 0;
  OpenDirectory op;
  JPanel arrayPanel,mainPanel;
  JButton refreshButton;
  ImageIcon openIcon;

  public FileChecker() {
    arrayPanel = new JPanel();
    mainPanel = new JPanel();
    openIcon = new ImageIcon("icona.ico");
    System.out.println("Codice Apertura icona: " + openIcon.getImageLoadStatus());
    

    try {
      UIManager.setLookAndFeel( new FlatLightLaf() );
    } catch (UnsupportedLookAndFeelException e1) {
      e1.printStackTrace();
    }
    
    SwingUtilities.updateComponentTreeUI(this);
    
    try {
        ReadFile.getFile();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
        System.err.println(e + " file directories.txt non trovato o inaccessibile");
    }
    
    mainPanel.setBounds(0,0, WIDTH, HEIGHT);
    mainPanel.setBackground(Color.white);
    mainPanel.setLayout(null);
    
    if (ReadFile.getArray().size() > 0 && ReadFile.getArray().size() < 12) {
    	
	    JLabel nome = new JLabel("File Checker " + version + "\t\t clicca sul pulsante relativo per aprire la cartella");
	    nome.setBounds(25,0,445,25);
	    nome.setHorizontalAlignment(SwingConstants.LEFT);
	    nome.setLayout(new BorderLayout());
	    nome.setBackground(this.getForeground());
	    nome.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
	    mainPanel.add(nome);
	
	    try{
	    	num = ReadFile.countLines();
	      arrayPanel.setBackground(Color.white);
	      arrayPanel.setLayout(null);
	      arrayPanel.setBounds(15,35,415,550);
	      
	      int i = 0;
	      // aggiungo al JPanel la label e il pulsante APRI
	      for(Viewer viewer : ReadFile.getArray()){
	    	  
	          JLabel label = new JLabel(viewer.label);
	          label.setBounds(85,5+i,300,32);
	          label.setFocusable(false);
	          arrayPanel.add(label);
	          
	          JButton button = new JButton("APRI"); //viewer.openDirectory
	          //button.setHorizontalAlignment(SwingConstants.CENTER);
	          button.addActionListener(new OpenLine(viewer.openDirectory));
	          button.setIcon(openIcon);
	          button.setBounds(315,5+i,100,32);
	          button.setFocusable(false);
	          arrayPanel.add(button);
	          
	          i = i + 50;
	          mainPanel.add(arrayPanel);
	      }
	      
	      refreshButton = new JButton("AGGIORNA");
	      refreshButton.addActionListener(new RefreshWindow());
	      refreshButton.setBounds(190,620,120,30);
	      refreshButton.setVisible(true);
	      mainPanel.add(refreshButton);
	      mainPanel.validate();
	
	    } catch (NullPointerException np){
	      np.getCause();
	      System.err.println(np + " array directories vuoto o errore di formattazione in directories.txt");
	    }
	    
    } else if(ReadFile.getArray().size() > 11) {
    	
    	mainPanel.setLayout(null);
    	JTextField emp = new JTextField("Troppe directory impostate");
    	emp.setToolTipText("Aspettare le prossime versioni di File Checker");
    	emp.setAlignmentY(CENTER_ALIGNMENT);
    	emp.setAlignmentX(CENTER_ALIGNMENT);
    	emp.setBackground(Color.white);
    	emp.setBorder(BorderFactory.createEmptyBorder());
    	emp.setEditable(false);
    	emp.setFocusable(false);
    	mainPanel.add(emp);
    	
        refreshButton = new JButton("AGGIORNA");
        refreshButton.addActionListener(new RefreshWindow());
        refreshButton.setFocusable(false);
        emp.setBounds(175,150,250,35);
        refreshButton.setBounds(200,300,100,35);
        mainPanel.add(refreshButton);
        mainPanel.validate();
    }
    else {
    	
    	mainPanel.setLayout(null);
    	JTextField emp = new JTextField("Non ci sono Documenti da Firmare!");
    	emp.setToolTipText("Cliccare 'Aggiorna' oppure chiudere e riaprire l'applicazione");
    	emp.setAlignmentY(CENTER_ALIGNMENT);
    	emp.setAlignmentX(CENTER_ALIGNMENT);
    	emp.setBackground(Color.white);
    	emp.setBorder(BorderFactory.createEmptyBorder());
    	emp.setEditable(false);
    	emp.setFocusable(false);
    	mainPanel.add(emp);
    	
        refreshButton = new JButton("AGGIORNA");
        refreshButton.addActionListener(new RefreshWindow());
        refreshButton.setFocusable(false);
        emp.setBounds(150,150,250,35);
        refreshButton.setBounds(200,300,100,35);
        mainPanel.add(refreshButton);
        mainPanel.validate();
    }

    this.add(mainPanel);
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
      ReadFile.array.clear();
      dispose();
      main(new String[0]);
    }
  }

  public static void main(String[] args) {
    FlatLightLaf.setup();
    run(new FileChecker(), WIDTH, HEIGHT);
  }

  public static void run(JFrame frame, int width, int height) { 
	frame.setName("File Checker " + version);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(width, height);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);  
    frame.setLayout(null);
    frame.setBackground(Color.white);
    frame.setForeground(Color.white);
    frame.setVisible(true);
  }
}