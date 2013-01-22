package me.waaghals.dungeoncrawler.factory;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.algorithms.generators.GraphGenerator;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import me.waaghals.dungeoncrawler.Constants;
import me.waaghals.dungeoncrawler.Path;
import me.waaghals.dungeoncrawler.Room;

/**
 * A maze generator for the creation of maps
 * 
 * @author Patrick Berenschot
 * @see http://www.eecs.tufts.edu/~ttaniz01/comp175/DFS_algorithm.pdf
 * 
 */
public class MapFactory <V,E> implements GraphGenerator<V,E> {

	private Object[][] matrix; //Room objects
	private Boolean[][] visited;
	private Factory<Graph<V,E>> graphFactory;
	private Factory<V> vertexFactory;
	private Factory<E> edgeFactory;
	private Graph<V, E> graph;

	
	/**
     * Creates an instance with the specified factories and specifications.
     * @param graphFactory the factory to use to generate the graph
     * @param vertexFactory the factory to use to create vertices
     * @param edgeFactory the factory to use to create edges
     * @param numVertices the number of vertices for the generated graph
     * distribution will approximate a power-law
     */
	public MapFactory(
    		Factory<Graph<V,E>> graphF,
            Factory<V> vertexF,
            Factory<E> edgeF, 
            int numVertices) {
        graphFactory = graphF;
        vertexFactory = vertexF;
        edgeFactory = edgeF;
        
        int x = (int) Math.sqrt(numVertices);
		int y = numVertices / x;

		matrix = new Object[x][y];
		visited = new Boolean[x][y];
		graph = graphFactory.create();

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {

				// Add a room
				matrix[i][j] = vertexFactory.create();
				visited[i][j] = false;
			}
		}
    }
    
    public Graph<V, E> create(){
    	search(0, 0, Constants.NORTH);
    	return graph;
    }

	//Suppress warning for casting Room to generic type V
	@SuppressWarnings("unchecked")
	private void search(int row, int column, int heading) {
		if (withinBounds(row, column) && !visited[row][column]) {

			int source = Constants.getOppositeDirection(heading);
			visited[row][column] = true;

			/*
			 * Get the room from source. No need to check for out of bound,
			 * We've already came from source, so it exists.
			 */
			V sourceRoom;
			switch (source) {
			case Constants.NORTH:
				sourceRoom = (V) matrix[row - 1][column];
				break;
			case Constants.SOUTH:
				sourceRoom = (V) matrix[row + 1][column];
				break;
			case Constants.EAST:
				sourceRoom = (V) matrix[row][column + 1];
				break;
			case Constants.WEST:
				sourceRoom = (V) matrix[row][column - 1];
				break;
			default:
				sourceRoom = null;
			}

			E sourcePath = edgeFactory.create();
			((Path) sourcePath).setDirection(source);
			graph.addEdge(sourcePath, (V) matrix[row][column], sourceRoom);

			E destPath = edgeFactory.create();
			((Path) destPath).setDirection(heading);
			graph.addEdge(destPath, sourceRoom, (V) matrix[row][column]);

			// Exclude source
			Integer[] depthSearchDirections = getRandomDirections(source);
			shuffleArray(depthSearchDirections);
			for (int i = 0; i < depthSearchDirections.length; i++) {
				switch (depthSearchDirections[i]) {
				case Constants.NORTH:
					search(row - 1, column, Constants.NORTH);
				case Constants.SOUTH:
					search(row + 1, column, Constants.SOUTH);
				case Constants.EAST:
					search(row, column + 1, Constants.EAST);
				case Constants.WEST:
					search(row, column - 1, Constants.WEST);
				}
			}
		}
	}

	private boolean withinBounds(int row, int column) {
		return (column >= 0 && row >= 0 && row < matrix.length && column < matrix[0].length);
	}

	/**
	 * Return the directions array without <exclude> and shuffle it
	 * 
	 * @param exclude
	 * @return new array without <exclude>, shuffled
	 * @see http://stackoverflow.com/a/4870214
	 */
	private Integer[] getRandomDirections(int exclude) {
		List<Integer> result = new LinkedList<Integer>();

		for (int item : Constants.directions) {
			if (item != exclude) {
				result.add(item);
			}
		}
		return result.toArray(new Integer[0]);
	}

	/**
	 * @see http://www.vogella.com/articles/JavaAlgorithmsShuffle/article.html
	 * @param array
	 */
	private void shuffleArray(Integer[] array) {
		int n = array.length;
		Random random = Constants.generator;
		for (int i = 0; i < n; i++) {
			int change = i + random.nextInt(n - i);
			swap(array, i, change);
		}
	}

	private void swap(Integer[] array, int i, int change) {
		int helper = array[i];
		array[i] = array[change];
		array[change] = helper;
	}
}
