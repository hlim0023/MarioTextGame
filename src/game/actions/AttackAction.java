package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Utils;
import game.actors.KoopaDormant;
import game.items.BowserKey;

import java.util.Random;

/***
 * @author Huiying Lin
 * @version 22/05/2022
 *
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 *
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/** overite the method
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return String message
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if (this.target.getDisplayChar() == 'B') {
			Weapon weapon = actor.getWeapon();
			if (!(Utils.idRateCheck(80))) {
				return actor + " misses " + target + ".";
			}
			target.hurt(150);
			String result = actor + " " + weapon.verb() + " " + target + " for " + 150 + " damage.";
			if (!target.isConscious()) {
				map.at(4,4).addItem(new BowserKey());
				ActionList dropActions = new ActionList();
				// drop all items
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(target, map);
				// remove actor
				Location location = map.locationOf(target);
				map.removeActor(target);
				result += System.lineSeparator() + target + " is killed.";;
			}
			return result;
		}

        if (this.target.getDisplayChar() != 'k') {
			Weapon weapon = actor.getWeapon();
			if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
				return actor + " misses " + target + ".";
			}

			int damage = weapon.damage();
			String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
			target.hurt(damage);
			if (!target.isConscious()) {
				ActionList dropActions = new ActionList();
				// drop all items
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(target, map);
				// remove actor
				map.removeActor(target);
				result += System.lineSeparator() + target + " is killed.";
			}
			return result;
		}
		else if (this.target.getDisplayChar() == 'k') {
			Weapon weapon = actor.getWeapon();
			if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
				return actor + " misses " + target + ".";
			}
			int damage = weapon.damage();
			String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
			target.hurt(damage);
			if (!target.isConscious()) {
				ActionList dropActions = new ActionList();
				// drop all items
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(target, map);
				// remove actor
				Location location = map.locationOf(target);
				map.removeActor(target);
				map.addActor(new KoopaDormant(),location);
				result += System.lineSeparator()  + "Koopa takes its shell out to defend.(Only Wrench can destroy the shell)";
			}
			return result;
		}
		else if (this.target.getDisplayChar() == 'D') {
			Weapon weapon = actor.getWeapon();
			if (!weapon.toString().equals("Wrench")){
				String result = "You can only attack the dormant Koopa with a wrench";
				return result;
			}
			else if (weapon.toString().equals("Wrench")){
				if(Utils.idRateCheck(weapon.chanceToHit())){
					target.hurt(weapon.damage());
					ActionList dropActions = new ActionList();
					// drop all items
					for (Item item : target.getInventory())
						dropActions.add(item.getDropAction(actor));
					for (Action drop : dropActions)
						drop.execute(target, map);
					// remove actor
					map.removeActor(target);
					String result = "Dormant Koopa has been destroyed";
					return result;
				}
				else{
					String result = actor + " misses" + target + ".";
					return result;
				}
			}

		}
		return null;
	}


	/**  the description to user
	 * @param actor The actor performing the action.
	 * @return String message
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}

}

