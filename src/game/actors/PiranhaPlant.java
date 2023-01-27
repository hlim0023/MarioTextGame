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
import game.behaviours.SpeakingBehaviour;

import java.util.HashMap;
import java.util.Map;
/***
 * @author Huiying Lin
 * @version 15/05/2022
 * @see
 */
public class PiranhaPlant extends Actor implements Resettable {

    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); //behaviour

    private int round = 0;

    private boolean isrestable = false;

    /**
     * Constructor
     * @param
     */
    public PiranhaPlant() {
        super("PiranhaPlant", 'Y', 2);
        registerInstance();
    }


    /**
     * what the player can do to the actor (plant)
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        this.behaviours.put(1, new AttackBehaviour(otherActor));
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) { // the actor is a player
            actions.add(new AttackAction(this, direction)); // allow the player to attack the plant
        }

        return actions;


//
//        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
//        for (Exit highground: map.locationOf(this).getExits()){
//            if (highground.getDestination().getDisplayChar() == 'm' && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
//                //actions.add(new ChompsAction(highground.getDestination().getActor()));
//                System.out.println("allow action ");
//                //this.behaviours.put(1,chomp);
//
//            }
//        }
//
//        return actions;
    }

    /**
     * figuring out what to do
     * what the actor (plant) should do in this round -> atatcak the player (with fire/ weapon/ dhfajkfds),
     * folow drink water dkfak (actions)
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (isrestable) {
            super.increaseMaxHp(50);
            super.heal(10000000);
            isrestable = false;
        }
        for (game.behaviours.Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
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

    @Override
    public void resetInstance() {
        isrestable = true;
    }

}

