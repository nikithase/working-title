package network.messages;

import network.ClientNetworkMessageHandler;
import network.NetworkMessage;
import network.ServerNetworkMessageHandler;

/**
 * A message in allchat.
 *
 * @author Michael
 */
public class AllchatNetworkMessage extends NetworkMessage {

    private String sender;
    private String message;

    public AllchatNetworkMessage(String sender, String message) {
        this.sender = sender;
        this.message = message;
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
