package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.SpeakingBehaviour;

import java.util.HashMap;
import java.util.Map;

public class FlyingKoopa extends Koopa {
    /***
     * @author Huiying Lin
     * @version 22/05/2022
     * @see
     */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>();
    private int round = 0;
    public FlyingKoopa() {
        /**
         * Constructor.
         *
         * @param
         */
        super("FlyKoopa", 'F', 150);
        super.addCapability(Flying.FLYING);
    }
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        this.behaviours.put(1, new AttackBehaviour(otherActor));
        this.behaviours.put(2, new FollowBehaviour(otherActor));
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) { // the actor is a player
            actions.add(new AttackAction(this, direction)); // allow the player to attack the plant
        }

        return actions;
    }
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (game.behaviours.Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);

            if (action != null)
                return new SpeakingBehaviour(this);
            if (action != null) {
                if (round % 2 == 0) {
                    round++;
                    return new SpeakingBehaviour(this);
                } else {
                    round++;
                    return action;
                }

            }
        }
        if(round %2 ==0){
            round ++;
            return new SpeakingBehaviour(this);
        }
        round ++;
        return new DoNothingAction();
    }

}
