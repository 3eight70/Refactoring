package levels;


import affichage.Screen;
import affichage.SheetSquare;
import bomberman.Bomberman;
import bomberman.pause.GameOffStateGame;
import bomberman.pause.GameOnStateGame;
import bomberman.pause.MusicOnStateGame;
import gameobjects.bonus.Bonus;
import levels.tiles.AnimatedSolidTile;
import levels.tiles.Tile;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Level2 extends Level {

    BufferedImage image = null;
    protected int animation = 0;
    private HashMap<Integer, Tile> tileMap;
    private HashMap<Integer, AnimatedSolidTile> animatedSolidTileMap;
    private final String[] bonusTypes = {
            "firePower", "fetaBonus", "rangeBonus", "lifeBonus", "bombBonus"
    };

    public Level2(String imagePath) {
        super(imagePath, "level2");
        initializeTileMaps();
    }

    private void initializeTileMaps() {
        tileMap = new HashMap<>();
        animatedSolidTileMap = new HashMap<>();

        tileMap.put(0xff527b9c, Tile.teleport);
        tileMap.put(0xff950950, Tile.noteleport);
        tileMap.put(0xff555555, Tile.rockLevel2);
        tileMap.put(0xff969696, Tile.lightrockLevel2);
        tileMap.put(0xff00FF00, Tile.grassLevel2);
        tileMap.put(0xff999999, Tile.grassSolid);
        tileMap.put(0xffff52ff, Tile.treeSO);
        tileMap.put(0xffff62ff, Tile.treeO);
        tileMap.put(0xffeec400, Tile.treeNO);
        tileMap.put(0xffff82ff, Tile.treeN);
        tileMap.put(0xffeec500, Tile.treeNE);
        tileMap.put(0xff0000bd, Tile.treeSE);
        tileMap.put(0xff0010bd, Tile.treeE);
        tileMap.put(0xffeedc00, Tile.bordNO);
        tileMap.put(0xffeec000, Tile.bordNOO);
        tileMap.put(0xffeedc10, Tile.bordN);
        tileMap.put(0xffeedc20, Tile.bordNE);
        tileMap.put(0xffeec200, Tile.bordNEE);
        tileMap.put(0xffeedc30, Tile.bordO);
        tileMap.put(0xffeedc40, Tile.bordE);
        tileMap.put(0xffeedc50, Tile.bordSO);
        tileMap.put(0xffeec100, Tile.bordSOO);
        tileMap.put(0xffeedc60, Tile.bordS);
        tileMap.put(0xffeedc70, Tile.bordSE);
        tileMap.put(0xffeec300, Tile.bordSEE);
        tileMap.put(0xff00ffff, Tile.house1);
        tileMap.put(0xff80ffff, Tile.house9);
        tileMap.put(0xff90ffff, Tile.house10);

        animatedSolidTileMap.put(0xff10ffff, new AnimatedSolidTile(Tile.house2, Tile.house2b, Tile.house2c, Tile.house2d));
        animatedSolidTileMap.put(0xff20ffff, new AnimatedSolidTile(Tile.house3, Tile.house3b, Tile.house3c, Tile.house3d));
        animatedSolidTileMap.put(0xff30ffff, new AnimatedSolidTile(Tile.house4, Tile.house4b, Tile.house4c, Tile.house4d));
        animatedSolidTileMap.put(0xff40ffff, new AnimatedSolidTile(Tile.house5, Tile.house5b, Tile.house5c, Tile.house5d));
        animatedSolidTileMap.put(0xff50ffff, new AnimatedSolidTile(Tile.house6, Tile.house6b, Tile.house6c, Tile.house6d));
        animatedSolidTileMap.put(0xff60ffff, new AnimatedSolidTile(Tile.house7, Tile.house7b, Tile.house7c, Tile.house7d));
        animatedSolidTileMap.put(0xff70ffff, new AnimatedSolidTile(Tile.house8, Tile.house8b, Tile.house8c, Tile.house8));
        animatedSolidTileMap.put(0xff93ffff, new AnimatedSolidTile(Tile.house11, Tile.house11b, Tile.house11c, Tile.house11d));
        animatedSolidTileMap.put(0xff96ffff, new AnimatedSolidTile(Tile.house12, Tile.house12b, Tile.house12c, Tile.house12d));
        animatedSolidTileMap.put(0xff00eeee, new AnimatedSolidTile(Tile.house13, Tile.house13b, Tile.house13c, Tile.house13d));
        animatedSolidTileMap.put(0xff30eeee, new AnimatedSolidTile(Tile.house14, Tile.house14b, Tile.house14c, Tile.house14d));
        animatedSolidTileMap.put(0xff60eeee, new AnimatedSolidTile(Tile.house15, Tile.house15b, Tile.house15c, Tile.house15d));
    }

    public void updateEntities() {
        super.updateEntities();
        if (animation < 13000) animation++; // augmente de 60 toute les secondes
        else animation = 0;

        // Ici on pourrait utiliser le factory pattern
        updates++;
        if ((updates % 1200) == 0) {        //tous les 20 secondes
            entityManager.notifyObservers("bonusSpawn");
            int bonusIndex = r.nextInt(5);
            updates = 0;

            entityManager.addBonus(new Bonus(this, entityManager.bonusCoord(), bonusTypes[bonusIndex]));
        }
    }

    public void renderEntities(Screen screen, Bomberman bomberman) {
        super.renderEntities(screen, bomberman);
        //en fonction du nombre de joueur !!!
        if (bomberman.getGamePauseState() instanceof GameOnStateGame)
            screen.renderRectangle(0, 0, SheetSquare.afficheon, 128, 544, false, false, 0xff527B9C);
        else if (bomberman.getGamePauseState() instanceof GameOffStateGame)
            screen.renderRectangle(0, 0, SheetSquare.afficheoff, 128, 544, false, false, 0xff527B9C);
        else if (bomberman.getMusicPauseState() instanceof MusicOnStateGame)
            screen.renderRectangle(0, 0, SheetSquare.affichepauseon, 128, 544, false, false, 0xff527B9C);
        else screen.renderRectangle(0, 0, SheetSquare.affichepauseoff, 128, 544, false, false, 0xff527B9C);
    }


    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.seaLevel2; //sea

        int tilesColour = tilesColours[x + y * width];

        if (tileMap.containsKey(tilesColour))
            return tileMap.get(tilesColour);
        else if (animatedSolidTileMap.containsKey(tilesColour))
            return animatedSolidTileMap.get(tilesColour).getCurrentFrame(animation);


        return Tile.grassLevel2;
    }
}
