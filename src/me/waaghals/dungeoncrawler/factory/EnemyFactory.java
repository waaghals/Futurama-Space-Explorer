package me.waaghals.dungeoncrawler.factory;

import me.waaghals.dungeoncrawler.Constants;
import me.waaghals.dungeoncrawler.Enemy;
import me.waaghals.dungeoncrawler.items.Stick;

import org.apache.commons.collections15.Factory;


/**
 * @author Patrick Berenschot
 * 
 */
public class EnemyFactory  implements Factory<Enemy>{
	@Override
	public Enemy create() {
		Enemy newEnemy = new Enemy(new Stick(), randomSpeed());
		return newEnemy;
	}
	
	private int randomSpeed(){
		return Constants.generator.nextInt(3);
	}

}
