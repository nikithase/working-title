package gui;

import gamelogic.Gamelogic;
import gamelogic.Unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Textbased GUI for testing.
 *
 * @author Michael
 */
public class AsciiGraphics {

	/**
	 * 
	 */
	private Gamelogic logic;
	private int sizeX;
	private int sizeY;
	private String player1;
	private String player2;

	/**
	 * Does something.
	 * 
	 * @param logic
	 * @param sizeX
	 * @param sizeY
	 * @param player1 
	 * @param player2 
	 */
	public AsciiGraphics(Gamelogic logic, int sizeX, int sizeY, String player1, String player2) {
		System.out.println("working tilte V0.0");

		this.logic = logic;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.player1 = player1;
		this.player2 = player2;

	}

	/**
	 * 
	 * Prints text to the command line.
	 * 
	 * @param text
	 */
	public void printText(String text) {
		System.out.println(text);
	}

	/**
	 * 
	 */
	public void start() {

		while (true) {
			
			this.showGamefield();

			BufferedReader console = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.print("Geben Sie etwas ein: ");
			String zeile = null;
			try {
				zeile = console.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Ihre Eingabe war: " + zeile);

		}

	}

	/**
	 * 0 := empty Field X := Player 1 Y := Player 2
	 */
	private void showGamefield(){
		
		String[][] field = new String[sizeX][sizeY];
		
		for(int x = 0; x < field.length;x++){
			for(int y = 0; y < field.length;y++){
				field[x][y] = "0";
			}
		}

		List<Unit> units = logic.getUnits();

		System.out.print("\n");
		
		for(Unit u :units){
			if(u.owner == player1){
				field[u.posX][u.posY] = "X";
			} else {
				field[u.posX][u.posY] = "Y";
			}
			System.out.println("Unit " + u.id + " at: " + u.posX + " : " +  u.posY);
			
		}
		System.out.print("\n");
		
		for(int x = 0; x < field.length;x++){
			for(int y = 0; y < field.length;y++){
				System.out.print(field[x][y]);
			}
			System.out.print("\n");
		}
	}

}
