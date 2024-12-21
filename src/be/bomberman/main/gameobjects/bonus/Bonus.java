package be.bomberman.main.gameobjects.bonus;

import java.util.Random;

import be.bomberman.main.affichage.Screen;
import be.bomberman.main.affichage.SheetSquare;
import be.bomberman.main.affichage.font.SomeFont;
import be.bomberman.main.gameobjects.GameObject;
import be.bomberman.main.levels.Level;
import be.bomberman.main.levels.tiles.Tile;

public class Bonus extends GameObject{
	
	
	private int lifeSpan= 4500 ;
	protected String bonusType;
	protected int num = 500 ;               // temps qu'il peut etre utilis�
	protected Random rand = new Random() ;	
	

	public Bonus(Level level, int[] coord, String bonusType) {
		super(level);
		this.x = coord[0];
		this.y = coord[1];
		this.bonusType = bonusType ;
		center();
	}

	
	@Override
	public void update() {
		lifeSpan-- ;		
		if (lifeSpan < 0 || dieBonusByDef()){
			remove();		
		}		
	}
	

	@Override
	public void render(Screen screen) {
		if (!removed){
			if (level.theLevel == "level1"){
				screen.renderTile(x, y, Tile.bonus);
				SomeFont.renderW(this.bonusType, screen, x - 16 , y - 8 , 1);
			}
			else if (level.theLevel == "level2"){
				if (this.getType() == "fetaBonus"){
					screen.renderEntity(x, y, SheetSquare.bonusspeed, 32, false, false, 0xff527B9C);
				}
				else if (this.getType() == "rangeBonus"){
					screen.renderEntity(x, y, SheetSquare.bonusrange, 32, false, false, 0xff527B9C);
				}
				else if (this.getType() == "firePower"){
					screen.renderEntity(x, y, SheetSquare.bonusspike, 32, false, false, 0xff527B9C);
				}
				else if (this.getType() == "lifeBonus"){
					screen.renderEntity(x, y, SheetSquare.bonuslife, 32, false, false, 0xff527B9C); //a modif
				}
				else if (this.getType() == "bombBonus"){
					screen.renderEntity(x, y, SheetSquare.bonusbomb, 32, false, false, 0xff527B9C); //a modif
				}
			}
		}
	}

	
	@Override
	public void remove(){
		removed = true;		
	}
	
	public boolean dieBonusByDef(){	
		/*
		 * Une bombe touchee par une deflagration fait boom
		 * 
		 */
		for(int i = 0; i < Level.deflagrations.size(); i++) {
			if(Level.deflagrations.get(i).burnedByCollision(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean bonusCollision(int x, int y){
		// x y position du player en pixel
		int[] entCoord = getCoordinates(x, y);
		if ( (getCoordinates(this.x, this.y)[0] == entCoord[0]) && (getCoordinates(this.x, this.y)[1] == entCoord[1]) ){
			
			return true;
		}
		return false;
	}
	
	
	public boolean getYourBonus(int xEntity, int yEntity){
		
		int xMin = 9;
		int xMax = 21;
		int yMin = 20;
		int yMax = 29;
		for (int x = xMin; x < xMax; x++){
			if (bonusCollision(xEntity + x, yEntity + yMin)) return true ;
			if (bonusCollision(xEntity + x, yEntity + yMax)) return true ; 
		}
		for (int y = yMin; y < yMax; y++){
			if (bonusCollision(xEntity + xMin, yEntity + y)) return true;		
			if (bonusCollision(xEntity + xMax, yEntity + y)) return true ;
		}		
		return false;
	}
	
	
	public void setRemoved(boolean bool){
		this.removed = bool;
	}
	

	public String getType() {
		return bonusType;
	}
	
	
	
	
}
