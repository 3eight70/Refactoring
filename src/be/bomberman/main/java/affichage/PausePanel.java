package affichage;

import bomberman.Bomberman;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PausePanel extends JPanel {
    // INUTILE !!!!!!!!!!!!!!!!!
    private final Dimension size;
    private final JButton pauseGameButton;
    private final JButton pauseSoundButton;
    private final int width;
    private final int height;
    private final int scale;
    private final Bomberman bomberman;

    public PausePanel(int WIDTH, int HEIGHT, int SCALE, Bomberman bomberman) {
        this.width = WIDTH;
        this.height = HEIGHT;
        this.scale = SCALE;
        this.bomberman = bomberman;

        JPanel pausePanel = new JPanel();
        size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        pausePanel.setMinimumSize(size);
        pausePanel.setMaximumSize(size);
        pausePanel.setPreferredSize(size);
        pausePanel.setLayout(new GridLayout(1, 0, 8, 8));
        pauseGameButton = new JButton("PauseGame");
        pauseSoundButton = new JButton("StopSound");
        pausePanel.add(pauseGameButton);
        pausePanel.add(pauseSoundButton);
    }


    //*************************************************************************************************

    private class ListenForButton implements ActionListener {
        private int timePressedPauseGame = 0;
        private int timePressedPauseSound = 0;

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == pauseGameButton) {
                timePressedPauseGame++;
                if (timePressedPauseGame % 2 == 1) {
                    bomberman.isPaused = true;
                }
            }
            if (e.getSource() == pauseSoundButton) {
                timePressedPauseSound++;
                if (timePressedPauseSound % 2 == 1) ;
                // ToDO
            }
        }
    }
}

//*************************************************************************************************
	
	


