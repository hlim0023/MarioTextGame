package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Utils;
import game.items.BowserKey;

public class ChompsAction extends Action {

    /** The plant attack player
     * @author Huiying Lin
     * @version 22/05/2022
     * @see
     */

    private int ChangeTohit = 50;
    private int damage;
    /**
     * The Actor that is to be attacked
     */
    //protected Actor attacker;
    protected Actor target;


    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public ChompsAction(Actor target,int damage) {
         //this.attacker = actor;
         this.target = target;
         this.damage = damage;

    }

    /** overite the method
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String message
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getDisplayChar() == 'B'){
            String result = "Fire!!!!!" + actor + " attacks " + target + " for " + damage + " damages by FIRE!!!";
            target.hurt(20);
            
        }

        if (Utils.idRateCheck(this.ChangeTohit)) {
            return actor + " misses " + target + ".";
        }
        else {
            String result = actor + " attacks " + target + " for " + damage + " damages.";
            target.hurt(damage);
            if (!target.isConscious()) {
                // if player is dead
                ActionList dropActions = new ActionList();
                // drop all items
                for (Item item : target.getInventory())
                    dropActions.add(item.getDropAction(target));
                for (Action drop : dropActions)
                    drop.execute(target, map);
                // remove player
                map.removeActor(target);
                result += System.lineSeparator() + target + " is killed.";
                return result;
            } else if (!actor.isConscious()) {
                // if actor is dead
                // remove the actor
                map.removeActor(actor);
                result += System.lineSeparator() + actor + " is killed.";
                if (actor.getDisplayChar() == 'B')
                    map.at(4,4).addItem(new BowserKey());
                //result += "\n And Mario has " + this.player.hitPoints + " hit points left";
                return result;
            } else {
                return result;
            }
        }
        //return "dafhe";
    }

    /**  the description to user
     * @param actor The actor performing the action.
     * @return String message
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target;
    }


}
