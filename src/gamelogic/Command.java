package gamelogic;

/**
 *
 * @author Michael
 */
public class Command {

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
		return null;
	}

	/**
	 * 
	 * Interprets a byte array as a command
	 * 
	 * @return a Command
	 */
	public Command fromBytes() {
		return null;
	}
}
