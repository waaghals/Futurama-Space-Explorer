package me.waaghals.dungeoncrawler.items;


/**
 * @author Patrick Berenschot
 * 
 */
public class Stick extends Item {
	
	public Stick() {
		super("stick");
		setDamageMap( new int[] {0, 25, 25, 25, 25, 25, 25, 25, 35, 35, 90});
		setFancyName("Pointy Rock Tied to a Stick");
	}

	public void use() {
		farnsworth.say("Dancing like a girl with a twirling stick!");
	}
	
	public void use(String something){
		farnsworth.say("Can't use %s on %s", getFancyName(), something);
	}
}
