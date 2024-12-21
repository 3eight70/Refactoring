package be.bomberman.main.affichage;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	public String path;
	private int width;
	private int height;	
	private int[] spriteSheetPixels;	
	
	//*********************************************************************************************************
	
	public static SpriteSheet minecraft = new SpriteSheet("/SpriteSheets/terain512x512.png");
	public static SpriteSheet minecraftModif = new SpriteSheet("/SpriteSheets/terain512x512MODIF.png");
	public static SpriteSheet players = new SpriteSheet("/SpriteSheets/SpritesSeigneurs.png");
	public static SpriteSheet ghosts = new SpriteSheet("/SpriteSheets/Yushido.png");
	public static SpriteSheet bomberman = new SpriteSheet("/SpriteSheets/Bombermantiles.png");
	public static SpriteSheet death = new SpriteSheet("/SpriteSheets/linkdead.png");
	public static SpriteSheet background = new SpriteSheet("/SpriteSheets/background.png");
	public static SpriteSheet informations = new SpriteSheet("/SpriteSheets/Informations1.png");
	public static SpriteSheet informations2 = new SpriteSheet("/SpriteSheets/Informations2.png");
	public static SpriteSheet informations3 = new SpriteSheet("/SpriteSheets/Informations3.png");
	public static SpriteSheet informations4on = new SpriteSheet("/SpriteSheets/Informations4on.png");
	public static SpriteSheet informations4off = new SpriteSheet("/SpriteSheets/Informations4off.png");
	public static SpriteSheet informations4pauseon = new SpriteSheet("/SpriteSheets/Informations4pauseon.png");
	public static SpriteSheet informations4pauseoff = new SpriteSheet("/SpriteSheets/Informations4pauseoff.png");
	
	//*********************************************************************************************************
	
	
	public SpriteSheet(String path){
		this.path = path ;
		loadSheet(path) ;
	}
	
	
	private void loadSheet(String path){
		/*
		 * telecharge la spritesheet et la met dans la BufferedImage cr��e en adaptant ses dimensions a
		 * celles sp�cifi�es lors de la cr�ation de la BufferedImage
		 * Met ensuite tous les pixels dans une liste (ici la liste spriteSheetPixels)
		 * 
		 */				
		
		BufferedImage image = null ;
		try {
			image = ImageIO.read(SpriteSheet.class.getResource(path)); // (load RGB + alpha channel);
			this.width = image.getWidth();
			this.height = image.getHeight();
			spriteSheetPixels = new int[width*height];
			spriteSheetPixels = image.getRGB(0, 0, width, height, null, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	
	
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	public int[] getSpriteSheetPixels(){return spriteSheetPixels;}
	

}
