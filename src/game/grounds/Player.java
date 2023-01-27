package game.grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;
import game.Resettable;
import game.Status;
import game.actions.DrinkAction;
import game.actions.FillAction;
import game.actions.Jump;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable {
	/***
	 * @author Huiying Lin
	 * @version 30/04/2022
	 * @see
	 */

	private final Menu menu = new Menu();
	private int count = 0;
	private boolean isresetable = false;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		registerInstance();
	}


	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

		if (isresetable) {
			if (this.hasCapability(Status.TALL)) {
				this.removeCapability(Status.TALL);
				this.addCapability(Status.HOSTILE_TO_ENEMY);
			}
			this.heal(1000000000);
			isresetable = false;
		}
		for (Exit highground: map.locationOf(this).getExits()){
			if (highground.getDestination().getGround().canActorEnter(this) == false){
				actions.add(new Jump(highground.getDestination(), highground.getName()));
			}
		}

        if(isConscious()){
			actions.add(new DrinkAction());
		}
		if(isConscious()){
			actions.add(new FillAction(map.locationOf(this).getGround()));
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// handle with reset
		if (count == 0) {
			actions.add(new ResetAction());
		}
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	public boolean getCurrentHp(){
		int maxHpSize = Integer.toString(super.getMaxHp()).length();
		String currentHp ="";
		for (int i =1; i < (this.printHp().length() - (maxHpSize + 2)); i++)
			currentHp = currentHp + this.printHp().charAt(i);
		return Integer.parseInt(currentHp) < super.getMaxHp() ;
	}



	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.SUPER) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}


	@Override
	public void resetInstance() {
		isresetable = true;
	}


	public class ResetAction extends Action {

		@Override
		public String execute(Actor actor, GameMap map) {
			for (Resettable re: ResetManager.getInstance().getResettableList()){
				re.resetInstance();
			}
			map.tick();
			count++;
			return actor + " is resetting the game.";
		}


		@Override
		public String menuDescription(Actor actor) {
			return "Rest the game";
		}

		@Override
		public String hotkey() {
			return "r";
		}
	}


}
