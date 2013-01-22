package me.waaghals.dungeoncrawler.factory;

import org.apache.commons.collections15.Factory;
import edu.uci.ics.jung.graph.*;
import me.waaghals.dungeoncrawler.*;
import me.waaghals.dungeoncrawler.items.DarkMatter;


/**
 * 
 * @author Patrick Berenschot
 * 
 *         All the formulas are made up. I just went and used a plotter, plotted
 *         some graphs which made sense and used those formulas.
 * 
 */
public class GameLevelFactory implements Factory<GameLevel>{
	private int level;
	private GameLevel currLevel;

	public GameLevelFactory(int level) {
		this.level = level;
	}

	/**
	 * Generate a level based on the level the users is playing. (Starts at
	 * level 1)
	 * 
	 * @param level
	 *            The level which needs to be generated
	 * @param items
	 *            A list of items which will be added. Items are used from top
	 *            to bottom. When the array is empty the first item will be
	 *            added again.
	 */
	public GameLevel create(){
		currLevel = new GameLevel();
		Graph<Room, Path> g = 
				new MapFactory<Room, Path>(
						new GraphFactory(),
						new VertexFactory(),
						new EdgeFactory(),
						numRooms())
					.create();
		currLevel.setGraph(g);
		
		addItems();
		addEnemies();
		
		//numItems + 1 'cuz we added fuel, vertexCount / 2 'cus for every path north there is a path south
		currLevel.setStats(level, numRooms(), (numItems() + 1), numEnemies(), g.getVertexCount() / 2);
		return currLevel;
	}

	private void addEnemies() {
		EnemyFactory enemyFactory = new EnemyFactory();
		for (int i = 0; i < numEnemies(); i++) {
			Enemy newEnemy = enemyFactory.create();
			currLevel.addEnemies(newEnemy);
			newEnemy.setRoom(currLevel.getRandomRoom());
		}		
	}

	private void addItems() {
		ItemFactory itemFactory = new ItemFactory();
		for (int i = 0; i < numItems(); i++) {
			Room room = currLevel.getRandomRoom();
			room.addItem(itemFactory.create());
		}
		//Make sure we add fuel to the level else the goal can't be completed
		currLevel.getRandomRoom().addItem(new DarkMatter());
	}

	/**
	 * Calculate the number of rooms the level has.
	 * 
	 * @param level
	 *            The level that is being generated. Needs to be higher that 1.
	 * @return 8 + (level * 4) where level > 1
	 */
	private int numRooms() {
		if (level < 1) {
			return 12;
		}
		return 8 + (level * 4);
	}

	/**
	 * The number of Enemies in the level, level 1 and 2 have 0 enemies.
	 * 
	 * @param level
	 * @return f(x) = sqr(x-2)*1.1
	 */
	private int numEnemies() {
		return (int) Math.round(Math.sqrt(level - 2) * 1.1);
	}

	/**
	 * The number of Items in the level. A level has less than half the items of numRooms
	 * 
	 * @param level
	 * @return numRooms / 2.5
	 */
	private int numItems() {
		return (int) Math.round(numRooms() / 5);
	}
}
