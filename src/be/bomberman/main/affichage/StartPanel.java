package be.bomberman.main.affichage ;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import be.bomberman.main.Bomberman;
import be.bomberman.main.audio.Sound;
import be.bomberman.main.gameobjects.PlayerBomberman;
import be.bomberman.main.gameobjects.PlayerBomberman2;
import be.bomberman.main.gameobjects.PlayerBomberman3;
import be.bomberman.main.gameobjects.PlayerBomberman4;
import be.bomberman.main.inputs.KeyboardInput;
import be.bomberman.main.inputs.MouseInput;
import be.bomberman.main.levels.Level2;


public class StartPanel {

	private JFrame startFrame = new JFrame();
	private Dimension size;
	private BorderLayout border;
	private JPanel panel = new JPanel();
	private JButton button1, button2, button3 ;
	private int width, height, scale;
	
	public StartPanel(int WIDTH, int HEIGHT, int SCALE){
		this.width = WIDTH;
		this.height = HEIGHT;
		this.scale = SCALE;
		
		size = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
		startFrame.setMinimumSize(size);
		startFrame.setMaximumSize(size);
		startFrame.setPreferredSize(size);
		startFrame.setTitle("WELCOME");
		
		panel.setLayout(new GridLayout(0, 1, 8, 8));
		
		button1 = new JButton("PLAY ONLINE");
		button1.setToolTipText("Jouer en ligne avec un ami");
		ListenForButton lForButton = new ListenForButton();
		button1.addActionListener(lForButton);
		button2 = new  JButton("PLAY OFFLINE");
		button1.setFont(new Font("Courier", Font.PLAIN, scale*40));
		button2.setToolTipText("Jouer avec un ami sur le meme clavier");		
		button2.addActionListener(lForButton);
		button2.setFont(new Font("Courier", Font.PLAIN, scale*40));		
		
		
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

	private class ListenForButton implements ActionListener{
				
		public void actionPerformed(ActionEvent e) {
			Sound.pressStart.play();
			if (e.getSource() == button1){				
				 startFrame.dispose();
				 UDPTCPPanel frame = new UDPTCPPanel(width, height, scale);
				//JOptionPane.showMessageDialog(button1, this, "Ask Daniel", 0);
			}
			if (e.getSource() == button2){				
				startFrame.dispose();
				WorldPanel frame = new WorldPanel(width, height, scale);
			}
			
		}
		
	}
	
	//*************************************************************************************************
	
	
}	

