package me.waaghals.dungeoncrawler.factory;

import me.waaghals.dungeoncrawler.Path;
import me.waaghals.dungeoncrawler.Room;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;

/**
 * @author Patrick Berenschot
 * 
 */
class GraphFactory implements Factory<Graph<Room, Path>> {
	public DirectedGraph<Room, Path> create() {
		return new DirectedSparseMultigraph<Room, Path>();
	}
}