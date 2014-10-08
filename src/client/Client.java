package client;

import gamelogic.Gamelogic;
import gui.AsciiGraphics;

/**
 *
 * @author Michael
 */
public class Client {

	/**
	 * Name of client 1
	 */
	public final static String PLAYER1 = "player1";
	
	
	/**
	 * Name of client 2
	 */
	public final static String PLAYER2 = "player2"; 
	
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		Gamelogic logic = new Gamelogic();
		
		AsciiGraphics graphic = new AsciiGraphics(logic, 0, 0, PLAYER1, PLAYER2);
		graphic.start();
	}

}
