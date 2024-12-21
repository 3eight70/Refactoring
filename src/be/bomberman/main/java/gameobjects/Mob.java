package gameobjects;


import affichage.SheetSquare;
import levels.Level;
import levels.tiles.Tile;


public abstract class Mob extends GameObject {

    protected SheetSquare square;
    protected boolean isMoving = false;
    protected int movingDir = 0;              // 0 Nord, 1 Sud, 2 Ouest, 3 Est
    protected int speed;
    protected int scale;
    protected int life;
    protected int animation;


    public Mob(Level level, int x, int y, int speed, int scale) {
        super(level);
        this.speed = speed;
        this.scale = scale;
        this.x = x * 32;
        this.y = y * 32;
    }


    public void move(int xa, int ya) {
        /*
         *
         * entr�es: le signe de xa et ya indique quel touche (up down right left) est enfonc�e
         * renvoi la direction et fait bouger le personnage tant qu'il n'y a pas collision.
         * Cette methode est appell�e dans update() donc si speed = 1 et qu'on garde la touche enfonc�e
         * pendant une seconde on avance de 60 pixels
         *
         * Si xa =  1 on avance de 1 ... a droite
         * Si xa =  0 on avance pas en x
         * Si xa = -1 on avance de 1 ... a gauche
         * Si ya =  1 on avance de 1 vers le bas
         * Si ya =  0 on avance pas en y
         * Si ya = -1 on avance de 1 vers le haut
         *
         */

        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
            return;
        }

        if (!collision(xa, ya)) {
            if (ya < 0) {
                movingDir = 0;
            }
            if (ya > 0) {
                movingDir = 1;
            }
            if (xa < 0) {
                movingDir = 2;
            }
            if (xa > 0) {
                movingDir = 3;
            }
            x += xa * speed;
            y += ya * speed;
        }
    }


    public abstract boolean collision(int xa, int ya);


    protected boolean isSolidTile(int xa, int ya, int x, int y) {
        if (level == null) return false;

        Tile lastTile = level.getTile((this.x + x) >> 5, (this.y + y) >> 5);
        Tile nextTile = level.getTile((this.x + x + xa) >> 5, (this.y + y + ya) >> 5);

        return !lastTile.equals(nextTile) && nextTile.isSolid();
    }


    protected boolean isTeleportTile(int xa, int ya, int x, int y) {
        if (level == null) return false;

        Tile lastTile = level.getTile((this.x + x) >> 5, (this.y + y) >> 5);
        Tile nextTile = level.getTile((this.x + x + xa) >> 5, (this.y + y + ya) >> 5);

        return !lastTile.equals(nextTile) && nextTile.isTeleport();
    }

}
