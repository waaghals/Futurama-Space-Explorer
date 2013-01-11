package me.waaghals.dungeoncrawler.items;

import me.waaghals.dungeoncrawler.Game;
import me.waaghals.dungeoncrawler.Narrator;


public class Medicine extends Item {

	public Medicine() {
		super("Medicine");
		
	}

	public void use() {
		Game.getInstance().getPlayer().setHealth(100);
		farnsworth.say("You took some medicine, you're healty now.");
	}
	
	public void use(String something){
		//Game.getInstance().getEnemys();
		//TODO enemy list, use medicine on enemy. 
		//Revive enemy
	}
}
