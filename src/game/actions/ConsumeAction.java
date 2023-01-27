package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Comsume;

public class ConsumeAction extends Action {
    /**
     * @author Huiying Lin
     * @version 1/05/2022
     * @see
     */

    private final Item target;// the target item

    public ConsumeAction(Item target) {
        /** Constructor
         * @param item, the item to consume
         * @return void
         */
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        /** when the hotkey entered execute
         * @param actor, map
         * @return string
         * @exception
         * @throws
         */
        target.addCapability(Comsume.COMSUME);
        //target.getPickUpAction(actor);
        //actor.addItemToInventory(target);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        /** the menu description shown on the concloe
         * @param actor
         * @return void
         * @exception
         * @throws
         */
        return actor + " consumes " + target;
    }

    @Override
    public String hotkey() {
        /** the hotkey before the description
         * @param actor
         * @return string
         * @exception
         * @throws
         */
        return "c";
    }
}

