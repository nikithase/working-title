package client;

import gamelogic.Command;
import gamelogic.Gamelogic;
import gui.CanvasGraphic2D;
import gui.iGraphic;
import java.util.Arrays;
import network.ClientNetwork;
import network.ClientNetworkMessageHandler;
import network.messages.PlayerCommandMessage;

/**
 *
 * @author Michael
 */
public class Client implements Runnable, ClientNetworkMessageHandler {

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
    private String name;

    /**
     *
     */
    public Client(String name, String position, boolean autoconnect) {
        if (name != null) {
            this.name = name;
        }
        network = new ClientNetwork(this);
        gamelogic = new Gamelogic();
        gamelogic.initClientWaitingForOtherPlayersState();
        mainloop = new Thread(this);
        mainloop.setName("Clientmainloop");

        graphic = new CanvasGraphic2D(gamelogic, size, size, name, this);

        graphic.initialize(position);
        if (autoconnect) {
            graphic.autoconnect();
        }
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
        network.sendMessage(new PlayerCommandMessage(command));
    }

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        String name = "unknownPlayer";
        if (Arrays.asList(args).contains("--Klaus")) {
            name = "Klaus";
        } else if (Arrays.asList(args).contains("--Peter")) {
            name = "Peter";
        }

        String position = null;
        if (Arrays.asList(args).contains("--left")) {
            position = "--left";
        } else if (Arrays.asList(args).contains("--right")) {
            position = "--right";
        }

        boolean autoconnect = Arrays.asList(args).contains("--autoconnect");

        new Client(name, position, autoconnect);

    }

    @Override
    public void run() {
        while (true) {
            network.receiveMessage();
            graphic.refresh();
            graphic.nextTurn();
        }
    }

    @Override
    public void startGame(Gamelogic gamelogic) {
        this.gamelogic = gamelogic;
        graphic.changeGamelogic(gamelogic);
        graphic.refresh();
        graphic.nextTurn();
    }

    @Override
    public void receiveChatMessage(String sender, String text) {
        System.out.println(sender + ": " + text);
    }

    @Override
    public void setActivePlayer(String activePlayer) {

    }

    @Override
    public void executePlayerCommand(Command command) {
        command.executeCommand(gamelogic);
        graphic.refresh();
        graphic.nextTurn();
    }

}
