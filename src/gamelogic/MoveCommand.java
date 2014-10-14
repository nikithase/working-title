package gamelogic;

import gamelogic.gameobjects.Unit;

/**
 *
 * @author Michael
 */
public class MoveCommand extends Command {

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

    public MoveCommand(int unitId, int targetX, int targetY) {
        this.unitId = unitId;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    @Override
    public void executeCommand(Gamelogic gamelogic) {
        Unit unit = gamelogic.getUnitById(unitId);
        unit.move(targetX, targetY);
    }

}
