package server;

import gamelogic.Command;
import gamelogic.Gamelogic;
import java.util.List;
import network.ServerNetwork;
import network.ServerNetworkMessageHandler;

/**
 * Simple Server. Relays received messages to all clients, including the sender.
 *
 * @author Michael
 */
public class Server implements ServerNetworkMessageHandler {

    private ServerNetwork network;

    public static void main(String args[]) {
        new Server();
    }

    /**
     * Start a server.
     */
    public Server() {
        network = new ServerNetwork(this);

        try {
            while (network.getConnectedClients().size() < 2) {
                Thread.sleep(100);
            }

            Gamelogic gamelogic = new Gamelogic();
            gamelogic.initTestState();
            network.broadcastStartGame(gamelogic);
            
            while (true) {
                Thread.sleep(100);
                network.handleMessages();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void chatMessage(String player, String message) {
        network.broadcastChatMessage(player, message);
    }

    @Override
    public void playerCommand(String player, Command command) {
        network.broadcastPlayerCommand(command);
    }
}
