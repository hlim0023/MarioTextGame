package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 *   @author Dongyi Wang
 *   @version 30/04/2022
 * Special Action for Goomba to attack other player
 */
public class KoopaAttackAction extends Action {
    private int ChangeTohit = 50;
    private int damage = 30;
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
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    public KoopaAttackAction(Actor target, String direction) {
        this.player = target;
        this.direction = direction;
    }

    public void MoveToPlayer(){
        this.behaviours.put(1,new FollowBehaviour(this.player));
    }

    public int getDemage() {
        return this.damage;
    }

    @Override
    public String execute(Actor Koopa, GameMap map) {
        if (Utils.idRateCheck(this.ChangeTohit)) {
            return Koopa + " misses " + player + ".";
        } else {
            int hurt = this.getDemage();
            String result = Koopa + " attacks " + player + " for " + hurt + " damage.";
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
            }
        }
        return null;
    }
    @Override
    public String menuDescription(Actor Koopa) {
        return Koopa + " attacks " + player + " at " + direction;
    }
}

