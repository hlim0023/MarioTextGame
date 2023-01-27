package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Resettable;


/**
 *  Coin class is used to check the money that mario has and buy things
 *  Display coin in the map
 *
 *  @author Dongyi Wang
 *  @version 24/04/2022
 */
public class Coin extends Item implements Resettable {
    /**
     *  static attribute that can be referred globally
     *  @author Huiying Lin
     *  @version 28/04/2022
     *  @see
     *
     */
    private static int coinNumber;
    private int xLocation;
    private int yLocation;
    private GameMap map;

    /**
     *
     * @param coinNumber
     * @param xLocation
     * @param yLocation
     * @param map
     */
    public Coin(int coinNumber,int xLocation, int yLocation, GameMap map) {
        super("coin",'$',true);
        this.coinNumber = coinNumber;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.map = map;
        registerInstance();
    }

    /**
     *
     * @return
     */
    public static int getCoin(){
        return coinNumber;
    }

    /**
     * @param newCoin
     */
    public static void setCoin(int newCoin){
        coinNumber = newCoin;
    }

    /**
     * @param price
     * @return
     */
    public static boolean checkCoin(int price){
        if(price > coinNumber){
            return false;
        }
        return true;
    }


    @Override
    public void resetInstance() {
        map.at(xLocation,yLocation).removeItem(this);
    }
}
