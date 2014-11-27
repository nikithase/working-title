package server;

import gamelogic.Command;
import gamelogic.Gamelogic;
import network.ServerNetwork;
import network.ServerNetworkMessageHandler;
import network.messages.StartGameMessage;
import network.messages.AllchatNetworkMessage;
import network.messages.PlayerCommandMessage;

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
            network.broadcastMessage(new StartGameMessage(gamelogic));

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
        network.broadcastMessage(new AllchatNetworkMessage(player, message));
    }

    @Override
    public void playerCommand(String player, Command command) {
        network.broadcastMessage(new PlayerCommandMessage(command));
    }
}
