package me.waaghals.dungeoncrawler;

import java.util.Random;

/**
 * @author Patrick Berenschot
 * 
 */
public class Narrator {

	public static void say(String[] text, Object... args) {
		System.out.printf(getRandomString(text), args);
		System.out.println();
	}

	public static void say(String[] text) {
		System.out.print(getRandomString(text));
		System.out.println();
	}

	public static void say(String text, Object... args) {
		System.out.printf(text, args);
		System.out.println();
	}

	public static void say(String text) {
		System.out.print(text);
		System.out.println();
	}

	private static String getRandomString(String[] strings) {
		if (strings.length != 0) {
			Random randomGenerator = Constants.generator;
			return strings[randomGenerator.nextInt(strings.length)];
		}
		return null;
	}

	public static final String[] ITEM_NOT_IN_ROOM = {
			"The item %s is not in this room.", "Nope, there is no %s here",
			"Are you sure you meant %s?" };

	public static final String[] ITEM_NOT_IN_BACKPACK = {
			"You don't have a %s.", "Can't find a %s in your backpack",
			"Are you sure you meant %s, because you don't have one?" };

	public static final String[] ITEM_DROPPED = { "Dropped %s",
			"Removed %s from backpack",
			"You no longer have a %s in your backpack" };

	public static final String[] BACKPACK_CONTENT = { "You have a %s.",
			"This looks like %s", "There is a %s in your backpack",
			"You found a %s somewhere" };

	public static final String[] ITEMS_IN_ROOM = { "There is a %s here.",
			"This looks like a %s don't you think?",
			"We've stumbled upon a %s", "Look! a %s." };

	public static final String[] ITEM_RETRIEVED = { "Picked up %s",
			"* Puts %s in backpack" };

	public static final String[] ITEM_DUPLICATE = { "You already have a %s",
			"There already is a %s in you backpack" };

	public static final String[] BACKPACK_FULL = { "Your backpack is full",
			"Could not add %s to your backpack" };

	public static final String[] BACKPACK_EMPTY = { "Your backpack is empty",
			"There is nothing in your backpack",
			"You have air! in your backpack. Sadly you can't use it." };

	public static final String[] WALKING = { "Taking a hike %s",
			"Walking into the %s", "Heading %s" };

	public static final String[] NO_EXIT = {
			"We can't go %s, there is nothing but trees there.",
			"There is a sheer cliff %s of here. We can not go there.",
			"Looks like we can't go there.",
			"There are spiders in the %s, lets NOT go there",
			"Sweet Zombie Jesus! spiders in the %s! I'm not going there" };

	public static final String[] DIRECTIONS = { "We can exit at the %s side",
			"%s from here is a path we could take", "We could go %s",
			"There is an exit %s from here" };

	public static final String[] NOT_RECOGNISED = {
			"Wha?",
			"Say what?",
			"No idea what you want to do. type <help> for a list with valid commands",
			"Only english please" };

	public static final String[] MISSING_ARGUMENT = { "%s what?",
			"You need to type %s <argument>", "I did not understand that!",
			"Missing an argument for %s" };

	public static final String[] NO_ENEMY = { "There is no enemy here",
			"No enemy in this room", "Can't find any opponents here" };

	public static final String[] NO_DEAD_ENEMY = {
			"There is no dead enemy here", "No dead enemy in this room",
			"Can't find any dead opponents here" };

	public static final String[] NOTHING_TO_LOOT = {
			"The enemy does not have any items on him",
			"Nothing found on emeny" };

	public static final String[] ENEMY_KILLED = { "You have killed an enemy!",
			"That 'll teach 'em, he's dead!", "The enemy is dead!" };

	public static final String[] GAME_OVER = { "GAME OVER! Highest level %d",
			"You've lost. You've made it until level %d",
			"You've died. Game over. Can you can go higher than %d next time?" };

	public static final String[] ENEMY_GRIP = {
			"A enemy holds you in its grip! You can't move.",
			"There is a enemy in the room, you can't leave!",
			"Are you a chicken, you can't leave now. Fight the enemy!" };

	public static final String[] SEE_YOU_SOON = {
			"Wanna try and beat your last level?", "New game?",
			"Wan't to try again?" };

	public static final String[] FIGHT_USING_ITEM_HIGH_DAMAGE = {
			"Massive hit. %d%% damage to your opponent!.",
			"BAM! %d%% damage against your opponent.",
			"Blow to the head, that'll teach 'em!",
			"KAPOW! You hit like Badr Hari!",
			"Good job Chris brown! %d%% damage!" };

