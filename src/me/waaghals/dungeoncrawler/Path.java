package me.waaghals.dungeoncrawler;

/**
 * @author Patrick Berenschot
 * 
 */
public class Path {

	private int id;	//Each Path should be unique mandated by the JUNG library
	private int direction;
	
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
	
	public String toString(){
		return Constants.getStringDirection(direction);
	}
}
