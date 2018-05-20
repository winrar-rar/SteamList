import java.io.File;
import java.io.FileNotFoundException;

import fileHandler.TxtHandler;


/**
 * Launcher class.
 * @author Christian Andersson
 *
 */
public class Launcher {
	
	public static void main(String[] args){
		String path1 = "src/SteamList2.txt";
//		String path2 = "src/userdata.json"; fixa!!
		
		TxtHandler handler = new TxtHandler();
		handler.handleFile(new File(path1));
		
	}
}
