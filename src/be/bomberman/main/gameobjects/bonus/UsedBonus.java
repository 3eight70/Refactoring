package be.bomberman.main.gameobjects.bonus;

import be.bomberman.main.gameobjects.GameObject;
import be.bomberman.main.gameobjects.Mob;
import be.bomberman.main.gameobjects.Player;
import be.bomberman.main.levels.Level;

public abstract class UsedBonus extends GameObject {

	public static int useRate = 20 ;
	protected Player player;
	
	public UsedBonus(Level level, int x, int y) {
		super(level);
		this.x = x;
		this.y = y;
		center();
	}	
	
	public abstract void doUsedBonusAction(Player player);
	public abstract void doUsedBonusActionOnCollision(Player player);
	
	/*
	public abstract void appear(int x, int y);
	public abstract void dissapear(int x, int y);
	
	//public abstract void setCoordinates(int x, int y);
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	*/
}
