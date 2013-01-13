package me.waaghals.dungeoncrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import javax.swing.JFrame;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import me.waaghals.dungeoncrawler.factory.GameLevelFactory;
import me.waaghals.dungeoncrawler.items.*;
import me.waaghals.dungeoncrawler.transformers.RoomPainter;

/**
 * @author Patrick Berenschot
 * 
 */
public enum Game {
	INSTANCE;
	private Player player;
	private Narrator farnsworth = Narrator.getInstance();
	private InputStreamReader istream = new InputStreamReader(System.in);
	private BufferedReader bufRead = new BufferedReader(istream);
	private GameLevel currLevel;
	private Room startRoom;

	public Room getStartRoom() {
		return startRoom;
	}

	/**
	 * Start a new game, starting at level 1 Show the intro
	 */
	public void start() {

		player = new Player();
		// Create the level and add them to the room
		currLevel = new GameLevelFactory(5).create();
		startRoom = currLevel.getRandomRoom();

		intro();

		// All systems GO!
		run();
	}

	private void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Remove the stuff from the console
	 * 
	 * @param slow
	 *            if true only cleans 23 rows, and does it slowly, for title
	 */
	private void cleanScreen(boolean slow) {
		if (slow) {
			for (int i = 0; i < 23; i++) {
				System.out.println();
				sleep(150);
			}
		}
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}

	/**
	 * Show the intro sequence
	 * 
	 */
	private void intro() {
		farnsworth.say(Narrator.IMAGE_LOGO);
		sleep(1000);
		cleanScreen(true);
		for (int i = 10; i > 0; i--) {
			sleep(150);
			cleanScreen(false);
			farnsworth.say(Narrator.IMAGE_SHIP);

			// Space between ship and ground
			for (int j = i; j > 0; j--) {
				System.out.println();
			}
			farnsworth
					.say("----------------------------------------------------------------------------");
		}
		sleep(1000);
		farnsworth.say(Narrator.GAME_INTRO);
		player.setCurrRoom(startRoom);
	}

	/**
	 * Go to the next level and start that level
	 * 
	 */
	public void levelUp() {
		int currLevelNr = currLevel.getLevel() + 1;
		currLevel = new GameLevelFactory(currLevelNr).create();
		startRoom = currLevel.getRandomRoom();

		player.setCurrRoom(startRoom);
		player.emptyBackpack();
		farnsworth.say(Narrator.GAME_INTRO);
	}

	/**
	 * Run the game and keep asking for user input
	 * 
	 */
	private void run() {
		while (true) {
			// Ask for user input
			if (!handleCommand(getUserInput())) {
				// Stop asking for user input
				break;
			} else {
				if (!player.isAlive()) {
					farnsworth.say(Narrator.GAME_OVER, currLevel.getLevel());
					break;
				}
			}
		}
		farnsworth.say(Narrator.SEE_YOU_SOON);
		switch (getUserInput()) {
		case "yes":
		case "y":
		case "ja":
		case "j":

			// Start a new game
			Game.INSTANCE.start();
			break;

		default:
			System.exit(0);
			break;
		}
	}

	/**
	 * Is the player in the same room he started in
	 * 
	 * @return true if the player is back in the room he started in.
	 */
	public boolean isHome() {
		return player.getCurrRoom() == startRoom;
	}

