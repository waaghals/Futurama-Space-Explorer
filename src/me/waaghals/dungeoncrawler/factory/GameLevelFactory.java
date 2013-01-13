package me.waaghals.dungeoncrawler.factory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.collections15.Factory;
import edu.uci.ics.jung.algorithms.generators.random.EppsteinPowerLawGenerator;
import edu.uci.ics.jung.graph.*;
import me.waaghals.dungeoncrawler.*;


/**
 * 
 * @author Patrick Berenschot
 * 
 *         All the formulas are made up. I just went and used a plotter, plotted
 *         some graphs which made sense and used those formulas.
 * 
 */
public class GameLevelFactory implements Factory<GameLevel>{

	//private ArrayList<Room> map;
	public Graph<Room, Path> levelMap = new UndirectedSparseGraph<Room, Path>();
	private int level;

	public GameLevelFactory(int level) {
		this.level = level;
	}

	/**
	 * Generate a level based on the level the users is playing. (Starts at
	 * level 1)
	 * 
	 * @param level
	 *            The level which needs to be generated
	 * @param items
	 *            A list of items which will be added. Items are used from top
	 *            to bottom. When the array is empty the first item will be
	 *            added again.
	 */
	public GameLevel create(){
		GameLevel currLevel = new GameLevel(getGraph());
		ItemFactory itemFactory = new ItemFactory();
		for (int i = 0; i < numItems(); i++) {
			Room room = currLevel.getRandomRoom();
			room.addItem(itemFactory.create());
		}
		
		EnemyFactory enemyFactory = new EnemyFactory();
		for (int i = 0; i < numEnemies(); i++) {
			Enemy newEnemy = enemyFactory.create();
			currLevel.addEnemies(newEnemy);
			newEnemy.setRoom(currLevel.getRandomRoom());
		}
		
		currLevel.addStat("Num Items: \t" + numItems() + "\n");
		currLevel.addStat("Num Rooms: \t" + currLevel.getMap().getVertexCount() + "\n");
		currLevel.addStat("Num Enemies: \t" + numEnemies() + "\n");
		currLevel.addStat("Num Paths: \t" + currLevel.getMap().getEdgeCount() + "\n");
		currLevel.addStat("currLevel: \t" + level + "\n");
		currLevel.setLevel(level);
		
		return currLevel;
	}

	private int[] getRandomDirections(int amount) {
		if(amount > Constants.directions.length){
			throw new IllegalArgumentException("amount is higher that directions.lenght");
		}
		
		//Mix the array up
		Collections.shuffle(Arrays.asList(Constants.directions));
		
		//Grab the first amount items
		int [] possibilities = Arrays.copyOfRange(Constants.directions, 0, amount);
		return possibilities;
	}

	/**
	 * Calculate the number of rooms the level has.
	 * 
	 * @param level
	 *            The level that is being generated. Needs to be higher that 1.
	 * @return f(x) = ((x-1)^2) * 0.3+1
	 */
	private int numRooms() {
		if (level < 1) {
			// No need to do the math, f(1) = 10
			return 10;
		}
		return 9 + (level * 3);
	}

	/**
	 * The number of Enemies in the level, level 1 and 2 have 0 enemies.
	 * 
	 * @param level
	 * @return f(x) = sqr(x-2)*1.1
	 */
	private int numEnemies() {
		return (int) Math.round(Math.sqrt(level - 2) * 1.1);
	}

	/**
	 * The number of Items in the level. A level has less than half the items of numRooms
	 * 
	 * @param level
	 * @return numRooms / 2.5
	 */
	private int numItems() {
		return (int) Math.round(numRooms() / 2.5);
	}
	
	/**
	 * The level has numRooms * 2 paths;
	 * 
	 * @param level
	 * @return numRooms * 2
	 */
	private int numPaths() {
		return (int) Math.round(numRooms() * 2);
	}

	/**
	 * Take one fancy Graph generator then brute force the edges. Highly elegant ;)
	 * 
	 * @return the graph for this level
	 */
	Graph<Room, Path> getGraph() {

		Graph<Room, Path> g = new EppsteinPowerLawGenerator<Room, Path>(
				new GraphFactory(), new VertexFactory(), new EdgeFactory(),
				numRooms(), numPaths(), 1 ).create();

		// Nominees Sets
		Set<Room> removeVertex = new HashSet<Room>();
		Set<Path> removeEdge = new HashSet<Path>();

		//Nominate all Edges which are in the same direction
		for (Room v : g.getVertices()) {
			//Nominate all edges which point to there self.
			Path selfRef = g.findEdge(v, v);
			//If there is a connection
			if(selfRef != null){
				g.removeEdge(selfRef);
			}
		}
		
		// Remove all nominees
		for (Path e : removeEdge) {
			//System.out.println("Removing edge " + e);
			g.removeEdge(e); // Remove the first edge
		}
		

		// Nominate all Vertices with more than 4 edges.
		for (Room v : g.getVertices()) {
			//Get all outgoing edges from Room v

			Collection<Path> edges = g.getInEdges(v);

			int i = edges.size();
			//System.out.println("Number of edges for Room " + v + " = " + i);
			while (i > Constants.directions.length) {
				//System.out.println("Found an vertex with to many edges");
				Path e = edges.iterator().next();
				g.removeEdge(e);
				i--;
			}
		}
		
		// Remove all nominees
		for (Path e : removeEdge) {
			//System.out.println("Removing edge " + e);
			g.removeEdge(e);
		}
		
		// Nominate all Vertices without an edge
		for (Room v : g.getVertices()) {
			if (g.degree(v) == 0) {
				//System.out.println("Found a vertex with zero edges");
				removeVertex.add(v);
			}
		}

		// Remove all nominees
		for (Room v : removeVertex) {
			g.removeVertex(v);
		}
		
		//Connect all the path to a random direction
		for(Room currRoom : g.getVertices()){
			Collection<Path> paths = g.getIncidentEdges(currRoom);
			
			//Get paths.size() amount of random direction, all unique because there can't be two path east for example
			int[] directions = getRandomDirections(paths.size());
			int index = 0;
			for (Path edge : g.getIncidentEdges(currRoom)) {
				
				//If the first room from the endpoint pair is the same as Room V use second room and vice versa.
				Room firstRoom = g.getEndpoints(edge).getFirst();
				Room destRoom = g.getEndpoints(edge).getSecond();
				if(firstRoom != currRoom){
					destRoom = firstRoom;
				}
				
				//We don't add the reverse direction to the destRoom, it get it's own direction.
				//It doesn't matter if both directions are north, there could be a semi circular path.
				currRoom.addExit(directions[index], destRoom);
				index++;
			}
		}

		return g;
	}
}
