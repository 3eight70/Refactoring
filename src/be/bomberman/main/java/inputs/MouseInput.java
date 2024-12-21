package inputs;


import bomberman.Bomberman;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    private int xMouse = 0, yMouse = 0;
    private Bomberman bomberman;
    private final int scale = Bomberman.SCALE;

    public MouseInput(Bomberman bomberman) {
        this.bomberman = bomberman;
        bomberman.requestFocusInWindow(); // permet de ne pas devoir cliquer sur l'���cran
        bomberman.addMouseListener(this);
    }

    public void update() {
        //System.out.println(xMouse +" "+ yMouse);
        //System.out.println("updating");

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        xMouse = e.getX();
        yMouse = e.getY();

        if (isPauseButtonClicked(xMouse, yMouse, scale)) {
            bomberman.togglePause();
        }

        if (isMusicPauseButtonClicked(xMouse, yMouse, scale)) {
            bomberman.toggleMusicPause();
        }
    }


    private boolean isPauseButtonClicked(int x, int y, int scale) {
        return switch (scale) {
            case 3 -> x >= 15 && x <= 368 && y >= 1412 && y <= 1488;
            case 2 -> x >= 12 && x <= 236 && y >= 921 && y <= 964;
            case 1 -> x >= 5 && x <= 121 && y >= 467 && y <= 495;
            default -> false;
        };
    }

    private boolean isMusicPauseButtonClicked(int x, int y, int scale) {
        return switch (scale) {
            case 3 -> x >= 15 && x <= 368 && y >= 1508 && y <= 1561;
            case 2 -> x >= 12 && x <= 236 && y >= 980 && y <= 1019;
            case 1 -> x >= 19 && x <= 40 && y >= 504 && y <= 517;
            default -> false;
        };
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
