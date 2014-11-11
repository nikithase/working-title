/**
 * 
 */
package gui;

import gamelogic.Command;

/**
 * An Java 8 interface for a JavaFX GUI 
 * 
 * @author Ludwig Biermann
 *
 */
public interface iGui {
	
	/**
	 * starts the GUI
	 * 
	 * @return true if success
	 */
	default boolean initialize(){
		return false;
	}

	/**
	 * 
	 * performs an action like: Move Figure 2 2 to 4 4
	 * 
	 * @return
	 */
	default boolean performAction(Command cmd){
		throw new UnsupportedOperationException("performAction()");
	}
	
}
