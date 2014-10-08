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
	public final static String PLAYER1 = "Peter";
	
	
	/**
	 * Name of client 2
	 */
	public final static String PLAYER2 = "Klaus"; 
	

	/**
	 * size of field
	 */
	public final static int size = 10; 
	
	
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		Gamelogic logic = new Gamelogic();
		logic.initTestState();
		
		AsciiGraphics graphic = new AsciiGraphics(logic, size, size, PLAYER1, PLAYER2);
		graphic.start();
	}

}
