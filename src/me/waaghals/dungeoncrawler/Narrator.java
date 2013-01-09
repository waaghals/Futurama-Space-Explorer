package me.waaghals.dungeoncrawler;

import java.util.Random;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * @author Patrick Berenschot
 * 
 */
public class Narrator {
	private static Narrator instance;

	private Narrator() {
	}

	public static Narrator getInstance() {
		if (instance == null)
			// Call the its own constructor, which it can only do itself.
			instance = new Narrator();
		return instance;
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
		// that'll teach 'em
	}

	public void say(String[] text, Object...args) {
		System.out.printf(getRandomString(text), args);
	}
	
	public void say(String[] text) {
		System.out.print(getRandomString(text));
	}
	
	public void say(String text) {
		System.out.print(text);
	}
	
	private String getRandomString(String[] strings){
		if(strings.length != 0){
			Random randomGenerator = new Random();
			return  strings[randomGenerator.nextInt(strings.length)];
		}
		return null;
	}

	//I love ram!!!!
	public static final String[] ITEM_NOT_IN_ROOM = {
		"The item %s is not in this room.",
		"Nope, there is no %s here",
		"Are you sure you meant %s?"
	};
	
	public static final String[] BACKPACK_CONTENT = {
		"You have a %s.",
		"This looks like a %s",
		"There is a %s in your backpack",
		"You found a %s somewhere"
	};
}