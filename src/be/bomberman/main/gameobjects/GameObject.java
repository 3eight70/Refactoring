package be.bomberman.main.gameobjects;

import be.bomberman.main.affichage.Screen;
import be.bomberman.main.levels.Level;


public abstract class GameObject {
	
	//template method design pattern
	
	public int x;
	public int y;
	/*
	 * 
	 */
	protected static Level level;
	protected static Player player;
	protected boolean removed = false;
	
	
	public GameObject(Level level){
		init(level);
	}
	
	
	public final void init(Level level){
		this.level = level;
	}
	
	
	public int[] getCoordinates(int x, int y){
		// entr�es en pixels
		// return les coordon�es du tile sur lequel le ixel se trouve
		int[] coord = new int[2];
		coord[0] = x >> 5; // /32 
		coord[1] = y >> 5;
		return coord;
	}
	

	public abstract void update();
	public abstract void render(Screen screen);	
	public abstract void remove();
	
	
	public boolean isRemoved() {
		return removed ;
	}

	
	public void center(){
		if (x%32 > 24){
			while (x % 32 != 0){
				this.x++;
			}
		} else {
			while (x % 32 != 0){
				this.x--;
			}
		}
		if (y%32 > 21){
			// 21 = 31 - 10  le 10 est la distance yMin allou�e a la collision voir (player)
			// On peut mettre un chiffre plus bas pour raisons esth�tiques
			while (y % 32 != 0){
				this.y++;
			}
		} else {
			while (y % 32 != 0){
				this.y--;
			}
		}		
	}
	

}
