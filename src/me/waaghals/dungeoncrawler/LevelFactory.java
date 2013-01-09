package me.waaghals.dungeoncrawler;

/**
 * 
 * @author Patrick Berenschot
 * 
 * All the formulas are made up.
 * I just want to a plotter, plotted some graphs which made sense and used those formulas.
 *
 */
public class LevelFactory {

	public LevelFactory(int level){
		
	}
	
	/**
	 * Calculate the number of rooms the level has.
	 * 
	 * @param level 
	 * 			The level that is being generated. Needs to be higher that 1.
	 * @return f(x) = ((x-1)^2) * 0.3+1
	 */
	private int numRooms(int level){
		if(level < 1){
			//No need to do the math, f(1) = 10
			return 10;
		}
		return (int) Math.round((Math.pow((level - 1), 2) * 3 + 10));
	}
	
	/**
	 * The number of Enemies in the level, level 1 and 2 have 0 enemies.
	 * 
	 * @param level
	 * @return f(x) = sqr(x-2)*1.1
	 */
	private int numEnemies(int level){
		return (int) Math.round(Math.sqrt(level-2)*1.1);
	}
	
	/**
	 * The number of Items in the level.A level has half the items of numRooms
	 * 
	 * @param level
	 * @return numRooms / 2.5
	 */
	private int numItems(int level){
		return (int) Math.round(numRooms(level)/2.5);
	}
}
