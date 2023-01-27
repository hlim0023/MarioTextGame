package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class SavePrincess extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return "Congratulation, Victory !!!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Save the princess";
    }
}
