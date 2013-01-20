package me.waaghals.dungeoncrawler;

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

	public Path(int id, int direction){
		this.id = id;
		this.direction = direction;
	}
	
	public String toString() {
		return "P" + Integer.toString(id) + " " + getDirection();
	}
}
