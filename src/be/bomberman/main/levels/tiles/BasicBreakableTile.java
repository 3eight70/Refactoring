package be.bomberman.main.levels.tiles;

import be.bomberman.main.affichage.Screen;
import be.bomberman.main.affichage.SheetSquare;
import be.bomberman.main.levels.Level;

public class BasicBreakableTile extends Tile{

	
	public BasicBreakableTile(SheetSquare square) {
		super(square, true, true, false);
	}
	

	@Override
	public void render(int xPos, int yPos, Screen screen) {
		screen.renderTile(xPos, yPos, this);		
	}
	

}
