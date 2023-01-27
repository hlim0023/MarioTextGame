package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.Pipe;

public class WarpPipe extends Item {
    /***
     * @author Huiying Lin
     * @version 15/05/2022
     * @see
     */
    private int x;
    private int y;



    public WarpPipe(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    public void setXY(GameMap map, int x, int y, int mapcode){
        this.x = x;
        this.y = y;
        map.at(x,y).setGround(new Pipe(mapcode));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addSampleAction(Action newAction){
        this.addAction(newAction);
    }


}
