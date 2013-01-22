package me.waaghals.dungeoncrawler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

	public static final String LEVEL_COMPLETE = new StringBuilder()
			.append("88O88888888888888O8888N8Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$OM8ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZNOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ\n")
			.append("888888888888888888NNO$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ZNNOZOZOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOON8ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOOZZ8D\n")
			.append("8888888888888888ND$$$$$$$$$$$$$$$$$$$$$$$$$$$Z$ZDM8ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOONZZZZZZZZZZZZZZZZZZZZZZZZZOZOZOZZ8DNND8\n")
			.append("888888888888DMZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ONNZO88ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZON$ZZZZZZZZZZZZZZZZZZZZOZZZODMMDD88OZZZ\n")
			.append("8888888888NNZ$$$$$$$$$$$$$$$$$$$$$$$$$7$$$8N8ZZZZZDZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOZOZZOZZOZZZZZZZZZZZZZZZZZZZZZZZZZZOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOZZOZZZZZZZZZOZZZZZZZZZZZZZOZOZZZZZ8NNND88OOOOOZZZ\n")
			.append("8888888M8$$$$$$$$$$$$$$$$$$$$$$$$$$......ZZO.....,$NZ$ZZ~.......+ZZZZI..............$OZOZZZOZO$........IOZZZZ~......$.......$OZ~.........:?OZZZZ$...........$ZZZZZZZZ.......ZZ$...........ZZZZZZZZZZZZZO8NNN88OOOOOOZZZZZZZZ\n")
			.append("8888NNO$$$$$$$$$$$$$$$$$$$$$$$$$$$Z......?OO......OOMO$...........ZZZ?..............ZZZZZZZZO............IZZZ:......$.......ZZZ:............?OZZZ...........ZZZZZZZZZ.......ZZZ...........ZOZOZZZZZOONNN88OOOOZOOZOOZZZZZZZZ\n")
			.append("8MN$$$$$$$$$$$$$$$$$$$$$$$$$$$$7OM8.......ZO......ZZO=.............?O?..............ZZZZZZZZ..............$OZ:......$.......ZZZ:.............,OZZ...........ZZZZZZZZZ.......ZZZ...........OZZZODNNDD8OOOOOOOOOZZZZZOZZZZZZZZ\n")
			.append("O$$$$$$$$$$$$$$$$$$$$$$$$$$$$ONDOOZ.......7O......ZZ$.......8.......O?..............ZZZZZZZ7......,$......ND$~......$.......ZZZ:......~.......OZZ...........ZZZZZZZZZ.......ZZZ...........N8NMD88OOOOOZOOOZZOZZZZZZOZZZZZZZZ\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$ZDN8ZOZZZZ........Z......ZZZ......:O.......ZZZZO,......$ZZZZZZOZZZ?......:Z......7ID=......$.......8$Z:......$,......ZZZ.......ZZZ$ZZZZZZZZZ.......ZZO......,8NNND888OOOOOOZZZZZZZZZZZZZZZZZZZZZZZZ\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$ON8ZZZOOZOZZ........I......ZZO......:Z.......NNMND,......$ZZZZZZZZZZ7.......O+~+I~~7I7,......$.......ZN8:......$.......ZZZ.......ZZZZZZZZZZZZZ.......ZZO......,D88OOZZOOZZOZZZZZZZZZZZZZZZZZZZZZZZZZZ\n")
			.append("$$$$$$$$$$$$$$$$$$$ZDNOZZZZZZZZZZZZ...............ZZ$......:Z.......DDDDD,......$ZZZZZZZZZZZ.........,OONI777:......$.......7I$=.....,........ZZZ...........$ZZZZZZZZ.......ZDN..........,OOZZOOOZZZZZZZZZZZZZZZZZZZZZZZZZZZ\n")
			.append("$$$$$$$$$$$$$$$$$8N8ZOZZZOZZZZZZZZZ...............ZZ$......:Z.......DDDDD,......$ZZZZZZZZZZ$O..........:N$77I:......$.......777:.............$ZZZ...........ZOZZZZZZZ.......D88..........,OZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ\n")
			.append("$$$$$$$$$$$$Z$NNOOOZOZZZZZZZZZZZZZZ...............ZZ$......~Z.......DDDDD,......$ZZZZZZZZZZZOZZ..........,I77:......$.......I77:.............OZ$Z...........ZZZZZOZZ8.......OOO..........,OZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ\n")
			.append("$$$$$$$$$$8NDZOOZZZZZZZZZZZZZZZZZZZ...............NN8......:D.......NDDDD,......$OZZZZZZZZZOZZZZ$?........?77:......8.......I$7:......Z.......OZZ.......=+++ZZODNDD88.......OOO.......+=++ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ\n")
			.append("$$$$$$$OMDOOZZZZZZZZZZZZZZZZZZZZZZZ......7........DDD......:8.......NDDDD,......$OZZZZZZZZZO......,:......:7I:......?.......877:......$.......ZZZ.......OZODNNDD8OOZO.......ZZO......,ZZZZZZZZZZZZZZZZZZZZZZZOOZZZZZZZZOOZZO\n")
			.append("$$$ZDNDZZOZZZZZZZZZZZZZZZZZZZZZZZZZ......Z........DDD......:Z.......ZNNDD,......$OZZZZZZZZZZ......:$.......II:......?.......N77:......8,......ZZZ.......NN88OOOOOOOZO.......ZZO......,ZZZZZZZZZZZZZZZZZZZZZZZOOOOOOZZZOOZZO8\n")
			.append("ZON8ZOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZ......Z+.......DDD......,O.......O8NDD,......$OZZZZZZZZZZ.......Z.......7I~......?.......8N7:......?,......ZZZ.......8OOOOZZZZOOZZ.......ZOO......,ZZZZZZZZZZZZZZZZZZZZZZOOOOOOOZOO8DNN8$\n")
			.append("OZZOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ......ZO.......DD8..............:ZZOMD,......$OOZOOOZOOZO..............=NII..............ZN$:......?,......NND............ZZZZZZZZ.......ZOO......,ZZZZZZZZZZZZZZZOOZOZZZOOOZONNNOZ$$ZZZ$\n")
			.append("OZOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ......ZZ~......DDDD.............OZZZ8M,......NMMMND88O88ND.............ZMI7,............=O8O:......?:......88O............ZZZZZZZZ.......ZOO......,ZZZZZZZZZZZOOOOOOOOZZO8NN8O$$$Z$$$$ZZ$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ......OZZ......D88DN?.........$$OZZZOO,......I77777777777I7+.........?8ZM777?,........:7NZOD:......I:......OZZ............ZZZZZZZZ.......OZZ......,OZZZZZOOOOZZOOOO8NMDO$$$ZZZZZ$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOZZZOZ$ZZZONDDDDD8NDZI77ZOOZD$OZOZODMD77777777777777777777III??77II78N$777III7++?I7$?INONI7I77777II7IMZZOOOOZZZZZZZZZOOOZZZOZZZZZZZOZZZZOOZZZOOOZOOOOOZOZZOODMNDZZZZ$$$$$$$$ZZ$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZDNDDDDDDDNOZZZZZZONZOOZNZI7777777777777777777777777777777777777777777777777ZN8I7777I777777ODOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOOZO8DNN8ZZ$ZZ$$ZZ$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZNDDDDDDDDMOZZZZZZ8DZ8M77777777777777777777777777777777777777777777777777777?7777777777777IMOOZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOOZZO8NNNO$$ZZ$ZZ$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOMMDNDNDDDNZZZZZZOOD$I777777777777777777777777777777777777777777777777777777777777III7777I$NOZZZZZZZZZZZZZZZZZZZZZZZZOZOOZZOOOOODNNOZ$$$$$$$$Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOZZMZNNDNDDDDOZZZO8NI777777777777777777777777777777777777777777777777777777777777777I7777777MZZZOZZZZZZZZZZZZOZZOZZOOOOOOOOO8NM8ZZ$ZZZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOOOMI7IONND8OZZZN8777I7I777777777777777777777777777777777777777777777II77777II777777777777IODZOOZZZZZZZZZZZZOOOOOOOZO8MMDOZZ$$$$$$Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZON7777I$DM8ZD7I7777777II7777777II7777777777777II777777777777I7I77777777777777777777777777MZOOOZZZOOOZZOOZOZO8NMNZ$$ZZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZD7777777I$DO7I777777777777777777777777777777777777777777777777777777777I7777777777777777DOZZZZOOOOOOZOODNN8ZZZ$$Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZMI7~,.....,?7I77II,..,..,I,..,..,II7..........,I777=........,:I77?..............I7...,.......ZO=......,::=$Z$Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOZ8?...........=I7II.......7,......II7...........II7I:..........I7I?..............II...........NN+............:$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOZZOZZ..............+77I.......7.......777...........I77I...........77I?..............II...........$Z~..............ZZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOZD.......I......,I7I.......I.......I77...........I777...........I77?...........,.,77...........$Z~......:.......$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOZZZONO7I......:I.......77I.......$.......NNN.......77777777...........I77777I,......+77777......,$$$$$$=......I$......$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOZONN$I777......,I.......I77.......O.......~~~.......O7III77I.....,.....=77777I.......+77777......,$$$$$$~......IZ......7$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ8M8I777777I......,I.......I7I...............=~~.......,,,,ODNZ.....=.....,777777,......+77777.......::::ZZ~......:.......Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZM7I777I777I......,7,,,,,,,I7I...............~~=...........=~=,.....O......?II77I.......+77I77...........$$~.............,Z$Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOZZOONNO7777I......,77IIIIIII77...............~~~...........~=~......:......8NDO77.......+77777...........$$~.............?$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOZZONMNNN7II......,7III7777777...............~==...........~~=......~,.....,~~+ID.......=777?7...........$$=......=~......$$Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOZZONMN88OOOZOOOD......,I.......I77.......N.......N=+.......=~==~~:......,,......==~==.......?8OZZZ......,ZZ$$$$~......IZ......$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOZOZZOZDNNND8OOOZZZOOOZZZO......,7.......I77.......=.......7D~.......~=~~=~,..............~~~~~.......:DZ$ZZ......,$$$Z$$~......I$......7$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZOZZODMN888OZOOOOZZZZOZZOOZ......,7.......I77.......+.......NN8.......~~~~~~...............:=~~~.......:N$Z$Z.......$$$Z$$~......I$......7$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZZZZOZOOZZONMDD8OOOOZZZZZZZZZZZZZZOOON..............,I7I.......O.......=~=...........,=.......~~.......~~~~.......:=ZMZZ...........~$~......I$......7$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZOZZZZONMND8OOOOOOZZZZZZZZZZZZZZZZOZZM~.............II7I.......I.......~~=...........,~.......~~.......~~~~.......:=+~N8...........~$~......I$......7$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZZZZZZZ8DMN888OOOZOOZOOZZZOZZZZZZZZZZZZZOODZI7:.........=I7I7I.......7.......~~=...........,,......,~=.......~=~~.......:==~=I...........~$~......I$......7$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZZZZZZOZ8DNND88OOOOOOOOOOOOOOOOOZZZZZZZZZZZOOOONI7777I+~:=+I77I77I7IIIIIII7III$~=~=~~~~~~~~~~~~~~~~~~~~~~=~~~~~~~~~~~=~~~~~~~~~==~~8OOZZZZZZZ$$$$ZZZ$Z$ZZ$$ZZZZZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZZZOOODMN8O8OOZOOOOZZZZZOOOZZZZZZZZZZZZZOZZZZOZOD8777I777777IIIII77I7IIIII7I77I78O~===~~~~~~~~~~~~~~=~~~~==~=~===7=~===~=~==~=~~=~~?7OMZZZZZ$$$ZZ$$$$$$$$$$$$$$$$$$Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZZZODNND88OOZZOOOOOOOOZZZZZZZZZZZZZZZZZZZZOOZOOOZON$7777777777777777777777I77777777D7~=~~~~~~~~~~~~~~~~~~~~~~~~=====ON+=~=~~~~~~~~~~===~=ONZZZ$$Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("8DNND88OOOZZOOZZOZZZZZZZZZZZZZZZZZZZZZZZZZOOOOOZOO8NNI77777777777777777777777I7777777IN?==~==~~~~~~~~~~~~~~~~~~~~~~~~=~~8=~==~~~~~~~~~~~====~$MZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("D8OOOOOOOOOZZZZZZZZZZZZZZZZZOOOOOZZZOZZZOOZOOO8NNDZ$NI77777777777777777777777777777777$N=~~=====~~~~~~========~~~~======~M:====~~~==~=~=====~~?OZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("OOZOOOOOOOOZZZZZZZZZZZZZZZOOOOOZZOZOOOZO8NMNO$ZZ$$$ZN777777777777777777777777777777777IZ?=======~==~~~=====~~==~~~======~~N=~=~~~~=~~~~~===~~~~MZZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("OOZZZOOZZZOZZZZZZZZZZZZZZOOOOOOZOOOODNNDO$ZZ$$ZZ$$$ZN7777777777777777777777777777777I77ID~=====N~===~~===~~~====~~==~~~===7O=~=?8DNMNO$7IND8NMDNZZZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("OZZZZZZZZZZZZZZZOZOOOZOOZZOOZO8NNDOZ$$ZZZ$$$$$$$$$$ZMII777777777777777777777777777777777OO~==~~O~=~~=?NM8ZI=:+MM...,,:~=?MMMO=...,.,.,..IMM....Z8$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("OZZZZZZZOOOOOOZOOOZZZZZO8MMNOZZZZZZ$$Z$$$$$$$$$$$$$$NZ777777777777777777777777777777I7777D+==~~O~$N7:.....,,~IMMODDDD7=,...M,.:?8DNMNDOZZ$ZNNMMM8Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZOZZZZZZZOOOOZOOOZZO8NM8Z$Z$$$$$ZZ$$$$$$$$$$$$$$$$ZZZNI777777777777777777777777777777777I8$===~M$:...:8NMM7?++~~~~~~~=?IDMDMMNI+~~~~=~~~~~==~~~8O$Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZOZZZZZOOOOZOO8NNDZ$$$$Z$Z$$$$$Z$$Z$$$$$$$$$$$$$$$$$$8D7777777777777777777777777777777777IN~===ZOOM$==~====~~==========~=~?MMZ~~~===~~=====~~=~MZ$Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZOOOZOOOZO8NNDZZZ$ZZZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ZDI7777777777777777777$7$7777I777777IN~~==~M~~=~=====~~~~~~==~~~~~~=~OI=?DZ~~==~~~=~~~~=~IOZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("OZZODNM8O$$$ZZ$$$Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ZD8I7777777777777777777DNDZDNOI7777ID~====+N~=~=~~~~~~~~~~~~~~~~=~~NI~~==~MI~=~~~~~=~~~ODZZZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("MNNOZ$ZZZ$Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$Z$$$ONI7777777II77777IIDO~~=~~~+DI7777O~==~~=IO=~~~~~~~~~~~~~~~~~~~==+======~ZD+~~~~~~=?MOZ$$Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZ$$$Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$DOI77777777777I8O~===~====~N$I77O~~~~~==~~~=~~~~~~~~~~~~~~~~~=~~==~~~=~==~==?7$ZO8NMOZ$Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$OM$7777777777I$~~=IDMZ?==~=N7IIO~~~~=~=~=~~~~~~~~~~~~~~~~~~~=~~=~~~~=~~=~=~~==~~~~~+MO$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ZZ$$$$MD7I777777D~~~$=?M~~+N~~=8II8~=~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~===~~=~OD$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ZZ8N$7777IN~=~D~N+===~O=~~DIN~~=~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~==~?N$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$Z$ZZDMD$N~===:D===~~~====M7~==~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~=~==~~Z8$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$Z$$ZZOD===~=78=====~~==~~==~=~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~====~~~===~NZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$Z$M~==~==D+~~~~~===~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~==~~=~~~~~~=ZN8Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ZN7~~=~=~~:=~=~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~=~?NDZ=+==~=:$ZZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$Z$DO=~==~==~===~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~==:MZZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$Z$Z$OM$~~~=D7~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~==NZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ZZ8DMZ====~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~=~~~MZZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$Z$Z$M~=~=~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~===~~MOZZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ZZZ8+=~=~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~=~ODZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$N~~=~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~=~~DZ$Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$Z$ZM?==~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~=DD$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ZN~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~===?M$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$M==~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~====~8O$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$M+~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~====~~~~~~~~~~~=~~===~=~~~~~~~=~~?MZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ZM+=~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~=~~=~~~~~~~~~~~:~~~~~~~~~==+=+IMN7Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ZM+~~~~~~~~~~~~~~~~~~~~~~~~~~~~~=~ZND?~=======~==$MOZZZZZZZ$ZZ$Z$$ZZZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$Z$ZNI=~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~===~=====~~====~~NZ$$$$$$$$$Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ZZ$8M7~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~=~~~~~~===~~OZZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$Z$$Z$Z$Z$$Z$$$Z$$$$$$$$$ZZ$$$$$$$$$$$Z$$$$$$$$$$$$$$$$$$$$ZZZ$NNZD$==~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~=~~~~==~~+M8$$$Z$$$$$$$$Z$$$$Z$$$$$$Z$$$$$$$$$$$Z$$$$$$$$$$$$$$$$Z$$$$$$$$$$Z$$$$Z$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$ZI~....,IZ$Z$$$?+++++++?I$Z$$$$$$$$$$$Z++++++++II$ZZ$Z$$==+++?I?7Z8$~~~~~~~~~:,....,:~~~~~~~~~~~~~~~=:,.....:~?ND$Z$Z7++=++++==$$$$$I=++++++=?$$==+++++++$$$=+++++++++=7$$=+++=++?I$$Z$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$Z............$$$$=............~Z$$$$$$$$$.............:Z$Z.............~=~~~~,...........~~=~~~~~~~~~,...........?$Z$Z$:.........=$$$$=.........$$.........$$$...........I$7............,Z$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$Z~,............Z$$=..............$ZZ$$$$$$..............=$$..............~~=~,.............~~~~~~~~~~..............?$$$$...........$$$$=.........$7.........$$$...........I$7..............$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$Z7......+$.......Z$=......=~......7$$$$$$$$.......,,......$Z.......+.......~~:......~~.......~=~~~~~~~.......D.......$$$$...........Z$$$=.........Z~.........$$$...........I$7.......=......+$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$?......?$.......$$=......+$......?ZZ$$$$$$.......:Z......$Z.......Z:......~~,......~~.......~=~~=~~~~.......O.......$$$$...........7$$$=.........$..........$$$.......Z$$$$$7.......$......:$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$=......I$.......$$=......=$......I$$$$$$$$.......:Z......ZZ.......O:......~~,......~~.......==~~=~~~~.......N.......$Z$~.....,.....~ZZ$=.........$..........$$$.......$$$$$$7.......Z......~$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$~......I$.......$$=......~=......$$$$$$$$$.......:7......ZZ.......?......,~~,......~~.......~=~~=~~~=.......Z~~,::::Z$$......~......$$$=.....,...?..........$$$.......?III$$7.......+......I$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$~......I$.......$$=.............I$Z$$$$$$$...............ZN.............,~=~,......~~.......~=~~=~~~=.......=+?I=+=+Z$$......?......$$$=.....=...,...+......$$$...........$$7.............+$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$~......I$.......$$=............=Z$$$$$$$$$..............IND.............~~~~,......~~.......~=~~~~~~=...............Z$$......$:.....I$$=.....~.......?......$$$...........$$7............:$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$~......I$.......$$=......=?......7$Z$$$$$$............+ZZDD......,Z,.....:=~,......~~.......~=~~=~~==...............$$$......$?.....,ZZ=.....~.......+......$$$...........$$7......,7......7$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$~......I$.......$Z=......=Z......?$$$$$$$$.......:M8ZZZZON8.......Z:......~~.......~~.......~~~~=~~==.......Z.......Z$$..............$$=.....~=......?......$$$.......$$$$$$7.......$......:$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$=......I$.......$$=......=Z......?$$$$$$$$.......~ZOZOZZONO.......Z:......I~,......~~.......~=~~=~~==.......Z.......Z$+..............$$=.....~7......?......$$$.......$$$$$$7.......$......:$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$ZI......+Z.......$Z+......+Z......?$$$$$$ZZ.......:ZZZZZZZNO.......Z:.......O7......:~.......~~~~~===~.......Z.......7$...............=$=.....~$......?......$$Z.......IIII?$7.......$......:$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$......,=......=$Z=......=Z......?$$$$$$$Z.......:ZZZZZZZNZ.......Z:......,.........,......~~===~~~==.......=.......DZ.......II.......Z=.....~$......?......$$$............$$.......$......:$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$............:Z$$=......+$......?Z$$$$$ZZ.......:ZZZZZZZDZ.......Z:......,,..............+OOO8DDNNM7...............MM.......Z$.......Z=.....~$.....=?......$$$............$$.......$......:$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$?..........7$$$$=......=$......?$$Z$ZZN8.......:ZZZZZOZDZ.......Z:......,.................,,................,,....87.......$$.......Z=.....~$=....7?......$$$............$7......,Z......:$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$ZZ$Z$Z7IZ$$$Z$$$$Z$$$$$$$$Z$$$$$$$ZZZMOZOOOZZZZZZZZZZZOO8ZOOZZZZZZZZZZZON...............,.............,.887OZOOZZZOZDDOMO$$$ZZZ$$$$$Z$ZZ$$Z$$$$ZZ$$Z$Z$$Z$$Z$$$$$$$$$$$$$$ZZZ$$$Z$$$$$$$$$Z$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$Z$ZZDNZZZZZZZZZZZZZZZZZOO8ZZZZZZZZZZZZZZZDO.,............................$DZZOZZZZZZZONZOND$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$Z$$D8ZOZZOZZZZZZZZZZZZOZZO8ZZZZZZZZZZZZZZZOD,.............................:MZZZZZZZZZZOO8ZZON$Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("ZZZ$Z$$7$$$$$Z$$$Z$$$$Z$Z$$Z$ZZZZ$$$$Z$Z$$ZZZ$Z7OMOOOZZOZOZZZZZ$ZOZZZZ$OOOZZZZZZZZZZZZZZOZO8..............................D8ZOZZZZZZZZZNZZZDD$Z$Z$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n")
			.append("D8ZDD888DD88DD88D8D8DDD888D8DDNDDD8ND88ODDDDD88NDDDDDDNN8DMMDD8DDDO7M7ND8D8DDDDDDDDDDDDDDDDNDII777777777777777777777777777ODDDDDDDDDDDDNDDDDDMD88888888888888888888888888888888888888888888888888888888888888888888888888888\n")
			.append("8MM.MI.$.Z..MM.M$.MM.$M.M.M.M.M..MM...$M.M.M.MMM.ZMMMNMDD:??7D$..DM??+MDNDDDDDDDDDDDDDDDDDDDM$77II7777777777777777777777I7ONDDDDDDDDDDDDMDDDDNN88DD8888888888888888888888888888888888888888888888888888888888888888888888888\n")
			.append("8MM.MMNM.$..MMM.M.7M.$M.M.M.M.M..$.M.MMMMM.MM..M.$MMMMMO$:?+:$7..DM+?~MNNDDDDDDDDDDDDDDDDDDDDMZII7777777777777777777777777$MDDDDDDDDDDDDMDDDDDND8D88888888888888888888888888888888888888888888888888888888888888888888888888\n")
			.append("888DMZ8D8D8O8DDO8DD8ON8D8D88DN88DDZO8OD8888NDNODNN8DND88DDDNNDD8DD8N8DDDDDDDDDDDDDDDDDDDDDD8DDMI77777777777777777777777777$MDDDDDDDDDDDDMDDDDDDMD888888888888888888888888888888888888888888888888888888888888888888888888888\n")
			.toString();
}
