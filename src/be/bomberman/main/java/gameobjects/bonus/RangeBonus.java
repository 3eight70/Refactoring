package gameobjects.bonus;


import affichage.Screen;
import gameobjects.Player;
import levels.Level;

public class RangeBonus extends UsedBonus {

    private int counter = 6000;

    public RangeBonus(Level level, int x, int y) {
        super(level, x, y);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void doUsedBonusAction(Player player) {
        this.player = player;
        if (player.getRange() < 3) player.setRange(player.getRange() + 1);
        else counter = 6000;
    }

    @Override
    public void doUsedBonusActionOnCollision(Player player) {
    }

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
