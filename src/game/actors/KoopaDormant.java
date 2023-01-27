package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.Behaviour;
import game.Resettable;

public class KoopaDormant extends Actor implements Resettable, Behaviour {
    private boolean isrestable = false;
    /**
     * Constructor
     *   @author Dongyi Wang
     *   @version 30/04/2022
     */
    public KoopaDormant() {
        super("Koopa", 'D', 50);
        registerInstance();
    }

    /**
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (isrestable) {
            map.removeActor(this);
            isrestable = false;
        }
        else {
            return new DoNothingAction();
        }
        return new DoNothingAction();
    }

    @Override
    public void resetInstance() {
        isrestable = true;
    }

    @Override
    // do nothing
    /**
     *
     */
    public Action getAction(Actor actor, GameMap map) {
        return null;
    }
}
