package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.Behaviour;
import game.behaviours.SpeakingBehaviour;
import game.behaviours.WanderBehaviour;
import game.Resettable;

import java.util.HashMap;
import java.util.Map;

public class Koopa extends Actor implements Resettable {
    /***
     * @author
     * @version 28/04/2022
     * @see
     */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    private boolean isrestable = false;
    private int round = 0;
    public Koopa() {
        super("Koopa", 'k', 100);
        this.behaviours.put(10, new WanderBehaviour());
        registerInstance();
    }

    public Koopa(String flyKoopa, char f, int i) {
        super(flyKoopa,f,i);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (isrestable) {
            map.removeActor(this);
            isrestable = false;
        }
        else {
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
