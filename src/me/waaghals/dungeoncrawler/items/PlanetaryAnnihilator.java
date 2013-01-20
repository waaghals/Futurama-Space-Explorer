package me.waaghals.dungeoncrawler.items;

import me.waaghals.dungeoncrawler.Narrator;

/**
 * @author Patrick Berenschot
 * 
 */
public class PlanetaryAnnihilator extends Item {

	public PlanetaryAnnihilator() {
		super("annihilator");
		setDamageMap( new int[] {80, 90});
		setFancyName("Planetary Annihilator");
	}
	
	public void use() {
		Narrator.say("Pointing the %s at the stars", getFancyName());
	}
	
	public void use(String something){
		Narrator.say("Can't use %s on %s", getFancyName(), something);
	}

}
