package affichage;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UDPTCPPanel {

    private final JFrame startFrame = new JFrame();
    private final Dimension size;
    private final JPanel panel = new JPanel();
    private final JButton button1;
    private final JButton button2;
    private final int width;
    private final int height;
    private final int scale;

    public UDPTCPPanel(int WIDTH, int HEIGHT, int SCALE) {
        this.width = WIDTH;
        this.height = HEIGHT;
        this.scale = SCALE;

        size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        startFrame.setMinimumSize(size);
        startFrame.setMaximumSize(size);
        startFrame.setPreferredSize(size);
        startFrame.setTitle("Choisissez Un Protocole");

        panel.setLayout(new GridLayout(0, 1, 8, 8));

        button1 = new JButton("TCP");
        button1.setToolTipText("Risque de lag");
        ListenForButton lForButton = new ListenForButton();
        button1.addActionListener(lForButton);
        button2 = new JButton("UDP");
        button1.setFont(new Font("Courier", Font.PLAIN, scale * 40));
        button2.setToolTipText("Risque de perte de paquets");
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

        private WorldPanel frame;

        public void actionPerformed(ActionEvent e) {

            //TCP
            if (e.getSource() == button1) {
                startFrame.dispose();
                //JOptionPane.showMessageDialog(button1, this, "Ask Daniel", 0);
                WorldPanelOnline wpo = new WorldPanelOnline(width, height, scale, "TCP");

            }

            //UDP
            if (e.getSource() == button2) {
                startFrame.dispose();
                WorldPanelOnline wpo = new WorldPanelOnline(width, height, scale, "UDP");

            }

        }
    }

    //*************************************************************************************************

}
	