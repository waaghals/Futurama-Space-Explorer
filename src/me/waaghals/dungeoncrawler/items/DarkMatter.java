package me.waaghals.dungeoncrawler.items;

import me.waaghals.dungeoncrawler.Game;

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
			farnsworth.say("Game complete!");
			currGame.levelUp();
		} else {
			farnsworth.say("You can't do that here. We need to be back at the spaceship.");
		}
	}
	
	public void use(String something){
		farnsworth.say("Can't use %s on %s", getFancyName(), something);
	}
}
