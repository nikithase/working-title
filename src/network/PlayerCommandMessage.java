package network;

import gamelogic.Command;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * A players action during his turn, like attack or move for example.
 *
 * @author Michael
 */
class PlayerCommandMessage extends NetworkMessage {

    private Command command;

    public PlayerCommandMessage(Command command) {
        this.command = command;
    }

    @Override
    public void writeToStream(ObjectOutputStream stream) throws IOException {
        stream.writeByte(NetworkMessage.PLAYERCOMMAND);
        stream.writeObject(command);
        stream.flush();
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
