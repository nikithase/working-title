package network;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * A message in allchat.
 *
 * @author Michael
 */
class AllchatNetworkMessage extends NetworkMessage {

    private String sender;
    private String message;

    public AllchatNetworkMessage(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    @Override
    public void writeToStream(ObjectOutputStream stream) throws IOException {
        stream.writeByte(NetworkMessage.CHAT);
        stream.writeUTF(sender);
        stream.writeUTF(message);
        stream.flush();

    }

    @Override
    public void handleOnServer(ServerNetworkMessageHandler messageHandler) {
        messageHandler.chatMessage(sender, message);
    }

    @Override
    public void handleOnClient(ClientNetworkMessageHandler messageHandler) {
        messageHandler.receiveChatMessage(sender, message);
    }

}
