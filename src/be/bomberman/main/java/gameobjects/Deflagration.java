package gameobjects;


import affichage.Screen;
import affichage.SheetSquare;
import levels.Level;
import levels.tiles.Tile;

public class Deflagration extends GameObject {

    private int lifeSpan;
    private final int range;
    private final int x;
    private final int y;

    public Deflagration(int x, int y, Level level, int lifeSpan, int range) {
        super(level);
        this.lifeSpan = lifeSpan * 30;
        this.range = range;
        this.level = level;
        this.x = x;
        this.y = y;

    }

    @Override
    public void update() {
        lifeSpan--;
        if (lifeSpan < 0) {
            remove();

        }
        //System.out.println(x+" "+y);
    }

    @Override
    public void render(Screen screen) {
        if (!removed) {
            if (level.theLevel == "level1") {
                renderDeflagrationLevel1(screen);
            } else if (level.theLevel == "level2") {
                renderDeflagrationLevel2(screen);
            }
        }
    }


    private void renderDeflagrationLevel1(Screen screen) {
        screen.renderTile(x, y, Tile.fire);
        for (int i = 1; i <= range; i++) {
            if (breakableLeft(i) && breakableLeft(i - 1)) { // la bombe est toujours sur un breakable
                screen.renderTile(x - (i << 5), y, Tile.fire);
                breakTile((x >> 5) - i, y >> 5);
                //(level.getTile((x >> 5) - i, y >> 5)).setBurning(true);
            }
            if (breakableRight(i) && breakableRight(i - 1)) {
                screen.renderTile(x + (i << 5), y, Tile.fire);
                breakTile((x >> 5) + i, y >> 5);
                //level.getTile((x >> 5) + i, y >> 5).setBurning(true);
            }
            if (breakableDown(i) && breakableDown(i - 1)) {
                screen.renderTile(x, y + (i << 5), Tile.fire);
                breakTile(x >> 5, (y >> 5) + i);
                //level.getTile(x >> 5 , (y >> 5) + i).setBurning(true);

            }
            if (breakableUp(i) && breakableUp(i - 1)) {
                screen.renderTile(x, y - (i << 5), Tile.fire);
                breakTile(x >> 5, (y >> 5) - i);
                //level.getTile(x >> 5 , (y >> 5) - i).setBurning(true);

            }
        }

    }


    private void renderDeflagrationLevel2(Screen screen) {
        screen.renderEntity(x, y, SheetSquare.level2fire1, 32, false, false, 0xff527B9C); //NEW
        for (int i = 1; i <= range; i++) {
            if (breakableLeft(i) && breakableLeft(i - 1) && breakableLeft(i - 2)) { // la bombe est toujours sur un breakable (1-0 / 2-1 / ...)
                if (i == range)
                    screen.renderEntity(x - (i << 5), y, SheetSquare.level2fire3, 32, true, false, 0xff527B9C); //NEW
                else screen.renderEntity(x - (i << 5), y, SheetSquare.level2fire2, 32, true, false, 0xff527B9C);
                breakTile((x >> 5) - i, y >> 5);
                //(level.getTile((x >> 5) - i, y >> 5)).setBurning(true);
            }
            if (breakableRight(i) && breakableRight(i - 1) && breakableRight(i - 2)) {
                if (i == range)
                    screen.renderEntity(x + (i << 5), y, SheetSquare.level2fire3, 32, false, false, 0xff527B9C);
                else screen.renderEntity(x + (i << 5), y, SheetSquare.level2fire2, 32, false, false, 0xff527B9C);
                breakTile((x >> 5) + i, y >> 5);
                //level.getTile((x >> 5) + i, y >> 5).setBurning(true);
            }
            if (breakableDown(i) && breakableDown(i - 1) && breakableDown(i - 2)) {
                if (i == range)
                    screen.renderEntity(x, y + (i << 5), SheetSquare.level2fire5, 32, false, false, 0xff527B9C);
                else screen.renderEntity(x, y + (i << 5), SheetSquare.level2fire4, 32, false, false, 0xff527B9C);
                breakTile(x >> 5, (y >> 5) + i);
                //level.getTile(x >> 5 , (y >> 5) + i).setBurning(true);

            }
            if (breakableUp(i) && breakableUp(i - 1) && breakableUp(i - 2)) {
                if (i == range)
                    screen.renderEntity(x, y - (i << 5), SheetSquare.level2fire5, 32, false, true, 0xff527B9C);
                else screen.renderEntity(x, y - (i << 5), SheetSquare.level2fire4, 32, false, true, 0xff527B9C);
                breakTile(x >> 5, (y >> 5) - i);
                //level.getTile(x >> 5 , (y >> 5) - i).setBurning(true);

            }
        }

    }

