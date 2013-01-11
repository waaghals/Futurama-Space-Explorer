package me.waaghals.dungeoncrawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import me.waaghals.dungeoncrawler.items.Item;

/**
 * @author Patrick Berenschot
 * 
 */
public class Room {

	private HashMap<Integer, Room> exits = new HashMap<Integer, Room>();
	private HashMap<String, Item> items = new HashMap<String, Item>();
	private Narrator farnsworth = Narrator.getInstance();
	private int roomId; // Used in LevelFactory so that rooms can be kept apart.

	public String toString() {
		return "R" + Integer.toString(roomId);
	}

	public void setRoomId(int id) {
		this.roomId = id;
	}

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
		int index = roomId % ROOM_DESC.length;
		String[] roomText = ROOM_DESC;
		farnsworth.say(roomText[index]);
	}

	/**
	 * Add a door to a new room
	 * 
	 * @param direction
	 * @param room
	 */
	public void addExit(Integer direction, Room room) {
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

		// if the item exists
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
	private Item get(String itemName) {
		if (items.containsKey(itemName)) {
			return items.get(itemName);
		}
		return null;
	}

	public static final String[] ROOM_DESC = {
			"We are in a dark and damp envirement. Surrounded by bats, We've reached a cave!",
			"A lot of trees! This must be a forest I guess...",
			"Knee deep in the grass, soggy ground, lets hope we don't find any alligators in this swamp.",
			"Hill, hill, and more hills. I feel like singing Do-Re-Mi!"
	// TODO add more rooms
	};

	public void sayPosibleDirections() {
		int i = 0;
		// For each key in the exits Hashmap
		for (Integer intDirection : exits.keySet()) {
			String stringDirection = Constants.getStringDirection(intDirection);
			
			farnsworth.say(Narrator.DIRECTIONS, stringDirection);
			//TODO make this work
			// Add a comma between sentences
			if (i > 0 && i < exits.size()) {
				farnsworth.say(", ");

				// In the last place add " and "
			} else if (i == exits.size()) {
				farnsworth.say(" and ");
			}
		}

	}
	
	public void sayItems() {
		for (String itemName : items.keySet()) {
			farnsworth.say(Narrator.ITEMS_IN_ROOM, itemName);	
		}

	}
}