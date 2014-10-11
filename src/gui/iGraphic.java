package gui;

import gamelogic.Gamelogic;

/**
 * 
 * Defines the GUI
 * 
 * @author Ludwig Biermann
 *
 */
public interface iGraphic {

	/**
	 * initialize the GUI
	 */
	public void initialize(String position);
	
	/**
	 * informs the GUI to perform the next Turn
	 */
	public void nextTurn();
	
	/**
	 * Show a Text on the GUI ... something like Errors
	 * @param text 
	 */
	public void showText(String text);
	
	/**
	 * Refreshes the displayed Graphics
	 */
	public void refresh();
	
	/**
	 * Returns the current Version of the GUI
	 * 
	 * @return current Version
	 */
	public String getVersion();
        
        public void changeGamelogic(Gamelogic newGameLogic);
}
