package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.ChompsAction;

/*** Player attack enemies
 * @author Huiying Lin
 * @version 28/04/2022
 * @see
 */
public class AttackBehaviour  implements Behaviour {
    private final Actor target;



    // TODO: develop and use it to attack the player automatically.
    public AttackBehaviour(Actor subject) {
        /** Constructor
         *  @param subject the Actor we attack
         */
        this.target = subject;

    }

    /**
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return Action to do
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()){
            if (exit.getDestination().containsAnActor()){
                if (!target.isConscious()) {
                    map.removeActor(target);
                    return new DoNothingAction();
                } else {
                    //if (actor.getDisplayChar() == 'Y')
                    if (target.hasCapability(Status.HOSTILE_TO_ENEMY)){
                        if (target.getDisplayChar() == 'Y')
                            return new ChompsAction(target,90);//plant
                        else
                            return new ChompsAction(target,80);//

                    }
                }
            }
        }


    return new DoNothingAction();
    }
}
