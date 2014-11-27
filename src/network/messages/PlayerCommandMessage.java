package network.messages;

import gamelogic.Command;
import java.io.IOException;
import java.io.ObjectOutputStream;
import network.ClientNetworkMessageHandler;
import network.NetworkMessage;
import network.ServerNetworkMessageHandler;

/**
 * A players action during his turn, like attack or move for example.
 *
 * @author Michael
 */
public class PlayerCommandMessage extends NetworkMessage {

    private Command command;

    public PlayerCommandMessage(Command command) {
        this.command = command;
    }

    @Override
    public void handleOnServer(ServerNetworkMessageHandler messageHandler) {
        messageHandler.playerCommand(receivedFrom, command);
    }

    @Override
    public void handleOnClient(ClientNetworkMessageHandler messageHandler) {
        messageHandler.executePlayerCommand(command);
    }

}
