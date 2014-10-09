package client;

import gamelogic.Command;
import gamelogic.Gamelogic;
import gui.AsciiGraphics;
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
    public final static int size = 10;

    private ClientNetwork network;
    private Gamelogic gamelogic;
    private AsciiGraphics graphic;
    private Thread mainloop;

    public Client() {
        network = new ClientNetwork();
        gamelogic = new Gamelogic();
        gamelogic.initTestState();
        mainloop = new Thread(this);
        mainloop.setName("Clientmainloop");

        graphic = new AsciiGraphics(this, gamelogic, size, size, PLAYER1, PLAYER2);
        Thread graphicsThread = new Thread(new Runnable() {

            @Override
            public void run() {
                graphic.start();
            }
        });
        graphicsThread.setName("graphicsThread");
        graphicsThread.start();
    }

    /**
     * Try to connect to a server. Returns true on success, false on failure.
     *
     * @param host
     * @param name
     * @return
     */
    public boolean connect(String host, String name) {
        boolean success = network.tryConnect(host, 12345, name);
        if (success) {
//            Sound.playSound("wolfe.wav");
            mainloop.start();
        }
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
            graphic.render();
            graphic.start();
        }
    }

}
