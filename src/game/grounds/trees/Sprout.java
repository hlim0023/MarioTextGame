package game.grounds.trees;


import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actors.Goomba;
import game.grounds.Dirt;

public class Sprout extends Tree {
    /***
     * @author Huiying Lin
     * @version 28/04/2022
     * @see
     */
    private int age = 0; // storing the age proteted

    public Sprout() {
        /** Constructor
         * @param  void
         */
        super('+');
        //addCapability(TreeStatus.SPROUT);
    }

    @Override
    public void tick(Location location) {
        /** tick is running each time, make goomba 10%, when age = 10 it will grow into the sapling
         * The rest is applied here as well
         * @param location
         * @return void
         */
        age++;
        super.tick(location);
        if (location.getActor() == null && Utils.idRateCheck(10) ){
            location.addActor(new Goomba());
            }
        if (age == 10){
            location.setGround(new Sapling());
        }
        if(super.isReset()) {
            if (Utils.idRateCheck(50))
                location.setGround(new Dirt());
            super.changeReset();
        }
    }



}
