package game.grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PurchaseAction;
import game.behaviours.SpeakingBehaviour;

/**
 * @author Dongyi Wang
 * @version 30/04/2022
 */
public class Toad extends Actor {
    private int round = 0;
    private Actor player;
    public Toad(Actor player1) {
        super("Toad",'r',99999);
        player =player1;
    }


    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(round % 2 ==0){
            round ++;
            return new SpeakingBehaviour(player);
        }
        round ++;
        return new DoNothingAction();
    }
    @Override
    public String toString() {
        return name;
    }

    @Override
    public char getDisplayChar() {
        return 'O';
    }


}
