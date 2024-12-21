package be.bomberman.main.levels.tiles;

import be.bomberman.main.affichage.Screen;
import be.bomberman.main.affichage.SheetSquare;

public class BasicSolidTile extends Tile {
	

	public BasicSolidTile(SheetSquare square) {
		super(square, true, false, false);
	}
	

	@Override
	public void render(int xPos, int yPos, Screen screen) {
		screen.renderTile(xPos, yPos, this);		
	}
	
	
}
