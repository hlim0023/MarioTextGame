package game;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Bowser;
import game.actors.Princess;
import game.grounds.*;
import game.grounds.trees.Sprout;
import game.items.Bottle;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.WarpPipe;

import java.util.Arrays;
import java.util.List;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout() );

			List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);


			//player
			Actor mario = new Player("Player", 'm', 10000);
			world.addPlayer(mario, gameMap.at(42, 10));
		    mario.addItemToInventory(new Bottle());// add the bottle to the inventory when the game begin

			//System.out.println(mario.getInventory());

			//new map
			List<String> lavaMap = Arrays.asList(
					".............",
					"......######.",
					"......+....+.",
					"......######.",
					".............");
			GameMap lavaGame = new GameMap(groundFactory, lavaMap);
			world.addGameMap(lavaGame);

			//to new map
			for (int i = 0; i < 10; i++ ) {
				WarpPipe pipe = new WarpPipe("Pipe", 'C', false);
				pipe.addSampleAction(new MoveActorAction(lavaGame.at(1, 1), "Teleport to Lava Zone"));
				int x =  Utils.generateID(60);
				int y =  Utils.generateID(15);
				gameMap.at(x, y).addItem(pipe);
				pipe.setXY(gameMap,x,y, 1);
			}

			lavaGame.addActor(new Princess(), lavaGame.at(1,4));
			lavaGame.addActor(new Bowser(), lavaGame.at(4,4));

			//back to home map
			WarpPipe pipeBack = new WarpPipe("PipeBack", 'C', false);
			pipeBack.addSampleAction(new MoveActorAction(gameMap.at(1, 1), "Leave Lava Zone, Go back home!"));
			lavaGame.at(1,1).addItem(pipeBack);
			pipeBack.setXY(lavaGame,1,1, 0);

            //gameMap.addActor(new Toad(),gameMap.at(42, 11));
		    gameMap.at(43,11).setGround(new PowerFountain());
		    gameMap.at(44,11).setGround(new HealthFountain());
			//Place Super Mushroom and Power Star on the same ground when Player instantiated.
			SuperMushroom superMushroom = new SuperMushroom();
			PowerStar powerStar = new PowerStar();
			gameMap.at(40,5).addItem(superMushroom);
		    gameMap.at(42,6).addItem(powerStar);
		    mario.addItemToInventory(superMushroom);
			mario.addItemToInventory(powerStar);


			ResetManager.getInstance();

			// FIXME: the Goomba should be generated from the Tree

			world.run();

	}
}
