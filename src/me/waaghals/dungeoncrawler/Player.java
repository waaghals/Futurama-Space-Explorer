package me.waaghals.dungeoncrawler;

import java.util.ArrayList;
import java.util.HashMap;

import me.waaghals.dungeoncrawler.items.Item;

/**
 * @author Patrick Berenschot
 * 
 */
public class Player {
	
	public static final int BACKPACK_SIZE = 10;
	
	private HashMap<String, Item> backpack;
	private Room curRoom; // The room the user is in

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
	 * @return entryText from room
	 */
	public String setCurRoom(Room curRoom) {
		this.curRoom = curRoom;

		// return the entryText for the new room so it can be spoken
		return curRoom.getEntryText();
	}
}
