package levels.managers;

import affichage.Screen;
import levels.Level;
import levels.tiles.Tile;

public class TileManager {

    private int[] tilesColours;
    private int width, height;
    private int tileTimer = 0;
    private final Level currentLevel;

    public TileManager(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void setTilesColours(int[] tilesColours) {
        this.tilesColours = tilesColours;
    }

    public void updateTileTimer() {
        tileTimer++;
        if (tileTimer < 1500) {
            Tile.teleport = Tile.noteleport;
        } else {
            Tile.teleport = Tile.teleport2;
            Tile.teleport2 = Tile.teleport;
        }
    }

    public void renderTiles(Screen screen, int xOffset, int yOffset) {
        for (int y = yOffset >> 5; y < ((yOffset + screen.getHeight() + 32) >> 5); y++) {
            if (currentLevel.getLevel().equals("level1")) {
                for (int x = xOffset >> 5; x < ((xOffset + screen.getWidth() + 32) >> 5); x++) {
                    currentLevel.getTile(x, y).render(x << 5, y << 5, screen);
                }
            } else if (currentLevel.getLevel().equals("level2")) {
                for (int x = (xOffset + 128) >> 5; x < ((xOffset + screen.getWidth() + 32) >> 5); x++) {
                    currentLevel.getTile(x, y).render(x << 5, y << 5, screen);
                }
            }
        }
    }


    public int[] getTilesColours() {
        return tilesColours;
    }
}
