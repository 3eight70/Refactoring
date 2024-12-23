package levels;


import levels.tiles.Tile;

import java.util.Random;

public class Level1 extends Level {
    private String[] bonusTypes = new String[2];

    public Level1(String imagePath) {
        super(imagePath, "level1");
        bonusTypes[0] = "firePower";
        bonusTypes[1] = "fetaBonus";
    }

    public void updateEntities() {
        super.updateEntities();

        updates++;
        if ((updates % 1200) == 0) {
            int should = r.nextInt(2);
            updates = 0;
            entityManager.addBonus(this, bonusTypes[should]);
        }
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.sea;
        if (tilesColours[x + y * width] == 0xff555555) return Tile.rock;
        if (tilesColours[x + y * width] == 0xff00FFFF) return Tile.sea;
        if (tilesColours[x + y * width] == 0xff969696) return Tile.lightrock;
        if (tilesColours[x + y * width] == 0xff00FF00) return Tile.grass;
        if (tilesColours[x + y * width] == 0xff000000) return Tile.burningGrass;
        return Tile.grass;
    }
}
