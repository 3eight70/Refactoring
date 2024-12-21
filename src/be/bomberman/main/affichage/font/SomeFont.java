package be.bomberman.main.affichage.font;

import be.bomberman.main.affichage.Screen;
import be.bomberman.main.affichage.SpriteSheet;

public class SomeFont {
	
	private static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ      "+"0123456789 ?;.!                  " ;
	private static SpriteSheet font = new SpriteSheet("/SpriteSheets/StuffSheet.png") ;	
	
		
	public static void renderT(String msg, Screen screen, int x, int y, int scale){
		msg = msg.toUpperCase();		
		for (int i = 0; i < msg.length(); i ++){
			int charIndex = chars.indexOf(msg.charAt(i));
			if (charIndex >= 0) {
				renderFont(x+i*8*scale, y, (charIndex + 30 * 32)%32 ,(charIndex + 30 * 32)/32, false, false, font, scale, screen);
			}			
		}
	}
	
	public static void renderW(String msg, Screen screen, int x, int y, int scale){
		msg = msg.toUpperCase();		
		for (int i = 0; i < msg.length(); i ++){
			int charIndex = chars.indexOf(msg.charAt(i));
			if (charIndex >= 0) {
				renderFont(x+i*8*scale, y, (charIndex + 26 * 32)%32 ,(charIndex + 26 * 32)/32, false, false, font, scale, screen);
			}			
		}
	}
	
	
	public static void renderFont(int xPos, int yPos, int xTile, int yTile, boolean mirrorX, boolean mirrorY, SpriteSheet sheet, int scale, Screen screen){
		/*
		 * Cette fonction va directement chercher ce qu'elle affiche sur la spriteSheet
		 */
		
		/*
		 * colour = entier au format long qui contient les info sur 4 couleurs chacune cod�es en 8 bits ==> 2^24 bits
		 * xPos et yPos sont les coord. du tile en pixels sur la screen ==> la ou il doit etre affich�
		 * xTile et yTile sont les coord. du tile (pas en pixel) dans la spriteSheet
		 * si mirrorX est vrai la fonction effectue une reflexion par rapport a  y = 0  du tile
		 * si mirrorY est vrai la fonction effectue une reflexion par rapport a  x = 0  du tile 
		 * 
		 * 
		 * Cette fonction parcour les pixels du tile dont les coord sont xPos et yPos (a partir du coin superieur gauche) tels que
		 * stock�s dans le tableau spriteSheetPixels et les transferts dans le tableau screenPixels (qui sont les pixels affich�s)
		 * 
		 */
		
		xPos -= screen.getXOffset();
		yPos -= screen.getYOffset();		
		
		int scaleMap = scale - 1 ;
				
		/* 
		 *   SpriteSheet de (32*8 x 32*8) pixels c'est a dire qu'elle contient 32*32 tiles de 8x8 pixels
		 *   
		 *   (xTile % largeur)  donne la coordon�e en x du tile sur la spriteS�heet
		 *   (coordon�e pas en pixels mais en unit� de 8 pixels qui est la longeur du tile)
		 *   
		 *   (xTile / hauteur)  donne la coordon�e en y du tile  (car / arrondi a l'entier inferieur) sur la spriteSheet
		 *   (coordon�e pas en pixels mais en unit� de 8 pixels qui est la hauteur du tile)
		 *   
		 */
				
		int tileOffset = (xTile << 3) + (yTile << 3) * sheet.getWidth();
		
		/*
		 *  Ceci correspond a l'index du premier pixel d'un tile dans le tableau des pixels de la spriteSheet 
		 *  		   
		 *  On remarque d'abord que l'operation (a << b) est equivalente � (a * 2^b): (xTile << 3) ==> (xTile * 8)
		 *  
		 *  Dans ce qui suit les pixels (des tiles) sont transf�r�s depuis spriteSheetPixels dans le tableau screenPixels,
		 *  l'index du premier pixel du tile dans le tableau spriteSheetPixels correspond a:
		 *  
		 *  la largeur de la spriteSheet (en pixels) * le nombre de fois qu'on a chang� de ligne + l'endroit dans la ligne actuelle (en pixels):
		 *          sheet.width (pixels)             *           yTile*8 (pixels)                +                xTile*8 (pixels)
		 *  
		 *  (voir grillage de la spritesheet pour une meilleure visualisation)
		 *  
		 *  A partir de ce pixel on parcour les pixels du tile dans la spriteSheet
		 *  
		 */
		
		for (int y = 0; y < 8; y++){	
			
			int yPixel = y + yPos + (y * scaleMap) - ((scaleMap << 4) / 2) ;
			/*
			 * si scaleMap  = 0 yPixel = y + yPos qui est la coordon�e du pixel
			 * si scaleMap != 0 yPixel = y*scale + yPos - ((scaleMap << 4) / 2)
			 * 
			 *  Exemple:   si scale = 2   ==>   scaleMap = 2 - 1 = 1
			 * 
			 *    yPixel = y + yPos + (y * 1) - ((1 << 4) / 2) = yPos + 2y - 8
			 *    
			 *    
			 *                   y = 0  |   y = 1  |   y = 2  |   y = 3  |  y = 4  |   y = 5  |  y = 6  |   y = 7   
			 *    ==> yPixel = yPos - 8 , yPos - 6 , yPos - 4 , yPos - 2 ,   yPos  , yPos + 2 , yPos + 4, yPos + 6 
			 *    
			 *    
			 *    
			 *             si scale = 3  ==>   scaleMap = 3 - 1 = 2
			 *     
			 *    yPixel = y + yPos + (y * 2) - ((2 << 4) / 2) = yPos + 3y - 16
			 *    
			 *    
			 *                    y = 0   |   y = 1   |   y = 2   |  y = 3  |   y = 4  |   y = 5  |  y = 6  |   y = 7 
			 *     ==> yPixel = yPos - 16 , yPos - 13 , yPos - 10 , yPos - 7  , yPos - 4 , yPos - 1 , yPos - 2 , yPos - 5
			 *    
			 *    Le scale est utilis� lors du transfert vers ScreenPixels
			 */
			
						
			int ySheet = y;		            // Coord. y de parcours du tile en pixels (coordon�e dans le tile)		
			if (mirrorY) ySheet = 7 - y ;  // hauteur des tiles = 8: effectue une reflexion par rapport a  x = 0
			
			for (int x = 0; x < 8; x++){
				
				int xPixel = x + xPos + (x * scaleMap) - ((scaleMap << 4) / 2) ;
								
				int xSheet = x;                 // Coord. x de parcours du tile en pixels (coordon�e dans le tile)	
				if (mirrorX) xSheet = 7 - x ;  // largeur des tiles = 8: effectue une reflexion par rapport a  y = 0
											
				int col = sheet.getSpriteSheetPixels()[xSheet + ySheet*sheet.getWidth() + tileOffset];
			
				
				if (col != 0xffFF00FF){
					
					for (int yScale = 0; yScale < scale; yScale++ ){
						
						if(yPixel + yScale < 0 || yPixel + yScale >= screen.getWidth()) continue;
						
						for (int xScale = 0; xScale < scale; xScale++ ){
							
							if(xPixel + xScale < 0 || xPixel + xScale >=screen.getWidth()) continue;
							
							screen.getScreenPixels()[(xPixel + xScale) + (yPixel + yScale)*screen.getWidth()] = col ;
							
							// Les pixels qui seront affich�s a l'�cran sont mis dans la liste et correspondent a ceux de la spriteSheet
							
							/*
							 * Pour scale = 2
							 * 
							 * Quand yScale = 0 et xScale = 0
							 * Screenpixels[(xPixel) + (yPixel)*width] = col rempli 8 x 8 pixels (les pixels aux indices pairs)
							 * Quand yScale = 1 et xScale = 1 
							 * les pixels intermediaires aux indices impaires ont aussi �t�s stock�s 
							 * ce qui fait que la liste contient a present 16 x 16 pixels c'est a dire un sprite deux fois plus grand (surface x4)
							 * 
							 * 
							 * Pour scale = 3
							 * 	  
							 * Quand yScale = 0 et xScale = 0
							 * Screenpixels[(xPixel) + (yPixel)*width] = col rempli 8 x 8 pixels (les pixels espac� de 3)
							 * Quand yScale = 1 et xScale = 1 
							 * les premiers pixels intermediaires ont aussi �t�s stock�s 
							 * ce qui fait que le tableau contient a present 16 x 16 pixels c'est a dire un sprite deux fois plus grand (surface x4)
							 * Quand yScale = 2 et xScale = 2
							 * Tous les indice intermediaires ont �t�s stock�s et le tableau contient  24x24 pixels
							 * 
							 */
						}
					}							
				}								
			}
		}		
	} //END OF renderFont METHOD
		
	
	

}
