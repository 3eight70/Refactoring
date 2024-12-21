package be.bomberman.main.gameobjects;

import javax.swing.JOptionPane;

import be.bomberman.main.Bomberman;
import be.bomberman.main.affichage.Screen;
import be.bomberman.main.affichage.SheetSquare;
import be.bomberman.main.inputs.KeyboardInput;
import be.bomberman.main.levels.Level;

public class Player1 extends Player{
	
	private Bomberman bomberman;
	
	public Player1(Level level, int x, int y, String name, KeyboardInput input, Bomberman bomberman) {
		super(level, x, y, name, input, bomberman);
		this.bomberman = bomberman;
		// TODO Auto-generated constructor stub
	}
	

	public void update(){
	
super.update();
		
		int xa = 0, ya =0 ;
		String onPlaceBombe = "n";
		
		if(bomberman.jouerEnLigne==false){
			
			if (input.up){ya--;}
			if (input.down){ya++;}
			if (input.left){xa--;}
			if (input.right){xa++;}
			if (input.space && bombRate == 0 && Bomb.canBePlaced(x, y)){
				Bomb bomb = new BasicBomb(x,y,level,this);
				Level.bombs.add(bomb);
				bombRate = BasicBomb.bombRate ;
			}
			if(carryBonus && input.B){
				if (bonusCarried == "firePower" ){
					if (useRate == 0 && bonusTimer>0){
						useFireBonus();
					}
				}
				if (bonusCarried == "fetaBonus"){
					useFetaBonus();
					System.out.println("yep");
				}
			
			}
				
		}else{			
			if(name.equals(bomberman.playerName)){
				//ajout des variables
				if (input.up){ya--;}
				if (input.down){ya++;}
				if (input.left){xa--;}
				if (input.right){xa++;}
				if (input.space && bombRate == 0 && Bomb.canBePlaced(x, y)){
					Bomb bomb = new BasicBomb(x,y,level,this);
					Level.bombs.add(bomb);
					bombRate = BasicBomb.bombRate ;
					onPlaceBombe = "o";
				}	
				//recuperation de l'image du bomberman actuelle
			
				//on envoie au serveur notre position
				//System.out.println("On envoie au serveur: " + bomberman.playerName+"-"+bomberman.player.x+"_"+bomberman.player.y+"_"+onPlaceBombe);
				if(bomberman.protocol.equals("UDP")){
					bomberman.socketClient.sendData((bomberman.playerName+"-"+bomberman.player1.x+"_"+bomberman.player1.y+"_"+onPlaceBombe).getBytes());
				}else if(bomberman.protocol.equals("TCP")){
					bomberman.socketClientTCP.sendData(bomberman.playerName+"-"+bomberman.player1.x+"_"+bomberman.player1.y+"_"+onPlaceBombe);
				}
				
			}else{
				int nouvellePosX = Integer.valueOf(bomberman.msg.trim().split("-")[1].trim().split("_")[0]);
				int nouvellePosY = Integer.valueOf(bomberman.msg.trim().split("-")[1].trim().split("_")[1]);
				//on bouge a la position ou se trouve le vrai joueur
				xa = nouvellePosX - bomberman.anciennePosX[0];
				ya = nouvellePosY - bomberman.anciennePosY[0];
				//System.out.println("On bouge vers: "+(nouvellePosX - bomberman.anciennePosX)+"_"+(nouvellePosY - bomberman.anciennePosY));
				
				bomberman.anciennePosX[0] = nouvellePosX;
				bomberman.anciennePosY[0] = nouvellePosY;
				//System.out.println("On a une bombe? "+(bomberman.msg.trim().split("-")[1].trim().split("_")[2]));
				if (bomberman.ilFautPlacerUneBombe == true){
					System.out.println("ON PLACE UNE BOMBE");
					bomberman.ilFautPlacerUneBombe = false;
					Bomb bomb = new BasicBomb(bomberman.coordBombe[0],bomberman.coordBombe[1],level,this);
					Level.bombs.add(bomb);
					bombRate = BasicBomb.bombRate ;
					
				}
			}
		}
		
			
		if(xa!=0 || ya != 0) {
			move(xa,ya);
			isMoving = true ;
		}else{
			isMoving = false ;
		}
		
		
		
	}
	
	
	public void render(Screen screen) {
		boolean xMirror = false;
		boolean yMirror = false;
		
		if(movingDir == 0) {
			square = SheetSquare.player1_back1;
			if (isMoving){
				if (animation % 60 < 30){
					square = SheetSquare.player1_back2;
				}else{
					square = SheetSquare.player1_back3;
				}
			}
		}
		if(movingDir == 1) {
			square = SheetSquare.player1_front1;
			if (isMoving){
				if (animation % 60 < 30){
					square = SheetSquare.player1_front2;
				}else{
					square = SheetSquare.player1_front3;
				}
			}
		}
		if(movingDir == 2) {
			square = SheetSquare.player1_side1;
			if (isMoving){
				if (animation % 20 < 10){
					square = SheetSquare.player1_side2;
				}else{
					square = SheetSquare.player1_side3;
				}
			}
		}
		if(movingDir == 3) {
			square = SheetSquare.player1_side1;
			if (isMoving){
				if (animation % 20 < 10){
					square = SheetSquare.player1_side2;
				}else{
					square = SheetSquare.player1_side3;
				}
			}
			xMirror = true;
		}
		if (immunisation < 300) {
			if (immunisation%50 < 10) square = SheetSquare.immu2;
		}
		screen.renderEntity(x, y, square, 32, xMirror, yMirror, 0xffffffff);
		
	}


}
