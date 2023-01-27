package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;

import java.util.Scanner;
/**
 *   @author Dongyi Wang
 *   @version 30/04/2022
 */
public class JumpAction extends Action {

    //protected Random rand = new Random();
    protected Ground position;
    protected Location moveToLocation;

    private int sproutRate = 90;
    private int saplingRate = 80;
    private int matureRate = 70;
    private int wallRate = 80;

    private int sproutDamage = 10;
    private int saplingDamage = 20;
    private int matureDamage = 30;
    private int wallDamage = 20;

    public JumpAction(Ground ground){
        this.position = ground;
    }

    public Ground getGroundType(){
        return this.position;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.menuDescription(actor);
        Scanner sc = new Scanner(System.in);
        String ans = sc.next();
        if (ans.equals('j')) {
            //int randNumber = rand.nextInt(100);
            // sprout
            // check if the mario consumed the Super Mushroom
            if (actor.getDisplayChar() == 'm') {
                if (getGroundType().getDisplayChar() == '+') {
                    if (Utils.idRateCheck(sproutRate)) {
                        map.moveActor(actor, moveToLocation);
                        return "You did a nice jump";
                    } else {
                        //int damage = 10;
                        String result = actor + " fell from the Sprout and get" + sproutDamage + " damages";
                        actor.hurt(sproutDamage);
                        if (!actor.isConscious()) {
                            ActionList dropActions = new ActionList();
                            // drop all items
                            for (Item item : actor.getInventory())
                                dropActions.add(item.getDropAction(actor));
                            for (Action drop : dropActions)
                                drop.execute(actor, map);
                            // remove actor
                            map.removeActor(actor);
                            result += System.lineSeparator() + actor + " is killed.";
                            return result;
                        }
                    }
                    // sapling
                } else if (getGroundType().getDisplayChar() == 't') {
                    if (Utils.idRateCheck(saplingRate)) {
                        map.moveActor(actor, moveToLocation);
                        return "You did a nice jump";
                    } else {
                        //int damage = 20;
                        String result = actor + " fell from the Sapling and get" + saplingDamage + " damages";
                        actor.hurt(saplingDamage);
                        if (!actor.isConscious()) {
                            ActionList dropActions = new ActionList();
                            // drop all items
                            for (Item item : actor.getInventory())
                                dropActions.add(item.getDropAction(actor));
                            for (Action drop : dropActions)
                                drop.execute(actor, map);
                            // remove actor
                            map.removeActor(actor);
                            result += System.lineSeparator() + actor + " is killed.";
                            return result;
                        }
                    }
                    // mature
                } else if (getGroundType().getDisplayChar() == 'T') {
                    if (Utils.idRateCheck(matureRate)) {
                        map.moveActor(actor, moveToLocation);
                        return "You did a nice jump";
                    } else {
                        //int damage = 30;
                        String result = actor + " fell from the Mature and get" + matureDamage + " damages";
                        actor.hurt(matureDamage);
                        if (!actor.isConscious()) {
                            ActionList dropActions = new ActionList();
                            // drop all items
                            for (Item item : actor.getInventory())
                                dropActions.add(item.getDropAction(actor));
                            for (Action drop : dropActions)
                                drop.execute(actor, map);
                            // remove actor
                            map.removeActor(actor);
                            result += System.lineSeparator() + actor + " is killed.";
                            return result;
                        }
                    }
                    // wall
                }else if (getGroundType().getDisplayChar() == '#') {
                    if (Utils.idRateCheck(wallRate)) {
                        map.moveActor(actor, moveToLocation);
                        return "You did a nice jump";
                    } else {
                        //int damage = 30;
                        String result = actor + " fell from the Mature and get" + matureDamage + " damages";
                        actor.hurt(wallDamage);
                        if (!actor.isConscious()) {
                            ActionList dropActions = new ActionList();
                            // drop all items
                            for (Item item : actor.getInventory())
                                dropActions.add(item.getDropAction(actor));
                            for (Action drop : dropActions)
                                drop.execute(actor, map);
                            // remove actor
                            map.removeActor(actor);
                            result += System.lineSeparator() + actor + " is killed.";
                            return result;
                        }
                    }
                }
                //if the mario consumed the Super Mushroom then mario can jump freely
            } else if (actor.getDisplayChar() == 'M') {
                if (getGroundType().getDisplayChar() == '+') {
                    map.moveActor(actor, moveToLocation);
                    return "You did a nice jump";
                } else if (getGroundType().getDisplayChar() == '+') {
                    map.moveActor(actor, moveToLocation);
                    return "You did a nice jump";
                } else if (getGroundType().getDisplayChar() == '+') {
                    map.moveActor(actor, moveToLocation);
                    return "You did a nice jump";
                }
            }
        }
        if (actor.getDisplayChar() != 'm' || actor.getDisplayChar() != 'M'){
            if (this.getGroundType().getDisplayChar() == 't' && this.getGroundType().getDisplayChar() == '+' && this.getGroundType().getDisplayChar() == 'T'){
                if(moveToLocation.getGround().getDisplayChar() == '.'){
                    map.moveActor(actor, moveToLocation);
                }
            }
        }
        return "Try moving to another positions or jump again, young man";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "If you want to jump up then enter j \n if you don't want to jump up then enter n";
    }
    public String hotkey(){
        return "j/n";
    }
}
