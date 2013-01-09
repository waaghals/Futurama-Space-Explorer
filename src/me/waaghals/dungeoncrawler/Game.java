package me.waaghals.dungeoncrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.collections15.FactoryUtils;

import edu.uci.ics.jung.algorithms.generators.random.BarabasiAlbertGenerator;

import me.waaghals.dungeoncrawler.items.Item;

/**
 * @author Patrick Berenschot
 * 
 */
public class Game {
	private ArrayList<Room> rooms;
	private Player player;
	private Narrator attenborough = Narrator.getInstance();
	InputStreamReader istream = new InputStreamReader(System.in);
	BufferedReader bufRead = new BufferedReader(istream);

	/**
	 * @param none
	 * 
	 * 
	 */
	public Game() {

		player = new Player(getUserInput("What is your name?"));
		
		
		// Initialize everything you need:
		// the player, the rooms, items you want in the rooms, everything
		// then call the run() command.

		// All systems GO!
		run();
	}

	/**
	 * Run the game and keep asking for user input
	 * 
	 * Run the game
	 */
	private void run() {
		while (true) {
			if (!handleCommand(getUserInput())) {
				// Stop asking for user input
				break;
			}
		}
	}

	private String getUserInput(String question) {
		System.out.println(question);
		return getUserInput();
	}
	
	private String getUserInput() {
		try {
			return bufRead.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Process the users input
	 * 
	 * @param userInput
	 * @return false if user typed quit else true
	 */
	private boolean handleCommand(String userInput) {
		String[] arguments = userInput.split(" ");

		// With each command move the enemy closer to the player
		// TODO cleanup argument checking
		switch (arguments[0]) // arguments[0] is the command by the user
		{

		case "go":
			// Make sure the user adds a direction
			if (arguments.length == 2) {
				if (!player.move(arguments[1])) { // direction
					// TODO say player could not move in that direction
				}
				return true;
			}
			// TODO say missing argument
			return true;
		case "get":
			// Make sure the user adds an item to use
			if (arguments.length == 2) {
				handleGetCommand(arguments[1]);
				return true;
			}
			// TODO say missing argument
			return true;
		case "use":
			// Make sure the user adds an item to use
			if (arguments.length == 2) {
				handleUseCommand(arguments[1]);
				return true;
			} else if (arguments.length > 2) {
				handleUseCommand(arguments[1], arguments[2]); // item, argument
				return true;
			}
			// TODO say needs item to use (argument missing)
			return true;
		case "drop":
			// Make sure the user adds an item to use
			if (arguments.length == 2) {
				handleDropCommand(arguments[1]);
				return true;
			}
			// TODO say needs item to use (argument missing)

			return true;
		case "pack":
			player.showBackpack();
			return true;
		case "look":
			player.getCurRoom().sayEntryText();
			return true;

		case "fight":
			int damage;
			if (arguments.length == 2) {
				// TODO say not enough arguments
				damage = player.fight();
			} else if (arguments.length == 3) {
				if (arguments[1].equals("usage")) {
					damage = player.fight(arguments[2]);
				}
				// TODO check if opponent is in same room

			}
			// TODO default to fist fighting, "fight using" <Item> fights with
			// said item.
			return true;

		case "loot":

			// TODO make sure the enemy is dead first
			return true;

		case "quit":
			// Player wants to quit, return false!
			return false;

		case "help":
		default:
			handleHelpCommand();
			return true;
		}
	}

	private void handleUseCommand(String itemName) {
		if (player.get(itemName) != null) { // Does the player have itemName
			player.use(itemName);
		} else if (player.getCurRoom().get(itemName) != null) { // Does the room
																// have itemName
			if (player.add(player.getCurRoom().get(itemName))) { // Is the
																	// player
																	// able to
																	// add the
																	// item from
																	// the
																	// current
																	// room to
																	// its
																	// backpack
				player.use(itemName);
			}
		} else {
			attenborough.say(Narrator.ITEM_NOT_IN_ROOM, itemName);
			// TODO say no item with itemName
		}
	}

	private void handleUseCommand(String itemName, String argument) {
		if (player.get(itemName) != null) { // Does the player have itemName
			player.use(itemName, argument);
		} else if (player.getCurRoom().get(itemName) != null) { // Does the room
																// have itemName
			if (player.add(player.getCurRoom().get(itemName))) { // Is the
																	// player
																	// able to
																	// add the
																	// item from
																	// the
																	// current
																	// room to
																	// its
																	// backpack
				player.use(itemName, argument);
			}
		} else {
			attenborough.say(Narrator.ITEM_NOT_IN_ROOM, itemName);
		}
	}

	private void handleHelpCommand() {
		attenborough
				.say("Usage: \n"
						+ "go <north, east, south or west>\t\t\tTravel in said direction\n"
						+ "get <item>\t\t\tPut <item> in backpack\n"
						+ "use <item>\t\t\tUse <item>\n"
						+ "pack\t\t\t\t\tShows what is in your back pack\n"
						+ "look\t\t\t\t\tTells you what your surrounding look like\n"
						+ "fight\t\t\t\t\tFight your opponent\n"
						+ "fight using <item>\t\t\tFight your opponent and use <item> as a weapon\n"
						+ "loot\t\t\t\t\tGrab stuff from your opponent after you killed it\n"
						+ "help\t\t\t\t\tShows this message\n"
						+ "quit\t\t\t\t\tStops the game\n"
						+ "\n\n\n"
						+ "Objective: \n"
						+ "Survive, find the tresure and bring it back to the start.");

	}

	private void handleDropCommand(String itemName) {
		Item droppedItem = player.drop(itemName);
		if (droppedItem != null) {
			// TODO say stuff was dropped
			player.getCurRoom().addItem(droppedItem);
		}
	}

	// Please note that this method looks A LOT like
	// Game.handleDropCommand, only the other way arround!
	private void handleGetCommand(String itemName) {
		Item itemFromRoom = player.getCurRoom().removeItem(itemName);
		if (itemFromRoom != null) {
			// TODO say item was retrieved
			player.add(itemFromRoom);
		}
		// TODO tell the player he can't do that
	}
}
