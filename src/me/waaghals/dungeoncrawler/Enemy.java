package me.waaghals.dungeoncrawler;

import me.waaghals.dungeoncrawler.items.Fists;
import me.waaghals.dungeoncrawler.items.Item;

/**
 * 
 * @author Patrick Berenschot
 *
 */
public class Enemy {
	private Room currRoom;
	private int health = 100;
	private int speed = 3;
	private Item weapon = new Fists();
	private Item lootItem;

	public Item loot() {
		if (lootItem != null) {
			Item tempItem = lootItem;
			lootItem = null;
			return tempItem;
		}
		return null;

	}

	public void setLootItem(Item lootItem) {
		this.lootItem = lootItem;
	}

	public Enemy(Item weapon, int speed) {
		this.weapon = weapon;
		this.setLootItem(weapon);
		this.speed = speed;
	}

	public Enemy(Item weapon) {
		this.weapon = weapon;
	}

	public Enemy() {
		// Creates an enemy with speed 3 and Fists as weapon
	}

	/*
	 * Move the enemy towards the destRoom Places the enemy in this new room
	 * returned by getNextRoomTowardsDest()
	 */
	public void step() {
		Game currGame = Game.INSTANCE;
		Player player = currGame.getPlayer();
		Room playerRoom = player.getCurrRoom();
		if (isAlive()) {
			// If the enemy is with the player in the same room, no need to move
			if (playerRoom == currRoom) {
				Narrator.say("There is an enemy with you in the room!");
				int damage = fight();
				Narrator.say("The enemy did %d%% of damage on you.", damage);
				player.damage(damage);
			} else {
				// Move the enemy <speed> rooms
				for (int i = 0; i < speed; i++) {
					if (playerRoom != currRoom) {
						GameLevel level = currGame.getGameLevel();
						Room newRoom = level.getNextRoomTowardsDest(currRoom,
								playerRoom);
						currRoom = newRoom;
					} else {
						Narrator.say("An enemy has found you! Fight him!");
					}
					// If the enemy is in the playerRoom, do nothing. Will fight
					// the enemy when the users makes the next move
				}
			}
		}
	}

	/**
	 * Set the health directly. Cannot be higher that 100 or lower than 0
	 * 
	 * @param health
	 */
	public void setHealth(int health) {
		if (health > 100) {
			this.health = 100;
		} else if (health < 0) {
			this.health = 0;
		} else {
			this.health = health;
		}
	}

	/**
	 * Use the weapon from the enemy and returns the damage it did.
	 * 
	 * @return Amount of damage the hit did.
	 */
	public int fight() {
		return weapon.fight();
	}

	/**
	 * Take point from the enemy's health.
	 * 
	 * @param diff
	 *            amount of points to subtract, to heal use negative points
	 */
	public void damage(int diff) {
		health = health - diff;
	}

	/**
	 * 
	 * @return true if Enemy is alive else false
	 */
	public boolean isAlive() {
		return health > 0;
	}

	/**
	 * Set the enemy in a new room
	 * 
	 * @param newRoom
	 */
	public void setRoom(Room newRoom) {
		currRoom = newRoom;
	}

	/**
	 * Get the room where the enemy is
	 * 
	 * @return Room, enemy's current room
	 */
	public Room getCurrRoom() {
		return currRoom;
	}
}
