package me.waaghals.dungeoncrawler.transformers;

import java.awt.Color;
import java.awt.Paint;
import java.util.List;

import org.apache.commons.collections15.Transformer;

import me.waaghals.dungeoncrawler.Enemy;
import me.waaghals.dungeoncrawler.Game;
import me.waaghals.dungeoncrawler.GameLevel;
import me.waaghals.dungeoncrawler.Player;
import me.waaghals.dungeoncrawler.Room;

/**
 * 
 * @author Patrick Berenschot
 *
 */
public class RoomPainter<Room> implements Transformer<Room,Paint> {

	public Paint transform(Room room) {
		Game currGame = Game.INSTANCE;
		Player currPlayer = currGame.getPlayer();
		GameLevel currLevel = currGame.getGameLevel();
		Room playerRoom = (Room) currPlayer.getCurrRoom();
		
		//The user's room
		if(room == playerRoom) {
			return Color.GREEN;
		}
		
		
		//Give the enemy's room color
		List<Enemy> fromLevel = currLevel.getEnemies();
		if (fromLevel != null) {
			for (Enemy enemy : fromLevel) {
				if (room == enemy.getCurrRoom()) {
					if (enemy.isAlive()) {
						System.out.println(room + " =? " + enemy.getCurrRoom());
						return Color.MAGENTA;
					}
				}
			}
		}
		return Color.BLUE;
	}
}
