package inputs;


import bomberman.Bomberman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardInput implements KeyListener {

    private final boolean[] keys = new boolean[600];  //assez grand pour prendre toutes les touches du clavier
    public boolean up, down, left, right, A, E; // joueur 2
    public boolean Q, Z, D, S, space, B;      // joueur 1
    public boolean T, F, G, H, R, Y;               // Ghost
    public boolean J, K, L, O, I, U;
    public boolean ENTER, backspace;
    private int key;
    public Bomberman bomberman;

    public KeyboardInput(Bomberman bomberman) {
        bomberman.requestFocusInWindow(); // permet de ne pas devoir cliquer sur l'�cran
        bomberman.addKeyListener(this);
        this.bomberman = bomberman;
    }


    public void update() {
        up = keys[KeyEvent.VK_UP]; // KeyEvent.VK_UP retourne l'id de la touche up
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        space = keys[KeyEvent.VK_SPACE];
        ENTER = keys[KeyEvent.VK_ENTER];
        backspace = keys[KeyEvent.VK_BACK_SPACE];
        Z = keys[KeyEvent.VK_Z];
        S = keys[KeyEvent.VK_S];
        Q = keys[KeyEvent.VK_Q];
        D = keys[KeyEvent.VK_D];
        A = keys[KeyEvent.VK_A];
        T = keys[KeyEvent.VK_T];
        F = keys[KeyEvent.VK_F];
        G = keys[KeyEvent.VK_G];
        H = keys[KeyEvent.VK_H];
        E = keys[KeyEvent.VK_E];
        B = keys[KeyEvent.VK_B];
        U = keys[KeyEvent.VK_U];
        J = keys[KeyEvent.VK_J];
        K = keys[KeyEvent.VK_K];
        I = keys[KeyEvent.VK_I];
        O = keys[KeyEvent.VK_O];
        L = keys[KeyEvent.VK_L];
        R = keys[KeyEvent.VK_R];
        Y = keys[KeyEvent.VK_Y];
    }


    public void keyPressed(KeyEvent e) {
        /*
         * Met le boolean dans keys correspondant � la touche appuy�e � true
         *
         */
        key = e.getKeyCode();
        keys[key] = true;        //e.getkeycode retourne l'id de la touche e
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }

        if (key == KeyEvent.VK_BACK_SPACE) {
            bomberman.togglePause();
        }

        if (key == KeyEvent.VK_ENTER) {
            bomberman.togglePause();
        }
    }


    public void keyReleased(KeyEvent e) {
        key = e.getKeyCode();
        keys[key] = false;
    }


    public void keyTyped(KeyEvent e) {
    }
}