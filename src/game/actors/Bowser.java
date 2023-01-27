package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Resettable;
import game.Status;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;

import java.util.HashMap;
import java.util.Map;

public class Bowser extends Actor implements Resettable {
    /***
     * @author Huiying Lin
     * @version 22/05/2022
     * @see
     */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); //behaviour

    private boolean isrestable = false;


    /**
     * Constructor.
     *
     * @param
     */
    public Bowser() {
        super("Bowser", 'B', 500);
        registerInstance();

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
        if (isrestable) {
            super.heal(100000);
            map.moveActor(this,map.at(4,4));
            isrestable = false;
        }

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }
        //map.removeActor(this);

        return new DoNothingAction();
    }
    @Override
    public void resetInstance() {
        isrestable = true;
    }

}
