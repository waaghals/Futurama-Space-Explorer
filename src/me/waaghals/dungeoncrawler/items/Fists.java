package me.waaghals.dungeoncrawler.items;

import me.waaghals.dungeoncrawler.Narrator;


/**
 * @author Patrick Berenschot
 * 
 */
public class Fists extends Item{
	public Fists(){
		super("fists");
		//super.setDamageMap(new int[] {0, 5, 5, 5, 8, 8, 15});
		super.setDamageMap(new int[] {90,91,91,92,93});
	}
	
	public void use() {
		Narrator.say("Can't use %s", getFancyName());
	}
	
	public void use(String something){
		Narrator.say("Can't use %s on %s", getFancyName(), something);
	}
}
