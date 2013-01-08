package me.waaghals.dungeoncrawler;

import java.util.ArrayList;

/**
 * @author Patrick Berenschot
 * 
 */
public class Game {
	private ArrayList<Room> rooms;
	private Player player;

	/**
	 * @param none
	 * 
	 * 
	 */
	public Game() {
		
		// Initialize everything you need:
		// the player, the rooms, items you want in the rooms, everything
		// then call the run() command.
	}

	/**
	 * @param none
	 * 
	 * 
	 *            Run the game
	 */
	private void run() {
		try {
			// As long as the command isn’t to quit:
			// get the next input line and handle it. (With handleCommand.)
		} catch (Exception e) {
			// Something went terribly wrong. Inform the user.
		}
	}

	/**
	 * @param userInput
	 *            (This is the entire input string from the user.)
	 * 
	 * 
	 *            (Tell others to) Perform the task which belongs to the given
	 * 
	 *            command.
	 */
	private void handleCommand(String userInput) {
		String[] arguments = userInput.split(" ");

		//With each command move the enemy closer to the player
		switch (arguments[0]) // arguments[0] is the command by the user
		{
		
		case "go":

			break;
		case "get":

			break;
		case "use":

			break;
		case "pack":

			break;
		case "look":

			break;
		case "help":

			break;

		case "fight":

			// TODO default to fist fighting, "fight using" <Item> fights with
			// said item.
			break;

		case "loot":

			// TODO make sure the enemy is dead first
			break;

		case "quit":

			break;
		default:

			break;
		}

		// Split the user input string.
		// The first word is a command. The rest is extra information
		// Check if the command is to travel between rooms. If so, handle
		// the room travelling using the method: checkRoomTravel
		// This one is explained later.
		//
		//
		//
		//
		//
		//
		//
		//
		/*
		 * If there isn’t any room travel, then check all other command command
		 * options. (Oh, look: this might be a great place for a switch over the
		 * command string.) Depending on the command, you might also need the
		 * extra information. e.g. “use stick”, has “use” as command and “stick”
		 * as extra information. To make things easy, we created private methods
		 * to handle the commands. They are presented below.
		 */
	}

	private void handleUseCommand(String itemName) {
		if(player.get(itemName) != null){
			player.use(itemName);
		} else if(player.getCurRoom().get(itemName) != null) { //Does the room has this item?
			//TODO player.addItem()
		}
		// Check if the player has the item in his backpack.
		// If in the backpack: use it.
		// If not in the backpack: check if the item is in the room.
		// If the item is in the room: use it.
		// If no item with that name is present: tell the user he’s
		// trying to use something that isn’t there.
	}

	private void handleDropCommand(String itemName) {
		
		// Check if the item is in the backpack.
		// If so: remove the item from the backpack and put it
		// in the room.
		// If not: tell the player he can’t drop that.
	}

	// Please note that this method looks A LOT like
	// Game.handleDropCommand, only the other way arround!
	private void handleGetCommand(String itemName) {
		// Check if the item is in the room.
		// If so: remove the item from the room and put it
		// in the backpack.
		// If not: tell the player he can’t get that. 
	}

	/**
	 * @param command
	 * 
	 * 
	 *            Check if the command can take us to another room. If so: do
	 * 
	 *            it! Let the caller know if we actually traveled.
	 */
	private boolean checkRoomTravel(String command) {
		return false;
		// Get the currentRoom from the player and check if this room
		// has an exit in the direction of command. (East, south, north, west.)
		// If there is an exit in that direction, ask the currentRoom to get
		// that
		// that room.
		// Tell the player to travel to the destination room.
		// If there is no exit in that direction, tell the player.
		// If travel was successful, return true. If not, return false.
	}

}
