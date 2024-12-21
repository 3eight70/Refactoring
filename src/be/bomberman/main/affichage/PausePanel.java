package be.bomberman.main.affichage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import be.bomberman.main.Bomberman;
import be.bomberman.main.audio.Sound;
import be.bomberman.main.gameobjects.Ghost;
import be.bomberman.main.gameobjects.Player1;
import be.bomberman.main.gameobjects.Player2;
import be.bomberman.main.gameobjects.PlayerBomberman;
import be.bomberman.main.gameobjects.PlayerBomberman2;
import be.bomberman.main.gameobjects.PlayerBomberman3;
import be.bomberman.main.gameobjects.PlayerBomberman4;
import be.bomberman.main.inputs.KeyboardInput;
import be.bomberman.main.levels.Level1;
import be.bomberman.main.levels.Level2;

public class PausePanel extends JPanel {
// INUTILE !!!!!!!!!!!!!!!!!	
	private Dimension size;
	private JButton pauseGameButton, pauseSoundButton;
	private int width, height, scale;
	private Bomberman bomberman;
	
	public PausePanel(int WIDTH, int HEIGHT, int SCALE, Bomberman bomberman){
		this.width = WIDTH;
		this.height = HEIGHT;
		this.scale = SCALE;
		this.bomberman = bomberman;
		
		JPanel pausePanel = new JPanel();
		size = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
		pausePanel.setMinimumSize(size);
		pausePanel.setMaximumSize(size);
		pausePanel.setPreferredSize(size);
		pausePanel.setLayout(new GridLayout(1,0,8,8));
		pauseGameButton = new  JButton("PauseGame");
		pauseSoundButton = new JButton("StopSound");
		pausePanel.add(pauseGameButton);
		pausePanel.add(pauseSoundButton);	
	}
	
	
	//*************************************************************************************************

	private class ListenForButton implements ActionListener{
			private int timePressedPauseGame = 0;
			private int timePressedPauseSound = 0;
		
		public void actionPerformed(ActionEvent e) {			
			if (e.getSource() == pauseGameButton){
				timePressedPauseGame++;
				if (timePressedPauseGame % 2 == 1){
					bomberman.isPaused = true ;
				}
			}
			if (e.getSource() == pauseSoundButton){
				timePressedPauseSound++;
				if (timePressedPauseSound % 2 == 1);
					// ToDO
				}
			}
		}	
	}
	
	//*************************************************************************************************
	
	


