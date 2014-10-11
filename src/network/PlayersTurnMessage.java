package network;

import java.io.IOException;
import java.io.ObjectOutputStream;

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
    public void writeToStream(ObjectOutputStream stream) throws IOException {
        stream.writeByte(NetworkMessage.PAYERSTURN);
        stream.writeUTF(currentPlayer);
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
