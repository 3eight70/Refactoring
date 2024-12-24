package gameobjects;


import levels.Level;
import levels.tiles.Tile;

public abstract class Bomb extends GameObject {

    protected int timer;
    protected int range;
    protected Player player;

    public Bomb(int x, int y, Level level, int range, Player player) {
        super(level);
        this.x = x;
        this.y = y;
        this.level = level;
        this.player = player;
        this.timer = 0;
        this.range = range;
        center();
    }


    public abstract void boom();

    public static boolean canBePlaced(int x, int y) {
        /*
         * Seul les coord en haut a gauche son n�cessaire car les autres bords du joueur ne peuvent pas etre su un autre tile
         */
        if (level.getLevel() == "level1") {
            return level.getTile((x + 16) >> 5, (y + 16) >> 5) == Tile.grass;
        } else if (level.getLevel() == "level2") {
            return level.getTile((x + 16) >> 5, (y + 16) >> 5) == Tile.grassLevel2;
        }

        return false;
    }

    public boolean bombCollision(int x, int y) {
        // x y position du player en pixel
        int[] entCoord = getCoordinates(x, y);
        return (getCoordinates(this.x, this.y)[0] == entCoord[0]) && (getCoordinates(this.x, this.y)[1] == entCoord[1]);
    }

    public boolean getYourBomb(int xEntity, int yEntity) {

        int xMin = 9;
        int xMax = 21;
        int yMin = 20; //pour que la tete ne brule pas
        int yMax = 29;
        if (bombCollision(xEntity + xMin, yEntity + yMin)) return true;
        if (bombCollision(xEntity + xMax, yEntity + yMax)) return true;
        if (bombCollision(xEntity + xMin, yEntity + yMax)) return true;
        return bombCollision(xEntity + xMax, yEntity + yMin);
    }
}
