package network.messages;

import java.io.IOException;
import java.io.ObjectOutputStream;
import network.ClientNetworkMessageHandler;
import network.NetworkMessage;
import network.ServerNetworkMessageHandler;

/**
 * Tells all clients whos turn it is.
 *
 * @author Michael
 */
class PlayersTurnMessage extends NetworkMessage {

    private String currentPlayer;

    public PlayersTurnMessage(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    @Override
    public void handleOnServer(ServerNetworkMessageHandler messageHandler) {
        throw new RuntimeException("Client " + receivedFrom + " tried to send a PLAYERSTURN message!");
    }

    @Override
    public void handleOnClient(ClientNetworkMessageHandler messageHandler) {
        messageHandler.setActivePlayer(currentPlayer);
    }

}
