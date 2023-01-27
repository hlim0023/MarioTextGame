package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Comsume;
import game.Status;

public class PowerStar extends Item {
    /***
     * @author Huiying Lin
     * @version 28/04/2022
     * @see
     */

    //protected local attributes
    private int age = 10 ;// age for remaining
    private int count = 0 ; //  fade away 10 turns
    private Actor player; // player

    public PowerStar() {
        /***
         * Constructor.
         *  @param void
         *
         */
        super("Power Star", '*', false);
    }

    @Override
    public void tick(Location currentLocation) {
        /** This class is using for power star items
         * @param location for the current tick
         * @return void
         * @exception
         * @throws
         */
        if (count >= 10)
            currentLocation.removeItem(this);
        count++;
        if (this.hasCapability(Comsume.COMSUME)) {
            if (age == 10) {
                count =0;
                player = currentLocation.getActor();
                powerOn(player);
                super.setDisplayChar('.');
            }
            System.out.println("Mario is INVINCIBLE!");
            System.out.println("Remaining " + age + " game.");
            age --;
        }
        if (age<= 0){
            // 10 turns
            currentLocation.map().locationOf(player).removeItem(this);
            powerOff(player);

        }
        super.tick(currentLocation);
    }



    public void powerOn(Actor actor){
        /** this method is to active the power star
         * @param actor
         * @return voud
         * @exception
         * @throws
         */
        actor.heal(200);
        actor.removeCapability(Status.HOSTILE_TO_ENEMY);
        actor.addCapability(Status.TALL);
        actor.addCapability(Status.INVINCIBLE);
    }

    public void powerOff(Actor player){
        /** this method is using to off the effect
         * @param the actor player
         * @return void
         * @exception
         * @throws
         */
        this.removeCapability(Comsume.COMSUME);
        player.removeCapability(Status.TALL);
        player.addCapability(Status.HOSTILE_TO_ENEMY);
        //player.getInventory().remove(this);

    }


}
