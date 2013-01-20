package me.waaghals.dungeoncrawler;

import java.util.Random;

/**
 * 
 * @author Patrick Berenschot
 * 
 */
public class Constants {
	public static final int NORTH = 2;
	public static final int EAST = 4;
	public static final int SOUTH = 8;
	public static final int WEST = 16;

	public static final int[] directions = { NORTH, EAST, SOUTH, WEST };
	public static Random generator = new Random();

	public static int getIntDirection(String direction) {
		switch (direction) {

		case "north":
		case "noord":
		case "n":
			return Constants.NORTH;

		case "east":
		case "oost":
		case "e":
			return Constants.EAST;

		case "south":
		case "zuid":
		case "s":
			return Constants.SOUTH;

		case "west":
		case "w":
			return Constants.WEST;

		default:
			// Direction not recognized
			return 0;
		}
	}

	public static String getStringDirection(int direction) {
		switch (direction) {

		case Constants.NORTH:
			return "north";

		case Constants.EAST:
			return "east";

		case Constants.SOUTH:
			return "south";

		case Constants.WEST:
			return "west";

		default:
			// Direction not recognized
			return null;
		}
	}

	public static int getOppositeDirection(int direction) {
		switch (direction) {

		case Constants.NORTH:
			return Constants.SOUTH;

		case Constants.EAST:
			return Constants.WEST;

		case Constants.SOUTH:
			return Constants.NORTH;

		case Constants.WEST:
			return Constants.EAST;

		default:
			// Direction not recognized
			return 0;
		}
	}
}
