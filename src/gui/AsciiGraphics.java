package gui;

import gamelogic.Command;
import gamelogic.Gamelogic;
import gamelogic.Unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Textbased GUI for testing.
 *
 * @author Michael
 */
public class AsciiGraphics {

	private static final String EXIT = "exit";
	private static final String VERSION = "ConsoleGraphics v1.0";
	
	/**
	 * pointer to the GameLogic
	 */
	private Gamelogic logic;
	
	/**
	 *  Size of the gamefield
	 */
	private int sizeX;
	private int sizeY;
	
	/**
	 * player1
	 */
	private String player1;
	
	/**
	 * player2
	 */
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
	public AsciiGraphics(Gamelogic logic, int sizeX, int sizeY, String player1,
			String player2) {
		System.out.println("working tilte V0.0");

		this.logic = logic;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.player1 = player1;
		this.player2 = player2;
		
		printGraphicVersion();

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

		
		boolean run = true;

		while (run) {

			this.showUnitList();

			this.showGamefield();

			// reads command
			
			BufferedReader console = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.println("\n");
			System.out.print("Command: ");
			String line = null;
			try {
				line = console.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			String command = line.split(" ", 2)[0];

			System.out.println(line);

			//interpret command
			
			if ((command.equals(Command.ATTACK) || command.equals(Command.MOVE)) && line.split(" ", 5).length == 5) {
				System.out.println(command + "!!!!!!!!");
				

				int x = Integer.parseInt(line.split(" ", 5)[1]);
				int y = Integer.parseInt(line.split(" ", 5)[2]);
				int targetX = Integer.parseInt(line.split(" ", 5)[3]);
				int targetY = Integer.parseInt(line.split(" ", 5)[4]);

				int unitId = this.getUnitID(x, y);

				if (unitId != -1) {
					Command c = new Command(command, unitId, targetX, targetY);
					logic.executeCommand(c);
				} else {
					System.out.println("ERROR: Unit not found");
					this.printHelp();
				}

			} else if (command.equals(EXIT)) {
				System.out.print("BYE BYE");
				run = false;
			} else {
				System.out
						.println("ERROR");
				this.printHelp();
			}
		}

	}

	/**
	 * prints the Help
	 */
	private void printHelp(){
		System.out
		.println("Need an command (attack or move) + unit X Coordinate + unit Y Coordinate + target X Coordinate + target Y Coordinate");
System.out.println("For example attack 0 0 1 1");
	}
	
	/**
	 * 
	 * returns the UnitID of an specific field
	 * 
	 * @param x x-Position 
	 * @param y y-Position
	 * @return the unique Unit ID
	 */
	private int getUnitID(int x, int y) {
		for (Unit u : logic.getUnits()) {
			if (u.posX == x && u.posY == y) {
				return u.id;
			}
		}
		return -1;
	}

	/**
	 * prints a List of Units
	 */
	private void showUnitList() {

		System.out.print("\n");
		System.out.print("\n");

		for (Unit u : logic.getUnits()) {
			System.out.println("Unit " + u.id + " at: " + u.posX + " : "
					+ u.posY + " Hitpoints: " + u.hitpoints + " Damage: "
					+ u.damage);
		}
	}

	/**
	 * 0 := empty Field X := Player 1 Y := Player 2
	 */
	private void showGamefield() {

		String[][] field = new String[sizeX][sizeY];

		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field.length; y++) {
				field[x][y] = "0";
			}
		}

		System.out.print("\n");

		for (Unit u : logic.getUnits()) {
			if (u.owner == player1) {
				field[u.posX][u.posY] = "X";
			} else {
				field[u.posX][u.posY] = "Y";
			}

		}
		System.out.print("\n");

		System.out.print(" ");
		System.out.print(" ");
		System.out.print(" ");
		System.out.print(" ");

		for (int y = 0; y < field[0].length; y++) {
			System.out.print(y);
			System.out.print(" ");
		}
		System.out.print("\n");
		
		System.out.print(" ");
		System.out.print(" ");
		System.out.print(" ");
		System.out.print(" ");
		
		for (int y = 0; y < field[0].length; y++) {
			System.out.print("_");
			System.out.print(" ");
		}
		System.out.print("\n");

		for (int x = 0; x < field.length; x++) {
			System.out.print(x + " | ");
			for (int y = 0; y < field[0].length; y++) {
				System.out.print(field[x][y]);
				System.out.print(" ");
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * prints the current Graphic Version
	 */
	public void printGraphicVersion(){
		System.out.print(VERSION);
	}
}
