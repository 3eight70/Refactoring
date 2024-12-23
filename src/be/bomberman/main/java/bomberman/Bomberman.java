package bomberman;//PlayerBomberman, reseau en commentaire !!!


import affichage.Screen;
import affichage.StartPanel;
import affichage.font.SomeFont;
import affichage.font.SomeFont2;
import bomberman.pause.GameOnStateGame;
import bomberman.pause.GamePauseState;
import bomberman.pause.MusicOnStateGame;
import bomberman.pause.MusicPauseState;
import gameobjects.Ghost;
import gameobjects.Mob;
import gameobjects.Player;
import gameobjects.Player1;
import gameobjects.Player2;
import gameobjects.PlayerBomberman;
import inputs.KeyboardInput;
import inputs.MouseInput;
import levels.Level;
import levels.Level1;
import levels.Level2;
import net.GameClient;
import net.GameClientTCP;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Bomberman extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    public static int WIDTH;
    public static int HEIGHT;
    public static int SCALE = 1;
    public static final String NAME = "bomberman.Bomberman";
    /*
     * Defini les dimensions du JFrame (en pixels * SCALE)
     */

    public int timer = 0;
    private GamePauseState gamePauseState = new GameOnStateGame();
    private MusicPauseState musicPauseState = new MusicOnStateGame();
    public int timePressedPauseGame = 0, timePressedPauseMusic = 0;
    public boolean runningMusic = false;
    public boolean running = false;
    public static boolean musicIsPaused = false;

    /*
     * Boolean indiquant si le jeu a commenc� ou est fini.
     * Est utilis� dans la boucle principale pour savoir quand elle doit s'ex�cuter
     */


    public BufferedImage image;
    public int[] pixels;
    /*
     *  On cr�e une BufferedImage qui va etre dessin�e avec drawgraphics
     *
     *  (Raster = data structure which represents a rectangular grid(array) of pixels)
     *  On construit ici un espace pixelis� pour la BufferedImage image associ� au tableau pixel[] qui
     *  va acceuillir les pixels de la classe screen
     */

    public JFrame frame;
    //public JPanel panel;
    private Thread thread;
    private StartPanel frame2;
    public Screen screen;
    public KeyboardInput keys;
    public MouseInput mouse;
    public Level level;
    public Mob ghost;

    public Player player1, player2, player3, player4;


    //j'ai ajout�� des publiques
    public GameClient socketClient;
    public GameClientTCP socketClientTCP;
    public int[] portServers = {5000, 5001, 5002, 5003}; //port 5000 pour MANO, pour 5001 pour DanZi
    public int choixPort;
    public String ipAdressServer;
    public String playerName = "";
    public Boolean jouerEnLigne = false;
    public String msg = "";

    public Integer[] anciennePosX = new Integer[4]; //0 pour bomberman, 1 pour bombergirl, 2 pour Zelda, 3 pour Link OU BIEN 0 pour mano, 1 ppour DanZi
    public Integer[] anciennePosY = new Integer[4];

    public String protocol;
    public Boolean ilFautPlacerUneBombe = false;
    public Integer[] coordBombe = new Integer[2];

    //JOptionPane.showInputDialog(this, "Enter the name of player 1: ");

    public Bomberman(String theLevel, int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        frame = new JFrame(NAME);
        //panel = new JPanel();
        //socketClient = new GameClient(this,"localhost"); //modifier aussi le port
        //socketClientTCP = new GameClientTCP(this,"localhost",5000);

        //initBomberman(theLevel);
    }

    private void initBomberman(String theLevel) {


        //this.addMouseListener(new MouseInput(this));
        keys = new KeyboardInput(this);
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        mouse = new MouseInput(this);

        if (theLevel == "level1") {
            screen = new Screen(WIDTH, HEIGHT);
            level = new Level1("/Levels/LEVEL1F.png");
            player1 = new Player2(level, 2, 2, "MANO", keys, null);
            player2 = new Player1(level, 13, 2, "DanZi", keys, null);
            ghost = new Ghost(level, 8, 8, keys);
            level.addPlayer(player1);
            level.addPlayer(player2);
            level.addPlayer(ghost);
        } else if (theLevel == "level2") {
            screen = new Screen(WIDTH, HEIGHT);
            level = new Level2("/Levels/LEVEL2Fm.png");

            player1 = new PlayerBomberman(level, 6, 2, "Rosette", keys, null);
            //player2 = new PlayerBomberman3(level, 20, 2, "Miyu", keys, null); //NEW
            //player3 = new PlayerBomberman4(level, 20, 14, "MANO", keys, null);
            //player4 = new PlayerBomberman2(level, 6, 14, "DanZi", keys, null);
            level.addPlayer(player1);
            //level.addPlayer(player2);
            //level.addPlayer(player3);
            //level.addPlayer(player4);
        }
    }

    public synchronized void start() {
        /*
         * Synchronization: empeche les threads d'interferer entre eux?
         * bomberman.start() dans PanelStuff
         * Cette fonction lance le jeu
         */

        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();                                 //fini le thread (kills)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void run() {

        /*
         * Cette fonction est la boucle principale du bomberman
         * Elle appelle la methode update() 60 fois par seconde
         * Le nobre de FPS depend de l'ordinateur
         */

        /*
         *  long startTime = System.nanoTime();
         *  long estimatedTime = System.nanoTime() - startTime;
         *
         *  ==> nanoTime sert a mesurer le temps entre deux instances
         */

        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60D; // 60 updates par seconde
        int updates = 0;
        int frames = 0;

        long lastTimer = System.currentTimeMillis(); // Returns the current time in milliseconds.
        double delta = 0; // nombre de nanosecondes pass�es /nsPerTick ==> nbr de updates

        //init();

        while (running) {

            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick; // calcule le nombre de updates
            lastTime = now;

            boolean shouldRender = true;

            while (delta >= 1) { // pour chaque tick appelle la methode update()
                //System.out.println(pausestate);
                updates++;
                delta--;
                shouldRender = true;
                update();
            }

            if (shouldRender) {
                frames++;
                render();
            }
            if (System.currentTimeMillis() - lastTimer > 1000) {    //1000 = 1s
                lastTimer += 1000;
                System.out.println("FPS: " + frames + ", ticks per second: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    } //FIN de la METHODE run()


    public void update() {
        mouse.update();
        if (gamePauseState instanceof GameOnStateGame) {
            timer++;
            keys.update();
            level.updateEntities();
        }
        if (musicPauseState instanceof MusicOnStateGame) {
            if (!runningMusic) {
                //Sound.backgroundMusic.loop();
                runningMusic = true;
            }
        } else {
            runningMusic = false;
        }

        //if (!bomberman.Bomberman.musicIsPaused && timer == 1) Sound.gamestart.play();
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            // calcul en avance les trois prochaines images
            this.requestFocus(); // plac� dans KeyboardInput aussi
            return;
        }

        screen.clear(); // inutile


        if (level.theLevel == "level1") {
            int xOffset = ghost.x - screen.getWidth() / 2;
            int yOffset = ghost.y - screen.getHeight() / 2;
            level.renderTile(screen, xOffset, yOffset);
            level.renderEntities(screen, this);
        } else if (level.theLevel == "level2") {
            level.renderTile(screen, 0, 0);
            level.renderEntities(screen, this);


            if (player1.getLife() == 5) SomeFont2.renderW("" + player1.getLife(), screen, 98, 21, 1);
            else SomeFont.renderW("" + player1.getLife(), screen, 98, 21, 1);
            if (player2.getLife() == 5) SomeFont2.renderW("" + player2.getLife(), screen, 98, 120, 1);
            else SomeFont.renderW("" + player2.getLife(), screen, 98, 120, 1);
            if (player3.getLife() == 5) SomeFont2.renderW("" + player3.getLife(), screen, 98, 217, 1);
            else SomeFont.renderW("" + player3.getLife(), screen, 98, 217, 1);
            if (player4.getLife() == 5) SomeFont2.renderW("" + player4.getLife(), screen, 98, 315, 1);
            else SomeFont.renderW("" + player4.getLife(), screen, 98, 315, 1);

            if (player1.getBomb() == 9) SomeFont2.renderW("" + player1.getBomb(), screen, 98, 41, 1);
            else SomeFont.renderW("" + player1.getBomb(), screen, 98, 41, 1);
            if (player2.getBomb() == 9) SomeFont2.renderW("" + player2.getBomb(), screen, 98, 140, 1);
            else SomeFont.renderW("" + player2.getBomb(), screen, 98, 140, 1);
            if (player3.getBomb() == 9) SomeFont2.renderW("" + player3.getBomb(), screen, 98, 237, 1);
            else SomeFont.renderW("" + player3.getBomb(), screen, 98, 237, 1);
            if (player4.getBomb() == 9) SomeFont2.renderW("" + player4.getBomb(), screen, 98, 335, 1);
            else SomeFont.renderW("" + player4.getBomb(), screen, 98, 335, 1);

            if (player1.bonusCarried() == "firePower") SomeFont2.renderW("SPIKE", screen, 15, 82, 1);
            if (player2.bonusCarried() == "firePower") SomeFont2.renderW("SPIKE", screen, 15, 181, 1);
            if (player3.bonusCarried() == "firePower") SomeFont2.renderW("SPIKE", screen, 15, 278, 1);
            if (player4.bonusCarried() == "firePower") SomeFont2.renderW("SPIKE", screen, 15, 376, 1);

            if (player1.getRange() == 3) SomeFont2.renderW("" + player1.getRange(), screen, 98, 61, 1);
            else SomeFont.renderW("" + player1.getRange(), screen, 98, 61, 1);
            if (player2.getRange() == 3) SomeFont2.renderW("" + player2.getRange(), screen, 98, 160, 1);
            else SomeFont.renderW("" + player2.getRange(), screen, 98, 160, 1);
            if (player3.getRange() == 3) SomeFont2.renderW("" + player3.getRange(), screen, 98, 257, 1);
            else SomeFont.renderW("" + player3.getRange(), screen, 98, 257, 1);
            if (player4.getRange() == 3) SomeFont2.renderW("" + player4.getRange(), screen, 98, 355, 1);
            else SomeFont.renderW("" + player4.getRange(), screen, 98, 355, 1);

            if (player1.bonusCarried() == "fetaBonus")
                SomeFont2.renderW("" + player1.getSpeed(), screen, 98, 81, 1); //pas de 20
            else SomeFont.renderW("" + player1.getSpeed(), screen, 98, 81, 1); //pas de 20
            if (player2.bonusCarried() == "fetaBonus")
                SomeFont2.renderW("" + player2.getSpeed(), screen, 98, 180, 1); //pas de 20
            else SomeFont.renderW("" + player2.getSpeed(), screen, 98, 180, 1); //pas de 20
            if (player3.bonusCarried() == "fetaBonus")
                SomeFont2.renderW("" + player3.getSpeed(), screen, 98, 277, 1); //pas de 20
            else SomeFont.renderW("" + player3.getSpeed(), screen, 98, 277, 1); //pas de 20
            if (player4.bonusCarried() == "fetaBonus")
                SomeFont2.renderW("" + player4.getSpeed(), screen, 98, 375, 1); //pas de 20
            else SomeFont.renderW("" + player4.getSpeed(), screen, 98, 375, 1); //pas de 20

            int seconds = (timer) / 60;
            String time = "" + (seconds);
            if (seconds < 10) time = "00" + seconds;
            if (seconds >= 10 && seconds < 100) time = "0" + (seconds);

            SomeFont.renderW(time, screen, 42, 467, 3);
        }


        String msg = "GAME OVER!";

        if (level.theLevel == "level1") {
            if (player1.getShouldRenderFont() || player2.getShouldRenderFont()) {
                player1.x = 2 << 5;
                player2.x = 13 << 5;
                player1.y = 2 << 5;
                player2.y = 2 << 5;
                SomeFont.renderT(msg, screen, screen.getXOffset() + screen.getWidth() / 2 - (msg.length() / 2) * 16, screen.getYOffset() + screen.getHeight() / 2, 3);
            }
        } else if (level.theLevel == "level2") {
            if (player1.getShouldRenderFont() && player2.getShouldRenderFont() && player3.getShouldRenderFont()) {
                SomeFont.renderW("Link won !", screen, screen.getXOffset() + screen.getWidth() / 2 - (msg.length() / 2) * 16 + 15, 48 + screen.getYOffset() + screen.getHeight() / 2, 3);
            }

            if (player1.getShouldRenderFont() && player2.getShouldRenderFont() && player4.getShouldRenderFont()) {
                SomeFont.renderW("Zelda won !", screen, screen.getXOffset() + screen.getWidth() / 2 - (msg.length() / 2) * 16 + 18, 48 + screen.getYOffset() + screen.getHeight() / 2, 3);
            }

            if (player1.getShouldRenderFont() && player4.getShouldRenderFont() && player3.getShouldRenderFont()) {
                SomeFont.renderW("Bombergirl won !", screen, screen.getXOffset() + screen.getWidth() / 2 - (msg.length() / 2) * 16 - 18, 48 + screen.getYOffset() + screen.getHeight() / 2, 3);
            }

            if (player2.getShouldRenderFont() && player4.getShouldRenderFont() && player3.getShouldRenderFont()) {
                SomeFont.renderW("bomberman.Bomberman won !", screen, screen.getXOffset() + screen.getWidth() / 2 - (msg.length() / 2) * 16 + 15, 48 + screen.getYOffset() + screen.getHeight() / 2, 3);
            }
            if (player2.getShouldRenderFont() && player4.getShouldRenderFont() && player3.getShouldRenderFont() && player1.getShouldRenderFont()) {
                //stop();
            }
        }


        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.getScreenPixels()[i];
        }


        Graphics g = bs.getDrawGraphics();
        //Commence l'affichage


        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        //g.setFont(new Font("Verdana",0, 50));
        //g.drawString("X: "+" Y: ", 950,950);

        //g.setColor(Color.WHITE);
        //g.fillRect(0, 0, getWidth(), getHeight());


        // Fini l'affichage
        g.dispose();     // lib�re la memoire quand on a fini avec les graphiques
        bs.show();      // Montre le contenu du buffer c'est a dire les pixels

    }


    public static void main(String[] args) {
        StartPanel startFrame = new StartPanel(608, 544, SCALE);
        //JOptionPane.showMessageDialog(startFrame, "You burned one life");
    }

    // modifi� la position de GAME OVER
    // faire clignoter les joueur dans le monde 1
    // eventuellement remmetre la position de joueurs au debut quand ils meurent
    // implementer les bonus dans le monde deux


    public GamePauseState getGamePauseState() {
        return gamePauseState;
    }

    public void setGamePauseState(GamePauseState pauseState) {
        this.gamePauseState = pauseState;
    }

    public MusicPauseState getMusicPauseState() {
        return musicPauseState;
    }

    public void setMusicPauseState(MusicPauseState musicPauseState) {
        this.musicPauseState = musicPauseState;
    }

    public void toggleGamePause() {
        gamePauseState.toggleGamePause(this);
    }

    public void toggleMusicPause() {
        musicPauseState.toggleMusicPause(this);
    }

    public void togglePause() {
        gamePauseState.toggleGamePause(this);
        musicPauseState.toggleMusicPause(this);
    }
}
