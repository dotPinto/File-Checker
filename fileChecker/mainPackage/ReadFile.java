package mainPackage;

import java.awt.Window;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.View;

public class ReadFile {
	static List<Viewer> array = new ArrayList<>();

	public static List<Viewer> getArray(){
		return array;
	}

	public static void clearArray(){
		array.clear();
	}

	static void setArray(Viewer v){
		array.add(new Viewer(v.label, v.openDirectory));
	}

	public static int getNumFiles(String dir) {
		return new File(dir).list().length;
	}

	public static int countLines() {

		Path path = Paths.get(System.getProperty("user.dir")+"/directories.txt");

		int lines = 0;

		try {

			lines = (int) Files.lines(path).count();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;

	}

	public static void getFile() throws FileNotFoundException{
		JFrame frame = new JFrame("FileChecker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		OpenDirectory op;
		String patternString = "^[a-zA-Z0-9]* --- ";
		try {
			op = new OpenDirectory();
			File directories = new File(Paths.get(System.getProperty("user.dir")+"/directories.txt").toString());
			Scanner reader = new Scanner(directories);
			while(reader.hasNextLine()){
				String data = reader.nextLine();
				String temp = data.replaceFirst(patternString,"");
				if (Files.exists(Paths.get(temp),LinkOption.NOFOLLOW_LINKS) && !(op.isEmpty(Paths.get(temp)))){
					try{
						String name[] = data.split(" --- ");
						setArray(new Viewer(name[0],name[1]));
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

