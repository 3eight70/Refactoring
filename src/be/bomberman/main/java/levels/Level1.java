package levels;


import gameobjects.bonus.Bonus;
import levels.tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Level1 extends Level {

    BufferedImage image = null;


    public Level1(String imagePath) {
        super(imagePath, "level1");
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

        // Ici on pourrait utiliser le factory pattern
        updates++;
        if ((updates % 1200) == 0) {
            int should = r.nextInt(5);
            updates = 0;
            if (should == 0 || should == 2) {
                Bonus firePowerBonus = new Bonus(this, bonusCoord(), "firePower");
                //System.out.println( bonusCoord()[0] +" "+ bonusCoord()[1]);
                bonusses.add(firePowerBonus);
            }
            if (should == 1 || should == 3) {
                Bonus fetaBonus = new Bonus(this, bonusCoord(), "fetaBonus");
                //System.out.println( bonusCoord()[0] +" "+ bonusCoord()[1]);
                bonusses.add(fetaBonus);
            }
			/*
			if (should == 5) {
				Bonus bonus = new SomeBonus(this);
				bonusses.add(bonus);		
			}
			*/
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
