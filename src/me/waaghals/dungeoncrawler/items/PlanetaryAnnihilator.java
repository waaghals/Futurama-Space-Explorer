package me.waaghals.dungeoncrawler.items;

import me.waaghals.dungeoncrawler.Game;
import me.waaghals.dungeoncrawler.Narrator;

public class PlanetaryAnnihilator extends Item {

	public PlanetaryAnnihilator() {
		super("annihilator");
		setDamageMap( new int[] {80, 90});
		setFancyName("Planetary Annihilator");
	}
	
	public void use() {
		farnsworth.say("Angirly pointing the %s", getFancyName());
	}
	
	public void use(String something){
		farnsworth.say("Can't use %s on %s", getFancyName(), something);
	}

}
