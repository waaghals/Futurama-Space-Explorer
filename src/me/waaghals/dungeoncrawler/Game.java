package me.waaghals.dungeoncrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import me.waaghals.dungeoncrawler.factory.GameLevelFactory;
import me.waaghals.dungeoncrawler.items.*;

/**
 * @author Patrick Berenschot
 * 
 */
public enum Game {
	INSTANCE;
	private Player player;
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

		// intro();
		Narrator.say(Narrator.GAME_INTRO);
		player.setCurrRoom(startRoom);

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
		Narrator.say(Narrator.IMAGE_LOGO);
		sleep(1000);
		cleanScreen(true);
		for (int i = 10; i > 0; i--) {
			sleep(150);
			cleanScreen(false);
			Narrator.say(Narrator.IMAGE_SHIP);

			// Space between ship and ground
			for (int j = i; j > 0; j--) {
				System.out.println();
			}
			Narrator.say("----------------------------------------------------------------------------");
		}
		sleep(1000);

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
		Narrator.say(Narrator.GAME_INTRO);
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
					Narrator.say(Narrator.GAME_OVER, currLevel.getLevel());
					break;
				}
			}
		}
		Narrator.say(Narrator.SEE_YOU_SOON);
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
			Narrator.say(Narrator.MISSING_ARGUMENT, "go");
			return true;
		case "get":
			// Make sure the user adds an item to use
			if (arguments.length == 2) {
				handleGetCommand(arguments[1]);
				stepEnemies();
				return true;
			}
			Narrator.say(Narrator.MISSING_ARGUMENT, "get");
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
			Narrator.say(Narrator.MISSING_ARGUMENT, "use");
			return true;
		case "drop":
			// Make sure the user adds an item to use
			if (arguments.length == 2) {
				handleDropCommand(arguments[1]);
				stepEnemies();
				return true;
			}
			Narrator.say(Narrator.MISSING_ARGUMENT, "drop");
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
			Narrator.say("There are " + getEnemyFromPlayerRoom().size() + " opponents");
			Narrator.say("There are " + getDeadEnemyFromPlayerRoom().size() + " opponents which can be looted");
			stepEnemies();
			return true;

		case "fight":
			if (arguments.length == 1) {
				handleFightCommand();
				stepEnemies();
			} else if (arguments.length == 2) {
				Narrator.say(Narrator.MISSING_ARGUMENT, "fight");
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
			Narrator.say(currLevel.toString());
			return true;

			// TODO un-fail this
			/*
			 * case "map":
			 * 
			 * // Show a window with the current map JFrame jf = new JFrame();
			 * Graph<Room, Path> g = currLevel.getMap(); Dimension size = new
			 * Dimension(900,900); FRLayout2<Room, Path> frMap = new
			 * FRLayout2<Room, Path>(g); frMap.setRepulsionMultiplier(1.5);
			 * frMap.setSize(size);
			 * 
			 * CircleLayout<Room, Path> circleMap = new CircleLayout<Room,
			 * Path>(g); circleMap.setSize(size); VisualizationViewer<Room,
			 * Path> vv = new VisualizationViewer<Room, Path>( frMap);
			 * vv.getRenderContext().setVertexFillPaintTransformer(new
			 * RoomPainter<Room>());
			 * vv.getRenderContext().setVertexShapeTransformer(new
			 * ItemShaper<Room>());
			 * vv.getRenderContext().setArrowFillPaintTransformer(new
			 * PathPainter<Path>());
			 * vv.getRenderContext().setEdgeLabelTransformer(new
			 * ToStringLabeller<Path>());
			 * 
			 * //jf.getContentPane().add(vv); jf.add(new
			 * GraphZoomScrollPane(vv));
			 * 
			 * // jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 * //jf.setSize(size); jf.pack(); jf.setVisible(true); return true;
			 */
		case "quit":
			// Player wants to quit, return false!
			return false;

		case "help":
			handleHelpCommand();
			return true;

		default:
			Narrator.say(Narrator.NOT_RECOGNISED);
			return true;
		}
	}

	private void handleGoCommand(String direction) {
		int intDirection = Constants.getIntDirection(direction);

		if (getEnemyFromPlayerRoom().size() > 0) {
			Narrator.say(Narrator.ENEMY_GRIP);
			return;
		}

		// Does the currRoom have a exit in intDirection?
		if (player.getCurrRoom().hasExit(intDirection)) {
			Narrator.say(Narrator.WALKING, direction);
			try {
				player.move(intDirection);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Narrator.say(Narrator.NO_EXIT, direction);
		}

	}

	private void handleUseCommand(String itemName) {
		// Cheat!
		if (itemName.equals("nibler")) {
			player.add(new DarkMatter());
			player.add(new PlanetaryAnnihilator());
			Narrator.say("Cheaters!\n:( \n\nI don't wan't to live on this planet anymore");
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
			Narrator.say(Narrator.ITEM_NOT_IN_ROOM, itemName);
		}
	}

	private void handleHelpCommand() {
		Narrator.say(Narrator.GAME_HELP);

	}

	private void handleDropCommand(String itemName) {
		Item droppedItem = player.drop(itemName);
		if (droppedItem != null) {
			Narrator.say(Narrator.ITEM_DROPPED, itemName);
			player.getCurrRoom().addItem(droppedItem);
			return;
		}
		Narrator.say(Narrator.ITEM_NOT_IN_BACKPACK, itemName);
	}

	private void handleGetCommand(String itemName) {
		Item itemFromRoom = player.getCurrRoom().removeItem(itemName);
		if (itemFromRoom != null) {
			player.add(itemFromRoom);
			return;
		}
		Narrator.say(Narrator.ITEM_NOT_IN_ROOM, itemName);
	}

	private void handleFightCommand() {
		handleFightCommand("whole lot of nothing"); // Fill fight with fists
	}

	private void handleFightCommand(String withItemName) {
		List<Enemy> enemies = getEnemyFromPlayerRoom();
		if (enemies.size() > 0) {
			for (Enemy enemy : enemies) {
				int damage = player.fight(withItemName);

				if (damage > 80) {
					Narrator.say(Narrator.FIGHT_USING_ITEM_HIGH_DAMAGE, damage);
				} else if (damage > 30) {
					Narrator.say(Narrator.FIGHT_USING_ITEM_MEDIUM_DAMAGE,
							damage);
				} else if (damage > 0) {
					Narrator.say(Narrator.FIGHT_USING_ITEM_LOW_DAMAGE, damage);
				} else if (damage == 0) {
					Narrator.say(Narrator.FIGHT_USING_ITEM_NONE_DAMAGE, damage);
				}
				enemy.damage(damage);

				if (!enemy.isAlive()) {
					Narrator.say(Narrator.ENEMY_KILLED);
				}
			}
		} else {
			Narrator.say(Narrator.NO_ENEMY);
		}
	}

	private void handleLootCommand() {
		List<Enemy> enemies = getDeadEnemyFromPlayerRoom();
		if (enemies.size() > 0) {
			for (Enemy enemy : enemies) {

				Item lootedItem = enemy.loot();
				if (lootedItem != null) {
					player.add(lootedItem);
				} else {
					Narrator.say(Narrator.NOTHING_TO_LOOT);
				}
			}
		} else {
			Narrator.say(Narrator.NO_DEAD_ENEMY);
		}
	}

	public Player getPlayer() {
		return player;
	}

	public GameLevel getGameLevel() {
		return currLevel;
	}

	public List<Enemy> getEnemyFromPlayerRoom() {
		return getEnemyFromRoom(player.getCurrRoom(), false);
	}

	public List<Enemy> getDeadEnemyFromPlayerRoom() {
		return getEnemyFromRoom(player.getCurrRoom(), true);
	}

	public List<Enemy> getEnemyFromRoom(Room room, boolean dead) {
		List<Enemy> fromLevel = currLevel.getEnemies();
		List<Enemy> inRoom = new ArrayList<Enemy>();
		if (fromLevel != null) {
			for (Enemy enemy : fromLevel) {
				if (enemy.getCurrRoom() == room) {
					if (dead && !enemy.isAlive()) {
						inRoom.add(enemy);
					} else if (!dead && enemy.isAlive()) {
						inRoom.add(enemy);
					}
				}
			}
			return inRoom;
		}
		return null;
	}
}
