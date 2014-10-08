package gui;

import gamelogic.Command;
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
	private static final String EXIT = "exit";

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

		boolean run = true;
		
		while (run) {
			
			this.showGamefield();

			BufferedReader console = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.print("Command: ");
			String line = null;
			try {
				line = console.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			String command = line.split(" ", 2)[0];
			
			System.out.println(line);
			
			if(command.equals(Command.ATTACK) || command.equals(Command.MOVE)){
				System.out.println(command +  "!!!!!!!!");
				
				int x = Integer.parseInt(line.split(" ", 5)[1]);
				int y = Integer.parseInt(line.split(" ", 5)[2]);
				int targetX = Integer.parseInt(line.split(" ", 5)[3]);
				int targetY = Integer.parseInt(line.split(" ", 5)[4]);
				
				int unitId = this.getUnitID(x, y);
				
				
				if(unitId != -1){
					Command c = new Command(command, unitId , targetX , targetY );
					logic.executeCommand(c);
				} else {
					System.out.println("Unit not found");
				}
				
				
			} else if(command.equals(EXIT)){
				System.out.print("BYE BYE");
				run = false;
			} else {
				System.out.print("ERROR .... need an command (attack or move) + unit X Coordinate + unit Y Coordinate + target X Coordinate + target Y Coordinate");
				System.out.print("for exampple attack 0 0 1 1");
			}
		}

	}
	
	private int getUnitID(int x, int y){
		for(Unit u :logic.getUnits()){
			if(u.posX == x && u.posY == y){
				return u.id;
			}
		}
		return -1;
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
