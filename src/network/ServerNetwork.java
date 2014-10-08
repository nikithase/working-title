package network;

import java.util.List;

/**
 *
 * @author Michael
 */
public class ServerNetwork {

    /**
     * Send message to all clients.
     *
     * @param message
     */
    public void sendMessageToAll(byte[] message) {

    }

    /**
     * Return a list of all received messages. The list can be empty if no messages are received yet.
     *
     * @return a list of all received messages
     */
    public List<byte[]> getMessages() {
        return null;
    }

    /**
     * Return a list of the names of the connected clients.
     *
     * @return a list of the connected clients
     */
    public List<String> getConnectedClients() {
        return null;
    }

}
