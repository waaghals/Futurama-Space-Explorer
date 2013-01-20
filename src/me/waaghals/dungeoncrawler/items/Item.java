package me.waaghals.dungeoncrawler.items;

import me.waaghals.dungeoncrawler.*;

/**
 * @author Patrick Berenschot
 * 
 */
public abstract class Item {

	private String name;
	private String fancyName;

	

	//
	/*
	 * Array for damages, damage get randomly picked from this array. Repeat
	 * values more often for "weighted" random. Plotted, low to high (Bear with
	 * me xD)
	 * 
	 * Exponential Logaritmic Liniar | . | . | . | . | . | . | . | . | . | . | .
	 * | . | . |.... ` |. +-------------- +--------------- +---------------
	 */
	private int[] damageMap = new int[] { 0 };

	public Item(String name) {
		this.name = name.toLowerCase();
		this.fancyName = name;
	}

	public int[] getDamageMap() {
		return damageMap;
	}

	public void setDamageMap(int[] damageMap) {
		this.damageMap = damageMap;
	}

	public String getName() {
		return name;
	}

	/**
	 * Set the name of an item
	 * 
	 * @param newName
	 * @return void
	 * @throws IllegalArgumentException
	 */
	public void setName(String name) {

		// Can't use the name using because of fight using <itemName>
		if (name.equals("using")) {
			throw new IllegalArgumentException("Can't set name to \"using\"");
		}
		this.name = name;
	}

	public int fight() {
		if (damageMap.length != 0) {
			int index =  Constants.generator.nextInt(damageMap.length);
			return damageMap[index];
		}
		return 0;
	}

	// How does one create an interface for an an expendable object?
	// TODO Make use of interface
	public void use() {
		throw new UnsupportedOperationException(
				"use() must be overridden by all subclasses of Item");

	}

	public void use(String argument) {
		throw new UnsupportedOperationException(
				"use() must be overridden by all subclasses of Item");
	}

	public void setFancyName(String fancyName) {
		this.fancyName = fancyName;
	}

	public String getFancyName() {
		return fancyName;
	}
}