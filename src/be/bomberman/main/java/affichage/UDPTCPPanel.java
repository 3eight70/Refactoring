package affichage;


import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UDPTCPPanel extends BasePanel {
    private JButton button1;
    private JButton button2;

    public UDPTCPPanel(int WIDTH, int HEIGHT, int SCALE) {
        super(WIDTH, HEIGHT, SCALE);
    }

    @Override
    protected ActionListener createActionListener() {
        return new ListenForButton();
    }

    @Override
    protected void setFrameTitle() {
        mainFrame.setTitle("Choisissez Un Protocole");
    }

    @Override
    protected void configureButtons(ActionListener actionListener) {
        button1 = new JButton("TCP");
        button1.setToolTipText("Risque de lag");
        button1.addActionListener(actionListener);
        button2 = new JButton("UDP");
        button1.setFont(new Font("Courier", Font.PLAIN, scale * 40));
        button2.setToolTipText("Risque de perte de paquets");
        button2.addActionListener(actionListener);
        button2.setFont(new Font("Courier", Font.PLAIN, scale * 40));
    }


    private class ListenForButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button1) {
                mainFrame.dispose();
                // Не удалять, нужно для создания окна
                new WorldPanelOnline(width, height, scale, "TCP");
            } else if (e.getSource() == button2) {
                mainFrame.dispose();
                // Не удалять, нужно для создания окна
                new WorldPanelOnline(width, height, scale, "UDP");
            }
        }
    }
}
	