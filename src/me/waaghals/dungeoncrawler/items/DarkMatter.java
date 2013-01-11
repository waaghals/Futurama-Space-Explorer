package me.waaghals.dungeoncrawler.items;

import me.waaghals.dungeoncrawler.Game;

public class DarkMatter extends Item {

	public DarkMatter() {
		super("fuel");
		setFancyName("Dark matter");
	}

	public void use() {
		//Is the player back where he started?
		
		//WHY ON EARTH DOESN'T THIS WORK TODO fix this
		if(Game.getInstance().isHome()){
			farnsworth.say("Game complete!");
		}
	}
	
	public void use(String something){
		farnsworth.say("Can't use %s on %s", getFancyName(), something);
	}
}
