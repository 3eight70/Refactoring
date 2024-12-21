package levels.tiles;


import affichage.Screen;
import affichage.SheetSquare;

public class BasicTile extends Tile {


    public BasicTile(SheetSquare square) {
        super(square, false, true, false);
        // breakable pour que la deflagration aparaisse dessus sans devoir definir
    }


    @Override
    public void render(int xPos, int yPos, Screen screen) {
        screen.renderTile(xPos, yPos, this);
    }


}
