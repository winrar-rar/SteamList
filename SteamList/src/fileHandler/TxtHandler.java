package fileHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


/**
 * 
 * @author Christian Andersson
 *
 */
public class TxtHandler extends FileHandler{
	
	private FileInputStream fileReader;
	private Scanner scanner;
	


	@Override
	/**
	 * Method to iterate through all the lines in the file and saves them to a List.
	 * This is done without keeping references to them (in the memory). 
	 * @param file - The file that should be read.
	 * @return - The List with all the lines.
	 */
	public List<String> readFile(File file) {
		List<String> fileLines = new ArrayList<>();
		
		try {
			try {
				fileReader = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			scanner = new Scanner(fileReader);
			
//			Read all lines in the file and add them to the list.
			String line = null;
			while(scanner.hasNextLine()) {
				line = scanner.nextLine();
				//System.out.println(line);
				fileLines.add(line);
			}
			
			if(scanner.ioException() != null){
				try {
					throw scanner.ioException();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
//		Makes sure Scanner and FileInputStream closes.
		} finally { 
			if(fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(scanner != null) {
				scanner.close();
			}
			
		}
		
		return fileLines;
		
	}//readFile END

}
