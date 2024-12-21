package be.bomberman.main.gameobjects;


import be.bomberman.main.Bomberman;
import be.bomberman.main.affichage.Screen;
import be.bomberman.main.affichage.SheetSquare;
import be.bomberman.main.inputs.KeyboardInput;
import be.bomberman.main.levels.Level;
import be.bomberman.main.affichage.Screen;
import be.bomberman.main.affichage.SheetSquare;
import be.bomberman.main.inputs.KeyboardInput;
import be.bomberman.main.levels.Level;

	public class PlayerBomberman4 extends Player {
		
		private Bomberman bomberman;
		
		public PlayerBomberman4(Level level, int x, int y, String name,
				KeyboardInput input,Bomberman bomberman) {
			super(level, x, y, name, input,bomberman);
			this.bomberman = bomberman;
		}
		
		public void update() {
			super.update();
					
			int xa = 0, ya =0 ;
			String onPlaceBombe = "n";
			
			
			if (life > 0){
				if(bomberman.jouerEnLigne==false){
					if (input.T){ya--;}		
					if (input.G){ya++;}
					if (input.F){xa--;}
					if (input.H){xa++;}
					if (input.R && bombRate == 0 && Bomb.canBePlaced(x, y) && canplacebomb == true){
						useBomb();
						Bomb bomb = new BasicBomb(x,y,level,this);
						Level.bombs.add(bomb);
						bombRate = BasicBomb.bombRate ;
					}
					if(carryBonus && input.Y){
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
					if (carryBonus && bonusCarried == "lifeBonus"){
						useLifeBonus();
					}
					if (carryBonus && bonusCarried == "bombBonus"){
						useBombBonus();
					}
					if (carryBonus && bonusCarried == "rangeBonus"){
						useRangeBonus();
					}
				}else{
					if(name.equals(bomberman.playerName)){
						//ajout des variables
						if (input.up){ya--;}
						if (input.down){ya++;}
						if (input.left){xa--;}
						if (input.right){xa++;}
						if (input.space && bombRate == 0 && Bomb.canBePlaced(x, y) && canplacebomb == true){
							useBomb();
							Bomb bomb = new BasicBomb(x,y,level,this);
							Level.bombs.add(bomb);
							bombRate = BasicBomb.bombRate ;
							onPlaceBombe = "o";
						}	
						//recuperation de l'image du bomberman actuelle
					
						//on envoie au serveur notre position
						//System.out.println("On envoie au serveur: " + bomberman.playerName+"-"+bomberman.player.x+"_"+bomberman.player.y+"_"+onPlaceBombe);
						if(bomberman.protocol.equals("UDP")){
							bomberman.socketClient.sendData((bomberman.playerName+"-"+bomberman.player4.x+"_"+bomberman.player4.y+"_"+onPlaceBombe).getBytes());
						}else if(bomberman.protocol.equals("TCP")){
							bomberman.socketClientTCP.sendData(bomberman.playerName+"-"+bomberman.player4.x+"_"+bomberman.player4.y+"_"+onPlaceBombe);
						}
					}else{
						if(bomberman.msg.trim().split("-")[0].equals("Link")){
							int nouvellePosX = Integer.valueOf(bomberman.msg.trim().split("-")[1].trim().split("_")[0]);
							int nouvellePosY = Integer.valueOf(bomberman.msg.trim().split("-")[1].trim().split("_")[1]);
							//on bouge a la position ou se trouve le vrai joueur
							xa = nouvellePosX - bomberman.anciennePosX[3];
							ya = nouvellePosY - bomberman.anciennePosY[3];
							//System.out.println("On bouge vers: "+(nouvellePosX - bomberman.anciennePosX)+"_"+(nouvellePosY - bomberman.anciennePosY));
							
							bomberman.anciennePosX[3] = nouvellePosX;
							bomberman.anciennePosY[3] = nouvellePosY;
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
			
			if (life > 0) {
			if(movingDir == 0) {
				square = SheetSquare.link_back1;
				if (isMoving){
					if (animation % 45 < 5){
						square = SheetSquare.link_back2;
					}
					else if (animation % 45 < 10){
						square = SheetSquare.link_back3;
					}
					else if (animation % 45 < 15){
						square = SheetSquare.link_back4;
					}
					else if (animation % 45 < 20){
						square = SheetSquare.link_back5;
					}
					else if (animation % 45 < 25){
						square = SheetSquare.link_back6;
					}
					else if (animation % 45 < 30){
						square = SheetSquare.link_back7;
					}
					else if (animation % 45 < 35){
						square = SheetSquare.link_back8;
					}
					else if (animation % 45 < 40){
						square = SheetSquare.link_back9;
					}
					else{
						square = SheetSquare.link_back10;
					}
				}
			}
			if(movingDir == 1) {
				square = SheetSquare.link_front1;
				if (isMoving){
					if (animation % 45 < 5){
						square = SheetSquare.link_front2;
					}
					else if (animation % 45 < 10){
						square = SheetSquare.link_front3;
					}
					else if (animation % 45 < 15){
						square = SheetSquare.link_front4;
					}
					else if (animation % 45 < 20){
						square = SheetSquare.link_front5;
					}
					else if (animation % 45 < 25){
						square = SheetSquare.link_front6;
					}
					else if (animation % 45 < 30){
						square = SheetSquare.link_front7;
					}
					else if (animation % 45 < 35){
						square = SheetSquare.link_front8;
					}
					else if (animation % 45 < 40){
						square = SheetSquare.link_front9;
					}
					else{
						square = SheetSquare.link_front10;
					}
				}
			}
			if(movingDir == 2) {
				square = SheetSquare.link_side1;
				if (isMoving){
					if (animation % 50 < 5){
						square = SheetSquare.link_side2;
					}
					else if (animation % 50 < 10){
						square = SheetSquare.link_side3;
					}
					else if (animation % 50 < 15){
						square = SheetSquare.link_side4;
					}
					else if (animation % 50 < 20){
						square = SheetSquare.link_side5;
					}
					else if (animation % 50 < 25){
						square = SheetSquare.link_side6;
					}
					else if (animation % 50 < 30){
						square = SheetSquare.link_side7;
					}
					else if (animation % 50 < 35){
						square = SheetSquare.link_side8;
					}
					else if (animation % 50 < 40){
						square = SheetSquare.link_side9;
					}
					else if (animation % 50 < 45){
						square = SheetSquare.link_side10;
					}
					else{
						square = SheetSquare.link_side11;
					}
				}
				xMirror = true;
			}
			if(movingDir == 3) {
				square = SheetSquare.link_side1;
				if (isMoving){
					if (animation % 50 < 5){
						square = SheetSquare.link_side2;
					}
					else if (animation % 50 < 10){
						square = SheetSquare.link_side3;
					}
					else if (animation % 50 < 15){
						square = SheetSquare.link_side4;
					}
					else if (animation % 50 < 20){
						square = SheetSquare.link_side5;
					}
					else if (animation % 50 < 25){
						square = SheetSquare.link_side6;
					}
					else if (animation % 50 < 30){
						square = SheetSquare.link_side7;
					}
					else if (animation % 50 < 35){
						square = SheetSquare.link_side8;
					}
					else if (animation % 50 < 40){
						square = SheetSquare.link_side9;
					}
					else if (animation % 50 < 45){
						square = SheetSquare.link_side10;
					}
					else{
						square = SheetSquare.link_side11;
					}
				}
			}
			
			if (immunisation < 300) {
				if (immunisation%60 < 10) square = SheetSquare.immu;
			}
			}
			else if (life < 1){
				square = SheetSquare.link_dead;
			}
			
			if (square == SheetSquare.link_dead) screen.renderRectangle(x, y, square, 32, 40, xMirror, yMirror, 0xff527B9C);	
			else screen.renderEntity(x, y, square, 32, xMirror, yMirror,0xff527B9C);	
			if (xT != 0) {
				if (0 <= teleportTimer && teleportTimer < 4) screen.renderEntity(xT, yT, SheetSquare.tp1, 32, false, false, 0xff527B9C);
				if (4 <= teleportTimer && teleportTimer < 8) screen.renderEntity(xT, yT, SheetSquare.tp2, 32, false, false, 0xff527B9C);
				if (8 <= teleportTimer && teleportTimer < 12) screen.renderEntity(xT, yT, SheetSquare.tp3, 32, false, false, 0xff527B9C);
				if (12 <= teleportTimer && teleportTimer < 16) screen.renderEntity(xT, yT, SheetSquare.tp4, 32, false, false, 0xff527B9C);
				if (16 <= teleportTimer && teleportTimer < 20) screen.renderEntity(xT, yT, SheetSquare.tp5, 32, false, false, 0xff527B9C);
				if (20 <= teleportTimer && teleportTimer < 24) screen.renderEntity(xT, yT, SheetSquare.tp6, 32, false, false, 0xff527B9C);
				if (24 <= teleportTimer && teleportTimer < 28) screen.renderEntity(xT-16, yT-16, SheetSquare.tp7, 64, false, false, 0xff527B9C);
				if (28 <= teleportTimer && teleportTimer < 32) screen.renderEntity(xT-16, yT-16, SheetSquare.tp8, 64, false, false, 0xff527B9C);
			}
		}
	}
	
