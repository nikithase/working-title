package gamelogic;

import java.io.Serializable;

/**
 * Superclass for all commands used to synchronize between server and client.
 *
 * @author Michael
 */
public abstract class Command implements Serializable {

    private static final long serialVersionUID = 1L;

    public Command() {

    }

    /**
     * Execute this command for the given gamelogic/gamestate.
     *
     * @param gamelogic
     */
    public abstract void executeCommand(Gamelogic gamelogic);

}
