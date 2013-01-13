package me.waaghals.dungeoncrawler.factory;

import me.waaghals.dungeoncrawler.Room;

import org.apache.commons.collections15.Factory;

/**
 * @author Patrick Berenschot
 * 
 */
class VertexFactory implements Factory<Room> {
	int i = 0;
	
	public Room create() {
		Room room = new Room();
		room.setRoomId(i++);
		return room;
	}
}
