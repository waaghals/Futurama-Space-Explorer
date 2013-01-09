package me.waaghals.dungeoncrawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import me.waaghals.dungeoncrawler.items.Item;

/**
 * @author Patrick Berenschot
 * 
 */
public class Player {
	
	public static final int BACKPACK_SIZE = 10;
	
	private HashMap<String, Item> backpack;
	private Room curRoom; // The room the user is in
	private Narrator attenborough = Narrator.getInstance(); 
	private String name;

	
	public Player(String name){
		setName(name);
	}
	/**
	 * Returns an Item if backpack holds it else returns null.
	 * 
	 * @param itemName
	 * @return Item if backpack holds it
	 */
	public Item get(String itemName) {
		if(backpack.containsKey(itemName)){
			return backpack.get(itemName);
		} 
		return null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Tells the contents of the backpack
	 * 
	 */
	public void showBackpack() {
		int i = 0;
		for (Entry<String, Item> item : backpack.entrySet())
		{
		    //item key is the itemName
		    attenborough.say(Narrator.BACKPACK_CONTENT, item.getKey());
		    
		    //Add a comma between sentences
		    if(i > 0 && i < backpack.size()){
		    	attenborough.say(", ");
		    	
		    //In the last place add " and "
		    } else if (i == backpack.size()){
		    	attenborough.say(" and ");
		    }
		}
	}
	
	/**
	 * Add new item to the users backpack
	 * 
	 * @param item
	 * @return true on success else false
	 */
	public boolean add(Item item){
		if(backpack.size() < BACKPACK_SIZE){
			backpack.put(item.getName(), item);
			return true;
		}
		
		//TODO explain backpack is full
		return false;	
	}
	
	/**
	 * Remove an item with itemName from the users backpack
	 * 
	 * @param itemName
	 * @return Item which was removed else null
	 */
	public Item drop(String itemName) {
		if(backpack.containsKey(itemName)){
			Item tempItem = backpack.get(itemName);
			backpack.remove(itemName);
			//TODO say it is dropped
			return tempItem;
		}
		//TODO say nothing to drop
		return null;
	}
	
	public void use(String itemName){
		Item item = get(itemName);
		if(item != null){
			item.use();
		} else {
			//TODO emit player does not own.
		}
	}
	
	public void use(String itemName, String argument){
		Item item = get(itemName);
		if(item != null){
			item.use(argument);
		} else {
			//TODO emit player does not own.
		}
	}
	
	public int fight(String itemName){
		return backpack.get(itemName).fight();
	}

	public int fight(){
		//Fight using the players "fists"
		Item fists = new Fists("fists", new int[] {0, 5, 5, 5, 8, 8, 15});
		return fists.fight();
	}

	/**
	 * Move a player to a new room
	 * 
	 * @param direction
	 * @return boolean
	 */
	public boolean move(String direction) {
		switch (direction) // arguments[0] is the command by the user
		{

		case "north":
		case "noord":
		case "n":
			if (curRoom.hasExit(Main.NORTH)) {
				setCurRoom(curRoom.getAdjacentRoom(Main.NORTH));
				//TODO narrate
				return true;
			}
			break;

		case "east":
		case "oost":
		case "e":
			if (curRoom.hasExit(Main.EAST)) {
				setCurRoom(curRoom.getAdjacentRoom(Main.EAST));
				return true;
			}
			break;

		case "south":
		case "zuid":
		case "s":
			if (curRoom.hasExit(Main.SOUTH)) {
				setCurRoom(curRoom.getAdjacentRoom(Main.SOUTH));
				return true;
			}
			break;

		case "west":
		case "w":
			if (curRoom.hasExit(Main.WEST)) {
				setCurRoom(curRoom.getAdjacentRoom(Main.WEST));
				return true;
			}
			break;

		}
		return false;
	}

	public Room getCurRoom() {
		return curRoom;
		
	}

	/**
	 * Place the user in a new room
	 * 
	 * @param newRoom
	 */
	public void setCurRoom(Room curRoom) {
		this.curRoom = curRoom;

		// return the entryText for the new room so it can be spoken
		curRoom.sayEntryText();
	}
	
	public static class Fists extends Item{
		public Fists(String name, int[] damageMap) {
			super(name, damageMap);
		}
		
		public static final String[] FIGHT_USING_ITEM_HIGH_LOW = {
			"Did you even hit? Well don't expect that your fists do all the work!",
			"Well, you might have made a tiny mark there with your fists",
			"Like a feather bag to the knee, only %d%% amount of damage.",
			"%d%% damage, your opponent has an itch."
		};
		
		public static final String[] FIGHT_USING_ITEM_HIGH_NONE = {
			"Miss!!",
			"Are you even trying? No success",
			"Where did you learn how to fight?"
		};
		
	}
}
