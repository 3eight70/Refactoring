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

	public class PlayerBomberman3 extends Player {
		
		private Bomberman bomberman;
		
		public PlayerBomberman3(Level level, int x, int y, String name,
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
					if (input.I){ya--;}		
					if (input.K){ya++;}
					if (input.J){xa--;}
					if (input.L){xa++;}
					if (input.U && bombRate == 0 && Bomb.canBePlaced(x, y) && canplacebomb == true){
						useBomb();
						Bomb bomb = new BasicBomb(x,y,level,this);
						Level.bombs.add(bomb);
						bombRate = BasicBomb.bombRate ;
					}
					if(carryBonus && input.O){
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
							bomberman.socketClient.sendData((bomberman.playerName+"-"+bomberman.player3.x+"_"+bomberman.player3.y+"_"+onPlaceBombe).getBytes());
						}else if(bomberman.protocol.equals("TCP")){
							bomberman.socketClientTCP.sendData(bomberman.playerName+"-"+bomberman.player3.x+"_"+bomberman.player3.y+"_"+onPlaceBombe);
						}
					}else{
						if(bomberman.msg.trim().split("-")[0].equals("Zelda")){
							int nouvellePosX = Integer.valueOf(bomberman.msg.trim().split("-")[1].trim().split("_")[0]);
							int nouvellePosY = Integer.valueOf(bomberman.msg.trim().split("-")[1].trim().split("_")[1]);
							//on bouge a la position ou se trouve le vrai joueur
							xa = nouvellePosX - bomberman.anciennePosX[2];
							ya = nouvellePosY - bomberman.anciennePosY[2];
							//System.out.println("On bouge vers: "+(nouvellePosX - bomberman.anciennePosX)+"_"+(nouvellePosY - bomberman.anciennePosY));
							
							bomberman.anciennePosX[2] = nouvellePosX;
							bomberman.anciennePosY[2] = nouvellePosY;
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
				square = SheetSquare.zelda_back1;
				if (isMoving){
					if (animation % 60 < 20){
						square = SheetSquare.zelda_back2;
					}
					else if (animation % 60 < 40){
						square = SheetSquare.zelda_back3;
					}
					else square = SheetSquare.zelda_back4;
					
				}
			}
			if(movingDir == 1) {
				square = SheetSquare.zelda_front1;
				if (isMoving){
					if (animation % 60 < 15){
						square = SheetSquare.zelda_front2;
					}
					else if (animation % 60 < 30){
						square = SheetSquare.zelda_front3;
					}
					else if (animation % 60 < 45){
						square = SheetSquare.zelda_front4;
					}
					else square = SheetSquare.zelda_front5;
				}
			}
			if(movingDir == 2) {
				square = SheetSquare.zelda_side1;
				if (isMoving){
					if (animation % 60 < 15){
						square = SheetSquare.zelda_side2;
					}
					else if (animation % 60 < 30){
						square = SheetSquare.zelda_side3;
					}
					else if (animation % 60 < 45){
						square = SheetSquare.zelda_side4;
					}
					else square = SheetSquare.zelda_side5;
				}
			}
			if(movingDir == 3) {
				square = SheetSquare.zelda_side1;
				if (isMoving){
					if (animation % 60 < 15){
						square = SheetSquare.zelda_side2;
					}
					else if (animation % 60 < 30){
						square = SheetSquare.zelda_side3;
					}
					else if (animation % 60 < 45){
						square = SheetSquare.zelda_side4;
					}
					else square = SheetSquare.zelda_side5;
				}
				xMirror = true;
			}
			
			if (immunisation < 300) {
				if (immunisation%60 < 10) square = SheetSquare.immu;
			}
			
			}
			else if (life < 1){
				square = SheetSquare.zelda_dead;
			}
			screen.renderEntity(x, y, square, 32, xMirror, yMirror,0xff527B9C);		
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
	
	
