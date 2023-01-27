package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class Jump extends Action{
    /***
     * @author Huiying Lin
     * @version 15/05/2022
     * @see
     */
    protected Location jumpToLocation;
    /**
     * One of the 8-d navigation
     */
    protected String direction;
    /**
     * Or the command key
     */
    protected String hotKey;

    /**
     * Constructor
     * @param jumpToLocation
     * @param direction
     * @param hotKey
     */
    public Jump(Location jumpToLocation, String direction, String hotKey) {
        this.jumpToLocation = jumpToLocation;
        this.direction = direction;
        this.hotKey = hotKey;
    }

    /**
     * Constructor
     * @param jumpToLocation
     * @param direction
     */
    public Jump(Location jumpToLocation, String direction) {

        this.jumpToLocation = jumpToLocation;
        this.direction = direction;
        this.hotKey = null;
    }

    /** overite the method
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return menuDescrption to print where to jump
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor, jumpToLocation);
        return menuDescription(actor);
    }

    /**
     * Returns a description of this movement suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player moves east"
     */
    @Override
    public String menuDescription(Actor actor) {

        return actor +" JUMP TO " + direction;
    }



}
