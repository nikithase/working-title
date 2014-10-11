package network;

import gamelogic.Command;
import gamelogic.Gamelogic;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Used by the network to determine what kind of message is being sent.
 *
 * @author Michael
 */
abstract class NetworkMessage {

    protected static final byte CHAT = 0x01; // chat message
    protected static final byte PLAYERCOMMAND = 0x02; // (unit)-command
    protected static final byte STARTGAME = 0x03; // send starting units
    protected static final byte PAYERSTURN = 0x04; // set the player whos turn it is now

    /**
     * Needed by the server to determine which client sent this message.
     */
    protected String receivedFrom;

    public static NetworkMessage readNetworkMessage(String receivedFrom, ObjectInputStream input) throws IOException, ClassNotFoundException {
        switch (input.readByte()) {
            case NetworkMessage.CHAT:
                String name = input.readUTF();
                String message = input.readUTF();
                return new AllchatNetworkMessage(name, message);
            case NetworkMessage.PLAYERCOMMAND:
                Command command = (Command) input.readObject();
                return new PlayerCommandMessage(command);
            case NetworkMessage.PAYERSTURN:
                String currentPlayer = input.readUTF();
                return new PlayersTurnMessage(currentPlayer);
            case NetworkMessage.STARTGAME:
                Gamelogic gamelogic = (Gamelogic) input.readObject();
                return new StartGameMessage(gamelogic);
            default:
                throw new RuntimeException("Cant receive message, illegal message type!");
        }
    }

    public abstract void writeToStream(ObjectOutputStream stream) throws IOException;

    public abstract void handleOnServer(ServerNetworkMessageHandler messageHandler);

    public abstract void handleOnClient(ClientNetworkMessageHandler messageHandler);
}
