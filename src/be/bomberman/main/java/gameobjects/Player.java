package gameobjects;


import affichage.Screen;
import bomberman.Bomberman;
import gameobjects.bonus.BombBonus;
import gameobjects.bonus.FetaBonus;
import gameobjects.bonus.FirePower;
import gameobjects.bonus.LifeBonus;
import gameobjects.bonus.RangeBonus;
import gameobjects.bonus.UsedBonus;
import inputs.KeyboardInput;
import levels.Level;
import levels.tiles.Tile;

public class Player extends Mob {

    protected String name;
    protected KeyboardInput input;
    protected boolean isDead = false;
    protected int bombRate; // pour les bombes
    protected int useRate; // pour firePower
    protected int notifies = 0; //lifesLost
    protected boolean shouldRenderFont = false;
    protected boolean carryBonus = false;
    protected boolean canplacebomb = true;
    protected boolean canTeleport = true;
    protected String bonusCarried;
    protected int bonusTimer = 0;
    protected int teleportTimer = 1500;
    protected int xT = 0;
    protected int yT = 0;
    protected int onBomb; //
    protected int beforeBonusCollisionDetection = 0; // detection de collision
    protected int immunisation = 300;
    protected int bombposedtimer = 211;
    protected int bombposedtimer2 = 211;
    protected int bombposedtimer3 = 211;
    protected int bombposedtimer4 = 211;
    protected int bombposedtimer5 = 211;
    protected int bombposedtimer6 = 211;
    protected int bombposedtimer7 = 211;
    protected int bombposedtimer8 = 211;
    protected int bombposedtimer9 = 211;
    //protected boolean burned = false;
    protected int range, maxbomb, bombposed; //les 3 bonus
    protected int speed = 1;

    protected String selected;
    protected int[] position = new int[2];
    protected Bomberman bomberman;

    public Player(Level level, int x, int y, String name, KeyboardInput input, Bomberman bomberman) {
        super(level, x, y, 1, 1);
        this.name = name;
        this.input = input;
        this.bombRate = BasicBomb.bombRate;
        this.useRate = FirePower.useRate;
        life = 3;
        bombposed = 0;
        maxbomb = 1;
        if (level.theLevel == "level1") {
            range = 2;
        } else if (level.theLevel == "level2") {
            range = 1;
        }

        this.position[0] = x;
        this.position[1] = y;
        this.bomberman = bomberman;

    }


    public void move(int xa, int ya) {
        /*
         *
         * entr�es: le signe de xa et ya indique quel touche (up down right left) est enfonc�e
         * renvoi la direction et fait bouger le personnage tant qu'il n'y a pas collision.
         * Cette methode est appell�e dans update() donc si speed = 1 et qu'on garde la touche enfonc�e
         * pendant une seconde on avance de 60 pixels
         *
         * Si xa =  1 on avance de 1 ... a droite
         * Si xa =  0 on avance pas en x
         * Si xa = -1 on avance de 1 ... a gauche
         * Si ya =  1 on avance de 1 vers le bas
         * Si ya =  0 on avance pas en y
         * Si ya = -1 on avance de 1 vers le haut
         *
         */

        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
            return;
        }

        if (!collision(xa, ya) && (!collisionTeleport(xa, ya))) {
            if (ya < 0) {
                movingDir = 0;
            }
            if (ya > 0) {
                movingDir = 1;
            }
            if (xa < 0) {
                movingDir = 2;
            }
            if (xa > 0) {
                movingDir = 3;
            }
            x += xa * speed;
            y += ya * speed;
        }

