package network;

import network.messages.PlayerCommandMessage;
import network.messages.AllchatNetworkMessage;
import gamelogic.Command;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Manages communication with the server.
 *
 * @author Michael
 */
public class ClientNetwork {

    private final Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private final ClientNetworkMessageHandler messageHandler;
    /**
     * The name used to connect to the server.
     */
    private String clientName;

    /**
     * Initialize the Clientnetwork.
     *
     * @param messageHandler
     */
    public ClientNetwork(ClientNetworkMessageHandler messageHandler) {
        socket = new Socket();
        this.messageHandler = messageHandler;
    }

    /**
     * Tries to connect to the host
     *
     * @param host name of the host
     * @param port TCP Port
     * @param name Name of the player
     * @return true if connection success
     */
    public boolean tryConnect(String host, int port, String name) {
        try {
            socket.connect(new InetSocketAddress(host, port));
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            output.writeUTF(name);
            output.flush();
            clientName = name;
            return true;
        } catch (IOException ex) {
            System.out.println("Failed to connect to host \"" + host + "\".");
            return false;
        }
    }

    /**
     * Receive one message from the server and calls the
     * ClientNetworkMEssageHandler to handle it. Blocks until a complete message
     * is received.
     */
    public void receiveMessage() {

        try {
            NetworkMessage message = (NetworkMessage) input.readObject();

            message.handleOnClient(messageHandler);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Send a chat message to all players.
     *
     * @param message
     */
    public void sendChatMessage(String message) {
        try {
            output.writeObject(new AllchatNetworkMessage(clientName, message));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Send a command (such as move or attack) to the server.
     *
     * @param command
     */
    public void sendCommand(Command command) {
        try {
            output.writeObject(new PlayerCommandMessage(command));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
