package affichage;


import levels.tiles.Tile;

public class Screen {
    /*
     * Cette classe contient toutes les methodes renders (sauf celle des fonts pour pas trop l'alourdir)
     * A chaque fois que quelque chose doit etre affich� a l'ecran ses pixels sont stock�s dans le tableau screenPixels
     * Ceux-ci sont ensuite transfer�s dans le tableau pixels du main pour etre affich�s via le buffer
     * Ses dimensions sont les meme que PanelStuff et la BufferedImage (en pixels)
     *
     * xOffset et yOffset controllent la position de la camera :
     * ils representent la distance et la direction vers laquelle la camera bouge (voir les fonctions)
     *
     * Ainsi si on souhaite garder le joueur au milieu de l'affichage on posera
     * xOffset = player.x - screen.getWidth() / 2;
     * yOffset = player.y - screen.getHeight() / 2 ;
     *
     * Si on souhaite se trouver en haut a gauche de l'affichage
     * xOffset = player.x;
     * yOffset = player.y;
     *
     */

    private final int width;
    private final int height;
    private int xOffset, yOffset;

    private final int[] screenPixels;


    public Screen(int width, int height) {
        // entr�es: width et height sont en pixels la taille de la buffered image (on donne generalement aussi les memes valeurs que pour le panel)
        this.width = width;
        this.height = height;
        screenPixels = new int[width * height];
    }


    public void clear() {
        // efface les pixels de l'�cran avant raffraichissement de l'image
        for (int i = 0; i < screenPixels.length; i++) {
            screenPixels[i] = 0;
        }
    }


    public void renderTile(int xPos, int yPos, Tile tile) {
        /*
         * Entr�es xPos,yPos : position du Tile sur la carte loader dans level, multipli�s par 32 pixels
         * (Rappel: 32 = taille d'un tile sur la spritesheet)
         * Ainsi le Tile (0, 0) sur la carte a (xPos, yPos) = (0, 0)
         * 		 le Tile (1, 1) sur la carte a (xPos, yPos) = (32, 32)
         *
         * L'origine des coordon�e est donc en haut a gauche et commence a (xOffset, yOffset)>>5: voir level
         */
        xPos -= xOffset;
        yPos -= yOffset;

        for (int y = 0; y < tile.square.getSQUARESIZEy(); y++) {
            int yPixel = yPos + y;


            for (int x = 0; x < tile.square.getSQUARESIZEx(); x++) {
                int xPixel = xPos + x;


                if (xPixel < -tile.square.getSQUARESIZEx() || xPixel >= width || yPixel < 0 || yPixel >= height) break;
                // Pour le bord gauche
                if (xPixel < 0) xPixel = 0;

                screenPixels[xPixel + yPixel * width] = tile.square.getSquarePixels()[x + y * tile.square.getSQUARESIZEx()];

                /*
                 *La liste sera affich�e sur le frame, (width=width du frame) donc xPixel et yPixel sont les coordon�es du pixel sur le frame
                 */

                /*
                 * La taille d'un tile dans la spriteSheet est de 32 pixels
                 * Reprenons l'exemple (commenc� dans level)
                 *
                 *         Le ghost commence au centre de la carte,  initialement (xOffset, yOffset) = (0, 0)
                 * 			Donc l'affichage commence par le premier tile de la carte du level (la methode getTile va chercher le Tile (0, 0) sur le carte du level)
                 * 			==>xPixel et yPixel varient de 0 a 32 et remplissent les 32x32 premiers indices de screenPixels

                 *          - si on appuie sur down ==> la fonction move est appel�e ==> ya etant positif le ghost descend (par ex de 32 pixels)
                 *          yOffset est alors modifi� de +32 pixels ==> getTile pioche les tiles a partir de y (= 32 >> 5) = 1 et
                 *          par exemple pour le premier Tile a etre affich� (1,0): (xPos,yPos)=(32, 0)-(xOffset,yOffset) = (0,0)
                 *          ==>de nouveaux: xPixel et yPixel varient de 0 a 32 et remplissent les 32x32 premiers indices de screenPixels en remplacant les precedents
                 *
                 *          pour le tile (8 , 10) sur la carte de level (xPos,yPos)=(8*32,320)-(xOffset,yOffset) = (8*32,9*32)
                 *          comme pour le premier tile xPixel et yPixel remplissent les meme indices qu'avant le mouvement en remplacent les valeur de couleur precedentes
                 *
                 *          ==>au meme coordonn�es sur le frame sont affich�s les 32 pixels correspondant aux tiles juste en dessous sur la carte du level
                 *
                 *
                 */
            }
        }
    } //END OF renderTile() METHOD


    public void renderEntity(int xPos, int yPos, SheetSquare square2, int size, boolean xMirror, boolean yMirror, int sheetCol) {
        xPos -= xOffset;
        yPos -= yOffset;

        for (int y = 0; y < size; y++) {

            int yPixel = yPos + y;
            int ySheet = y;
            if (yMirror) ySheet = size - 1 - y;

            for (int x = 0; x < size; x++) {

                int xPixel = xPos + x;
                int xSheet = x;
                if (xMirror) xSheet = size - 1 - x;

                if (xPixel < 0 || xPixel >= width || yPixel < 0 || yPixel >= height) break;

                int colour = square2.getSquarePixels()[xSheet + ySheet * size];

                if (colour != sheetCol) {
                    // font noir utilis� poour sur la spritesheet
                    screenPixels[xPixel + yPixel * width] = colour;
                }
            }
        }
    } //END OF renderPlayer METHOD


    public void renderRectangle(int xPos, int yPos, SheetSquare square2, int xsize, int ysize, boolean xMirror, boolean yMirror, int sheetCol) {
        xPos -= xOffset;
        yPos -= yOffset;


        for (int y = 0; y < ysize; y++) {

            int yPixel = yPos + y;
            int ySheet = y;
            if (yMirror) ySheet = ysize - 1 - y;

            for (int x = 0; x < xsize; x++) {

                int xPixel = xPos + x;
                int xSheet = x;
                if (xMirror) xSheet = xsize - 1 - x;

                if (xPixel < 0 || xPixel >= width || yPixel < 0 || yPixel >= height) break;

                int colour = square2.getSquarePixels()[xSheet + ySheet * xsize];

                if (colour != sheetCol) {
                    // font noir utilis� poour sur la spritesheet
                    screenPixels[xPixel + yPixel * width] = colour;
                }
            }
        }
    } //END OF renderPlayer METHOD


    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

    public int[] getScreenPixels() {
        return screenPixels;
    }


}
