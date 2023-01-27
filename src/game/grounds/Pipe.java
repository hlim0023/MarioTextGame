package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.PiranhaPlant;

public class Pipe extends Ground {
    /***
     * @author Huiying Lin
     * @version 15/05/2022
     * @see
     */

    private int count;
    private boolean spawn;
    private int mapcode;


    /**
     * Constructor
     * @param mapcode
     */
    public Pipe(int mapcode) {
        super('C');
        count = 0;
        spawn = false;
        this.mapcode = mapcode;
    }

    /**
     * overwrite tick
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        count ++;
        super.tick(location);
        if (count >= 1 && !spawn && mapcode == 1 ){
            PiranhaPlant plant = new PiranhaPlant();
            location.addActor(plant);
            this.spawn = true;
        }

    }

    /**
     *
     * @param actor the Actor to check
     * @return
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(Status.TALL)) {
            return true;
        }
        return false;
    }
}