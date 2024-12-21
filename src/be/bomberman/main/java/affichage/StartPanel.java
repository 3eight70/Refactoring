package affichage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartPanel {

    private final JFrame startFrame = new JFrame();
    private final Dimension size;
    private BorderLayout border;
    private final JPanel panel = new JPanel();
    private final JButton button1;
    private final JButton button2;
    private JButton button3;
    private final int width;
    private final int height;
    private final int scale;

    public StartPanel(int WIDTH, int HEIGHT, int SCALE) {
        this.width = WIDTH;
        this.height = HEIGHT;
        this.scale = SCALE;

        size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        startFrame.setMinimumSize(size);
        startFrame.setMaximumSize(size);
        startFrame.setPreferredSize(size);
        startFrame.setTitle("WELCOME");

        panel.setLayout(new GridLayout(0, 1, 8, 8));

        button1 = new JButton("PLAY ONLINE");
        button1.setToolTipText("Jouer en ligne avec un ami");
        ListenForButton lForButton = new ListenForButton();
        button1.addActionListener(lForButton);
        button2 = new JButton("PLAY OFFLINE");
        button1.setFont(new Font("Courier", Font.PLAIN, scale * 40));
        button2.setToolTipText("Jouer avec un ami sur le meme clavier");
        button2.addActionListener(lForButton);
        button2.setFont(new Font("Courier", Font.PLAIN, scale * 40));


        panel.add(button1);
        panel.add(button2);
        startFrame.add(panel);
        startFrame.setFocusable(true);
        //frame.setResizable(false);

        startFrame.pack();
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setLocationRelativeTo(null);
        startFrame.setVisible(true);

    }

    //*************************************************************************************************

    private class ListenForButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button1) {
                startFrame.dispose();
                UDPTCPPanel frame = new UDPTCPPanel(width, height, scale);
                //JOptionPane.showMessageDialog(button1, this, "Ask Daniel", 0);
            }
            if (e.getSource() == button2) {
                startFrame.dispose();
                WorldPanel frame = new WorldPanel(width, height, scale);
            }

        }

    }

    //*************************************************************************************************


}	

