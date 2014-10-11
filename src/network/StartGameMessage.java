package network;

import gamelogic.Gamelogic;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This message sends the starting units to the clients and tells them that the game begins.
 *
 * @author Michael
 */
class StartGameMessage extends NetworkMessage {

    private Gamelogic gamelogic;

    public StartGameMessage(Gamelogic gamelogic) {
        this.gamelogic = gamelogic;
    }

    @Override
    public void writeToStream(ObjectOutputStream stream) throws IOException {
        stream.writeByte(NetworkMessage.STARTGAME);
        stream.writeObject(gamelogic);
        stream.flush();
    }

    @Override
    public void handleOnServer(ServerNetworkMessageHandler messageHandler) {
        throw new RuntimeException("Client " + receivedFrom + " tried to send a STARTGAME message!");
    }

    @Override
    public void handleOnClient(ClientNetworkMessageHandler messageHandler) {
        messageHandler.startGame(gamelogic);
    }

}
