package be.bomberman.main.audio;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.applet.Main;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Sound {

	
	
	
	public static final AudioClip backgroundMusic = Applet.newAudioClip(Main.class.getResource("/Audio/Bomberman_Party_Edition.wav"));
	public static final AudioClip aiie = Applet.newAudioClip(Main.class.getResource("/Audio/aiie.wav"));
	public static final AudioClip boom = Applet.newAudioClip(Main.class.getResource("/Audio/BOM_11_M.wav"));
	public static final AudioClip start = Applet.newAudioClip(Main.class.getResource("/Audio/Bomberman.wav"));
	public static final AudioClip ouch = Applet.newAudioClip(Main.class.getResource("/Audio/Ouch.wav"));
	public static final AudioClip select = Applet.newAudioClip(Main.class.getResource("/Audio/select.wav"));
	public static final AudioClip winner1 = Applet.newAudioClip(Main.class.getResource("/Audio/Winner.wav"));
	public static final AudioClip winner2 = Applet.newAudioClip(Main.class.getResource("/Audio/Winner2.wav"));
	public static final AudioClip bonus = Applet.newAudioClip(Main.class.getResource("/Audio/GOAL.wav"));
	public static final AudioClip gamestart = Applet.newAudioClip(Main.class.getResource("/Audio/gamestart.wav"));
	public static final AudioClip itemget = Applet.newAudioClip(Main.class.getResource("/Audio/ITEM_GET.wav"));
	public static final AudioClip bonusspawn = Applet.newAudioClip(Main.class.getResource("/Audio/PLAYER_WALK.wav"));
	public static final AudioClip teleport = Applet.newAudioClip(Main.class.getResource("/Audio/SHIP2.wav"));
	public static final AudioClip firePower = Applet.newAudioClip(Main.class.getResource("/Audio/BOS_KONO.wav"));
	public static final AudioClip bonuslife = Applet.newAudioClip(Main.class.getResource("/Audio/P1UP.wav"));
	public static final AudioClip lastlife = Applet.newAudioClip(Main.class.getResource("/Audio/NOO.wav"));
	public static final AudioClip linkbonuslife = Applet.newAudioClip(Main.class.getResource("/Audio/Link bonuslife.wav"));
	public static final AudioClip pressStart = Applet.newAudioClip(Main.class.getResource("/Audio/WW_PressStart.wav"));
	public static final AudioClip pauseOn = Applet.newAudioClip(Main.class.getResource("/Audio/pauseon.wav"));
	public static final AudioClip pauseOff  = Applet.newAudioClip(Main.class.getResource("/Audio/pauseoff.wav"));
	public static final AudioClip linklife2  = Applet.newAudioClip(Main.class.getResource("/Audio/Link life.wav"));
	public static final AudioClip linklife1  = Applet.newAudioClip(Main.class.getResource("/Audio/Link life 1.wav"));

}	
	

	/*
	public Sound(String path){
		
		try {
			input = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			audio = new AudioStream(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void play(){
		AudioPlayer.player.start(audio); 
	}


	
	
	public Sound(String path){
		try{
			clip = Applet.newAudioClip(Sound.class.getResource(path));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void play(){
		try{
			new Thread(){
				public void run(){
					clip.play();
				}
			}.start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		clip.play();
	}
	*/
	/*
	 	AudioPlayer BGP = null;
		AudioStream BGM;
		AudioData MD;
		ContinuousAudioDataStream loop = null;
		
		try {
			BGM = new AudioStream(new FileInputStream(path));
			MD = BGM.getData() ;
			loop = new ContinuousAudioDataStream(MD);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BGP.start(loop);
	 */
	
	


/*
AudioInputStream audioInputStream = null;
SourceDataLine line;
private boolean continuer=true;
 
public void run(){
	AudioFileFormat format = AudioSystem.getAudioFileFormat(fichier);
	audioInputStream = AudioSystem.getAudioInputStream(fichier);
	AudioFormat audioFormat = audioInputStream.getFormat();
	DataLine.Info info = new DataLine.Info(SourceDataLine.class,audioFormat);
	line = (SourceDataLine) AudioSystem.getLine(info);
	line.open(audioFormat);
	line.start();
    
    	try {
    		byte bytes[] = new byte[1024];
    		int bytesRead=0;
    		while ((bytesRead = audioInputStream.read(bytes, 0, bytes.length)) != -1 && continuer) {
    			line.write(bytes, 0, bytesRead);
    		}
    	} catch (IOException io) {
    		io.printStackTrace();
    		return;
    	}
    }
}
public synchronized boolean getContinuer(){return continuer;}
public synchronized void setContinuer(boolean continuer){this.continuer=continuer;}
}
*/

/*
  public static synchronized void playSound(final String url) {
  new Thread(new Runnable() {
  // The wrapper thread is unnecessary, unless it blocks on the
  // Clip finishing; see comments.
    public void run() {
      try {
        Clip clip = AudioSystem.getClip();
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
          Main.class.getResourceAsStream("/path/to/sounds/" + url));
        clip.open(inputStream);
        clip.start(); 
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }
  }).start();
}

 */
