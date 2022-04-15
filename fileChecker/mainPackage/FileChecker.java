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
import javax.swing.border.BevelBorder;

import com.formdev.flatlaf.FlatLightLaf;
import com.jtattoo.plaf.aero.AeroBorders;

public class FileChecker extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final String version = "0.1.6";
	public static int HEIGHT = 700 , WIDTH = 450;

	OpenDirectory op;
	JPanel arrayPanel,mainPanel;
	JButton refreshButton;
	ImageIcon openIcon;

	public FileChecker(String title) {

		this.setTitle(title);
		arrayPanel = new JPanel();
		mainPanel = new JPanel();
		//openIcon = new ImageIcon("icona.ico");
		//System.out.println("Codice Apertura icona: " + openIcon.getImageLoadStatus());

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

		if (ReadFile.getArray().size() > 0) {

			JPanel header = new JPanel();
			header.setBounds(0,1,WIDTH,28);
			header.setLayout(null);
			header.setBackground(Color.white);
			header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
			mainPanel.add(header);
			JLabel intNome = new JLabel("Nome");
			intNome.setBounds(50,0,50,30);
			JLabel intNum = new JLabel("Numero file");
			intNum.setBounds(155,0,80,30);
			intNome.setFocusable(false);
			intNum.setFocusable(false);
			intNome.setVisible(true);
			intNum.setVisible(true);
			header.add(intNome);
			header.add(intNum);

			try{
				//num = ReadFile.countLines();
				arrayPanel.setBackground(Color.white);
				arrayPanel.setLayout(null);
				arrayPanel.setBounds(5,35,415,550);
				int i = 0, num = 0;
				// aggiungo al JPanel la label e il pulsante APRI
				for(Viewer viewer : ReadFile.getArray()){

					JLabel space = new JLabel();
					if (i!=0) 
						space.setBounds(35,(-5)+i,370,1);
					space.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
					arrayPanel.add(space);
					JLabel label = new JLabel(viewer.label);
					JLabel numFiles = new JLabel(ReadFile.getNumFiles(viewer.openDirectory) + "");
					numFiles.setBounds(180,5+i,300,32);
					numFiles.setFocusable(false);
					Font f2 = label.getFont();
					label.setFont(f2.deriveFont(f2.getStyle() | Font. BOLD));
					label.setBounds(35,5+i,300,32);
					label.setFocusable(false);
					arrayPanel.add(label);
					arrayPanel.add(numFiles);

					JButton button = new JButton("APRI"); //viewer.openDirectory
					//button.setHorizontalAlignment(SwingConstants.CENTER);
					button.addActionListener(new OpenLine(viewer.openDirectory));
					//button.setIcon(openIcon);
					button.setBounds(300,5+i,100,32);
					button.setFocusable(false);
					arrayPanel.add(button);

					i = i + 50;
					num++;
					//aggiungere avviso che stai sforando fuori dalla finestra
					mainPanel.add(arrayPanel);
					
				}
				
				refreshButton = new JButton("AGGIORNA");
				refreshButton.addActionListener(new RefreshWindow());
				refreshButton.setVisible(true);
				this.add(refreshButton);
				
				switch (num) {
					case 1:
					case 2:
					case 3: 
						this.setSize(WIDTH, HEIGHT/2); 
						mainPanel.setBounds(0,0, WIDTH, HEIGHT/2);
						refreshButton.setBounds(165,HEIGHT/2-80,120,30);
						break;
					case 4: 
					case 5:
						this.setSize(WIDTH, HEIGHT-300); 
						mainPanel.setBounds(0,0, WIDTH, HEIGHT-300);
						refreshButton.setBounds(165,620-300,120,30);
						break;
					case 6:
						this.setSize(WIDTH, HEIGHT-200); 
						mainPanel.setBounds(0,0, WIDTH, HEIGHT-200);
						refreshButton.setBounds(165,620-200,120,30);
						break;
					case 7:
						this.setSize(WIDTH, HEIGHT-150); 
						mainPanel.setBounds(0,0, WIDTH, HEIGHT-150);
						refreshButton.setBounds(165,620-150,120,30);
						break;
					case 8:
						this.setSize(WIDTH, HEIGHT-100); 
						mainPanel.setBounds(0,0, WIDTH, HEIGHT-100);
						refreshButton.setBounds(165,520,120,30);
						break;
					case 11:
					case 12:
					default:
						this.setSize(WIDTH, HEIGHT); 
						refreshButton.setBounds(165,620,120,30);
						break;
				}
				
				mainPanel.revalidate();
				refreshButton.revalidate();

			} catch (NullPointerException np){
				np.getCause();
				System.err.println(np + " array directories vuoto o errore di formattazione in directories.txt");
			}

		} else {

			this.setSize(WIDTH, HEIGHT/2);
			mainPanel.setLayout(null);
			JTextField emp = new JTextField("Non ci sono Documenti da Firmare!");
			emp.setToolTipText("Cliccare 'Aggiorna' oppure chiudere e riaprire l'applicazione");
			emp.setAlignmentY(CENTER_ALIGNMENT);
			emp.setAlignmentX(CENTER_ALIGNMENT);
			emp.setBackground(Color.white);
			emp.setBorder(BorderFactory.createEmptyBorder());
			emp.setEditable(false);
			emp.setFocusable(false);
			emp.setBounds(125,100,250,35);
			mainPanel.add(emp);

			refreshButton = new JButton("AGGIORNA");
			refreshButton.addActionListener(new RefreshWindow());
			refreshButton.setFocusable(false);
			refreshButton.setBounds(175,200,100,35);
			mainPanel.add(refreshButton);
			mainPanel.validate();
		}

		this.revalidate();
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
		run(new FileChecker("File Checker " + version), WIDTH, HEIGHT);
	}

	@SuppressWarnings("static-access")
	public static void run(JFrame frame, int width, int height) { 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setSize(width, height);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);  
		frame.setLayout(null);
		frame.setBackground(Color.white);
		frame.setForeground(Color.white);
		frame.setVisible(true);
		frame.setDefaultLookAndFeelDecorated(true);
	}
}