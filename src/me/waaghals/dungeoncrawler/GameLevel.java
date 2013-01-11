package me.waaghals.dungeoncrawler;

import java.util.List;

import edu.uci.ics.jung.algorithms.shortestpath.ShortestPathUtils;
import edu.uci.ics.jung.algorithms.shortestpath.UnweightedShortestPath;
import edu.uci.ics.jung.graph.Graph;

public class GameLevel {

	private Graph<Room, Path> map;
	private StringBuilder stats = new StringBuilder();
	private int level = 1;
	private Enemy[] enemies;
	
	public GameLevel(Graph<Room, Path> map){
		this.map = map;
	}
	
	public Room getRandomRoom() {
		int size = map.getVertices().size();
		int item = Constants.generator.nextInt(size); 
		int i = 0;
		for (Room room : map.getVertices()) {
			if (i == item){
				return room;
			}
			i = i + 1;
		}
		//Won't reach
		return null;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		
		builder.append("Level: " + level + "\n");
		builder.append("Current map:\n");
		builder.append(map);
		builder.append("\nStats:\n");
		builder.append(stats);
		return builder.toString();
	}

	public void addStat(String string) {
		stats.append(string);
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public Graph<Room, Path> getMap(){
		return map;
	}
	
	public Room getNextRoomTowardsDest(Room startRoom, Room destRoom){
		//Shortest Path algorithm
		UnweightedShortestPath<Room, Path> path = new UnweightedShortestPath<Room, Path>(map);
		
		//Util returns a list of paths to the dest, firstPath is the first path we should take
		List<Path> route = ShortestPathUtils.getPath(map, path, startRoom, destRoom);
		Path firstPath = route.get(0);
		return map.getDest(firstPath);
	}
	
	public Enemy[] getEnemies(){
		return enemies;
	}
}
