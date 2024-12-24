package affichage;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartPanel extends BasePanel {
    private JButton button1;
    private JButton button2;

    public StartPanel(int WIDTH, int HEIGHT, int SCALE) {
        super(WIDTH, HEIGHT, SCALE);
        setFrameTitle();
    }

    @Override
    protected ActionListener createActionListener() {
        return new ListenForButton();
    }

    @Override
    protected void setFrameTitle() {
        mainFrame.setTitle("WELCOME");
    }

    @Override
    protected void configureButtons(ActionListener actionListener) {
        button1 = new JButton("PLAY ONLINE");
        button1.setToolTipText("Jouer en ligne avec un ami");
        button1.addActionListener(actionListener);
        button2 = new JButton("PLAY OFFLINE");
        button1.setFont(new Font("Courier", Font.PLAIN, scale * 40));
        button2.setToolTipText("Jouer avec un ami sur le meme clavier");
        button2.addActionListener(actionListener);
        button2.setFont(new Font("Courier", Font.PLAIN, scale * 40));

        panel.add(button1);
        panel.add(button2);
    }

    private class ListenForButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button1) {
                mainFrame.dispose();
                // Не удалять, нужно для создания окна
                new UDPTCPPanel(width, height, scale);
            } else if (e.getSource() == button2) {
                mainFrame.dispose();
                // Не удалять, нужно для создания окна
                new WorldPanelOffline(width, height, scale);
            }
        }
    }
}

