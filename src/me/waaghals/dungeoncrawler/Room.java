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
	private ArrayList<Item> items; //Don't use a single item, a user could drop an item in a room where a item already exists.
	private String entryText;
	
	public boolean hasExit(Integer direction){
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
	public Room getAdjacentRoom(Integer direction){
		
		//Make sure we don't get any errors trying to take a non existing exit.
		if(!exits.containsKey(direction)){
			throw new IllegalArgumentException("Room does not have an exit here.");
		}
		return exits.get(direction);
	}

	public String getEntryText() {
		return entryText;
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

	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * Add an item to the room
	 * 
	 * @param newItem
	 */
	public void addItem(Item item) {
		items.add(item);
	}
	
	/**
	 * Returns true if the room has an Item with itemName.
	 * 
	 * @param itemName
	 * @return boolean
	 */
	public boolean has(String itemName) {
		for (Item item : items) {
			if (item.getName().equals(itemName)) {
				return true;
			}
		}
		return false;
	}
}