package gui;

import java.util.logging.Logger;

public class FXGraphic implements iGui {

	// logger
	private static final Logger log = Logger.getLogger(FXGraphic.class.getName());

	/**
	 * starts a Gui Test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		iGui fx = new FXGraphic();
		log.info("Gui Start Status: " + fx.initialize());
		log.info("Move Action: " + fx.performAction(null));
	}

	/**
	 * 
	 * First: Username and IP
	 * 
	 * Second: Connect to Server /wait for player
	 * 
	 * Third: Load Gamefield
	 * 
	 * 
	 */
	
	@Override
	public boolean initialize() {
		
		login();
		connect();
		loadGamefield();
		
		return true;
	}
	
	
	private void login(){
		
		
	}

	private void connect(){
		
		
	}

	private void loadGamefield(){
		
		
	}
	
	
	public void performAction() {
		
	}
}
