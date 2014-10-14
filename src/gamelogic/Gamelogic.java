package gamelogic;

import gamelogic.gameobjects.Unit;
import gamelogic.gameobjects.units.BaguetteBruiser;
import gamelogic.gameobjects.units.Bauer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Michael
 */
public class Gamelogic implements Serializable {

    /**
     * Current id to use for next unit to spawn.
     */
    private static int ID = 100;

    private ArrayList<Unit> unitsOnField;

    public Gamelogic() {
        unitsOnField = new ArrayList<Unit>();
    }

    /**
     * Load a testing gamestate.
     */
    public void initTestState() {

        for (int i = 0; i < 10; i++) {

            int posX = (i < 5) ? i : 9 - i;
            int posY = (i < 5) ? 1 : 9;
            String owner = (i < 5) ? "Peter" : "Klaus";

            spawnUnit(Bauer.NAME, posX, posY, owner);
        }
        spawnUnit(BaguetteBruiser.NAME, 5, 1, "Peter");
        spawnUnit(BaguetteBruiser.NAME, 5, 9, "Klaus");
    }

    public void initClientWaitingForOtherPlayersState() {
        int matrix[][]
                = {{1, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                {1, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 0, 1, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 1, 1, 1},
                {0, 0, 0, 1, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 1, 1, 1}};

        for (int x = matrix.length - 1; x >= 0; x--) {
            for (int y = matrix[0].length - 1; y >= 0; y--) {
                if (matrix[y][x] == 1) {
                    spawnUnit(Bauer.NAME, x, y, "nobody");
                }
            }
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

   

    public void spawnUnit(String nameOfUnit, int posX, int posY, String owner) {
        Unit newUnit;
        switch (nameOfUnit) {
            case Bauer.NAME:
                newUnit = new Bauer(Gamelogic.ID++, posX, posY, owner);
                break;
            case BaguetteBruiser.NAME:
                newUnit = new BaguetteBruiser(Gamelogic.ID++, posX, posY, owner);
                break;
            default:
                throw new RuntimeException("Someone tried to create a \"" + nameOfUnit + "\" unit. Blasphemy!");
        }
        unitsOnField.add(newUnit);
    }

    /**
     * Returns the unit with that id.
     *
     * @param unitId
     * @return
     */
    public Unit getUnitById(int unitId) {
        for (Unit unit : unitsOnField) {
            if (unit.id == unitId) {
                return unit;
            }
        }
        throw new IllegalArgumentException("No unit with id " + unitId);
    }

    /**
     * Return the unit on a position.
     *
     * @param targetX
     * @param targetY
     * @return
     */
    Unit getUnitOnPosition(int targetX, int targetY) {
        for (Unit unit : unitsOnField) {
            if (unit.posX == targetX && unit.posY == targetY) {
                return unit;
            }
        }
        throw new IllegalArgumentException("No unit on Position " + targetX + " / " + targetY);
    }
}
