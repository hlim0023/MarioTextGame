package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.items.Coin;
import game.Resettable;
import game.Status;

public abstract class Tree extends Ground implements Resettable {
    /***
     * @author Huiying Lin
     * @version 30/04/2022
     * @see
     */
    private int once = 0; // make sure rest once only
    private boolean reset = false; // rest-able or not


    public Tree(char s) {
        /**
         * @param char to present
         * Constructor.
         * and setting for reset
         */
        super(s);
        registerInstance();
    }

    public boolean isReset() {
        /**  get reset-able
         * @param  void
         */
        return reset;
    }
    public void changeReset() {
        /**  make it false, cant reset anymore
         * @param  void
         */
        reset = false;
    }

    @Override
    public void tick(Location location) {
        /** tick is running each time, when actor enter to make it dirt and drops money
         * @param location
         * @return void
         * @exception
         * @throws
         */
        if (location.getActor() != null && canActorEnter(location.getActor())) {
            location.setGround(new Dirt());
            location.addItem(new Coin(5,location.x(),location.y(),location.map()));
        }
    }

    @Override
    public void resetInstance() {
        /** this method is use to rest
         * @param void
         * @return
         * @exception
         * @throws
         */
        if (once == 0)
            reset = true ;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        /** This method is using to check the higher ground enter
         * @param actor
         * @return boolean, true or false
         * @exception
         * @throws
         */
        if (actor.hasCapability(Status.TALL)){
            return true;
        }
        return false;
    }
}
