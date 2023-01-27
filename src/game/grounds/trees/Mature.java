package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actors.FlyingKoopa;
import game.actors.Koopa;
import game.grounds.Dirt;

import java.util.Random;

public class Mature extends Tree {
    /***
     * @author Huiying Lin
     * @version 28/04/2022
     * @see
     */

    private int age = 0; // store age
    public Mature() {
        /** Constructor
         * @param  void
         */
        super('T');
    }

    @Override
    public void tick(Location location) {
        /** tick is running each time, make koompa 15%, become dirt 20%
         * when every five it will grow + on the side
         * The rest is applied here as well
         * @param location
         * @return void
         */
        age++;
        super.tick(location);
        // spanm koopa
        if (Utils.idRateCheck(15) && location.getActor() == null){
            //addCapability(EnemyStatus.KOOPA);
            if (Utils.idRateCheck(50)){
                location.addActor(new Koopa("Koopa", 'k', 100));
            }
            else {
                location.addActor(new FlyingKoopa());
            }

        }
        //grow '+' around
        if (age % 5 == 0){
            Random random = new Random();
            location.getExits().get(random.nextInt(location.getExits().size())).getDestination().setGround(new Sprout());
        }
        // 20% to become Dirt
        if (Utils.idRateCheck(20))
            location.setGround(new Dirt());

        if(super.isReset()) {
            if (Utils.idRateCheck(50))
                location.setGround(new Dirt());
            super.changeReset();
        }

    }
}
