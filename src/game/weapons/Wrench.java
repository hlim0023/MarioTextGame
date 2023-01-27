package game.weapons;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;

public class Wrench extends Item implements Weapon {
    /***
     * Constructor.
     */
    private final int damage;
    private final String verb;
    private final int hitRate;

    public Wrench(int damage) {
        super("wrench", 'w', true);
        this.damage = damage;
        this.verb = "zaps";
        this.hitRate = 80;
    }

    @Override
    public int damage() {
        return damage;
    }

    @Override
    public String verb() {
        return "zaps";
    }

    @Override
    public int chanceToHit() {
        return 80;
    }

    @Override
    public String toString(){ return "Wrench";}

}
