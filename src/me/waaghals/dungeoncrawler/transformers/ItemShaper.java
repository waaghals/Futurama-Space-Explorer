package me.waaghals.dungeoncrawler.transformers;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import org.apache.commons.collections15.Transformer;

import me.waaghals.dungeoncrawler.Room;

/**
 * 
 * @author Patrick Berenschot
 * 
 */
public class ItemShaper<Room> implements Transformer<Room, Shape> {

	public Shape transform(Room room) {
		if (((me.waaghals.dungeoncrawler.Room) room).numItems() > 0) {
			return new Rectangle(-10, -10, 20, 20);
		}

		return new Ellipse2D.Double(-10, -10, 20, 20);
	}
}
