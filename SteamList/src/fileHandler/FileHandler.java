package fileHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


/**
 * An abstract class that takes a file makes sure that sends the file to the .
 * @author Christian Andersson
 *
 */
public abstract class FileHandler{
	
	protected static final String rgWishlist = "rgWishlist";
	protected static final String rgOwnedPackages = "rgOwnedPackages";
	protected static final String rgOwnedApps = "rgOwnedApps";
	protected static final String rgPackagesInCart = "rgPackagesInCart";
	protected static final String rgAppsInCart = "rgAppsInCart";
	protected static final String rgIgnoredApps = "rgIgnoredApps";
	protected static final String rgIgnoredPackages = "rgIgnoredPackages";
	
	
	private ArrayList<String> steamList;
	
	private Scanner in = new Scanner(System.in);
	
	private URLHandler url = new URLHandler();
	
	/**
	 * Abstract class that reads a file.
	 * @param file - The file that shall be read.
	 * @return - a list
	 */
	public abstract List<String> readFile(File file);
	
	/**
	 * Takes the file and reads through it, put the lines in a list and sends the list to the handleData method.
	 */
	public void handleFile(File file) {
		List<String> list = readFile(file);
		handleData(list);
	}
	
	/**
	 * 
	 * @param list
	 */
	public void handleData(List<String> list) {
		if(steamList == null) {
			steamList = new ArrayList<String>();
		}
		
		getData(list);
		extractData(steamList);
//		showData(steamList);
		
	}
	
	/**
	 * Removes a character in a String.
	 * @param oldString - The old String.
	 * @return - The new String.
	 */
	private String replaceCharacter(String oldString) {
		String newString = oldString.replaceAll("\\s", "").replace(",", ""); // removes space and , characters.
		return newString;
	}

	/**
	 * Transfer data from the temp list to the steamList (replaces all the space) and removes all data from the old list, freeing it up.
	 * @param list - The old list with all lines from the file.
	 */
	public void getData(List<String> list){
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
		    String line = iterator.next();
		    steamList.add(replaceCharacter(line));
		    iterator.remove();
		}
		
	}
	
	/**
	 * With an input the user can decide which data the user wants to see.
	 * @param list - the list data shall be extracted from.
	 */
	public void extractData(List<String> list){
		System.out.println("Press 1 for Whishlist. Press 2 for Ignoredlist");
		String msg = in.nextLine();
		int input = Integer.valueOf(msg);
			
		switch(input){
			case 1://rgWishlist:
				findList(list, rgWishlist);
				break;
			case 2://rgIgnoredApps:
				findList(list, rgIgnoredApps);
				break;
		}
	
	}
	
	/**
	 * Checks if the correct list has been found and if the boolean is set to true, start adding lines until end brackets appears. 
	 * @param list - The list containing all the lists.
	 * @param listID - The ID that will be used to find the correct list.
	 */
	public void findList(List<String> list, String listID){	
		
		boolean start = false;
		ArrayList<String> foundList = new ArrayList<>();
		
		for(String line : list){

			if(start == true){
				foundList.add(line);
				
				if(line.contains("]")){
//					The bracket will be added before the break, so this needs to be removed.
					foundList.remove(foundList.size()-1);
					break;
				}
			}
					
			if(line.contains(listID)){
				start = true;
			}
		}
		
		showData(foundList);
		start = false;
	}

	/**
	 * Takes a list with IDs and opens the in a browser.
	 * @param list - the list with data that shall be shown.
	 */
	public void showData(List<String> list){
		for(String line : list){
			url.setSearchAppID(line);
			url.findProduct(line);
			
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			url.shutdownURL();
		}
		
		url.kill_All_Pages();
	}


	
}
