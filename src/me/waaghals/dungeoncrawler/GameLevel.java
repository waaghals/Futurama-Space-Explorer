package me.waaghals.dungeoncrawler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.uci.ics.jung.algorithms.shortestpath.ShortestPathUtils;
import edu.uci.ics.jung.algorithms.shortestpath.UnweightedShortestPath;
import edu.uci.ics.jung.graph.Graph;

/**
 * @author Patrick Berenschot
 * 
 */
public class GameLevel {

	private Graph<Room, Path> map;
	private int level = 1;
	private int numRooms;
	private int numEnemies;
	private int numPaths;
	private int numItems;
	private List<Enemy> enemies = new ArrayList<Enemy>();

	public int getLevel(){
		return level;
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
	
	public Graph<Room, Path> getMap(){
		return map;
	}
	
	public Room getNextRoomTowardsDest(Room startRoom, Room destRoom){
		//Shortest Path algorithm
		UnweightedShortestPath<Room, Path> path = new UnweightedShortestPath<Room, Path>(map);

		
		//Util returns a list of paths to the dest, firstPath is the first path we should take
		List<Path> route = ShortestPathUtils.getPath(map, path, startRoom, destRoom);
		
		Path firstPath = route.get(0);

		return map.getOpposite(startRoom, firstPath);
	}
	
	public Room getRoomInDest(Room sourceRoom, int direction){
		Collection<Path> paths = map.getOutEdges(sourceRoom);
		for (Path path : paths) {
			if(path.getDirection() == direction){
				return map.getOpposite(sourceRoom, path);
			}
		}
		return null;
	}
	
	public boolean hasRoomInDest(Room sourceRoom, int direction){
		Collection<Path> paths = map.getOutEdges(sourceRoom);
		for (Path path : paths) {
			if(path.getDirection() == direction){
				return true;
			}
		}
		return false;
	}
	
	public List<Enemy> getEnemies(){
		return enemies;
	}
	
	public void addEnemies(Enemy enemy){
		enemies.add(enemy);
	}

	public void setGraph(Graph<Room, Path> graph) {
		map = graph;		
	}

	public void setStats(int level, int numRooms, int numItems,
			int numEnemies, int vertexCount) {
		this.level = level;
		this.numRooms = numRooms;
		this.numItems = numItems;
		this.numEnemies = numEnemies;
		this.numPaths = vertexCount;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Level stats\n");
		sb.append("-----------\n");
		sb.append("Level " + level + "\n");
		sb.append("Rooms " + numRooms + "\n");
		sb.append("Enemies " + numEnemies + "\n");
		sb.append("Paths " + numPaths + "\n");
		sb.append("Items " + numItems + "\n");
		return sb.toString();
	}
}
