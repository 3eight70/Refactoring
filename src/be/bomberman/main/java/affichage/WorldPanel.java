package affichage;

import bomberman.Bomberman;
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

public abstract class WorldPanel  {
    protected JFrame mainFrame = new JFrame();
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
        mainFrame.setMinimumSize(size);
        mainFrame.setMaximumSize(size);
        mainFrame.setPreferredSize(size);
        setFrameTitle();

        panel.setLayout(new GridLayout(0, 1, 8, 8));

        ActionListener actionListener = createActionListener();
        configureButtons(actionListener);

        configureMainFrame();
    }

    private void configureMainFrame() {
        mainFrame.add(panel);
        mainFrame.setFocusable(true);
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    protected abstract ActionListener createActionListener();

    protected void setFrameTitle() {
        mainFrame.setTitle("Choose a World");
    }

    protected void configureButtons(ActionListener actionListener) {
        button1 = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("/World1.png"));
            Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            button1.setIcon(new ImageIcon(newimg));
        } catch (IOException ex) {
        }
        button1.addActionListener(actionListener);
        button2 = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("/World2.png"));
            Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            button2.setIcon(new ImageIcon(newimg));
        } catch (IOException ex) {
        }
        button2.addActionListener(actionListener);

        panel.add(button1);
        panel.add(button2);
    }

    protected abstract class ListenForButton implements ActionListener {
        protected Bomberman bomberman;

        public void actionPerformed(ActionEvent e) {
            mainFrame.dispose();

            if (e.getSource() == button1) {
                initializeFirstLevel();
            } else if (e.getSource() == button2) {
                initializeSecondLevel();
            }

            bomberman.start();
        }

        protected void initializeFirstLevel() {
            bomberman = new Bomberman("level1", width, height);
            configureGame();

            bomberman.level = new Level1("/Levels/LEVEL1F.png");
            configurePlayersForFirstLevel();
        }

        protected void initializeSecondLevel() {
            bomberman = new Bomberman("level2", width + 128, height);
            configureGame();

            bomberman.level = new Level2("/Levels/LEVEL2Fm.png");
            configurePlayersForSecondLevel();
        }

        protected void configureGame() {
            Dimension size = new Dimension((width + 128) * scale, height * scale);
            bomberman.frame.setMinimumSize(size);
            bomberman.frame.setMaximumSize(size);
            bomberman.frame.setPreferredSize(size);

            bomberman.frame.setFocusable(true);

            BorderLayout border = new BorderLayout();
            bomberman.frame.setLayout(border);
            bomberman.frame.add(bomberman, BorderLayout.CENTER);
            bomberman.frame.pack();
            bomberman.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            bomberman.frame.setLocationRelativeTo(null);
            bomberman.frame.setVisible(true);

            bomberman.mouse = new MouseInput(bomberman);
            bomberman.keys = new KeyboardInput(bomberman);
            bomberman.image = new BufferedImage(Bomberman.WIDTH, Bomberman.HEIGHT, BufferedImage.TYPE_INT_RGB);
            bomberman.pixels = ((DataBufferInt) bomberman.image.getRaster().getDataBuffer()).getData();
            bomberman.screen = new Screen(Bomberman.WIDTH, Bomberman.HEIGHT);
        }

        abstract void configurePlayersForFirstLevel();

        abstract void configurePlayersForSecondLevel();
    }
}
