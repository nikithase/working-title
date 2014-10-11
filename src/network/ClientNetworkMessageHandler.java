package network;

import gamelogic.Command;
import gamelogic.Gamelogic;
import gamelogic.gameobjects.Unit;
import java.util.List;

/**
 * Handles incoming messages from the Server.
 *
 * @author Michael
 */
public interface ClientNetworkMessageHandler {

    /**
     * Start the game with the given starting units.
     *
     * @param startingUnits
     */
    public void startGame(Gamelogic gamelogic);

    /**
     * Receive a message in allchat.
     *
     * @param sender
     * @param text
     */
    public void receiveChatMessage(String sender, String text);

    /**
     * Set the player whos turn it is.
     *
     * @param activePlayer
     */
    public void setActivePlayer(String activePlayer);

    /**
     * Execute a command that was validated by the servfer.
     *
     * @param command
     */
    public void executePlayerCommand(Command command);

}
