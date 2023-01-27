package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Utils;

/**
 *
 *   @author Dongyi Wang
 *   @version 30/04/2022
 * Special Action for Goomba to attack other player
 */
public class GoombaAttackAction extends Action {
    private int ChangeTohit = 50;
    private int damage = 10;
    /**
     * The Actor that is to be attacked
     */
    protected Actor player;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public GoombaAttackAction(Actor target, String direction) {
        this.player = target;
        this.direction = direction;
    }

    public int getDemage() {
        return this.damage;
    }

    @Override
    public String execute(Actor Goomba, GameMap map) {
        if (Utils.idRateCheck(this.ChangeTohit)) {
            return Goomba + " misses " + player + ".";
        } else {
            int hurt = this.getDemage();
            String result = Goomba + " attacks " + player + " for " + hurt + " damage.";
            player.hurt(hurt);
            if (!player.isConscious()) {
                // if player is dead
                ActionList dropActions = new ActionList();
                // drop all items
                for (Item item : player.getInventory())
                    dropActions.add(item.getDropAction(player));
                for (Action drop : dropActions)
                    drop.execute(player, map);
                // remove player
                map.removeActor(player);
                result += System.lineSeparator() + player + " is killed.";
                return result;
            } else if (!Goomba.isConscious()) {
                // if Goomba is dead
                // remove the Goomba
                map.removeActor(Goomba);
                result += System.lineSeparator() + Goomba + " is killed.";
                //result += "\n And Mario has " + this.player.hitPoints + " hit points left";
                return result;
            }
        }
        return null;
    }
    @Override
    public String menuDescription(Actor Goomba) {
        return Goomba + " attacks " + player + " at " + direction;
    }
}

