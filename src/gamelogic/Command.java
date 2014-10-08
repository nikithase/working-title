package gamelogic;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/**
 *
 * @author Michael
 */
public class Command {

    public static final String ATTACK = "attack";
    public static final String MOVE = "move";

    /**
     * create a new command
     *
     * @param command
     * @param unitId
     * @param targetX
     * @param targetY
     */
    public Command(String command, int unitId, int targetX, int targetY) {
        this.command = command;
        this.unitId = unitId;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    /**
     * "move" or "attack"
     */
    public String command;

    /**
     * unique ID for a Unit
     */
    public int unitId;

    /**
     * Target X field
     */
    public int targetX;

    /**
     * Target Y field
     */
    public int targetY;

    /**
     *
     * Converts the command into bytes
     *
     * @return a array of bytes
     */
    public byte[] toBytes() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        buffer.write(command.equals("attack") ? 1 : 0);
        buffer.write(unitId);
        buffer.write(targetX);
        buffer.write(targetY);
        return buffer.toByteArray();
    }

    /**
     *
     * Interprets a byte array as a command
     *
     * @return a Command
     */
    public static Command fromBytes(byte bytes[]) {
        String command = (bytes[0] == 1) ? "attack" : "move";
        int unitId = bytes[1];
        int targetX = bytes[2];
        int targetY = bytes[3];
        return new Command(command, unitId, targetX, targetY);
    }
}
