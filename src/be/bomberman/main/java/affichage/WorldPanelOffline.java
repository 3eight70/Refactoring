package affichage;

import gameobjects.Ghost;
import gameobjects.Player1;
import gameobjects.Player2;
import gameobjects.PlayerBomberman;
import gameobjects.PlayerBomberman2;
import gameobjects.PlayerBomberman3;
import gameobjects.PlayerBomberman4;

import java.awt.event.ActionListener;

public class WorldPanelOffline extends WorldPanel {
    public WorldPanelOffline(int WIDTH, int HEIGHT, int SCALE) {
        super(WIDTH, HEIGHT, SCALE);
    }

    @Override
    protected ActionListener createActionListener() {
        return new ButtonListener();
    }

    private class ButtonListener extends ListenForButton {

        @Override
        void configurePlayersForSecondLevel() {
            bomberman.player1 = new PlayerBomberman(bomberman.level, 6, 2, "bomberman.Bomberman", bomberman.keys, bomberman);
            bomberman.player2 = new PlayerBomberman2(bomberman.level, 20, 14, "Bombergirl", bomberman.keys, bomberman); //NEW
            bomberman.player3 = new PlayerBomberman3(bomberman.level, 20, 2, "Zelda", bomberman.keys, bomberman);
            bomberman.player4 = new PlayerBomberman4(bomberman.level, 6, 14, "Link", bomberman.keys, bomberman);
            bomberman.level.addPlayer(bomberman.player1);
            bomberman.level.addPlayer(bomberman.player2);
            bomberman.level.addPlayer(bomberman.player3);
            bomberman.level.addPlayer(bomberman.player4);
        }

        @Override
        void configurePlayersForFirstLevel() {
            bomberman.player1 = new Player2(bomberman.level, 2, 2, "MANO", bomberman.keys, bomberman);
            bomberman.player2 = new Player1(bomberman.level, 13, 2, "DanZi", bomberman.keys, bomberman);
            bomberman.ghost = new Ghost(bomberman.level, 8, 8, bomberman.keys);
            bomberman.level.addPlayer(bomberman.player1);
            bomberman.level.addPlayer(bomberman.player2);
            bomberman.level.addPlayer(bomberman.ghost);
        }
    }
}	