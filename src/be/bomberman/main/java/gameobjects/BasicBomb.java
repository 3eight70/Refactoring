package gameobjects;


import affichage.Screen;
import affichage.SheetSquare;
import bomberman.Bomberman;
import levels.Level;
import levels.tiles.Tile;

public class BasicBomb extends Bomb {

    public static int bombRate = 30;


    public BasicBomb(int x, int y, Level level, Player player) {
        super(x, y, level, 2, player);
        level.setTile((x + 10) >> 5, (y + 10) >> 5, 0xff999999);
    }

    @Override
    public void update() {
        timer++;
        if (timer == 210 || dieBombByDef()) {
            boom();
            remove();
            level.setTile((x + 10) >> 5, (y + 10) >> 5, 0xff00FF00);
        }

    }

    public boolean dieBombByDef() {
        /*
         * Une bombe touchee par une deflagration fait boom
         *
         */
        for (int i = 0; i < Level.deflagrations.size(); i++) {
            if (Level.deflagrations.get(i).burnedByCollision(x, y)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void render(Screen screen) {
        if (!removed) {
            if (level.theLevel == "level1") {
                screen.renderTile(x, y, Tile.basicbomb);
            } else if (level.theLevel == "level2") {
                //System.out.println("rendering bomb");
                if (timer % 210 < 70)
                    screen.renderEntity(x, y, SheetSquare.basicbomb1, 32, false, false, 0xff527B9C); //NEW
                else if (timer % 210 < 140)
                    screen.renderEntity(x, y, SheetSquare.basicbomb2, 32, false, false, 0xff527B9C); //NEW
                else screen.renderEntity(x, y, SheetSquare.basicbomb3, 32, false, false, 0xff527B9C); //NEW
            }
        }
    }


    @Override
    public void boom() {
        Level.deflagrations.add(new Deflagration(x, y, level, 1, player.getRange()));
        if (!Bomberman.musicIsPaused) System.out.println("boom");
    }


    @Override
    public void remove() {
        removed = true;
    }

}
