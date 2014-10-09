package gui;

import client.Client;
import gamelogic.Command;
import gamelogic.Gamelogic;
import gamelogic.Unit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import sound.Sound;

/**
 * Textbased GUI for testing.
 *
 * @author Ludwig Biermann
 * @version 1.1
 */
public class AsciiGraphics implements iGraphic{

    private static final String EXIT = "exit";
    private static final String CONNECT = "connect";
    private static final String VERSION = "ConsoleGraphics v1.1";

    private Client client;

    /**
     * pointer to the GameLogic
     */
    private Gamelogic logic;

    /**
     * Size of the gamefield
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
    @SuppressWarnings("unused")
    private String player2;

    /**
     * Construct a new Console Graphic Engine
     *
     * @param client the core system
     * @param logic the logic system
     * @param sizeX the height of the field
     * @param sizeY the width of the field
     * @param player1 the Player 1
     * @param player2 the Player 2
     */
    public AsciiGraphics(Client client, Gamelogic logic, int sizeX, int sizeY, String player1, String player2) {
        System.out.println("working tilte V0.0");

        this.logic = logic;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.player1 = player1;
        this.player2 = player2;
        this.client = client;

        printGraphicVersion();

    }

    @Override
    public void showText(String text) {
        System.out.println(text);
    }
    
    @Override
    public void nextTurn(){
    	this.initialize();
    }

    @Override
    public void initialize() {

        boolean run = true;
//		boolean connected = false;

        while (run) {

            // reads command
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
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

            // interpret command
//			if (connected && (command.equals(Command.ATTACK) || command.equals(Command.MOVE)) && (line.split(" ", 5).length == 5 || line.split(" ", 5).length == 4)) {
//				System.out.println(command + "!!!!!!!!");
            if ((command.equals(Command.ATTACK) || command.equals(Command.MOVE)) && (line.split(" ", 5).length == 5 || line.split(" ", 5).length == 4)) {
                System.out.println(command + "!!!!!!!!");

                int x = 0;
                int y = 0;
                int targetX = 0;
                int targetY = 0;
                int unitId = -1;

                if (line.split(" ", 5).length == 5) {
                    x = Integer.parseInt(line.split(" ", 5)[1]);
                    y = Integer.parseInt(line.split(" ", 5)[2]);
                    targetX = Integer.parseInt(line.split(" ", 5)[3]);
                    targetY = Integer.parseInt(line.split(" ", 5)[4]);
                    unitId = this.getUnitID(x, y);
                } else {
                    unitId = Integer.parseInt(line.split(" ", 4)[1]);
                    targetX = Integer.parseInt(line.split(" ", 4)[2]);
                    targetY = Integer.parseInt(line.split(" ", 4)[3]);
                }

                if (unitId != -1) {
                    Command c = new Command(command, unitId, targetX, targetY);
//					logic.executeCommand(c);

                    System.out.println("Iterpreted Command: " + c.toString());

                    client.sendCommand(c);
                    
                    run = false;
                    
                } else {
                    System.out.println("ERROR: Unit not found");
                    this.printHelp();
                }

            } else if (command.equals(CONNECT) && line.split(" ", 3).length == 3) {
                String ip = line.split(" ", 3)[1];
                String name = line.split(" ", 3)[2];
                System.out.print("connect to " + ip + " as " + name);
                client.connect(ip, name);
//				connected = true;
                this.refresh();
            } else if (command.equals(EXIT)) {
                System.out.print("BYE BYE");
                run = false;
            } else if (command.equals("soundpls")) {
                Sound.playSound("wolfe.wav");
            } else {
                System.out.println("ERROR");
//				if(!connected){
//					System.out.println("\n First connect to server \n");
//				}
                this.printHelp();
            }


        }

    }
    
    
    /**
     * 
     * renders the game new
     * 
     */
    public void refresh(){

        this.showUnitList();
        this.showGamefield();
    }

    /**
     * prints the Help
     */
    private void printHelp() {
        System.out.println("Need an command (attack or move) + (unit X Coordinate + unit Y Coordinate || unitId ) + target X Coordinate + target Y Coordinate");
        System.out.println("For example attack 0 0 1 1 or attack 3 1 1");
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
            System.out.println("Unit " + u.id + " at: " + u.posX + " : " + u.posY + " Hitpoints: " + u.hitpoints + " Damage: " + u.damage);
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

    @Override
    public String getVersion(){
    	return VERSION;
    }
    
    
    /**
     * prints the current Graphic Version
     */
    private void printGraphicVersion() {
        System.out.print(VERSION);
    }
}
