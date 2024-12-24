package affichage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public abstract class BasePanel {
    protected JFrame mainFrame = new JFrame();
    protected Dimension size;
    protected JPanel panel = new JPanel();
    protected int width;
    protected int height;
    protected int scale;

    public BasePanel(int WIDTH, int HEIGHT, int SCALE) {
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

    protected abstract void setFrameTitle();

    protected abstract void configureButtons(ActionListener actionListener);
}
