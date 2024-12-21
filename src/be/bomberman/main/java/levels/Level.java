package levels;


import affichage.Screen;
import gameobjects.Bomb;
import gameobjects.Deflagration;
import gameobjects.Mob;
import gameobjects.bonus.Bonus;
import gameobjects.bonus.UsedBonus;
import levels.tiles.Tile;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Level {

    protected int width, height;
    protected int updates = 0;
    public String theLevel;

    public static List<Mob> players = new LinkedList<Mob>();
    public static List<Bomb> bombs = new LinkedList<Bomb>();
    public static List<Deflagration> deflagrations = new LinkedList<Deflagration>();
    public static List<Bonus> bonusses = new LinkedList<Bonus>();
    public static List<UsedBonus> usedBonusses = new LinkedList<UsedBonus>();
    /*
     * Ces listes contiennent tous les GameObjects dans le niveau
     * (un niveau comporte une carte et des objets )
     */

    protected int[] tilesColours;
    /*
     * La fonction loadLevel() lis un png de h x w pixels ou chaque pixel correspond � un et seulement un Tile dans le rendu
     * Chaque �lements de cette liste est une couleur de pixel correspondant � un Tile donn� (dans getTile())
     * Donc si on veut une carte de 20 x 30 Tiles le png doit contenir 20 x 30 pixels et tilesColours contient
     * 20 x 30 �lementd
     */

    Random r = new Random();   //utile pour l'apparition des bonus


    public Level(String imagePath, String theLevel) {
        this.theLevel = theLevel;
        if (imagePath != null) {
            loadLevelFromFile(imagePath);
        } else {
            this.width = 128;
            this.height = 128;
            generateBasicLevel();
        }
    }


    protected void loadLevelFromFile(String imagePath) {
    }

    protected void generateBasicLevel() {
    }


    public void addPlayer(Mob entity) {
        players.add(entity);
    }
	/*
	public void addBomb(Bomb entity){
		bombs.add(entity);
	}
	public void addDeflagration(Deflagration entity){
		deflagrations.add(entity);
	}
	public void removePlayer(int index){
		players.remove(index);
	}
	public void removeBomb(int index){
		bombs.remove(index);
	}
	public void removeDeflagration(int index){
		deflagrations.remove(index);
	}
	*/


    public void updateEntities() {
        for (Mob player : players) {
            player.update();
        }
        for (Bomb bomb : bombs) {
            bomb.update();
        }
        for (Deflagration deflagration : deflagrations) {
            deflagration.update();
        }
        for (Bonus bonus : bonusses) {
            bonus.update();
        }
        for (UsedBonus usedBonus : usedBonusses) {
            usedBonus.update();
        }
        Tile.tileTimer++;
        if (Tile.tileTimer < 1500) {
            Tile.teleport = Tile.noteleport; //teleport = notp
        } else {
            Tile.teleport = Tile.teleport2; //tp = tp
            Tile.teleport2 = Tile.teleport; //copie de teleport
        }
        clear();
    }


    protected void clear() {
        for (int i = 0; i < bombs.size(); i++) {
            Bomb b = bombs.get(i);
            if (b.isRemoved()) bombs.remove(i);
        }
        for (int i = 0; i < deflagrations.size(); i++) {
            Deflagration d = deflagrations.get(i);
            if (d.isRemoved()) deflagrations.remove(i);
        }
        for (int i = 0; i < bonusses.size(); i++) {
            Bonus b = bonusses.get(i);
            if (b.isRemoved()) bonusses.remove(i);
        }
        for (int i = 0; i < usedBonusses.size(); i++) {
            UsedBonus ub = usedBonusses.get(i);
            if (ub.isRemoved()) usedBonusses.remove(i);
        }
    }


    public void renderEntities(Screen screen, int pausestate) {

        for (UsedBonus usedBonus : usedBonusses) {
            usedBonus.render(screen);
        }
        for (Bonus bonus : bonusses) {
            bonus.render(screen);
        }
        for (Bomb bomb : bombs) {
            bomb.render(screen);
        }
        for (Mob player : players) {
            player.render(screen);
        }
        for (Deflagration deflagration : deflagrations) {
            deflagration.render(screen);
        }


    }


    public void renderTile(Screen screen, int xOffset, int yOffset) {
        /*
         * Cette fonction imprime la carte en appelant tile.render qui appelle screen.render
         *
         *
         * "Camera"
         *
         *  xOffset = ghost.x - screen.width / 2;    Distance horizontale du joueur par rapport au centre de l'affichage
         *  yOffset = ghost.y - screen.height / 2;   Distance verticale du joueur par rapport au centre de l'affichage
         *
         *  Si le fantome commence au centre de l'affichage xOffset et yOffset sont initialents nuls donc l'affichage commence en (0,0)
         *  Voir description plus bas
         *
         */
		
		/*
		if (xOffset < 0) {xOffset = 0;}
		if (xOffset >= (width << 5) - screen.getWidth()){ xOffset = (width << 5) - screen.getWidth(); }
		if (yOffset < 0) yOffset = 0;
		if (yOffset >= (height << 5) - screen.getHeight()) {yOffset = (height << 5) - screen.getHeight();}
		*/

        /*
         * Permet de ne pas afficher les zones en dehors de la carte
         */

        screen.setOffset(xOffset, yOffset);

        for (int y = yOffset >> 5; y < ((yOffset + screen.getHeight() + 32) >> 5); y++) {
            if (this.theLevel == "level1") {
                for (int x = xOffset >> 5; x < ((xOffset + screen.getWidth() + 32) >> 5); x++) {

                    //System.out.println(tilesColours[x + y*width]);
                    getTile(x, y).render(x << 5, y << 5, screen);
                    /*
                     * getTile va chercher le tile a la position x,y (pas en pixels) sur la carte du level qui est une image de h x w pixels
                     * si l'on veut h tiles en hauteur et w tiles en largeur
                     */
                }
            } else if (this.theLevel == "level2") {
                for (int x = (xOffset + 128) >> 5; x < ((xOffset + screen.getWidth() + 32) >> 5); x++) {

                    //System.out.println(tilesColours[x + y*width]);
                    getTile(x, y).render(x << 5, y << 5, screen);
                    /*
                     * getTile va chercher le tile a la position x,y (pas en pixels) sur la carte du level qui est une image de h x w pixels
                     * si l'on veut h tiles en hauteur et w tiles en largeur
                     */
                }
            }

        }


        /*
         * La taille d'un tile dans la spriteSheet est de 32 pixels
         *
         * Exemple: Le ghost commence au centre du frame,  initialement (xOffset, yOffset) = (0, 0)   (dans cet exemple on suppose que la carte est plus grande que le frame)
         * 			Donc l'affichage commence par le premier tile de la carte du level (la methode getTile va chercher le Tile (0, 0) sur le carte du level)
         * 			- si on appuie sur up ==> la fonction move est appel�e ==> ya etant n�gatif le joueur monte (par ex de 32 pixels)
         *          yOffset est alors modifi� de -32 pixels ==> yOffset et remis automatiquemen a zero ==> la camera ne bouge pas
         *          - si on appuie sur down ==> la fonction move est appel�e ==> ya etant positif le ghost descend (par ex de 32 pixels)
         *          yOffset est alors modifi� de +32 pixels ==> getTile pioche les tiles a partir de y (= 32 >> 5) = 1
         *           ==> Tous l'affichage descend de 1 tile(32 pixels) comme le fantome. Donc la camera suis le fantome
         *
         *
         *
         *          Donc getTile appelle tous les tiles rentrant dans la dimension de l'affichage et ensuite appelle tile.render
         *          qui a son tour appele directement screen.render de la classe screen qui contient toutes les fonction render.
         *
         *          Quand le joueur se rapproche trop d'un bord de la CARTE xOffset et yOffset restent constants
         *          et la camera arrete de bouger.
         */

    }


    public Tile getTile(int x, int y) {
	   	/*if (this.theLevel == "level1") return Tile.grass;
	   	else return Tile.grassLevel2;*/
        return Tile.grass;
    }

    public void setTile(int x, int y, int color) {

        tilesColours[(x + y * width)] = color;
    }

    public int[] bonusCoord() {
        // renvoi al�atoirement des coordon�es d'un tile grass pour y placer le bonus

        int[] coord = new int[2];
        int x = 6;  // r.nextInt(max - min + 1) + min;  return a random int between min and max
        int y = 0;

        if (this.theLevel == "level1") {
            while (getTile(x, y) != Tile.grass) {
                x = r.nextInt(width);
                y = r.nextInt(height);

            }
        } else if (this.theLevel == "level2") {
            while (getTile(x, y) != Tile.grassLevel2) {
                x = r.nextInt(width - 4) + 4;
                y = r.nextInt(height);

            }
        }

        coord[0] = x << 5;
        coord[1] = y << 5;


        //System.out.println(coord[0]+" "+coord[1]);

        return coord;
    }


    public static int log2(int size) {
        return (int) (Math.log(size) / Math.log(2));
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

}
