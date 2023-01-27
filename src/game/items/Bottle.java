package game.items;

import edu.monash.fit2099.engine.items.Item;

import java.util.Stack;

/**
 * @author Dongyi Wang
 * REQ3
 */
public class Bottle extends Item {
    /***
     * Constructor.
     * Create a bootle stack to store the water
     */
    public static Stack<String> bottleType = new Stack<String>();

    /**
     * This makes the bottle can be added into the inventory
     */
    public Bottle() {
        // cannot be dropped
        super("Bottle",'b',false);
    }

    /**
     *
     * @return all the string from the stack
     */
    public static String bottleTypeStack() {
        return "" + bottleType;
    }

    /**
     * check if there is anything in the stak
     * @return boolean statement
     */
    public static boolean checkIsFull() {
        if (bottleType.size()!=0) {
            return true;
        } else {
            return false;
        }
    }
}
