package network;

import java.io.Serializable;

/**
 * Used by the network to determine what kind of message is being sent.
 *
 * @author Michael
 */
public abstract class NetworkMessage implements Serializable {

    /**
     * Needed by the server to determine which client sent this message.
     */
    protected String receivedFrom;

    public abstract void handleOnServer(ServerNetworkMessageHandler messageHandler);

    public abstract void handleOnClient(ClientNetworkMessageHandler messageHandler);
}
