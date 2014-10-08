package gamelogic;

/**
 *
 * @author Michael
 */
public class Command {

    /**
     * "move" or "attack"
     *
     * @return
     */
    public String command;

    public int unitId;

    public int targetX;

    public int targetY;

    public byte[] toBytes() {
        return null;
    }

    public Command fromBytes() {
        return null;
    }
}
