package levels.tiles;


import affichage.Screen;
import affichage.SheetSquare;

public class BasicSolidTile extends Tile {


    public BasicSolidTile(SheetSquare square) {
        super(square, true, false, false);
    }


    @Override
    public void render(int xPos, int yPos, Screen screen) {
        screen.renderTile(xPos, yPos, this);
    }


}
