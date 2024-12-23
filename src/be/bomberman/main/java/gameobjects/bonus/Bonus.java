package gameobjects.bonus;


import affichage.Screen;
import affichage.SheetSquare;
import affichage.font.SomeFont;
import gameobjects.GameObject;
import levels.Level;
import levels.tiles.Tile;

import java.util.Random;

public class Bonus extends GameObject {


    private int lifeSpan = 4500;
    protected String bonusType;
    protected int num = 500;               // temps qu'il peut etre utilis�
    protected Random rand = new Random();


    public Bonus(Level level, int[] coord, String bonusType) {
        super(level);
        this.x = coord[0];
        this.y = coord[1];
        this.bonusType = bonusType;
        center();
    }

    @Override
    public void update() {
        lifeSpan--;
        if (lifeSpan < 0 || entityManager.dieBonusByDef(this)) {
            remove();
        }
    }


    @Override
    public void render(Screen screen) {
        if (!removed) {
            if (level.getLevel().equals("level1")) {
                screen.renderTile(x, y, Tile.bonus);
                SomeFont.renderW(this.bonusType, screen, x - 16, y - 8, 1);
            } else if (level.getLevel().equals("level2")) {
                renderBonus(screen);
            }
        }
    }

    private void renderBonus(Screen screen) {
        int color = 0xff527B9C;
        switch (this.getType()) {
            case "fetaBonus":
                screen.renderEntity(x, y, SheetSquare.bonusspeed, 32, false, false, color);
                break;
            case "rangeBonus":
                screen.renderEntity(x, y, SheetSquare.bonusrange, 32, false, false, color);
                break;
            case "firePower":
                screen.renderEntity(x, y, SheetSquare.bonusspike, 32, false, false, color);
                break;
            case "lifeBonus":
                screen.renderEntity(x, y, SheetSquare.bonuslife, 32, false, false, color);
                break;
            case "bombBonus":
                screen.renderEntity(x, y, SheetSquare.bonusbomb, 32, false, false, color);
                break;
        }
    }

    @Override
    public void remove() {
        removed = true;
    }

    public boolean bonusCollision(int x, int y) {
        // x y position du player en pixel
        int[] entCoord = getCoordinates(x, y);
        return (getCoordinates(this.x, this.y)[0] == entCoord[0]) && (getCoordinates(this.x, this.y)[1] == entCoord[1]);
    }


    public boolean getYourBonus(int xEntity, int yEntity) {

        int xMin = 9;
        int xMax = 21;
        int yMin = 20;
        int yMax = 29;
        for (int x = xMin; x < xMax; x++) {
            if (bonusCollision(xEntity + x, yEntity + yMin)) return true;
            if (bonusCollision(xEntity + x, yEntity + yMax)) return true;
        }
        for (int y = yMin; y < yMax; y++) {
            if (bonusCollision(xEntity + xMin, yEntity + y)) return true;
            if (bonusCollision(xEntity + xMax, yEntity + y)) return true;
        }
        return false;
    }


    public void setRemoved(boolean bool) {
        this.removed = bool;
    }


    public String getType() {
        return bonusType;
    }


}
