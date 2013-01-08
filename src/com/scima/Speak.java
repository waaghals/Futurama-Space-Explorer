package com.scima;

import com.sun.speech.freetts.FreeTTS;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;
import java.io.FileInputStream;

public class Speak {

	public void call(String msg) throws Exception {
		System.setProperty("mbrola.base", "/home/patrick/workspace/Dungeon Crawler/Dependensies/Mbrola");
		Voice voice;
		FreeTTS freetts;

		voice = VoiceManager.getInstance().getVoice("mbrola_us3");
		//voice.setPitch(1);
		
		VoiceManager voiceManager = VoiceManager.getInstance();  
        Voice[] voices = voiceManager.getVoices();  
        for (int i = 0; i < voices.length; i++) {  
            System.out.println("    " + voices[i].getName()  
                               + " (" + voices[i].getDomain() + " domain)");  
        }  

		System.setProperty("com.sun.speech.freetts.voice.defaultAudioPlayer",
				"me.waaghals.dungeoncrawler.bugfix.HackedAudioPlayer");
		

		if (voice == null)
			throw new Exception("Voice error");

		else {
			voice.allocate();
		}
		freetts = new FreeTTS(voice);
		voice.speak(msg);
		voice.deallocate();
	}
}