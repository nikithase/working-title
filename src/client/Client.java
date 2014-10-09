package client;

import gamelogic.Command;
import gamelogic.Gamelogic;
import gui.AsciiGraphics;
import gui.CanvasGraphic2D;
import gui.iGraphic;
import network.ClientNetwork;
import sound.Sound;

/**
 *
 * @author Michael
 */
public class Client implements Runnable {

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
    public final static int size = 11;

    private ClientNetwork network;
    private Gamelogic gamelogic;
    private iGraphic graphic;
    private Thread mainloop;

    /**
     * 
     */
    public Client() {
        network = new ClientNetwork();
        gamelogic = new Gamelogic();
        gamelogic.initTestState();
        mainloop = new Thread(this);
        mainloop.setName("Clientmainloop");

        //starts a Console Based Grafik
        //graphic = new AsciiGraphics(this, gamelogic, size, size, PLAYER1, PLAYER2);
        
        graphic = new CanvasGraphic2D(gamelogic, size, size, PLAYER1, this);
        
        Thread graphicsThread = new Thread(new Runnable() {

            @Override
            public void run() {
                graphic.initialize();
            }
        });
        graphicsThread.setName("graphicsThread");
        graphicsThread.start();
    }

    /**
     * Try to connect to a server. Returns true on success, false on failure.
     *
     * @param host the IP of the server
     * @param name the Name of the User
     * @return return true if connection succeeded
     */
    public boolean connect(String host, String name) {
        boolean success = network.tryConnect(host, 12345, name);
        if (success) {
//            Sound.playSound("wolfe.wav");
            mainloop.start();
        }
        graphic.refresh();
        graphic.nextTurn();
        return success;
    }

    /**
     * Send a command to the server.
     *
     * @param command
     */
    public void sendCommand(Command command) {
        network.sendCommand(command);
    }

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new Client();

    }

    @Override
    public void run() {
        while (true) {
            Command command = network.receiveCommand();
            gamelogic.executeCommand(command);
            graphic.refresh();
            graphic.nextTurn();
        }
    }

}
