package gamelogic.gameobjects.testunits;

import gamelogic.gameobjects.Minion;

public class Bauer extends Minion {

	public static final String NAME = "Bauer";

	public Bauer(int id, int posX, int posY, String owner) {
		super(id, 100, posX, posY, 1, 10, owner);
	}
}
