package me.waaghals.dungeoncrawler;

import java.util.ArrayList;
import java.util.HashMap;

import me.waaghals.dungeoncrawler.items.Item;

/**
 * @author Patrick Berenschot
 * 
 */
public class Room {

	private HashMap<Integer, Room> exits;
	private HashMap<String, Item> items; // Don't use a single item, a user could drop
									// an item in a room where a item already
									// exists.
	private String entryText;
	private Narrator attenborough = Narrator.getInstance(); 

	public boolean hasExit(Integer direction) {
		return exits.containsKey(direction);
	}

	/**
	 * Set the name of an item
	 * 
	 * @param direction
	 *            NORTH, EAST, SOUTH or WEST
	 * @return Room
	 * @throws IllegalArgumentException
	 */
	public Room getAdjacentRoom(Integer direction) {

		// Make sure we don't get any errors trying to take a non existing exit.
		if (!exits.containsKey(direction)) {
			throw new IllegalArgumentException(
					"Room does not have an exit here.");
		}
		return exits.get(direction);
	}

	public void sayEntryText() {
		attenborough.say(entryText);
	}

	public void setEntryText(String entryText) {
		this.entryText = entryText;
	}

	/**
	 * Add an door to a new room
	 * 
	 * @param direction
	 * @param room
	 */
	public void addExits(Integer direction, Room room) {
		exits.put(direction, room);
	}

	/**
	 * Add an item to the room
	 * 
	 * @param newItem
	 */
	public void addItem(Item item) {
		items.put(item.getName(), item);
	}
	
	/**
	 * Remove an item to the room
	 * 
	 * @param itemName
	 * @return Item which was removed
	 */
	public Item removeItem(String itemName) {
		Item tempItem = get(itemName);
		
		//if the item exists
		if (tempItem != null) {
			items.remove(itemName);
			return tempItem;
		}
		return null;
	}

	/**
	 * Returns an Item if it is in the room else returns null.
	 * 
	 * @param itemName
	 * @return Item if it is in the room
	 */
	public Item get(String itemName) {
		if (items.containsKey(itemName)) {
			return items.get(itemName);
		}
		//TODO say item was not in the room
		return null;
	}
}