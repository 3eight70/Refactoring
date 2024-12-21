package levels.tiles;


import affichage.Screen;
import affichage.SheetSquare;

public class BasicBreakableTile extends Tile {


    public BasicBreakableTile(SheetSquare square) {
        super(square, true, true, false);
    }


    @Override
    public void render(int xPos, int yPos, Screen screen) {
        screen.renderTile(xPos, yPos, this);
    }


}
