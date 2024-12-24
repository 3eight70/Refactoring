package affichage;

import gameobjects.Ghost;
import gameobjects.Player1;
import gameobjects.Player2;
import gameobjects.PlayerBomberman;
import gameobjects.PlayerBomberman2;
import gameobjects.PlayerBomberman3;
import gameobjects.PlayerBomberman4;
import net.GameClient;
import net.GameClientTCP;

import javax.swing.JOptionPane;
import java.awt.event.ActionListener;


public class WorldPanelOnline extends WorldPanel {

    private final String protocol;

    public WorldPanelOnline(int WIDTH, int HEIGHT, int SCALE, String protocol) {
        super(WIDTH, HEIGHT, SCALE);
        this.protocol = protocol;
    }

    @Override
    protected ActionListener createActionListener() {
        return new ButtonListener();
    }

    private class ButtonListener extends ListenForButton {

        private void configureHelloPanel(String message) {
            bomberman.jouerEnLigne = true;
            bomberman.playerName = JOptionPane.showInputDialog(message).trim();
            bomberman.ipAdressServer = JOptionPane.showInputDialog("Quel est l'adresse IP du serveur?").trim();
        }

        @Override
        void configurePlayersForFirstLevel() {
            configureHelloPanel("Quel est votre personnage? (MANO,DanZi)");
            bomberman.player1 = new Player1(bomberman.level, 2, 2, "MANO", bomberman.keys, bomberman);
            bomberman.player2 = new Player2(bomberman.level, 13, 2, "DanZi", bomberman.keys, bomberman);
            bomberman.ghost = new Ghost(bomberman.level, 8, 8, bomberman.keys);
            bomberman.level.addPlayer(bomberman.player1);
            bomberman.level.addPlayer(bomberman.player2);
            bomberman.level.addPlayer(bomberman.ghost);

            configurePlayerPositionByName();

            createGameConnection();
        }

        @Override
        void configurePlayersForSecondLevel() {
            configureHelloPanel("Quel est votre personnage? (bomberman.Bomberman,Bombergirl,Zelda,Link)");
            bomberman.player1 = new PlayerBomberman(bomberman.level, 6, 2, "bomberman.Bomberman", bomberman.keys, bomberman);
            bomberman.player2 = new PlayerBomberman2(bomberman.level, 20, 2, "Bombergirl", bomberman.keys, bomberman); //NEW
            bomberman.player3 = new PlayerBomberman3(bomberman.level, 20, 14, "Zelda", bomberman.keys, bomberman);
            bomberman.player4 = new PlayerBomberman4(bomberman.level, 6, 14, "Link", bomberman.keys, bomberman);
            bomberman.level.addPlayer(bomberman.player1);
            bomberman.level.addPlayer(bomberman.player2);
            bomberman.level.addPlayer(bomberman.player3);
            bomberman.level.addPlayer(bomberman.player4);

            configurePlayerPositionByName();

            createGameConnection();
        }

        private void createGameConnection() {
            if (protocol.equals("UDP")) {
                bomberman.socketClient = new GameClient(bomberman, bomberman.ipAdressServer);
                bomberman.socketClient.start();
                bomberman.protocol = protocol;
                bomberman.start();
            } else if (protocol.equals("TCP")) {
                bomberman.socketClientTCP = new GameClientTCP(bomberman, bomberman.ipAdressServer, bomberman.portServers[bomberman.choixPort]);
                bomberman.socketClientTCP.start();
                bomberman.protocol = protocol;
            }
        }

        private void configurePlayerPositionByName() {
            switch (bomberman.playerName) {
                case "MANO" -> {
                    bomberman.choixPort = 0;

                    bomberman.msg = bomberman.playerName + "-" + bomberman.player1.x + "_" + bomberman.player1.y + "_" + "n";
                    bomberman.anciennePosX[1] = bomberman.player2.x;
                    bomberman.anciennePosY[1] = bomberman.player2.y;
                }
                case "DanZi" -> {
                    bomberman.choixPort = 1;
                    bomberman.msg = bomberman.playerName + "-" + bomberman.player2.x + "_" + bomberman.player2.y + "_" + "n";
                    bomberman.anciennePosX[0] = bomberman.player1.x;
                    bomberman.anciennePosY[0] = bomberman.player1.y;
                }
                case "bomberman.Bomberman" -> {

                    bomberman.choixPort = 0;

                    bomberman.msg = bomberman.playerName + "-" + bomberman.player1.x + "_" + bomberman.player1.y + "_" + "n";
                    bomberman.anciennePosX[1] = bomberman.player2.x;
                    bomberman.anciennePosY[1] = bomberman.player2.y;

                    bomberman.anciennePosX[2] = bomberman.player3.x;
                    bomberman.anciennePosY[2] = bomberman.player3.y;

                    bomberman.anciennePosX[3] = bomberman.player4.x;
                    bomberman.anciennePosY[3] = bomberman.player4.y;
                }
                case "Bombergirl" -> {

                    bomberman.choixPort = 1;

                    bomberman.msg = bomberman.playerName + "-" + bomberman.player2.x + "_" + bomberman.player2.y + "_" + "n";
                    bomberman.anciennePosX[0] = bomberman.player1.x;
                    bomberman.anciennePosY[0] = bomberman.player1.y;

                    bomberman.anciennePosX[2] = bomberman.player3.x;
                    bomberman.anciennePosY[2] = bomberman.player3.y;

                    bomberman.anciennePosX[3] = bomberman.player4.x;
                    bomberman.anciennePosY[3] = bomberman.player4.y;
                }
                case "Zelda" -> {

                    bomberman.choixPort = 2;

                    bomberman.msg = bomberman.playerName + "-" + bomberman.player3.x + "_" + bomberman.player3.y + "_" + "n";
                    bomberman.anciennePosX[0] = bomberman.player1.x;
                    bomberman.anciennePosY[0] = bomberman.player1.y;

                    bomberman.anciennePosX[1] = bomberman.player2.x;
                    bomberman.anciennePosY[1] = bomberman.player2.y;

                    bomberman.anciennePosX[3] = bomberman.player4.x;
                    bomberman.anciennePosY[3] = bomberman.player4.y;
                }
                case "Link" -> {

                    bomberman.choixPort = 3;

                    bomberman.msg = bomberman.playerName + "-" + bomberman.player4.x + "_" + bomberman.player4.y + "_" + "n";
                    bomberman.anciennePosX[0] = bomberman.player1.x;
                    bomberman.anciennePosY[0] = bomberman.player1.y;

                    bomberman.anciennePosX[1] = bomberman.player2.x;
                    bomberman.anciennePosY[1] = bomberman.player2.y;

                    bomberman.anciennePosX[2] = bomberman.player3.x;
                    bomberman.anciennePosY[2] = bomberman.player3.y;
                }
            }

            System.out.println("On se trouve a: " + bomberman.anciennePosX + "_" + bomberman.anciennePosY);
        }
    }

    //*************************************************************************************************


}	