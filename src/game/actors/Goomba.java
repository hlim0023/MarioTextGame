package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.behaviours.Behaviour;
import game.behaviours.SpeakingBehaviour;
import game.behaviours.WanderBehaviour;
import game.Resettable;
import game.Status;
import game.Utils;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
/**
 * A little fungus guy.
 */
public class Goomba extends Actor implements Resettable {
	private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    private int ChanceToSuicide = 10;
	private boolean isrestable = false;
	private int round = 0;
	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
		this.behaviours.put(10, new WanderBehaviour());
		registerInstance();
	}

	/**
	 * At the moment, we only make it can be attacked by Player.
	 * You can do something else with this method.
	 * @param otherActor the Actor that might perform an action.
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */

	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			actions.add(new AttackAction(this,direction));
		}
		return actions;
	}

	/**
	 * Figure out what to do next.
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		if (isrestable) {
			map.removeActor(this);
			isrestable = false;
		}
		else {
			if(!Utils.idRateCheck(ChanceToSuicide)){
			    for (Behaviour Behaviour : behaviours.values()) {
					Action action = Behaviour.getAction(this, map);
					if (action != null) {
						if(round %2 ==0) {
							round++;
							return new SpeakingBehaviour(this);
						}else{
							round ++;
							return action;}
					}
				}
			}
			else{
				map.removeActor(this);
				if(round %2 ==0){
					round ++;
					return new SpeakingBehaviour(this);
				}else{round ++;
					return new DoNothingAction();}
			}
		}
		if(round %2 == 0){
			round ++;
			return new SpeakingBehaviour(this);
		}
		else{
			round ++;
		return new DoNothingAction();
		}
	}

	@Override
	public void resetInstance() {
		isrestable = true;
	}

}
