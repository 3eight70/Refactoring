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
import inputs.MouseInput;
import levels.Level1;
import levels.Level2;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;


public class WorldPanel {

    protected JFrame worldFrame = new JFrame("Choose a World");
    protected Dimension size;
    protected JPanel panel = new JPanel();
    protected JButton button1;
    protected JButton button2;
    protected int width;
    protected int height;
    protected int scale;


    public WorldPanel(int WIDTH, int HEIGHT, int SCALE) {
        this.width = WIDTH;
        this.height = HEIGHT;
        this.scale = SCALE;

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
                //bomberman.frame.add(bomberman.panel);
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

                bomberman.mouse = new MouseInput(bomberman); //
                bomberman.keys = new KeyboardInput(bomberman);
                bomberman.image = new BufferedImage(Bomberman.WIDTH, Bomberman.HEIGHT, BufferedImage.TYPE_INT_RGB);
                bomberman.pixels = ((DataBufferInt) bomberman.image.getRaster().getDataBuffer()).getData();

                bomberman.screen = new Screen(Bomberman.WIDTH, Bomberman.HEIGHT);
                bomberman.level = new Level1("/Levels/LEVEL1F.png");
                bomberman.player1 = new Player2(bomberman.level, 2, 2, "MANO", bomberman.keys, bomberman);
                bomberman.player2 = new Player1(bomberman.level, 13, 2, "DanZi", bomberman.keys, bomberman);
                bomberman.ghost = new Ghost(bomberman.level, 8, 8, bomberman.keys);
                bomberman.level.addPlayer(bomberman.player1);
                bomberman.level.addPlayer(bomberman.player2);
                bomberman.level.addPlayer(bomberman.ghost);

                bomberman.start();
            }
            if (e.getSource() == button2) {
                worldFrame.dispose();
                bomberman = new Bomberman("level2", width + 128, height);
                //pausePanel = new PausePanel(200,200,scale,bomberman);
				
				/*
				bomberman.panel.add(bomberman);
				bomberman.frame.add(bomberman.panel,BorderLayout.CENTER);
				bomberman.frame.add(pausePanel, BorderLayout.WEST);
				*/
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

                bomberman.mouse = new MouseInput(bomberman); //
                bomberman.keys = new KeyboardInput(bomberman);
                bomberman.image = new BufferedImage(Bomberman.WIDTH, Bomberman.HEIGHT, BufferedImage.TYPE_INT_RGB);
                bomberman.pixels = ((DataBufferInt) bomberman.image.getRaster().getDataBuffer()).getData();

                bomberman.screen = new Screen(Bomberman.WIDTH, Bomberman.HEIGHT);
                bomberman.level = new Level2("/Levels/LEVEL2Fm.png");

                bomberman.player1 = new PlayerBomberman(bomberman.level, 6, 2, "bomberman.Bomberman", bomberman.keys, bomberman);
                bomberman.player2 = new PlayerBomberman2(bomberman.level, 20, 14, "Bombergirl", bomberman.keys, bomberman); //NEW
                bomberman.player3 = new PlayerBomberman3(bomberman.level, 20, 2, "Zelda", bomberman.keys, bomberman);
                bomberman.player4 = new PlayerBomberman4(bomberman.level, 6, 14, "Link", bomberman.keys, bomberman);
                bomberman.level.addPlayer(bomberman.player1);
                bomberman.level.addPlayer(bomberman.player2);
                bomberman.level.addPlayer(bomberman.player3);
                bomberman.level.addPlayer(bomberman.player4);
                bomberman.start();
            }


        }


    }

    //*************************************************************************************************


}	