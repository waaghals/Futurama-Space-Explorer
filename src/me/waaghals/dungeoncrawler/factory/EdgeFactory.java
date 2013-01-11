package me.waaghals.dungeoncrawler.factory;

import me.waaghals.dungeoncrawler.Constants;
import me.waaghals.dungeoncrawler.Path;

import org.apache.commons.collections15.Factory;

class EdgeFactory implements Factory<Path> {
	int i = 0;
	public Path create() {
		int index = Constants.generator.nextInt(Constants.directions.length);
		int randomDirection = Constants.directions[index];
		Path edge = new Path(i++, randomDirection);
		return edge;
	}
}