	/**
	 * Get the user input from the console
	 * 
	 * @return String the users input
	 */
	private String getUserInput() {
		try {
			return bufRead.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Perform a step for each enemy, each enemy decides for its own if it
	 * should move, attack or do nothing.
	 */
	private void stepEnemies() {
		List<Enemy> enemies = currLevel.getEnemies();
		if (enemies != null) {
			for (Enemy enemy : enemies) {
				enemy.step();
			}
		}
	}

	/**
	 * Process the users input
	 * 
	 * @param userInput
	 * @return false if user typed quit else true
	 */
	private boolean handleCommand(String userInput) {
		userInput = userInput.toLowerCase();
		String[] arguments = userInput.split(" ");

		// With each command move the enemy closer to the player
		switch (arguments[0].toLowerCase()) // arguments[0] is the command by
											// the user
		{

		case "go":
			// Make sure the user adds a direction
			if (arguments.length == 2) {
				handleGoCommand(arguments[1]);
				stepEnemies();
				return true;
			}
			farnsworth.say(Narrator.MISSING_ARGUMENT, "go");
			return true;
		case "get":
			// Make sure the user adds an item to use
			if (arguments.length == 2) {
				handleGetCommand(arguments[1]);
				stepEnemies();
				return true;
			}
			farnsworth.say(Narrator.MISSING_ARGUMENT, "get");
			return true;
		case "use":
			// Make sure the user adds an item to use
			if (arguments.length == 2) {
				handleUseCommand(arguments[1]);
				stepEnemies();
				return true;
			} else if (arguments.length > 2) {
				handleUseCommand(arguments[1], arguments[2]); // item, argument
				stepEnemies();
				return true;
			}
			farnsworth.say(Narrator.MISSING_ARGUMENT, "use");
			return true;
		case "drop":
			// Make sure the user adds an item to use
			if (arguments.length == 2) {
				handleDropCommand(arguments[1]);
				stepEnemies();
				return true;
			}
			farnsworth.say(Narrator.MISSING_ARGUMENT, "drop");
			return true;

		case "pack":
		case "backpack":
		case "items":
			player.showBackpack();
			stepEnemies();
			return true;

		case "look":
			player.getCurrRoom().sayEntryText();
			player.getCurrRoom().sayPosibleDirections();
			player.getCurrRoom().sayItems();
			stepEnemies();
			return true;

		case "fight":
			if (arguments.length == 1) {
				handleFightCommand();
				stepEnemies();
			} else if (arguments.length == 2) {
				farnsworth.say(Narrator.MISSING_ARGUMENT, "fight");
			} else if (arguments[1].equals("using")) {
				handleFightCommand(arguments[2]);
				stepEnemies();
			}
			return true;

		case "loot":
			handleLootCommand();
			stepEnemies();
			return true;

		case "stats":
			farnsworth.say(currLevel.toString());
			return true;

		case "map":

			// Show a window with the current map
			JFrame jf = new JFrame();
			Graph<Room, Path> g = currLevel.getMap();
			VisualizationViewer<Room, Path> vv = new VisualizationViewer<Room, Path>(
					new CircleLayout<Room, Path>(g));
			vv.getRenderContext().setVertexFillPaintTransformer(
					new RoomPainter<Room>());

			jf.getContentPane().add(vv);
			// jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.pack();
			jf.setVisible(true);
			return true;

		case "quit":
			// Player wants to quit, return false!
			return false;

		case "help":
			handleHelpCommand();
			return true;

		default:
			farnsworth.say(Narrator.NOT_RECOGNISED);
			return true;
		}
	}

	private void handleGoCommand(String direction) {
		int intDirection = Constants.getIntDirection(direction);

		// Does the currRoom have a exit in intDirection?
		if (player.getCurrRoom().hasExit(intDirection)) {
			farnsworth.say(Narrator.WALKING, direction);
			try {
				player.move(intDirection);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			farnsworth.say(Narrator.NO_EXIT, direction);
		}

	}

	private void handleUseCommand(String itemName) {
		// Cheat!
		if (itemName.equals("nibler")) {
			// Add the Dark Matter to the user.

			// Add stuff
			player.add(new DarkMatter());
			farnsworth
					.say("Cheaters!\n:( \n\nI don't wan't to live on this planet anymore");
		} else {
			handleUseCommand(itemName, null);
		}
	}

	private void handleUseCommand(String itemName, String argument) {
		Item roomItem = player.getCurrRoom().removeItem(itemName);
		if (player.get(itemName) != null) { // Does the player have itemName
			if (argument == null) {
				player.use(itemName);
			} else {
				player.use(itemName, argument);
			}
		} else if (roomItem != null) {
			if (player.add(roomItem)) {
				if (argument == null) {
					player.use(itemName);
				} else {
					player.use(itemName, argument);
				}
			}
		} else {
			farnsworth.say(Narrator.ITEM_NOT_IN_ROOM, itemName);
		}
	}

	private void handleHelpCommand() {
		farnsworth.say(Narrator.GAME_HELP);

	}

	private void handleDropCommand(String itemName) {
		Item droppedItem = player.drop(itemName);
		if (droppedItem != null) {
			farnsworth.say(Narrator.ITEM_DROPPED, itemName);
			player.getCurrRoom().addItem(droppedItem);
			return;
		}
		farnsworth.say(Narrator.ITEM_NOT_IN_BACKPACK, itemName);
	}

	private void handleGetCommand(String itemName) {
		Item itemFromRoom = player.getCurrRoom().removeItem(itemName);
		if (itemFromRoom != null) {
			player.add(itemFromRoom);
			return;
		}
		farnsworth.say(Narrator.ITEM_NOT_IN_ROOM, itemName);
	}

	private void handleFightCommand() {
		handleFightCommand("A non existing itemName"); // Fill fight with fists
	}

	private void handleFightCommand(String withItemName) {
		int i = 0;
		List<Enemy> fromLevel = currLevel.getEnemies();
		if (fromLevel != null) {
			for (Enemy enemy : fromLevel) {
				// If there are more enemies in the same room, also fight them.
				if (enemy.getCurrRoom() == player.getCurrRoom()) {
					i++;
					// if withItemName does not exists the player fight using
					// his
					// fists
					int damage = player.fight(withItemName);
					enemy.damage(damage);
				}
			}
		}

		// Are there enemies in the room?
		if (i == 0) {
			farnsworth.say(Narrator.NO_ENEMY);
		}
	}

	private void handleLootCommand() {
		int i = 0;
		List<Enemy> fromLevel = currLevel.getEnemies();
		if (fromLevel != null) {
			for (Enemy enemy : fromLevel) {
				// If there are more enemies in the same room, also fight them.
				if (enemy.getCurrRoom() == player.getCurrRoom()) {
					if (!enemy.isAlive()) {
						i++;
						Item lootedItem = enemy.loot();
						if (lootedItem != null) {
							player.add(lootedItem);
						}
					}
				}
			}
		}

		// Are there dead enemies in the room?
		if (i == 0) {
			farnsworth.say(Narrator.NO_ENEMY);
		}
	}

	public Player getPlayer() {
		return player;
	}

	public GameLevel getGameLevel() {
		return currLevel;
	}
}
