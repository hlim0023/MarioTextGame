package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.items.Bottle;

/**
 *   @author Dongyi Wang
 *  A fill action can do by player when player is standing on the fountain
 */
public class FillAction extends Action {
    private Ground position;

    /**
     * test constructor
     * @param ground
     */
    public FillAction(Ground ground) {
        this.position = ground;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.position = map.locationOf(actor).getGround();
        if (map.locationOf(actor).getGround().getDisplayChar() == 'A' ) {
                Bottle.bottleType.push("Power water");
        }
        else if(map.locationOf(actor).getGround().getDisplayChar() == 'H'){
                Bottle.bottleType.push("Healing water");
        }
            //System.out.println(Bottle.bottleTypeStack());
            String result = "Player's bottle : " + Bottle.bottleTypeStack();
            return actor + " refill " + Bottle.bottleType.peek() + "\n" + result;

    }

    /**
     *
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        if (position.getDisplayChar() == 'H') {
            return actor + " refill Healing water ";
        } else if (position.getDisplayChar() == 'A') {
            return actor + " refill Power water ";
        }
        return actor + " cannot refill bottle here";
    }

    /**
     *
     * @return hotkey
     */
    @Override
    public String hotkey(){
        return "b";
    }
}
