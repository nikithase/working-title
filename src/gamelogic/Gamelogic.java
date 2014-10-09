package gamelogic;

import gamelogic.gameobjects.Unit;
import gamelogic.gameobjects.testunits.Bauer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Michael
 */
public class Gamelogic {

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
				case Command.MOVE:
					currentUnit.move(command.targetX, command.targetY);
					break;
				case Command.ATTACK:
					for (Iterator<Unit> iter2 = unitsOnField.iterator(); iter2.hasNext();) {
						Unit unitToAttack = iter2.next();
						if (unitToAttack.posX == command.targetX && unitToAttack.posY == command.targetY) {
							currentUnit.attack(unitToAttack);
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

	public void spawnUnit(String nameOfUnit, int posX, int posY, String owner) {
		Unit newUnit;
		switch (nameOfUnit) {
		case Bauer.NAME:
			newUnit = new Bauer(Gamelogic.ID++, posX, posY, owner);
			break;
		default:
			// TODO doesnt add any unit to the field
			return;
		}
		unitsOnField.add(newUnit);
	}
}
