package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.Bottle;

import java.util.Scanner;

/**
 * @author Dongyi Wang
 * REQ3
 * A drink action for player to use the water
 * Anytime can drink water but if there is no water in the bottle then skip the function
 */
public class DrinkAction extends Action {
    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Scanner sc = new Scanner(System.in);
        if(Bottle.checkIsFull()){
            String water = Bottle.bottleType.pop();
            switch(water){
                case "Healing water":
                    System.out.println("h: Heal the HitPoints");
                    System.out.println("i: Increase the max HitPoints without healing");
                    String ans = sc.next();
                    if (ans.equals('h')) {
                        actor.heal(50);
                    }else if(ans.equals('i')) {
                        actor.increaseMaxHp(50);
                    }
                    break;
                case "Power water":
                    if(actor.getWeapon().damage() == 5){
                        // no weapon
                        for (Item item : actor.getInventory()){
                            if (item.toString() == "no weapon"){
                                actor.removeItemFromInventory(item);
                            }
                        }
                        actor.addItemToInventory(new WeaponItem("no weapon", '?', 5 + 15, "punches", 50) {
                        });
                        System.out.println("Player's punch will be stronger");
                    }
                    else{
                        for (Item item : actor.getInventory()){
                            if (item.toString() == "wrench"){
                                actor.removeItemFromInventory(item);
                            }
                        }
                        //actor.addItemToInventory(new Wrench(50+15));
                        System.out.println("Player's wrench will be sharper");
                    }
                    break;
            }
            String result = "Player's bottle : " + Bottle.bottleTypeStack();
            return actor + " consumes " + water + "\n" + result;
        }
        return "The bottle is empty.";
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        if(Bottle.checkIsFull() == true) {
            return actor + " drink " + Bottle.bottleType.peek();
        }
        else{
            return actor + " has no water to drink";
        }
    }

    /**
     *
     * @return the hot key
     */
    public String hotkey() {
        return "a";
    }
}
