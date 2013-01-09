package me.waaghals.dungeoncrawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.waaghals.dungeoncrawler.items.Item;
import me.waaghals.dungeoncrawler.items.Narrator;

/**
 * @author Patrick Berenschot
 * 
 */
public class Game {
	private ArrayList<Room> rooms;
	private Player player;
	private Narrator attenborough = Narrator.getInstance(); 


	/**
	 * @param none
	 * 
	 * 
	 */
	public Game() {

		// Initialize everything you need:
		// the player, the rooms, items you want in the rooms, everything
		// then call the run() command.
		
		//All systems GO!
		run();
	}

	/**
	 * Run the game and keep asking for user input
	 * 
	 *            Run the game
	 */
	private void run() {
		InputStreamReader istream = new InputStreamReader(System.in) ;
        BufferedReader bufRead = new BufferedReader(istream) ;

        try {
        	 while(true){
        		 if(!handleCommand(bufRead.readLine())){
        			 //Stop asking for user input
        			 break;
        		 }
        	 }
        }catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Process the users input
	 * 
	 * @param userInput
	 * @return false if user typed quit else true
	 */
	private boolean handleCommand(String userInput) {
		String[] arguments = userInput.split(" ");
		

		//With each command move the enemy closer to the player
		//TODO cleanup argument checking
		switch (arguments[0]) // arguments[0] is the command by the user
		{
		
		case "go":
			//Make sure the user adds a direction
			if(arguments.length == 2){
				player.move(arguments[1]); //direction
				return true;
			}
			//TODO say missing argument
			return true;
		case "get":
			//Make sure the user adds an item to use
			if(arguments.length == 2){
				//Does the current room have an item with itemName
				Item itemFromRoom = player.getCurRoom().removeItem(arguments[1]);

				if(itemFromRoom != null){
					player.add(itemFromRoom);
				}
				return true;
			}
			//TODO say missing argument
			return true;
		case "use":
			//Make sure the user adds an item to use
			if(arguments.length == 2){
				handleUseCommand(arguments[1]);
			} else if(arguments.length > 2){
				handleUseCommand(arguments[1], arguments[2]); //item, argument
			} else {
				//TODO say needs item to use (argument missing)
			}
			return true;
		case "pack":

			return true;
		case "look":

			return true;

		case "fight":

			// TODO default to fist fighting, "fight using" <Item> fights with
			// said item.
			return true;

		case "loot":

			// TODO make sure the enemy is dead first
			return true;

		case "quit":
			//Player wants to quit, return false!
			return false;
			
		case "help":
		default:
			handleHelp();
			return true;
		}
	}

	private void handleUseCommand(String itemName) {
		if(player.get(itemName) != null){							//Does the player have itemName
			player.use(itemName);	
		} else if(player.getCurRoom().get(itemName) != null) { 		//Does the room have itemName
			if(player.add(player.getCurRoom().get(itemName))){  	//Is the player able to add the item from the current room to its backpack
				player.use(itemName);
			}
		} else {
			attenborough.say(Narrator.ITEM_NOT_IN_ROOM, itemName);
			//TODO say no item with itemName
		}
	}
	
	private void handleUseCommand(String itemName, String argument) {
		if(player.get(itemName) != null){							//Does the player have itemName
			player.use(itemName, argument);	
		} else if(player.getCurRoom().get(itemName) != null) { 		//Does the room have itemName
			if(player.add(player.getCurRoom().get(itemName))){  	//Is the player able to add the item from the current room to its backpack
				player.use(itemName, argument);
			}
		} else {
			attenborough.say(Narrator.ITEM_NOT_IN_ROOM, itemName);
		}
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
