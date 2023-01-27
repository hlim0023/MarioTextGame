package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Flying;
import game.items.Coin;
import game.Status;

public class Wall extends Ground {
	/***
	 * @author Huiying Lin
	 * @version 30/04/2022
	 * @see
	 */

	public Wall() {
		super('#');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		if (actor.hasCapability(Status.TALL) || actor.hasCapability(Flying.FLYING)){
			return true;
		}
		return false;
	}

	@Override
	public void tick(Location location) {
		if (location.getActor() != null && canActorEnter(location.getActor())) {
			location.setGround(new Dirt());
			location.addItem(new Coin(5, location.x(), location.y(), location.map()));
		}
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
