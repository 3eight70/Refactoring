package be.bomberman.main.gameobjects.bonus;

import be.bomberman.main.affichage.Screen;
import be.bomberman.main.gameobjects.Player;
import be.bomberman.main.levels.Level;

public class LifeBonus extends UsedBonus{

	//private int counter = 100000 ;
	
	public LifeBonus(Level level, int x, int y) {
		super(level, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doUsedBonusAction(Player player) {
		this.player = player ;
		if (player.getLife() < 5) player.setLife(player.getLife()+1);
		//else counter = 100000;
	}

	@Override
	public void doUsedBonusActionOnCollision(Player player) {}

	@Override
	public void update() {
		/*counter--;
		if (counter < 0){
			remove();
			player.setRange(player.getRange() - 1) ;
		}*/
	}

	@Override
	public void render(Screen screen) {
		
		
	}

	@Override
	public void remove() {
		removed = true;		
	}

}
