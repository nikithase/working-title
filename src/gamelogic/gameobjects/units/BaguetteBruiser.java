package gamelogic.gameobjects.units;

import gamelogic.gameobjects.Unit;

/**
 *
 * @author Michael
 */
public class BaguetteBruiser extends Unit {

    public final static String NAME = "baguette_bruiser";

    public BaguetteBruiser(int id, int posX, int posY, String owner) {
        super(id, 1500, posX, posY, 1, 50, owner);
        texture = "baguette_bruiser";
    }

}
