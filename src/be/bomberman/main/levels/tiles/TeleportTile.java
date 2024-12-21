package be.bomberman.main.levels.tiles;

import be.bomberman.main.affichage.Screen;
import be.bomberman.main.affichage.SheetSquare;

public class TeleportTile extends Tile{

	public TeleportTile(SheetSquare square) {
		super(square, false, true, true);
	}
	
	public void render(int xPos, int yPos, Screen screen) {
		screen.renderTile(xPos, yPos, this);		
	}
	
}
