package network;

import gamelogic.Command;

/**
 *
 * @author Michael
 */
public interface ServerNetworkMessageHandler {

    /**
     * A chat message from a client.
     *
     * @param sender
     * @param message
     */
    public void chatMessage(String player, String message);

    /**
     * A unitcommand from a player.
     *
     * @param player
     * @param command
     */
    public void playerCommand(String player, Command command);

}
