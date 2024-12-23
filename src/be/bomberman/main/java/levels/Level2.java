package levels;


import affichage.Screen;
import affichage.SheetSquare;
import bomberman.Bomberman;
import bomberman.pause.GameOffStateGame;
import bomberman.pause.GameOnStateGame;
import bomberman.pause.MusicOnStateGame;
import gameobjects.bonus.Bonus;
import levels.tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Level2 extends Level {

    BufferedImage image = null;
    protected int animation = 0;

    public Level2(String imagePath) {
        super(imagePath, "level2");
    }

    protected void loadLevelFromFile(String imagePath) {

        try {
            image = ImageIO.read(Level1.class.getResource(imagePath));
            this.width = image.getWidth();
            this.height = image.getHeight();
            tilesColours = new int[width * height];
            tilesColours = image.getRGB(0, 0, width, height, null, 0, width);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not load level file");
        }
    }


    public void updateEntities() {
        super.updateEntities();
        if (animation < 13000) animation++; // augmente de 60 toute les secondes
        else animation = 0;

        // Ici on pourrait utiliser le factory pattern
        updates++;
        if ((updates % 1200) == 0) {        //tous les 20 secondes
            if (!Bomberman.musicIsPaused) System.out.println("bonusSpawn");
            int should = r.nextInt(13);
            updates = 0;
            if (should == 0 || should == 2 || should == 7 || should == 13) {
                Bonus firePowerBonus = new Bonus(this, bonusCoord(), "firePower");
                //System.out.println( bonusCoord()[0] +" "+ bonusCoord()[1]);
                bonusses.add(firePowerBonus);
            }
            if (should == 1 || should == 3 || should == 8) {
                Bonus fetaBonus = new Bonus(this, bonusCoord(), "fetaBonus");
                //System.out.println( bonusCoord()[0] +" "+ bonusCoord()[1]);
                bonusses.add(fetaBonus);
            }
            if (should == 4 || should == 6 || should == 12) {
                Bonus rangeBonus = new Bonus(this, bonusCoord(), "rangeBonus");
                //System.out.println( bonusCoord()[0] +" "+ bonusCoord()[1]);
                bonusses.add(rangeBonus);
            }
            if (should == 5) {
                Bonus lifeBonus = new Bonus(this, bonusCoord(), "lifeBonus");
                //System.out.println( bonusCoord()[0] +" "+ bonusCoord()[1]);
                bonusses.add(lifeBonus);
            }
            if (should == 9 || should == 10 || should == 11) {
                Bonus bombBonus = new Bonus(this, bonusCoord(), "bombBonus");
                //System.out.println( bonusCoord()[0] +" "+ bonusCoord()[1]);
                bonusses.add(bombBonus);
            }
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


        if (tilesColours[x + y * width] == 0xff527b9c) return Tile.teleport;
        if (tilesColours[x + y * width] == 0xff950950) return Tile.noteleport;

<<<<<<< Updated upstream

        if (tilesColours[x + y * width] == 0xff555555) return Tile.rockLevel2;
        if (tilesColours[x + y * width] == 0xff969696) return Tile.lightrockLevel2;
        if (tilesColours[x + y * width] == 0xff00FF00) return Tile.grassLevel2; //0xff00FF00
        if (tilesColours[x + y * width] == 0xff999999) return Tile.grassSolid; //quand on pose une bombe !

        if (tilesColours[x + y * width] == 0xffff52ff) return Tile.treeSO;

        if (tilesColours[x + y * width] == 0xffff62ff) return Tile.treeO;
        if (tilesColours[x + y * width] == 0xffeec400) return Tile.treeNO;
        if (tilesColours[x + y * width] == 0xffff82ff) return Tile.treeN;
        if (tilesColours[x + y * width] == 0xffeec500) return Tile.treeNE;
        if (tilesColours[x + y * width] == 0xff0000bd) return Tile.treeSE;
        if (tilesColours[x + y * width] == 0xff0010bd) return Tile.treeE;

        if (tilesColours[x + y * width] == 0xffeedc00) return Tile.bordNO;
        if (tilesColours[x + y * width] == 0xffeec000) return Tile.bordNOO;
        if (tilesColours[x + y * width] == 0xffeedc10) return Tile.bordN;
        if (tilesColours[x + y * width] == 0xffeedc20) return Tile.bordNE;
        if (tilesColours[x + y * width] == 0xffeec200) return Tile.bordNEE;
        if (tilesColours[x + y * width] == 0xffeedc30) return Tile.bordO;
        if (tilesColours[x + y * width] == 0xffeedc40) return Tile.bordE;
        if (tilesColours[x + y * width] == 0xffeedc50) return Tile.bordSO;
        if (tilesColours[x + y * width] == 0xffeec100) return Tile.bordSOO;
        if (tilesColours[x + y * width] == 0xffeedc60) return Tile.bordS;
        if (tilesColours[x + y * width] == 0xffeedc70) return Tile.bordSE;
        if (tilesColours[x + y * width] == 0xffeec300) return Tile.bordSEE;

        if (tilesColours[x + y * width] == 0xff00ffff) return Tile.house1;
        if (tilesColours[x + y * width] == 0xff10ffff) {
            if (animation % 40 < 10) return Tile.house2;
            else if (animation % 40 < 20) return Tile.house2b;
            else if (animation % 40 < 30) return Tile.house2c;
            else return Tile.house2d;
        }
        if (tilesColours[x + y * width] == 0xff20ffff) {
            if (animation % 40 < 10) return Tile.house3;
            else if (animation % 40 < 20) return Tile.house3b;
            else if (animation % 40 < 30) return Tile.house3c;
            else return Tile.house3d;
        }
        if (tilesColours[x + y * width] == 0xff30ffff) {
            if (animation % 40 < 10) return Tile.house4;
            else if (animation % 40 < 20) return Tile.house4b;
            else if (animation % 40 < 30) return Tile.house4c;
            else return Tile.house4d;
        }
        if (tilesColours[x + y * width] == 0xff40ffff) {
            if (animation % 40 < 10) return Tile.house5;
            else if (animation % 40 < 20) return Tile.house5b;
            else if (animation % 40 < 30) return Tile.house5c;
            else return Tile.house5d;
        }
        if (tilesColours[x + y * width] == 0xff50ffff) {
            if (animation % 40 < 10) return Tile.house6;
            else if (animation % 40 < 20) return Tile.house6b;
            else if (animation % 40 < 30) return Tile.house6c;
            else return Tile.house6d;
        }
        if (tilesColours[x + y * width] == 0xff60ffff) {
            if (animation % 40 < 10) return Tile.house7;
            else if (animation % 40 < 20) return Tile.house7b;
            else if (animation % 40 < 30) return Tile.house7c;
            else return Tile.house7d;
        }
        if (tilesColours[x + y * width] == 0xff70ffff) {
            if (animation % 40 < 10) return Tile.house8;
            else if (animation % 40 < 20) return Tile.house8b;
            else if (animation % 40 < 30) return Tile.house8c;
            else return Tile.house8;
        }
        if (tilesColours[x + y * width] == 0xff80ffff) return Tile.house9;
        if (tilesColours[x + y * width] == 0xff90ffff) return Tile.house10;
        if (tilesColours[x + y * width] == 0xff93ffff) {
            if (animation % 40 < 10) return Tile.house11;
            else if (animation % 40 < 20) return Tile.house11b;
            else if (animation % 40 < 30) return Tile.house11c;
            else return Tile.house11d;
        }
        if (tilesColours[x + y * width] == 0xff96ffff) {
            if (animation % 40 < 10) return Tile.house12;
            else if (animation % 40 < 20) return Tile.house12b;
            else if (animation % 40 < 30) return Tile.house12c;
            else return Tile.house12d;
        }
        if (tilesColours[x + y * width] == 0xff00eeee) {
            if (animation % 40 < 10) return Tile.house13;
            else if (animation % 40 < 20) return Tile.house13b;
            else if (animation % 40 < 30) return Tile.house13c;
            else return Tile.house13d;
        }
        if (tilesColours[x + y * width] == 0xff30eeee) {
            if (animation % 40 < 10) return Tile.house14;
            else if (animation % 40 < 20) return Tile.house14b;
            else if (animation % 40 < 30) return Tile.house14c;
            else return Tile.house14d;
        }
        if (tilesColours[x + y * width] == 0xff60eeee) {
            if (animation % 40 < 10) return Tile.house15;
            else if (animation % 40 < 20) return Tile.house15b;
            else if (animation % 40 < 30) return Tile.house15c;
            else return Tile.house15d;
        }

        //if (tilesColours[x + y*width] == 0x00000000) return Tile.burningGrass;

=======
>>>>>>> Stashed changes
        return Tile.grassLevel2;
    }


}
