package me.waaghals.dungeoncrawler.factory;

import me.waaghals.dungeoncrawler.Constants;
import me.waaghals.dungeoncrawler.items.*;
import org.apache.commons.collections15.Factory;

/**
 * @author Patrick Berenschot
 * 
 */
public class ItemFactory implements Factory<Item> {

	// We repeat classes more often so that they are likelier to be made
	private String[] classNames = { "me.waaghals.dungeoncrawler.items.Ipod",
			"me.waaghals.dungeoncrawler.items.Ipod",
			"me.waaghals.dungeoncrawler.items.Stick",
			"me.waaghals.dungeoncrawler.items.Stick",
			"me.waaghals.dungeoncrawler.items.Stick",
			"me.waaghals.dungeoncrawler.items.Medicine",
			"me.waaghals.dungeoncrawler.items.Medicine",
			"me.waaghals.dungeoncrawler.items.PlanetaryAnnihilator" };

	// Note these have the same index, important!
	@SuppressWarnings("rawtypes")
	private Class[] typeNames = { Ipod.class, Ipod.class, Stick.class,
			Stick.class, Stick.class, Medicine.class, Medicine.class,
			PlanetaryAnnihilator.class };

	@SuppressWarnings("unchecked")
	@Override
	public Item create() {
		int i = Constants.generator.nextInt(classNames.length);
		return instantiate(classNames[i], typeNames[i]);
	}

	/**
	 * @see http://stackoverflow.com/questions/4865153/loading-a-class-from-a-string
	 */

	public <T> T instantiate(final String className, final Class<T> type) {
		try {
			return type.cast(Class.forName(className).newInstance());
		} catch (final InstantiationException e) {
			throw new IllegalStateException(e);
		} catch (final IllegalAccessException e) {
			throw new IllegalStateException(e);
		} catch (final ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
	}

}
