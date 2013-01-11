package me.waaghals.dungeoncrawler;

import me.waaghals.dungeoncrawler.items.Fists;
import me.waaghals.dungeoncrawler.items.Item;

public class Enemy {
	private Room currRoom;
	private int health = 100;
	private int speed = 3; //Higher number means slower!
	private int numSteps = 0;
	private Item weapon = new Fists();
	private Item lootItem;
	
	public Item loot() {
		if(lootItem != null){
			Item tempItem = lootItem;
			lootItem = null;
			return tempItem;
		}
		return null;
		
	}

	public void setLootItem(Item lootItem) {
		this.lootItem = lootItem;
	}

	public Enemy(Item weapon, int speed){
		this.weapon = weapon;
		this.speed = speed;
	}
	
	public Enemy(Item weapon){
		this.weapon = weapon;
	}
	
	public Enemy(){
		//Creates an enemy with speed 3 and Fists as weapon
	}
	
	/*
	 * Move the enemy towards the destRoom
	 * Places the enemy in this new room returned by getNextRoomTowardsDest()
	 */
	public void step(){
		Game currGame = Game.getInstance();
		Player player = currGame.getPlayer();
		Room playerRoom = player.getCurrRoom();
		if(isAlive()){
			//If the enemy is with the player in the same room, no need to move
			if(playerRoom == currRoom){
				player.damage(fight());
			} else {
				//Only move towards the player every speed'th time
				if((numSteps%speed) == speed){
					GameLevel level = currGame.getGameLevel();
					Room newRoom = level.getNextRoomTowardsDest(currRoom, playerRoom);

					currRoom = newRoom;
				}
			}
		}
		numSteps++;
	}
	
	public int fight() {
		return weapon.fight();
	}

	public void damage(int diff){
		health = health - diff;
	}
	
	/**
	 * 
	 * @return true if Enemy is alive else false
	 */
	public boolean isAlive(){
		return health > 0;
	}
	
	public void setRoom(Room newRoom){
		currRoom = newRoom;
	}
	
	public Room getCurrRoom(){
		return currRoom;
	}
}
