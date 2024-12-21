package levels.tiles;


import affichage.Screen;
import affichage.SheetSquare;

public class TeleportTile extends Tile {

    public TeleportTile(SheetSquare square) {
        super(square, false, true, true);
    }

    public void render(int xPos, int yPos, Screen screen) {
        screen.renderTile(xPos, yPos, this);
    }

}
