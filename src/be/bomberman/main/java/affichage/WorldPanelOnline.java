package affichage;

import bomberman.Bomberman;
import gameobjects.Ghost;
import gameobjects.Player1;
import gameobjects.Player2;
import gameobjects.PlayerBomberman;
import gameobjects.PlayerBomberman2;
import gameobjects.PlayerBomberman3;
import gameobjects.PlayerBomberman4;
import inputs.KeyboardInput;
import levels.Level1;
import levels.Level2;
import net.GameClient;
import net.GameClientTCP;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;


public class WorldPanelOnline extends WorldPanel {

    private final String protocol;

    public WorldPanelOnline(int WIDTH, int HEIGHT, int SCALE, String protocol) {
        super(WIDTH, HEIGHT, SCALE);
        this.width = WIDTH;
        this.height = HEIGHT;
        this.scale = SCALE;
        this.protocol = protocol;

        size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        worldFrame.setMinimumSize(size);
        worldFrame.setMaximumSize(size);
        worldFrame.setPreferredSize(size);


        panel.setLayout(new GridLayout(0, 1, 8, 8));

        button1 = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("/World1.png"));
            Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            button1.setIcon(new ImageIcon(newimg));
        } catch (IOException ex) {
        }
        ListenForButton lForButton = new ListenForButton();
        button1.addActionListener(lForButton);
        button2 = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("/World2.png"));
            Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            button2.setIcon(new ImageIcon(newimg));
        } catch (IOException ex) {
        }
        button2.addActionListener(lForButton);


        panel.add(button1);
        panel.add(button2);
        worldFrame.add(panel);
        worldFrame.setFocusable(true);
        //frame.setResizable(false);

        worldFrame.pack();
        worldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        worldFrame.setLocationRelativeTo(null);
        worldFrame.setVisible(true);

    }

    //*************************************************************************************************

    private class ListenForButton implements ActionListener {

        private Bomberman bomberman;

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button1) {
                worldFrame.dispose();
                bomberman = new Bomberman("level1", width, height);
                Dimension size = new Dimension(width * scale, height * scale);
                bomberman.frame.setMinimumSize(size);
                bomberman.frame.setMaximumSize(size);
                bomberman.frame.setPreferredSize(size);


                bomberman.frame.setFocusable(true);
                //bomberman.frame.setResizable(false);

                BorderLayout border = new BorderLayout();
                bomberman.frame.setLayout(border);
                bomberman.frame.add(bomberman, BorderLayout.CENTER);                   // Centre l'affichage
                bomberman.frame.pack();
                bomberman.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                bomberman.frame.setLocationRelativeTo(null);
                bomberman.frame.setVisible(true);


                bomberman.keys = new KeyboardInput(bomberman);
                bomberman.image = new BufferedImage(Bomberman.WIDTH, Bomberman.HEIGHT, BufferedImage.TYPE_INT_RGB);
                bomberman.pixels = ((DataBufferInt) bomberman.image.getRaster().getDataBuffer()).getData();

                bomberman.screen = new Screen(Bomberman.WIDTH, Bomberman.HEIGHT);
                bomberman.level = new Level1("/Levels/LEVEL1F.png");
                //on recupere le nom du joueur
                bomberman.jouerEnLigne = true;
                bomberman.playerName = JOptionPane.showInputDialog("Quel est votre personnage? (MANO,DanZi)").trim();
                bomberman.ipAdressServer = JOptionPane.showInputDialog("Quel est l'adresse IP du serveur?").trim();
                bomberman.player1 = new Player1(bomberman.level, 2, 2, "MANO", bomberman.keys, bomberman);
                bomberman.player2 = new Player2(bomberman.level, 13, 2, "DanZi", bomberman.keys, bomberman);
                bomberman.ghost = new Ghost(bomberman.level, 8, 8, bomberman.keys);
                bomberman.level.addPlayer(bomberman.player1);
                bomberman.level.addPlayer(bomberman.player2);
                bomberman.level.addPlayer(bomberman.ghost);

                if (bomberman.playerName.equals("MANO")) {

                    bomberman.choixPort = 0;

                    // Conexion au serveur
                    //bomberman.socketClient.start();


                    //on remplie avec les coordonnÃ©es de l'autre joueur, de l'adversaire
                    bomberman.msg = bomberman.playerName + "-" + bomberman.player1.x + "_" + bomberman.player1.y + "_" + "n";
                    //je met player 2 provisionnellement, car je metterai tjrs MANO sur cette machine
                    bomberman.anciennePosX[1] = bomberman.player2.x;
                    bomberman.anciennePosY[1] = bomberman.player2.y;

                    System.out.println("On se trouve a: " + bomberman.anciennePosX + "_" + bomberman.anciennePosY);

                } else if (bomberman.playerName.equals("DanZi")) {

                    bomberman.choixPort = 1;

                    // Conexion au serveur

                    //bomberman.socketClient.start();

                    //on remplie avec les coordonnÃ©es de l'autre joueur, de l'adversaire
                    bomberman.msg = bomberman.playerName + "-" + bomberman.player2.x + "_" + bomberman.player2.y + "_" + "n";
                    //je met player 2 provisionnellement, car je metterai tjrs MANO sur cette machine
                    bomberman.anciennePosX[0] = bomberman.player1.x;
                    bomberman.anciennePosY[0] = bomberman.player1.y;
                    System.out.println("On se trouve a: " + bomberman.anciennePosX + "_" + bomberman.anciennePosY);
                }

                //conexion au serveur
                if (protocol.equals("UDP")) {
                    bomberman.socketClient = new GameClient(bomberman, bomberman.ipAdressServer); //modifier aussi le port
                    bomberman.socketClient.start();
                    bomberman.protocol = protocol;
                    bomberman.start();
                } else if (protocol.equals("TCP")) {
                    bomberman.socketClientTCP = new GameClientTCP(bomberman, bomberman.ipAdressServer, bomberman.portServers[bomberman.choixPort]);
                    bomberman.socketClientTCP.start();
                    //bomberman.socketClientTCP.sendData(bomberman.msg);
                    bomberman.protocol = protocol;
                }
                //fin des declarations


            }

            //si on choisi le monde 2
            if (e.getSource() == button2) {
                worldFrame.dispose();
                bomberman = new Bomberman("level2", width + 128, height);
                Dimension size = new Dimension((width + 128) * scale, height * scale);
                bomberman.frame.setMinimumSize(size);
                bomberman.frame.setMaximumSize(size);
                bomberman.frame.setPreferredSize(size);

                bomberman.frame.setFocusable(true);
                //bomberman.frame.setResizable(false);

                BorderLayout border = new BorderLayout();
                bomberman.frame.setLayout(border);
                bomberman.frame.add(bomberman, BorderLayout.CENTER);                   // Centre l'affichage
                bomberman.frame.pack();
                bomberman.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                bomberman.frame.setLocationRelativeTo(null);
                bomberman.frame.setVisible(true);

                //declarations
                bomberman.keys = new KeyboardInput(bomberman);
                bomberman.image = new BufferedImage(Bomberman.WIDTH, Bomberman.HEIGHT, BufferedImage.TYPE_INT_RGB);
                bomberman.pixels = ((DataBufferInt) bomberman.image.getRaster().getDataBuffer()).getData();

                bomberman.screen = new Screen(Bomberman.WIDTH, Bomberman.HEIGHT);
                bomberman.level = new Level2("/Levels/LEVEL2Fm.png");
                //on recupere le nom du joueur
                bomberman.jouerEnLigne = true;
                bomberman.playerName = JOptionPane.showInputDialog("Quel est votre personnage? (bomberman.Bomberman,Bombergirl,Zelda,Link)").trim();
                bomberman.ipAdressServer = JOptionPane.showInputDialog("Quel est l'adresse IP du serveur?").trim();
                bomberman.player1 = new PlayerBomberman(bomberman.level, 6, 2, "bomberman.Bomberman", bomberman.keys, bomberman);
                bomberman.player2 = new PlayerBomberman2(bomberman.level, 20, 2, "Bombergirl", bomberman.keys, bomberman); //NEW
                bomberman.player3 = new PlayerBomberman3(bomberman.level, 20, 14, "Zelda", bomberman.keys, bomberman);
                bomberman.player4 = new PlayerBomberman4(bomberman.level, 6, 14, "Link", bomberman.keys, bomberman);
                bomberman.level.addPlayer(bomberman.player1);
                bomberman.level.addPlayer(bomberman.player2);
                bomberman.level.addPlayer(bomberman.player3);
                bomberman.level.addPlayer(bomberman.player4);

                if (bomberman.playerName.equals("bomberman.Bomberman")) {

                    bomberman.choixPort = 0;

                    // Conexion au serveur
                    //bomberman.socketClient.start();


                    //on remplie avec les coordonnÃ©es de l'autre joueur, de l'adversaire
                    bomberman.msg = bomberman.playerName + "-" + bomberman.player1.x + "_" + bomberman.player1.y + "_" + "n";
                    //je met player 2 provisionnellement, car je metterai tjrs MANO sur cette machine
                    bomberman.anciennePosX[1] = bomberman.player2.x;
                    bomberman.anciennePosY[1] = bomberman.player2.y;

                    bomberman.anciennePosX[2] = bomberman.player3.x;
                    bomberman.anciennePosY[2] = bomberman.player3.y;

                    bomberman.anciennePosX[3] = bomberman.player4.x;
                    bomberman.anciennePosY[3] = bomberman.player4.y;
                    //System.out.println("On se trouve a: "+bomberman.anciennePosX+"_"+bomberman.anciennePosY);
                    //

                } else if (bomberman.playerName.equals("Bombergirl")) {

                    bomberman.choixPort = 1;

                    // Conexion au serveur

                    //bomberman.socketClient.start();

                    //on remplie avec les coordonnÃ©es de l'autre joueur, de l'adversaire
                    bomberman.msg = bomberman.playerName + "-" + bomberman.player2.x + "_" + bomberman.player2.y + "_" + "n";
                    //je met player 2 provisionnellement, car je metterai tjrs MANO sur cette machine
                    bomberman.anciennePosX[0] = bomberman.player1.x;
                    bomberman.anciennePosY[0] = bomberman.player1.y;

                    bomberman.anciennePosX[2] = bomberman.player3.x;
                    bomberman.anciennePosY[2] = bomberman.player3.y;

                    bomberman.anciennePosX[3] = bomberman.player4.x;
                    bomberman.anciennePosY[3] = bomberman.player4.y;
                    //System.out.println("On se trouve a: "+bomberman.anciennePosX+"_"+bomberman.anciennePosY);
                } else if (bomberman.playerName.equals("Zelda")) {

                    bomberman.choixPort = 2;

                    // Conexion au serveur

                    //bomberman.socketClient.start();

                    //on remplie avec les coordonnÃ©es de l'autre joueur, de l'adversaire
                    bomberman.msg = bomberman.playerName + "-" + bomberman.player3.x + "_" + bomberman.player3.y + "_" + "n";
                    //je met player 2 provisionnellement, car je metterai tjrs MANO sur cette machine
                    bomberman.anciennePosX[0] = bomberman.player1.x;
                    bomberman.anciennePosY[0] = bomberman.player1.y;

                    bomberman.anciennePosX[1] = bomberman.player2.x;
                    bomberman.anciennePosY[1] = bomberman.player2.y;

                    bomberman.anciennePosX[3] = bomberman.player4.x;
                    bomberman.anciennePosY[3] = bomberman.player4.y;


                    System.out.println("On se trouve a: " + bomberman.anciennePosX + "_" + bomberman.anciennePosY);
                } else if (bomberman.playerName.equals("Link")) {

                    bomberman.choixPort = 3;

                    // Conexion au serveur

                    //bomberman.socketClient.start();

                    //on remplie avec les coordonnÃ©es de l'autre joueur, de l'adversaire
                    bomberman.msg = bomberman.playerName + "-" + bomberman.player4.x + "_" + bomberman.player4.y + "_" + "n";
                    //je met player 2 provisionnellement, car je metterai tjrs MANO sur cette machine
                    bomberman.anciennePosX[0] = bomberman.player1.x;
                    bomberman.anciennePosY[0] = bomberman.player1.y;

                    bomberman.anciennePosX[1] = bomberman.player2.x;
                    bomberman.anciennePosY[1] = bomberman.player2.y;

                    bomberman.anciennePosX[2] = bomberman.player3.x;
                    bomberman.anciennePosY[2] = bomberman.player3.y;


                    //System.out.println("On se trouve a: "+bomberman.anciennePosX+"_"+bomberman.anciennePosY);
                }

                //conexion au serveur
                if (protocol.equals("UDP")) {
                    System.out.println("on est en UDP");
                    bomberman.socketClient = new GameClient(bomberman, bomberman.ipAdressServer); //modifier aussi le port
                    bomberman.socketClient.start();
                    bomberman.protocol = protocol;
                    bomberman.start();
                } else if (protocol.equals("TCP")) {
                    System.out.println("on est en TCP");
                    bomberman.socketClientTCP = new GameClientTCP(bomberman, bomberman.ipAdressServer, bomberman.portServers[bomberman.choixPort]);
                    bomberman.socketClientTCP.start();
                    //bomberman.socketClientTCP.sendData(bomberman.msg);
                    bomberman.protocol = protocol;
                }
                //fin des declarations

            }


        }


    }

    //*************************************************************************************************


}	