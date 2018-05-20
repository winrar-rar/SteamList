package fileHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author Christian Andersson
 *
 */
public class URLHandler {
	
	private static String appID;
	private Process process;
	
	/**
	 * Sets a new ID.
	 * @param newAppID - The new ID.
	 */
	public void setSearchAppID(String newAppID){
		appID = newAppID;
	}
	
	/**
	 * 
	 * @return - The ID.
	 */
	public String getSearchAppID(){
//		System.out.println(appID);
		return appID;
	}
	
	/**
	 * Takes an ID, opens up Chrome browser and the Steam page of the game with the specific ID.
	 * @param appID - The ID of the game that should be looked for. 
	 */
	public void findProduct(String appID){
		try {
			process = Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\iexplore.exe \"store.steampowered.com/app/" + appID + "\""); //Problem with clothing pages with destroy.
			//process = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe \"store.steampowered.com/app/" + appID);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method used for closing the browser window but not the process.
	 */
	public void shutdownURL(){
		process.destroy();
	}
	
	/**
	 * Makes sure everything is dead at the end.
	 */
	public void kill_All_Pages() {
		try {
//			process = Runtime.getRuntime().exec("TASKKILL /F /IM iexplore.exe");
			process = Runtime.getRuntime().exec("TASKKILL /F /IM chrome.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}