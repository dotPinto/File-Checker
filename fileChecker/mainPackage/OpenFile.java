package mainPackage;

import java.io.File;
import java.awt.Desktop;

public class OpenFile {
	OpenDirectory op;

	public void OpenSingleFile(String string){
		op = new OpenDirectory();
		try{
			Desktop.getDesktop().open(new File(string));
		} catch (Exception e){
			e.getMessage();
		}
	}

}
