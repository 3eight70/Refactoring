package be.bomberman.main.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import be.bomberman.main.Bomberman;
import be.bomberman.main.audio.Sound;

public class MouseInput implements MouseListener {
	
	private int xMouse = 0, yMouse = 0;
	private Bomberman bomberman;
	private int scale = bomberman.SCALE;
	
	public MouseInput(Bomberman bomberman){
		this.bomberman = bomberman;
		bomberman.requestFocusInWindow(); // permet de ne pas devoir cliquer sur l'���cran
		bomberman.addMouseListener(this);
	}
	
	public void update(){
		//System.out.println(xMouse +" "+ yMouse);
		//System.out.println("updating");

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	    xMouse = e.getX();
	    yMouse = e.getY();
	    // bouton pause
	    if (scale == 3){
	    	  if (xMouse >= 15 && xMouse <=368 && yMouse >= 1412 && yMouse<= 1488) {
	    		  if (bomberman.pausestate == 1) {
	    			  Sound.pauseOn.play();
	    			  bomberman.pausestate = 3;	
	    		  }
	    		  else if (bomberman.pausestate == 2){
	    			  bomberman.pausestate = 4;		    			  
	    		  }
	    		  else if (bomberman.pausestate == 3){
	    			  Sound.pauseOff.play();
	    			  bomberman.pausestate = 1;		    			  
	    		  }
	    		  else if (bomberman.pausestate == 4){
	    			  bomberman.pausestate = 2;		    			  
	    		  }
	  	    	bomberman.timePressedPauseGame++;
	  			if (bomberman.timePressedPauseGame % 2 == 1){
	  				bomberman.isPaused = true ;
	  				System.out.println("paused");
	  			}
	  			if (bomberman.timePressedPauseGame % 2 == 0){
	  				bomberman.isPaused = false ;
	  				System.out.println("play");
	  			}	  			
	  	    }
	    }
	    else if (scale == 1){
	    	if (xMouse >= 5 && xMouse <= 121 && yMouse >= 467 && yMouse<= 495)  {
	    		  if (bomberman.pausestate == 1) {
	    			  Sound.pauseOn.play();
	    			  bomberman.pausestate = 3;	
	    		  }
	    		  else if (bomberman.pausestate == 2){
	    			  bomberman.pausestate = 4;		    			  
	    		  }
	    		  else if (bomberman.pausestate == 3){
	    			  Sound.pauseOff.play();
	    			  bomberman.pausestate = 1;		    			  
	    		  }
	    		  else if (bomberman.pausestate == 4){
	    			  bomberman.pausestate = 2;		    			  
	    		  }
		  	   	bomberman.timePressedPauseGame++;
		  		if (bomberman.timePressedPauseGame % 2 == 1){
		  			bomberman.isPaused = true ;
		  			System.out.println("paused");
		  		}
		  		if (bomberman.timePressedPauseGame % 2 == 0){
		  			bomberman.isPaused = false ;
		  			System.out.println("play");
		  		}	  			
		  	}
	    }
	    else if (scale == 2){
	     	if (xMouse >= 12 && xMouse <= 236 && yMouse >=921 && yMouse<= 964) {
	    		  if (bomberman.pausestate == 1) {
	    			  Sound.pauseOn.play();
	    			  bomberman.pausestate = 3;	
	    		  }
	    		  else if (bomberman.pausestate == 2){
	    			  bomberman.pausestate = 4;		    			  
	    		  }
	    		  else if (bomberman.pausestate == 3){
	    			  Sound.pauseOff.play();
	    			  bomberman.pausestate = 1;		    			  
	    		  }
	    		  else if (bomberman.pausestate == 4){
	    			  bomberman.pausestate = 2;		    			  
	    		  }
		  	   	bomberman.timePressedPauseGame++;
		  		if (bomberman.timePressedPauseGame % 2 == 1){
		  			bomberman.isPaused = true ;
		  			System.out.println("paused");
		  		}
		  		if (bomberman.timePressedPauseGame % 2 == 0){
		  			bomberman.isPaused = false ;
		  			System.out.println("play");
		  		}	  			
		  	}
	    }
	    
	    
//////////////////////////////////////////////////////
	    // bouton pauseMusic
	    if (scale == 3){
	    	  if (xMouse >= 15 && xMouse <=368 && yMouse >= 1508 && yMouse<= 1561) {
	    		  if (bomberman.pausestate == 1) { //on
	    			  bomberman.pausestate = 2;	//off
	    		  }
	    		  else if (bomberman.pausestate == 3){ //pauseon
	    			  bomberman.pausestate = 4;		//pauseoff    			  
	    		  }
	    		  else if (bomberman.pausestate == 2) { //off
	    			  bomberman.pausestate = 1;	//on
	    		  }
	    		  else if (bomberman.pausestate == 4) { //pauseoff
	    			  bomberman.pausestate = 3;		//pauseon   			  
	    		  }
	    		  bomberman.timePressedPauseMusic++;
	  			if (bomberman.timePressedPauseMusic % 2 == 1){
	  				bomberman.musicIsPaused = true ;
	  				System.out.println(" music paused");
	  			}
	  			if (bomberman.timePressedPauseMusic % 2 == 0){
	  				bomberman.musicIsPaused = false ;
	  				System.out.println("music playing");
	  			}	  			
	  	    }
	    }
	    else if (scale == 1){
	    	if (xMouse >= 19 && xMouse <= 40 && yMouse >= 504 && yMouse<= 517) {
  			  System.out.println(bomberman.pausestate );
	    		  if (bomberman.pausestate == 1) { //on
	    			  bomberman.pausestate = 2;	//off
	    			  System.out.println(bomberman.pausestate );
	    		  }
	    		  else if (bomberman.pausestate == 3){ //pauseon
	    			  bomberman.pausestate = 4;		//pauseoff    			  
	    		  }
	    		  else if (bomberman.pausestate == 2) { //off
	    			  bomberman.pausestate = 1;	//on
	    		  }
	    		  else if (bomberman.pausestate == 4) { //pauseoff
	    			  bomberman.pausestate = 3;		//pauseon   			  
	    		  }
	    		bomberman.timePressedPauseMusic++;
		  		if (bomberman.timePressedPauseMusic % 2 == 1){
		  			bomberman.musicIsPaused = true ;
		  			System.out.println("paused music");
		  		}
		  		if (bomberman.timePressedPauseMusic % 2 == 0){
		  			bomberman.musicIsPaused = false ;
		  			System.out.println(" music playing");
		  		}	  			
		  	}
	    }
	    else if (scale == 2){
	     	if (xMouse >= 12 && xMouse <= 236 && yMouse >=980 && yMouse<= 1019) {
	    		  if (bomberman.pausestate == 1) { //on
	    			  bomberman.pausestate = 2;	//off
	    		  }
	    		  else if (bomberman.pausestate == 3){ //pauseon
	    			  bomberman.pausestate = 4;		//pauseoff    			  
	    		  }
	    		  else if (bomberman.pausestate == 2) { //off
	    			  bomberman.pausestate = 1;	//on
	    		  }
	    		  else if (bomberman.pausestate == 4) { //pauseoff
	    			  bomberman.pausestate = 3;		//pauseon   			  
	    		  }
	     		bomberman.timePressedPauseMusic++;
		  		if (bomberman.timePressedPauseMusic % 2 == 1){
		  			bomberman.musicIsPaused = true ;
		  			System.out.println("paused music");
		  		}
		  		if (bomberman.timePressedPauseMusic % 2 == 0){
		  			bomberman.musicIsPaused = false ;
		  			System.out.println("music playing");
		  		}	  			
		  	}
	    }
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	    // bouton pause
	    /*if (scale == 3){
	    	if (xMouse >= 15 && xMouse <=368 && yMouse >= 1412 && yMouse<= 1488) {
	    		bomberman.pausestate = 2;			
	  	    }
	    }
	    else if (scale == 1){
	    	if (xMouse >= 5 && xMouse <= 121 && yMouse >= 467 && yMouse<= 495) {
	    		bomberman.pausestate = 2;	
		  	}
	    }
	    else if (scale == 2){
	     	if (xMouse >= 12 && xMouse <= 236 && yMouse >=921 && yMouse<= 964) {
	     		bomberman.pausestate = 2;	
		  	}
	    }*/
	  
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//bomberman.pausestate = 1;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		xMouse = e.getX();
	    yMouse = e.getY();
	    System.out.println(xMouse + " " + yMouse);
	    /*if (scale == 3){
	    	if (xMouse >= 15 && xMouse <=368 && yMouse >= 1412 && yMouse<= 1488) {
	    		bomberman.pausestate = 2;			
	  	    }
	    }
	    else if (scale == 1){
	    	if (xMouse >= 5 && xMouse <= 121 && yMouse >= 467 && yMouse<= 495) {
	    		bomberman.pausestate = 2;	
		  	}
	    }
	    else if (scale == 2){
	     	if (xMouse >= 12 && xMouse <= 236 && yMouse >=921 && yMouse<= 964) {
	     		bomberman.pausestate = 2;	
		  	}
	    }*/
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	   /*if (scale == 3){
	    	if (xMouse >= 15 && xMouse <=368 && yMouse >= 1412 && yMouse<= 1488) {
	    		bomberman.pausestate = 1;			
	  	    }
	    }
	    else if (scale == 1){
	    	if (xMouse >= 5 && xMouse <= 121 && yMouse >= 467 && yMouse<= 495) {
	    		bomberman.pausestate = 1;	
		  	}
	    }
	    else if (scale == 2){
	     	if (xMouse >= 12 && xMouse <= 236 && yMouse >=921 && yMouse<= 964) {
	     		bomberman.pausestate = 1;	
		  	}
	    }*/
	}

}