    public boolean entityBurning(int x, int y) {
        // x y position du player en pixel
        int[] entCoord = getCoordinates(x, y);
        //System.out.println(x+" "+y);
        //System.out.println(this.x+" "+this.y);
        boolean burning = false;

        // int[] defCoord = getCoordinates(this.x, this.y);

        if (isBurningAtCoordinates(this.x, this.y, entCoord)) {
            burning = true;
        } else {
            for (int i = 1; i <= range; i++) {
                if (checkBurningInDirection(0, i, entCoord) ||
                        checkBurningInDirection(1, i, entCoord) ||
                        checkBurningInDirection(2, i, entCoord) ||
                        checkBurningInDirection(3, i, entCoord)) {
                    burning = true;
                    break;
                }
            }
        }


        return burning;
    }

    private boolean isBurningAtCoordinates(int x, int y, int[] entCoord) {
        return (getCoordinates(x, y)[0] == entCoord[0]) && (getCoordinates(x, y)[1] == entCoord[1]);
    }

    private boolean checkBurningInDirection(int direction, int i, int[] entCoord) {
        int offset = i << 5;
        switch (direction) {
            case 0: // Влево
                return breakableLeft(i) && breakableLeft(i - 1) && breakableLeft(i - 2) && isBurningAtCoordinates(this.x - offset, this.y, entCoord);
            case 1: // Вправо
                return breakableRight(i) && breakableRight(i - 1) && breakableRight(i - 2) && isBurningAtCoordinates(this.x + offset, this.y, entCoord);
            case 2: // Вверх
                return breakableUp(i) && breakableUp(i - 1) && breakableUp(i - 2) && isBurningAtCoordinates(this.x, this.y - offset, entCoord);
            case 3: // Вниз
                return breakableDown(i) && breakableDown(i - 1) && breakableDown(i - 2) && isBurningAtCoordinates(this.x, this.y + offset, entCoord);
            default:
                return false;
        }
    }

    public boolean burnedByCollision(int xEntity, int yEntity) {
		/*if (level.theLevel == "level1"){
			int xMin = 3;
			int xMax = 29;
			int yMin = 3;
			int yMax = 29;}
		else if (level.theLevel == "level2"){*/
        int xMin = 9;
        int xMax = 21;
        int yMin = 20; //pour que la tete ne brule pas
        int yMax = 29;

        for (int x = xMin; x < xMax; x++) {
            if (entityBurning(xEntity + x, yEntity + yMin)) {
                return true;
            }
            if (entityBurning(xEntity + x, yEntity + yMax)) return true;
        }
        for (int y = yMin; y < yMax; y++) {
            if (entityBurning(xEntity + xMin, yEntity + y)) return true;
            if (entityBurning(xEntity + xMax, yEntity + y)) return true;
        }

        return false;
    }

    private boolean breakableLeft(int i) {
        if (i < 0) i = 0;
        //breakTile((x >> 5) - i, y >> 5);
        return level.getTile((x >> 5) - i, y >> 5).isBreakable();

    }

    private boolean breakableRight(int i) {
        if (i < 0) i = 0;
        //breakTile((x >> 5) + i, y >> 5);
        return level.getTile((x >> 5) + i, y >> 5).isBreakable();
    }

    private boolean breakableUp(int i) {
        if (i < 0) i = 0;
        //breakTile(x >> 5 , (y >> 5) - i);
        return level.getTile(x >> 5, (y >> 5) - i).isBreakable();
    }

    private boolean breakableDown(int i) {
        if (i < 0) i = 0;
        //breakTile(x >> 5 , (y >> 5) + i);
        return level.getTile(x >> 5, (y >> 5) + i).isBreakable();
    }


    @Override
    public void remove() {
        removed = true;
        //Level.deflagrations.remove(BasicBomb.indexOfDef);
    }

    private void breakTile(int xPos, int yPos) {
        /*
         *  Remlace le tile a la position xPos, yPos par un tile grass si il est cassable
         */
        if (xPos < 0 || yPos < 0 || xPos >= level.getWidth() || yPos >= level.getHeight()) return; //NEW

        if (level.getTilesColours()[xPos + yPos * level.getWidth()] == 0xff969696) {
            level.getTilesColours()[xPos + yPos * level.getWidth()] = 0xff00FF00;
        }

    }


    public int getRange() {
        return range;
    }

}
