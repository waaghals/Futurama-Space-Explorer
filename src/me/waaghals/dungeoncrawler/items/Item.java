package me.waaghals.dungeoncrawler.items;

import java.util.Random;
import me.waaghals.dungeoncrawler.*;

/**
 * @author Patrick Berenschot
 * 
 */
public abstract class Item {

	private String name;
	private String useText;
	private Narrator attenborough = Narrator.getInstance(); 
	
	public static final String[] FIGHT_USING_ITEM_HIGH_DAMAGE = {
		"Fighting using %s! Massive hit. %d%% damage to your opponent!.", //Escape % with a %
		"BAM! %d%% damage against your opponent.",
		"Blow to the head, that'll teach 'em!",
		"KAPOW! You hit like Badr Hari!",
		"Good job Chris brown! %d%% damage!"
	};
	
	public static final String[] FIGHT_USING_ITEM_HIGH_MEDIUM = {
		"You inflicted %d%% amount of damage.", 
		"Contact hit! Did %d%% damage",
		"HIT! %d%%, opponent says OUCH!"
	};
	
	public static final String[] FIGHT_USING_ITEM_HIGH_LOW = {
		"Did you even hit? Well don't expect that the %s does al the work!",
		"Well, you might have made a tiny mark there with a %s",
		"Like a feather bag to the knee, only %d%% amount of damage.",
		"%d%% damage, your opponent has an itch."
	};
	
	public static final String[] FIGHT_USING_ITEM_HIGH_NONE = {
		"Miss!!",
		"Are you even trying? No success",
		"Sure you can even use a %s for fighting?"
	};
	
	//
	/*  
	 * Array for damages, damage get randomly picked from this array. Repeat values more often for "weighted" random.
	 * Plotted, low to high  (Bear with me xD) 
	 * 
	 *  Exponential      Logaritmic        Liniar
	 *  |          .     |             .   |             .
	 *  |         .      |             .   |          .
	 *  |        .       |            .    |       .
	 *  |     .          |         .       |    .
	 *  | .              |....   `         |.
	 *  +--------------  +---------------  +---------------
	 */
	private int[] damageMap;

	public String getName() {
		return name;
	}

	/**
	 * Set the name of an item
	 * 
	 * @param newName
	 * @return void
	 * @throws IllegalArgumentException
	 */
	public void setName(String name) {

		// Can't use the name using because of fight using <itemName>
		if (name.equals("using")) {
			throw new IllegalArgumentException("Can't set name to \"using\"");
		}
		this.name = name;
	}

	public String getUseText() {
		return useText;
	}

	public void setUseText(String useText) {
		this.useText = useText;
	}
	
	public int fight(){
		int damage = getDamage();
		if(damage > 90){
			attenborough.say(Item.FIGHT_USING_ITEM_HIGH_DAMAGE, name, damage);
		} else if(damage > 60){
			attenborough.say(Item.FIGHT_USING_ITEM_HIGH_MEDIUM, name, damage);
		} else if(damage > 20){
			attenborough.say(Item.FIGHT_USING_ITEM_HIGH_LOW, name, damage);
		} else if(damage == 0){
			attenborough.say(Item.FIGHT_USING_ITEM_HIGH_NONE, name, damage);
		}
		return damage;
	}
	
	private int getDamage(){
		if(damageMap.length != 0){
			Random randomGenerator = new Random();
			return  randomGenerator.nextInt(damageMap.length);
		}
		return 0;
	}

	//How does one create an interface for an an expendable object?
	//TODO Make use of interface
	public void use() {
		throw new UnsupportedOperationException("use() must be overridden by all subclasses of Item");
		
	}

	public void use(String argument) {
		throw new UnsupportedOperationException("use() must be overridden by all subclasses of Item");
	}
}