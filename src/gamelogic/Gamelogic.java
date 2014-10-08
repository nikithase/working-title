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
        unitsOnField.add(new Unit(1, (int) Math.pow(Math.random() * 10,
                Math.random() * 10), (int) (Math.random() * 10), (int) (Math
                .random() * 10), (int) Math.pow(Math.random() * 10,
                Math.random() * 10), (int) Math.pow(Math.random() * 10,
                Math.random() * 10), "Peter"));

        unitsOnField.add(new Unit(2, (int) Math.pow(Math.random() * 10,
                Math.random() * 10), (int) (Math.random() * 10), (int) (Math
                .random() * 10), (int) Math.pow(Math.random() * 10,
                Math.random() * 10), (int) Math.pow(Math.random() * 10,
                Math.random() * 10), "Peter"));

        unitsOnField.add(new Unit(3, (int) Math.pow(Math.random() * 10,
                Math.random() * 10), (int) (Math.random() * 10), (int) (Math
                .random() * 10), (int) Math.pow(Math.random() * 10,
                Math.random() * 10), (int) Math.pow(Math.random() * 10,
                Math.random() * 10), "Peter"));

        unitsOnField.add(new Unit(4, (int) Math.pow(Math.random() * 10,
                Math.random() * 10), (int) (Math.random() * 10), (int) (Math
                .random() * 10), (int) Math.pow(Math.random() * 10,
                Math.random() * 10), (int) Math.pow(Math.random() * 10,
                Math.random() * 10), "Klaus"));

        unitsOnField.add(new Unit(5, (int) Math.pow(Math.random() * 10,
                Math.random() * 10), (int) (Math.random() * 10), (int) (Math
                .random() * 10), (int) Math.pow(Math.random() * 10,
                Math.random() * 10), (int) Math.pow(Math.random() * 10,
                Math.random() * 10), "Klaus"));

        unitsOnField.add(new Unit(6, (int) Math.pow(Math.random() * 10,
                Math.random() * 10), (int) (Math.random() * 10), (int) (Math
                .random() * 10), (int) Math.pow(Math.random() * 10,
                Math.random() * 10), (int) Math.pow(Math.random() * 10,
                Math.random() * 10), "Klaus"));

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
