package me.waaghals.dungeoncrawler.items;

import java.util.List;

import me.waaghals.dungeoncrawler.Enemy;
import me.waaghals.dungeoncrawler.Game;
import me.waaghals.dungeoncrawler.Narrator;
import me.waaghals.dungeoncrawler.Player;

/**
 * @author Patrick Berenschot
 * 
 */
public class Medicine extends Item {

	public Medicine() {
		super("medicine");

	}

	public void use() {
		Player player = Game.INSTANCE.getPlayer();
		player.setHealth(100);
		Narrator.say("You took some medicine, you're healty now.");
		
		//Destroy the medicine, because you can't use it again.
		player.drop(getName());
	}

	public void use(String something) {
		if(something.equals("enemy")){
			Game currGame = Game.INSTANCE;
			//Get all the enemies from the current game
			List<Enemy> enemies = currGame.getGameLevel().getEnemies();
			
			if (enemies != null) {
				for (Enemy enemy : enemies) {
					// If there are more enemies in the same room, also fight them.
					if (enemy.getCurrRoom() == currGame.getPlayer().getCurrRoom()) {
						if(enemy.isAlive()){
							Narrator.say("The enemy has full health now!");
						} else {
							Narrator.say("You have revived the enemy, not a very clever move!");
						}
						enemy.setHealth(100);
					}
				}
			}
		}
	}
}