        if (collisionTeleport(xa, ya) && teleportTimer > 1500) {

            if (y < 304) {
                if (x < 402) {
                    x += 16;
                    System.out.println("- 16x1");
                } else {
                    x -= 16;
                    System.out.println("+ 16x1");
                }
                y += 385;
            } else {
                if (x < 402) {
                    x += 16;
                    System.out.println("- 16x2");
                } else {
                    x -= 16;
                    System.out.println("+ 16x2");
                }
                y -= 385;
            }
            teleportTimer = 0;
            xT = x;
            yT = y;
            //maxteleport
        }

    }


    @Override
    public boolean collision(int xa, int ya) {
        int xMin = 0;
        int xMax = 0; //a modif ?
        int yMin = 0; //21 pixels de la tete aux pieds
        int yMax = 0; //1 pixels }
        if (level.theLevel == "level1") {
            xMin = 3;
            xMax = 28;
            yMin = 10;
            yMax = 31;
        } else {
            if (speed == 1) {
                xMin = 7;
                xMax = 24; //a modif ?
                yMin = 9; //21 pixels de la tete aux pieds
                yMax = 29; //1 pixels }
            } else if (speed == 2) {
                xMin = 5;
                xMax = 26; //a modif ?
                yMin = 8; //21 pixels de la tete aux pieds
                yMax = 35; //1 pixels }
            }
        }
        for (int x = xMin; x < xMax; x++) {
            if (isSolidTile(xa, ya, x, yMin)) return true;
        }
        for (int x = xMin; x < xMax; x++) {
            if (isSolidTile(xa, ya, x, yMax)) return true;
        }
        for (int y = yMin; y < yMax; y++) {
            if (isSolidTile(xa, ya, xMin, y)) return true;
        }
        for (int y = yMin; y < yMax; y++) {
            if (isSolidTile(xa, ya, xMax, y)) return true;
        }

        return false;
    }

    public boolean collisionTeleport(int xa, int ya) {
        int xMin = 0;
        int xMax = 0; //a modif ?
        int yMin = 0; //21 pixels de la tete aux pieds
        int yMax = 0; //1 pixels }
        if (level.theLevel == "level1") {
            xMin = 3;
            xMax = 28;
            yMin = 10;
            yMax = 31;
        } else {

            xMin = 15;
            xMax = 17; //a modif ?
            yMin = 5; //21 pixels de la tete aux pieds
            yMax = 15; //1 pixels }
        }
        for (int x = xMin; x < xMax; x++) {
            if (isTeleportTile(xa, ya, x, yMin)) return true;
        }
        for (int x = xMin; x < xMax; x++) {
            if (isTeleportTile(xa, ya, x, yMax)) return true;
        }
        for (int y = yMin; y < yMax; y++) {
            if (isTeleportTile(xa, ya, xMin, y)) return true;
        }
        for (int y = yMin; y < yMax; y++) {
            if (isTeleportTile(xa, ya, xMax, y)) return true;
        }

        return false;
    }


    public boolean haveBomb() {
        for (int i = 0; i < Level.bombs.size(); i++) {
            if (Level.bombs.get(i).getYourBomb(x, y)) {
                return true;
            }
        }
        return false;
    }

    public boolean usedBonusCollision(int x, int y, int xBonus, int yBonus) {
        // x y position d'un pixel du joueur
        int[] bonusCoord = getCoordinates(xBonus, yBonus);
        return (getCoordinates(x, y)[0] == bonusCoord[0]) && (getCoordinates(x, y)[1] == bonusCoord[1]);
    }


    public boolean touchUsedBonus(int xBonus, int yBonus) {
        int xMin = 9;
        int xMax = 21;
        int yMin = 20; //pour que la tete ne touche pas
        int yMax = 29;

        for (int x = xMin; x < xMax; x++) {
            if (usedBonusCollision(this.x + x, this.y + yMin, xBonus, yBonus)) {
                return true;

            }
            if (usedBonusCollision(this.x + x, this.y + yMax, xBonus, yBonus)) return true;
        }
        for (int y = yMin; y < yMax; y++) {
            if (usedBonusCollision(this.x + xMin, this.y + y, xBonus, yBonus)) return true;
            if (usedBonusCollision(this.x + xMax, this.y + y, xBonus, yBonus)) return true;
        }

        return false;
    }

    public void bonusCollisionDetection() {
        for (int i = 0; i < Level.usedBonusses.size(); i++) {
            int x = Level.usedBonusses.get(i).x;
            int y = Level.usedBonusses.get(i).y;
            if (touchUsedBonus(x, y)) {
                Level.usedBonusses.get(i).doUsedBonusActionOnCollision(this);
            }
        }
    }


    @Override
    public void update() {
        if (bombRate > 0) bombRate--;
        if (useRate > 0) useRate--;
        if (bonusTimer > 0) bonusTimer--;
        if (beforeBonusCollisionDetection > 0) beforeBonusCollisionDetection--;
        dieByDef();
        takeBonus();
        haveBomb();


        if (teleportTimer < 300) Tile.tileTimer = 0;
        // Tile.

        if (beforeBonusCollisionDetection == 0) bonusCollisionDetection();

        if (animation < 999999) animation++; // augmente de 60 toute les secondes
        else animation = 0;
        if (immunisation < 999999) immunisation++; //timer immunisation
        else immunisation = 0;

        teleportTimer++;
        bombposedtimer++;
        bombposedtimer2++;
        bombposedtimer3++;
        bombposedtimer4++;
        if (bombposedtimer == 210 || bombposedtimer2 == 210 || bombposedtimer3 == 210 || bombposedtimer4 == 210 ||
                bombposedtimer5 == 210 || bombposedtimer6 == 210 || bombposedtimer7 == 210 || bombposedtimer8 == 210 ||
                bombposedtimer9 == 210) bombposed--;
        isMaxBomb();
    }


    public void dieByDef() {
        /*
         * Quand le joueur se fait toucher par une deflagration immunisation passe a 0 et il perd une vie
         * Si il se refait toucher dans les 5 secondes sont immunisation ne passe pas a 0 et il ne perd pas
         * de vie
         *
         */
        for (int i = 0; i < Level.deflagrations.size(); i++) {
            if (Level.deflagrations.get(i).burnedByCollision(x, y)) {
                notifies++;
                if (life < 1) isDead = true;
                notifyUser(); // attention appel� 60 x par seconde
                if (immunisation > 300) immunisation = 0;
                if (immunisation == 0) {
                    if (life > 0) life--;
                    if (life > 0) {
                        if (name == "Link" && !Bomberman.musicIsPaused)
                            if (!Bomberman.musicIsPaused && name != "Link") System.out.println("ouch");
                    }
                    if (life == 0) {
                        if (name == "Link" && (!Bomberman.musicIsPaused))
                            if (!Bomberman.musicIsPaused && name != "Link") System.out.println("lastlife");
                    }
                    break;
                }
            }
        }
    }


    public void dieByBonus() {
        if (immunisation > 300) immunisation = 0;
        if (immunisation == 1 && life > 0) {
            life--;
            if (!Bomberman.musicIsPaused) System.out.println("aie");
        }
        notifies++;
        if (life < 1) isDead = true;
        notifyUser(); // attention appel� 60 x par seconde
    }


    public void takeBonus() {
        for (int i = 0; i < Level.bonusses.size(); i++) {
            if (Level.bonusses.get(i).getYourBonus(x, y)) {
                this.carryBonus = true;
                Level.bonusses.get(i).setRemoved(true);
                System.out.println(name);
                if (Level.bonusses.get(i).getType() == "firePower") {
                    if (!Bomberman.musicIsPaused) System.out.println("getitem");
                    bonusCarried = "firePower";
                    bonusTimer = 900;
                    System.out.println("got FirePower");
                }
                if (Level.bonusses.get(i).getType() == "fetaBonus") {
                    if (!Bomberman.musicIsPaused) System.out.println("getitem");
                    bonusCarried = "fetaBonus";
                    //bonusTimer = 900;
                    System.out.println("got Feta");
                }
                if (Level.bonusses.get(i).getType() == "rangeBonus") {
                    if (!Bomberman.musicIsPaused) System.out.println("getitem");
                    bonusCarried = "rangeBonus";
                    //bonusTimer = 900;
                    System.out.println("got rangeBonus");
                }
                if (Level.bonusses.get(i).getType() == "lifeBonus") {
                    if (!Bomberman.musicIsPaused) {
                        if (name == "Link") System.out.println("linkBonus");
                        else System.out.println("bonusLife");
                    }
                    bonusCarried = "lifeBonus";
                    //bonusTimer = 900;
                }
                if (Level.bonusses.get(i).getType() == "bombBonus") {
                    if (!Bomberman.musicIsPaused) System.out.println("getitem");
                    bonusCarried = "bombBonus";
                    //bonusTimer = 900;
                }
            }
        }
    }


    public void notifyUser() {
        if (isDead) {
            shouldRenderFont = true;
        }
    }

    protected void isMaxBomb() {
        canplacebomb = bombposed != maxbomb;
    }


    protected void useBomb() {
        bombposed++;
        if (bombposedtimer >= 210) bombposedtimer = 0;
        else {
            if (bombposedtimer2 >= 210) bombposedtimer2 = 0;
            else {
                if (bombposedtimer3 >= 210) bombposedtimer3 = 0;
                else {
                    if (bombposedtimer4 >= 210) bombposedtimer4 = 0;
                    else {
                        if (bombposedtimer5 >= 210) bombposedtimer5 = 0;
                        else {
                            if (bombposedtimer6 >= 210) bombposedtimer6 = 0;
                            else {
                                if (bombposedtimer7 >= 210) bombposedtimer7 = 0;
                                else {
                                    if (bombposedtimer8 >= 210) bombposedtimer8 = 0;
                                    else {
                                        if (bombposedtimer9 >= 210) bombposedtimer9 = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    protected void useFireBonus() {
        Level.usedBonusses.add(new FirePower(level, this.x + 16, this.y + 16)); // pass bonus carried sino rajoute le meme
        this.useRate = FirePower.useRate;
        this.beforeBonusCollisionDetection = 120;
        carryBonus = false;
        if (level.theLevel == "level2") {
            if (!Bomberman.musicIsPaused) System.out.println("firePower");
            bonusCarried = "";
        }
    }


    protected void useFetaBonus() {
        UsedBonus fetaBonus = new FetaBonus(level, this.x + 16, this.y + 16);
        Level.usedBonusses.add(fetaBonus);
        fetaBonus.doUsedBonusAction(this);
        carryBonus = false;
        if (level.theLevel == "level2") bonusCarried = "";
    }

    protected void useRangeBonus() {
        UsedBonus rangeBonus = new RangeBonus(level, this.x + 16, this.y + 16);
        Level.usedBonusses.add(rangeBonus);
        rangeBonus.doUsedBonusAction(this);
        carryBonus = false;
        bonusCarried = "";
    }

    protected void useLifeBonus() {
        UsedBonus lifeBonus = new LifeBonus(level, this.x + 16, this.y + 16);
        Level.usedBonusses.add(lifeBonus);
        lifeBonus.doUsedBonusAction(this);
        carryBonus = false;
        bonusCarried = "";
    }

    protected void useBombBonus() {
        UsedBonus bombBonus = new BombBonus(level, this.x + 16, this.y + 16);
        Level.usedBonusses.add(bombBonus);
        bombBonus.doUsedBonusAction(this);
        carryBonus = false;
        bonusCarried = "";
    }

    @Override
    public void remove() {
        removed = true;
    }


    public int getMovingDir() {
        return movingDir;
    }


    @Override
    public void render(Screen screen) {
    }

    public boolean getShouldRenderFont() {
        return shouldRenderFont;
    }


    public int getLife() {
        return life;
    }

    public int getBomb() {
        return maxbomb;
    }

    public int getRange() {
        return range;
    }

    public int getSpeed() {
        return speed;
    }

    public String bonusCarried() {
        return bonusCarried;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setBomb(int maxbomb) {
        this.maxbomb = maxbomb;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
