package gamelogic;

/**
 * 
 * @author Michael
 */
public class Unit {

	/**
	 * The unique ID of the Unit
	 */
    public int id;

    
    /**
     * The current health
     */
    public int hitpoints;

    /**
     * the x Position
     */
    public int posX;

    /**
     * the y Position
     */
    public int posY;

    /**
     * This value represents the possible Movement Speed of this Unit. 
     * Every Points grants the unit to move a field.
     * Can't be negative. 
     */
    public int movespeed;

    /**
     * This value represents the attack power of the unit. 
     * If a unit is hurt, it will lost 1 hit-point per damage point. 
     */
    public int damage;

    /**
     * The name of the player, who controls the unit.
     */
    public String owner;

    public Unit(int id, int hitpoints, int posX, int posY, int movespeed,
            int damage, String owner) {
        this.id = id;
        this.hitpoints = hitpoints;
        this.posX = posX;
        this.posY = posY;
        this.movespeed = movespeed;
        this.damage = damage;
        this.owner = owner;
    }
}
