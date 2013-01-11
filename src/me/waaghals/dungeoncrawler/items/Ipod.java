package me.waaghals.dungeoncrawler.items;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import me.waaghals.dungeoncrawler.Narrator;
/**
 * Ipod is an special item
 * It can play sound and can kill an enemy in an instant although highly unlikely
 * 
 * 
 * @author Patrick Berenschot
 *
 */
public class Ipod extends Item {

	public Ipod() {
		super("ipod");
		super.setDamageMap(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100});
	}

	public void use() {
		File wav = new File("./sounds/rickroll.wav");
	        try {
				Clip clip = AudioSystem.getClip();
				// getAudioInputStream() also accepts a File or InputStream
				AudioInputStream ais = AudioSystem.
				    getAudioInputStream( wav );
				clip.open(ais);
				farnsworth.say("Pressing play on the %s.", getFancyName());
				clip.start();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void use(String something){
		farnsworth.say("Can't use %s on %s", getFancyName(), something);
	}
	
	public int fight(){
		int damage = getDamage();
		if(damage > 90){
			farnsworth.say(Ipod.FIGHT_USING_ITEM_HIGH_DAMAGE, getName(), damage);
		} else if(damage > 60){
			farnsworth.say(Item.FIGHT_USING_ITEM_HIGH_MEDIUM, getName(), damage);
		} else if(damage > 30){
			farnsworth.say(Item.FIGHT_USING_ITEM_HIGH_LOW, getName(), damage);
		} else if(damage == 0){
			farnsworth.say(Item.FIGHT_USING_ITEM_HIGH_NONE, getName(), damage);
		}
		return damage;
	}
	
	public static final String FIGHT_USING_ITEM_HIGH_DAMAGE = "You've strangled your opponent with the ipod cord. He is dead for sure...";
}
