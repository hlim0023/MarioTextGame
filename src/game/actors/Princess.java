package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SavePrincess;
import game.behaviours.Behaviour;
import game.behaviours.SpeakingBehaviour;

import java.util.HashMap;
import java.util.Map;

//import game.behaviours.SpeakingBehaviour;

/***
 * @author Huiying Lin
 * @version 22/05/2022
 * @see
 */
public class Princess extends Actor {


    /**
     * Constructor.
     *
     * @param
     */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>();
    private int round = 0;
    public Princess() {

        super("Princess", 'P', 100);
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

        for (game.behaviours.Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null) {
                if (round % 2 == 0){
                    round++;

                    return new SpeakingBehaviour(this);

                    }

                } else {
                    round++;
                    return action;
                }
            }
        if(round %2 ==0){
            round ++;
            return new SpeakingBehaviour(this);
            }

        round ++;
        return new DoNothingAction();
    }

    /**
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList list = super.allowableActions(otherActor, direction, map);
        //list.add(new KickAction(this));
        for (Exit exit: map.locationOf(this).getExits()) {//check player
            if (exit.getDestination().getActor() != null){
                for (Item item: exit.getDestination().getActor().getInventory()) {//check key
                    if (item.getDisplayChar() == 'k') {
                        list.add(new SavePrincess());
                    }
                }
            }
        }
        return list;
    }
}
