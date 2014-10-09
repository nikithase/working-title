package server;

import gamelogic.Command;
import java.util.List;
import network.ServerNetwork;

/**
 * Simple Server. Relays received messages to all clients, including the sender.
 *
 * @author Michael
 */
public class Server {

    private ServerNetwork network;

    public static void main(String args[]) {
        new Server();
    }

    /**
     * Start a server.
     */
    public Server() {
        network = new ServerNetwork();

        while (true) {
            try {
                Thread.sleep(100);
                List<Command> messages = network.getMessages();
                if (!messages.isEmpty()) {
                    for (Command message : messages) {
                        network.sendMessageToAll(message);
                        System.out.println("message received: " + message.command);
                    }
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
    }
}
