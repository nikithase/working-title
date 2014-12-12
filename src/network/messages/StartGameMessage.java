package network.messages;

import gamelogic.Gamelogic;
import java.io.IOException;
import java.io.ObjectOutputStream;
import network.ClientNetworkMessageHandler;
import network.NetworkMessage;
import network.ServerNetworkMessageHandler;

/**
 * This message sends the starting units to the clients and tells them that the
 * game begins.
 *
 * @author Michael
 */
public class StartGameMessage extends NetworkMessage {

    private Gamelogic gamelogic;

    public StartGameMessage(Gamelogic gamelogic) {
        this.gamelogic = gamelogic;
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
