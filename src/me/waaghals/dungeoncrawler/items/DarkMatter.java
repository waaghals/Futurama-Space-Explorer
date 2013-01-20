package me.waaghals.dungeoncrawler.items;

import me.waaghals.dungeoncrawler.Game;
import me.waaghals.dungeoncrawler.Narrator;

/**
 * @author Patrick Berenschot
 * 
 */
public class DarkMatter extends Item {

	public DarkMatter() {
		super("fuel");
		setFancyName("Dark matter");
	}

	public void use() {
		Game currGame = Game.INSTANCE;
		//Is the player back where he started?
		if(currGame.isHome()){
			Narrator.say("Game complete!");
			currGame.levelUp();
		} else {
			Narrator.say("You can't do that here. We need to be back at the spaceship.");
		}
	}
	
	public void use(String something){
		Narrator.say("Can't use %s on %s", getFancyName(), something);
	}
}
