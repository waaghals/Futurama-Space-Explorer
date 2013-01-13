package me.waaghals.dungeoncrawler.items;

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
		farnsworth.say("Pointing the %s at the stars", getFancyName());
	}
	
	public void use(String something){
		farnsworth.say("Can't use %s on %s", getFancyName(), something);
	}

}
