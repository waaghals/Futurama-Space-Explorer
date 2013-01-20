package me.waaghals.dungeoncrawler.transformers;

import java.awt.Color;
import java.awt.Paint;
import java.util.List;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.graph.Graph;

import me.waaghals.dungeoncrawler.Constants;
import me.waaghals.dungeoncrawler.Enemy;
import me.waaghals.dungeoncrawler.Game;
import me.waaghals.dungeoncrawler.GameLevel;
import me.waaghals.dungeoncrawler.Path;
import me.waaghals.dungeoncrawler.Player;
import me.waaghals.dungeoncrawler.Room;

/**
 * 
 * @author Patrick Berenschot
 *
 */
public class PathPainter<Path> implements Transformer<me.waaghals.dungeoncrawler.Path,Paint> {
	
	public Paint transform(me.waaghals.dungeoncrawler.Path path) {
		//Graph<Room, me.waaghals.dungeoncrawler.Path> graph = Game.INSTANCE.getGameLevel().getMap();
		
		switch (path.getDirection()) {
		
		case Constants.NORTH:
			return Color.green;

		case Constants.EAST:
			return Color.blue;

		case Constants.SOUTH:
			return Color.yellow;

		case Constants.WEST:
			return Color.red;

		}
	
		return Color.gray;
	}
}