	public static final String[] FIGHT_USING_ITEM_MEDIUM_DAMAGE = {
			"You inflicted %d%% amount of damage.",
			"Contact hit! Did %d%% damage", "HIT! %d%%, opponent says OUCH!" };

	public static final String[] FIGHT_USING_ITEM_LOW_DAMAGE = {
			"Well, you might have made a tiny mark there....",
			"Like a feather bag to the knee, only %d%% amount of damage.",
			"%d%% damage, your opponent has an itch." };

	public static final String[] FIGHT_USING_ITEM_NONE_DAMAGE = { "Miss!!",
			"Are you even trying? No success",
			"Sure you can even use a %s for fighting?" };

	public static final String GAME_INTRO = new StringBuilder()
			.append("Good news, everyone!\n")
			.append("It seem that we've ran out of fuel!\n")
			.append("We need to find some Dark Matter to fuel the our ship.\n")
			.append("Fry, it is your job to find the Dark Matter and bring it back.\n")
			.append("When you've found the Dark Matter and are back at the ship\n")
			.append("then use it, than we'll be able to fly to the next level!\n")
			.append("\nThis envirment looks strange.\n")
			.append("Owh! Fry, remember if you don't know what to do, just type help.")
			.toString();

	public static final String LEVEL_COMPLETE = new StringBuilder()
			.append("You've finished this level!")
			.append("Lets fly off into space untill were out of fuel again.")
			.toString();

	public static final String GAME_HELP = new StringBuilder()
			.append("Usage: \n")
			.append("go \t<N, E, S or W>\t\tTravel in said direction\n")
			.append("get \t<item>\t\t\tPut <item> in backpack\n")
			.append("use \t<item> \t[argument]\tUse <item>\n")
			.append("pack\t\t\t\tShows what is in your back pack\n")
			.append("look\t\t\t\tTells you what your surrounding look like\n")
			.append("fight\t\t\t\tFight your opponent\n")
			.append("fight \tusing \t<item>\t\tFight your opponent and use <item> as a weapon\n")
			.append("loot\t\t\t\tGrab stuff from your opponent after you killed it\n")
			.append("help\t\t\t\tShows this message\n")
			.append("quit\t\t\t\tStops the game\n").toString();

	public static final String IMAGE_SHIP = new StringBuilder()
			.append("                         `. ___\n")
			.append("                        __,' __`.                _..----....____\n")
			.append("            __...--.'``;.   ,.   ;``--..__     .'    ,-._    _.-'\n")
			.append("      _..-''-------'   `'   `'   `'     O ``-''._   (,;') _,'\n")
			.append("    ,'________________                          \\`-._`-','\n")
			.append("     `._              ```````````------...___   '-.._'-:\n")
			.append("        ```--.._      ,.                     ````--...__\\-.\n")
			.append("                `.--. `-`                       ____    |  |`\n")
			.append("                  `. `.                       ,'`````.  ;  ;`\n")
			.append("                    `._`.        __________   `.      \'__/`\n")
			.append("                       `-:._____/______/___/____`.     \\  `\n")
			.append("                                   |       `._    `.    \\\n")
			.append("                                   `._________`-.   `.   `.___\n")
			.append("                                                      `------'`")
			.toString();

	public static final String IMAGE_LOGO = new StringBuilder()
			.append("\n   oooooooooooo ooooo     ooo ooooooooooooo ooooo     ooo ooooooooo.         .o.       ooo        ooooo       .o.\n")
			.append("   `888'     `8 `888'     `8' 8'   888   `8 `888'     `8' `888   `Y88.      .888.      `88.       .888'      .888.\n")
			.append("    888          888       8       888       888       8   888   .d88'     .8\"888.      888b     d'888      .8\"888.\n")
			.append("    888oooo8     888       8       888       888       8   888ooo88P'     .8' `888.     8 Y88. .P  888     .8' `888.\n")
			.append("    888    \"     888       8       888       888       8   888`88b.      .88ooo8888.    8  `888'   888    .88ooo8888.\n")
			.append("    888          `88.    .8'       888       `88.    .8'   888  `88b.   .8'     `888.   8    Y     888   .8'     `888.\n")
			.append("   o888o           `o888o'        o888o        `o888o'    o888o  o888o o88o     o8888o o8o        o888o o88o     o8888o\n")
			.append("\n")
			.append("                                         NOW IN ASCII ART")
			.toString();

}
