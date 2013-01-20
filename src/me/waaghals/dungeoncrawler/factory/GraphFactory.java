package me.waaghals.dungeoncrawler.factory;

import me.waaghals.dungeoncrawler.Path;
import me.waaghals.dungeoncrawler.Room;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

/**
 * @author Patrick Berenschot
 * 
 */
class GraphFactory implements Factory<Graph<Room, Path>> {
	public UndirectedGraph<Room, Path> create() {
		return new UndirectedSparseGraph<Room, Path>();
	}
}