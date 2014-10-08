package gamelogic;

/**
 * 
 * @author Michael
 */
public class Unit {

    public int id;

    public int hitpoints;

    public int posX;

    public int posY;

    public int movespeed;

    public int damage;

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
