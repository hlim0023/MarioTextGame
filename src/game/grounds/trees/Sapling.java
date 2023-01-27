package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.grounds.Dirt;
import game.items.Coin;

public class Sapling extends Tree {
    /***
     * @author Huiying Lin
     * @version 28/04/2022
     * @see
     */
    private int age = 0 ; // store the age

    public Sapling() {
        /** Constructor
         * @param  void
         */
        super('t');
        //addCapability(TreeStatus.SAPLING);
    }

    @Override
    public void tick(Location location) {
        /** tick is running each time, make money 10%, when age = 10 it will grow into the mature
         * The rest is applied here as well
         * @param location
         * @return void
         */
        age++;
        super.tick(location);
        if (Utils.idRateCheck(10)){
            //setDisplayChar('$');
            location.addItem(new Coin(20,location.x(),location.y(),location.map()));
        }
        if (age==10){
            //removeCapability(TreeStatus.);
            location.setGround(new Mature());
        }
        if(super.isReset() ) {
            if (Utils.idRateCheck(50))
                location.setGround(new Dirt());
            super.changeReset();
        }
    }
}
