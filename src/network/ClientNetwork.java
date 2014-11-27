package network;

import network.messages.PlayerCommandMessage;
import network.messages.AllchatNetworkMessage;
import gamelogic.Command;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * @return true if connection succeeds
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
     * Receive one message from the server and calls its handleOnClient method.
     * Blocks until a complete message is received.
     */
    public void receiveMessage() {

        try {
            NetworkMessage message = (NetworkMessage) input.readObject();
            message.handleOnClient(messageHandler);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Send a message to the server.
     *
     * @param message
     */
    public void sendMessage(NetworkMessage message) {
        try {
            output.writeObject(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}