package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;
import game.items.Coin;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.weapons.Wrench;

import java.util.Scanner;

public class PurchaseAction extends Action {
    /** @author Dongyi Wang
     *  constructor
     */
    public PurchaseAction(){}

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag){
            System.out.println("r:reset the game");
            System.out.println("a:Mario buys Power Star($600)");
            System.out.println("b:Mario buys Super Mushroom($400)");
            System.out.println("c:Mario buys wrench($200)");
            System.out.println("d:Mario talks with Toad");
            String newAns = sc.next();
            switch(newAns){
                case "a":
                    if (Coin.checkCoin(600)){
                        PowerStar power = new PowerStar();
                        //power.powerOn(actor);
                        actor.addItemToInventory(power);
                        Coin.setCoin(Coin.getCoin()-600);
                        return "Buy Power Star successfully.And $" + Coin.getCoin() + "left.";
                    }else{
                        return "You don't have enough coins!";
                    }
                case "b":
                    if (Coin.checkCoin(400)){
                        SuperMushroom item = new SuperMushroom();
                        //item.superOn();

                        actor.addItemToInventory(item);
                        Coin.setCoin(Coin.getCoin()-400);
                        return "Buy Super Mushroom successfully.And $" + Coin.getCoin() + "left.";
                    }else{
                        return "You don't have enough coins!";
                    }
                case "c":
                    if (Coin.checkCoin(200)){
                        Item item = new Wrench(50);
                        actor.addItemToInventory(item);
                        Coin.setCoin(Coin.getCoin()-200);
                        return "Buy wrench successfully.And $" + Coin.getCoin() + "left.";
                    }else{
                        return "You don't have enough coins!";
                    }
                case "d":
                    return "Toad is shy (✿◡‿◡)";
                case "r":
                    ResetManager.run();
                    return "The game has been reset";
            }
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Press B to buy things";
    }
    @Override
    public String hotkey() {
        return "B";
    }

}
