package gamelogic;

import java.io.Serializable;

/**
 *
 * @author Michael
 */
public class Command implements Serializable {

    public static final String ATTACK = "attack";
    public static final String MOVE = "move";
    
    private static final long serialVersionUID = 1L;

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
    
    @Override
    public String toString(){
    	return "Command: " + command + " Unit ID: " + unitId + " targetX: " + targetX + " targetY: " + targetY;
    }
}
