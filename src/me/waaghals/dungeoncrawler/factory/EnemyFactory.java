package me.waaghals.dungeoncrawler.factory;

import me.waaghals.dungeoncrawler.Constants;
import me.waaghals.dungeoncrawler.Enemy;
import me.waaghals.dungeoncrawler.items.Fists;
import me.waaghals.dungeoncrawler.items.Item;
import me.waaghals.dungeoncrawler.items.PlanetaryAnnihilator;
import me.waaghals.dungeoncrawler.items.Stick;

import org.apache.commons.collections15.Factory;


/**
 * @author Patrick Berenschot
 * 
 */
public class EnemyFactory  implements Factory<Enemy>{
	@Override
	public Enemy create() {
		//Give the Enemy weaker fist than the player
		Item weapon = new Fists();
		weapon.setDamageMap(new int[] {0, 3, 3, 3, 5, 5, 10});
		Enemy newEnemy = new Enemy(weapon, randomSpeed());
		//70%
		if(Constants.generator.nextInt(100) > 70){
			newEnemy.setLootItem(new PlanetaryAnnihilator());
		}
		return newEnemy;
	}
	
	private int randomSpeed(){
		return Constants.generator.nextInt(3);
	}

}
