package gamelogic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Michael
 */
public class Gamelogic {

    private ArrayList<Unit> unitsOnField;

    public Gamelogic() {
        unitsOnField = new ArrayList<Unit>();
    }

    /**
     * Load a testing gamestate.
     */
    public void initTestState() {

        for (int i = 0; i < 10; i++) {

            int id = i;
            int hp = 100;
            int posX = (i < 5) ? i : 9 - i;
            int posY = (i < 5) ? 1 : 9;
            int movespeed = 1;
            int damage = 10;
            String owner = (i < 5) ? "Peter" : "Klaus";

            unitsOnField.add(new Unit(id, hp, posX, posY, movespeed, damage, owner));
        }
    }

    /**
     *
     * Returns a List of actual existing units
     *
     * @return List of units
     */
    public List<Unit> getUnits() {
        return unitsOnField;
    }

    /**
     * Executs a Command
     *
     * @param command
     */
    public void executeCommand(Command command) {

        for (Iterator<Unit> iter = unitsOnField.iterator(); iter.hasNext();) {
            Unit currentUnit = iter.next();

            if (currentUnit.id == command.unitId) {

                switch (command.command) {
                    case "move":
                        currentUnit.posX = command.targetX;
                        currentUnit.posY = command.targetY;
                        break;
                    case "attack":
                        for (Iterator<Unit> iter2 = unitsOnField.iterator(); iter2
                                .hasNext();) {
                            Unit unitToAttack = iter2.next();
                            if (unitToAttack.posX == command.targetX
                                    && unitToAttack.posY == command.targetY) {
                                unitToAttack.hitpoints -= currentUnit.damage;
                                if (unitToAttack.hitpoints <= 0) {
                                    iter2.remove();
                                }
                            }
                        }
                        break;
                    default:
                    ;
                }

                return;
            }
        }
    }
}
