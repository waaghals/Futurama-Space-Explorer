package me.waaghals.dungeoncrawler;

import java.util.HashMap;

import org.apache.commons.collections15.BidiMap;
import org.apache.commons.collections15.bidimap.DualHashBidiMap;

import me.waaghals.dungeoncrawler.items.Item;

/**
 * @author Patrick Berenschot
 * 
 */
public class Room {

	private BidiMap<Integer, Room> exits = new DualHashBidiMap<Integer, Room>();
	private HashMap<String, Item> items = new HashMap<String, Item>();
	private int roomId; // Used in LevelFactory so that rooms can be kept apart.

	public String toString() {
		
		
		int index = roomId % ROOM_LOCATIONS.length;
		String[] roomText = ROOM_LOCATIONS;
		return roomText[index] + " (R" + roomId + ")";
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
		int index = roomId % ROOM_LOCATIONS.length;
		String[] roomText = ROOM_LOCATIONS;
		Narrator.say(ROOM_DESC, roomText[index]);
		if(Game.INSTANCE.getStartRoom() == this){
			Narrator.say("We are in the same room we started in");
		}
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

	public static final String[] ROOM_DESC = { "This is a %s.",
			"We are in a %s", "We reached a %s" };

	public static final String[] ROOM_LOCATIONS = { "cave.", "forest", "swamp",
			"desert", "place with a lot of hills", "deserted village", "valley" };

	public void sayPosibleDirections() {
		// For each key in the exits Hashmap
		for (Integer intDirection : exits.keySet()) {
			String stringDirection = Constants.getStringDirection(intDirection);

			Narrator.say(Narrator.DIRECTIONS, stringDirection);
		}

	}

	public void sayItems() {
		for (String itemName : items.keySet()) {
			Narrator.say(Narrator.ITEMS_IN_ROOM, itemName);
		}

	}

	public int getRoomId() {
		return roomId;
	}
	
	public int getDirectionByNeighbour(Room neighbour){
		if(exits.containsValue(neighbour)){
			return exits.getKey(neighbour);
		}
		return 0;
	}
	
	public int numItems(){
		return items.size();
	}
}