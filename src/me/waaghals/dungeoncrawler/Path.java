package me.waaghals.dungeoncrawler;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;

/**
 * @author Patrick Berenschot
 * 
 */
public class Path {

	int id;	//Each Path should be unique mandated by the JUNG library
	int direction;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public Path(int id){
		this.id = id;
	}
	
	//TODO Fix this BS
	public String toString() {
		Game currGame = Game.INSTANCE;
		GameLevel currLevel = currGame.getGameLevel();
		Graph<Room, Path> g = currLevel.getMap();
		//g.getDest(currGame.getPlayer().getCurrRoom())
		Pair<Room> rooms = g.getEndpoints(this);
		if(rooms.getFirst() == currGame.getPlayer().getCurrRoom()){
			int direction = rooms.getFirst().getDirectionByNeighbour(rooms.getSecond());
			return Constants.getStringDirection(direction);
		} else if (rooms.getSecond() == currGame.getPlayer().getCurrRoom()){
			int direction = rooms.getSecond().getDirectionByNeighbour(rooms.getFirst());
			return Constants.getStringDirection(direction);
		}
		//return id + " " + direction;
		//return Constants.getStringDirection(getDirection());
		return "";
	}
}
