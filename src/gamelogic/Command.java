package gamelogic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {

            ObjectOutputStream output = new ObjectOutputStream(buffer);
            output.writeUTF(command);
            output.writeInt(unitId);
            output.writeInt(targetX);
            output.writeInt(targetY);

            output.flush();
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return buffer.toByteArray();
    }

    /**
     *
     * Interprets a byte array as a command
     *
     * @return a Command
     */
    public static Command fromBytes(byte bytes[]) {
        ObjectInputStream input;
        try {
            input = new ObjectInputStream(new ByteArrayInputStream(bytes));
            String command = input.readUTF();
            int unitId = input.readInt();
            int targetX = input.readInt();
            int targetY = input.readInt();
            return new Command(command, unitId, targetX, targetY);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
