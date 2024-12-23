package levels;


import affichage.Screen;
import bomberman.Bomberman;
import gameobjects.Mob;
import levels.managers.EntityManager;
import levels.managers.TileManager;
import levels.tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Level {

    protected int width, height;
    protected int updates = 0;
    protected String theLevel;

    protected EntityManager entityManager;
    protected TileManager tileManager;
    protected int[] tilesColours;
    protected BufferedImage image = null;

    protected final Random r = new Random();

    public Level(String imagePath, String theLevel) {
        this.theLevel = theLevel;
        if (imagePath != null) {
            loadLevelFromFile(imagePath);
        } else {
            this.width = 128;
            this.height = 128;
            generateBasicLevel();
        }
        this.entityManager = new EntityManager(this);
        this.tileManager = new TileManager(this);
    }

    protected void generateBasicLevel() {
    }


    public void addPlayer(Mob entity) {
        entityManager.addPlayer(entity);
    }

    public void updateEntities() {
        entityManager.updateEntities();
        tileManager.updateTileTimer();
        entityManager.clearRemovedEntities();
    }

    public void renderEntities(Screen screen, Bomberman bomberman) {
        entityManager.renderEntities(screen);
    }

    protected void loadLevelFromFile(String imagePath) {
        try {
            image = ImageIO.read(Objects.requireNonNull(this.getClass().getResource(imagePath)));
            this.width = image.getWidth();
            this.height = image.getHeight();
            tilesColours = new int[width * height];
            tilesColours = image.getRGB(0, 0, width, height, null, 0, width);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not load level file");
        }
    }

    public Tile getTile(int x, int y) {
        return Tile.grass;
    }

    public void setTile(int x, int y, int color) {

        tilesColours[(x + y * width)] = color;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public int[] getTilesColours() {
        return tilesColours;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getLevel() {
        return theLevel;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
