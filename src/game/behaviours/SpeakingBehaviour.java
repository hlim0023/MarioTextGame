package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import java.util.Random;
import edu.monash.fit2099.engine.items.Item;
import game.Status;

/**
 * REQ5
 * @author Dongyi Wang
 * A behaviour that happen when each actor alive in a turn
 */
public class SpeakingBehaviour extends Action implements Behaviour {
    /**
     * round is used to check if the turn is alternating
     */

    private final Actor player;

    /**
     * check if the player has a weapon called "wrench"
     * @param player1
     */
    public SpeakingBehaviour(Actor player1){
        this.player = player1;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Random rand =new Random();
        if (actor.isConscious()){
                switch(actor.getDisplayChar()) {
                        //Princess Peach
                    case 'P':
                        if (rand.nextInt(3)==1){
                            return actor + ": \"Dear Mario, I'll be waiting for you...\"";
                        }
                        else if(rand.nextInt(3)==2){
                            return actor + ": \"Never gonna give you up!\"";
                        }
                        else{
                            return actor + ": \"Release me, or I will kick you!\"";
                        }
                        //Toad
                    case 'O':
                        for (Item item : player.getInventory()) {
                            if (player.hasCapability(Status.INVINCIBLE)){
                                if(item.getDisplayChar() == 'w'){
                                    if (rand.nextInt(2)==1){
                                        return actor + ": \"The Princess is depending on you! You are our only hope.\"";
                                }
                                    else if(rand.nextInt(2)==2){
                                        return actor + ": \"Being imprisoned in these walls can drive a fungus crazy :(\"";
                                    }
                                }
                                if (rand.nextInt(3)==1){
                                    return actor + ": \"The Princess is depending on you! You are our only hope.\"";
                                }
                                else if(rand.nextInt(3)==2){
                                    return actor + ": \"Being imprisoned in these walls can drive a fungus crazy :(\"";
                                }
                                else{
                                    return actor + ": \"You might need a wrench to smash Koopa's hard shells.\"";
                                }
                            }
                            else{
                                if(item.getDisplayChar() == 'w'){
                                    if (rand.nextInt(2)==1){
                                        return actor + ": \"The Princess is depending on you! You are our only hope.\"";
                                    }
                                    else{
                                        return actor + ": \"Being imprisoned in these walls can drive a fungus crazy :(\"";
                                    }
                                }
                                if (rand.nextInt(3)==1){
                                    return actor + ": \"The Princess is depending on you! You are our only hope.\"";
                                }
                                else if(rand.nextInt(3)==2){
                                    return actor + ": \"Being imprisoned in these walls can drive a fungus crazy :(\"";
                                }
                                else{
                                    return actor + ": \"You might need a wrench to smash Koopa's hard shells.\"";
                                }
                            }
                        }
                        //Bowser
                    case 'B':
                        if (rand.nextInt(4)==1){
                            return actor + ": \"What was that sound? Oh, just a fire.\"";
                        }
                        else if(rand.nextInt(4)==2){
                            return actor + ": \"Princess Peach! You are formally invited... to the creation of my new kingdom!\"";
                        }
                        else if(rand.nextInt(4)==3){
                            return actor + ": \"Never gonna let you down!\"";
                        }
                        else{
                            return actor + ": \"Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!\"";
                        }
                        //Goombas
                    case 'g':
                        if (rand.nextInt(3)==1){
                            return actor + ": \"Mugga mugga!\"";
                        }
                        else if(rand.nextInt(3)==2){
                            return actor + ": \"Ugha ugha... (Never gonna run around and desert you...)\"";
                        }
                        else{
                            return actor + ": \"Ooga-Chaka Ooga-Ooga!\"";
                        }
                        //All Koopas
                    case 'K':
                        if (rand.nextInt(2)==1){
                            return actor + ": \"Never gonna make you cry!\"";
                        }
                        else{
                            return actor + ": \"Koopi koopi koopii~!\"";
                        }
                        //Flying Koopa
                    case 'F':
                        return actor + ": \"Pam pam pam!\"";
                        //Piranha Plants
                    case 'Y':
                        if (rand.nextInt(2)==1){
                            return actor + ": \"Slsstssthshs~! (Never gonna say goodbye~)\"";
                        }
                        else{
                            return actor + ": \"Ohmnom nom nom nom.\"";
                        }
                }
            }

        return null;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return "";
    }

    /**
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        return new SpeakingBehaviour(actor);
    }
}
