package me.waaghals.dungeoncrawler.factory;

import me.waaghals.dungeoncrawler.Constants;
import me.waaghals.dungeoncrawler.Path;

import org.apache.commons.collections15.Factory;

/**
 * @author Patrick Berenschot
 * 
 */
class EdgeFactory implements Factory<Path> {
	int i = 1;
	public Path create() {
		Path edge = new Path(i++);
		return edge;
	}
}