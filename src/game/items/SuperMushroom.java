package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Comsume;
import game.grounds.Player;
import game.Status;

public class SuperMushroom extends Item {
    /***
     * @author Huiying Lin
     * @version 30/04/2022
     * @see
     */

    private int count = 0;// turn on the effect, condition

    private Player player;// player who eat it

    public SuperMushroom() {
        /***
         * Constructor.
         *  @param void
         *  add names, display cahr and protable into its parents
         */
        super("Super Mushroom", '^', false);
    }

    @Override
    public void tick(Location currentLocation) {
        /** this method is to active the power star and will check each turn
         * @param location
         * @return voud
         * @exception
         * @throws
         */
        if (this.hasCapability(Comsume.COMSUME) && count == 0) {
            player = (Player) currentLocation.getActor();
            superOn(currentLocation.getActor());
            count++;
        }

        if (player != null && player.getCurrentHp()){
            //System.out.println(player.getCurrentHp()+"/"+player.getMaximumHp());
            superOff(player);
            }


        //currentLocation.getActor().
        super.tick(currentLocation);
    }


    public void superOn(Actor player){
        /** this method is to active the super mushroom
         * @param actor
         * @return voud
         * @exception
         * @throws
         */
        if  (this.hasCapability(Comsume.COMSUME)) {
            super.setDisplayChar('.');
            player.addCapability(Status.SUPER);

            player.increaseMaxHp(50);
        }
    }

    public void superOff(Player player){
        /** this method is to off
         * @param actor
         * @return voud
         * @exception
         * @throws
         */
        player.getInventory().remove(this);
        player.removeCapability(Status.SUPER);
    }


}
