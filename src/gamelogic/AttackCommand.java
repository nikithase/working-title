package gamelogic;

import gamelogic.gameobjects.Unit;

/**
 *
 * @author Michael
 */
public class AttackCommand extends Command {

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
     * Create a new AttackCommand.
     *
     * @param unitId
     * @param targetX
     * @param targetY
     */
    public AttackCommand(int unitId, int targetX, int targetY) {
        this.unitId = unitId;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    @Override
    public void executeCommand(Gamelogic gamelogic) {
        Unit attacker = gamelogic.getUnitById(unitId);
        Unit target = gamelogic.getUnitOnPosition(targetX, targetY);
        attacker.attack(target);
        if (target.hitpoints <= 0) {
            gamelogic.getUnits().remove(target);
        }
    }

}
