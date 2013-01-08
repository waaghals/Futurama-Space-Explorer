package me.waaghals.dungeoncrawler.items;

/**
 * @author Patrick Berenschot
 * 
 */
public abstract class Item {

	private String name;
	private String useText;

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

	public String getUseText() {
		return useText;
	}

	public void setUseText(String useText) {
		this.useText = useText;
	}

	//How does one create an interface for an an expendable object?
	//TODO Make use of interface
	public void use() {
		throw new UnsupportedOperationException("use() must be overridden by all subclasses of Item");
		
	}

	public void use(String argument) {
		throw new UnsupportedOperationException("use() must be overridden by all subclasses of Item");
	}
}