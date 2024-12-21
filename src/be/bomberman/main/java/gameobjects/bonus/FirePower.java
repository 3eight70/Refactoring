package gameobjects.bonus;

import affichage.Screen;
import gameobjects.Player;
import levels.Level;
import levels.tiles.Tile;

public class FirePower extends UsedBonus {

    private int counter = 600;

    public FirePower(Level level, int x, int y) {
        super(level, x, y);
    }


    @Override
    public void update() {
        counter--;
        if (counter < 0) {
            //dissapear(x, y);
            remove();
        }
    }


    @Override
    public void render(Screen screen) {
        if (!removed) {
            if (level.theLevel == "level1") screen.renderTile(x, y, Tile.burningGrass);
            else {
                screen.renderTile(x, y, Tile.spike);
            }
        }
    }


    @Override
    public void remove() {
        removed = true;
    }


    @Override
    public void doUsedBonusAction(Player player) {
    }


    @Override
    public void doUsedBonusActionOnCollision(Player player) {
        // Cette fonction s'enclencehe apr�s la detection de collision avec le joueur
        player.dieByBonus();
        System.out.println("ahh");
    }
	
	
	/*
	@Override
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;		
	}
	
	
	@Override
	public void appear(int xPixel, int yPixel){	
		// entr�e position du joueur
		this.x = xPixel ;
		this.y = yPixel ;
		System.out.println(xPixel+" "+yPixel);
		level.getTilesColours()[(xPixel>>5) + (yPixel>>5)*level.getWidth()] = 0xff000000 ;
	}


	@Override
	public void dissapear(int xPixel, int yPixel){
	
		level.getTilesColours()[(xPixel>>5) + (yPixel>>5)*level.getWidth()] = 0xff00FF00 ;
	}
	*/

}
