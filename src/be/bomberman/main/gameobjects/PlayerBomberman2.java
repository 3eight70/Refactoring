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

public class PlayerBomberman2 extends Player {
	
	private Bomberman bomberman;
	
	public PlayerBomberman2(Level level, int x, int y, String name,
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
				if (input.Z){ya--;}		
				if (input.S){ya++;}
				if (input.Q){xa--;}
				if (input.D){xa++;}
				if (input.A && bombRate == 0 && Bomb.canBePlaced(x, y) && canplacebomb == true){
					useBomb();
					Bomb bomb = new BasicBomb(x,y,level,this);
					Level.bombs.add(bomb);
					bombRate = BasicBomb.bombRate ;
				}
				
				if(carryBonus && input.E){
					if (bonusCarried == "firePower" ){
						if (useRate == 0 && bonusTimer>0){
							useFireBonus();
						}
					}
					if (bonusCarried == "fetaBonus"){
						useFetaBonus();
						System.out.println("yep");
					}
					if (bonusCarried == "rangeBonus"){
						useRangeBonus();
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
						System.out.println(level.theLevel);
						Bomb bomb = new BasicBomb(x,y,level,this);
						Level.bombs.add(bomb);
						bombRate = BasicBomb.bombRate ;
						onPlaceBombe = "o";
					}	
					//recuperation de l'image du bomberman actuelle
				
					//on envoie au serveur notre position
					//System.out.println("On envoie au serveur: " + bomberman.playerName+"-"+bomberman.player.x+"_"+bomberman.player.y+"_"+onPlaceBombe);
					if(bomberman.protocol.equals("UDP")){
						bomberman.socketClient.sendData((bomberman.playerName+"-"+bomberman.player2.x+"_"+bomberman.player2.y+"_"+onPlaceBombe).getBytes());
					}else if(bomberman.protocol.equals("TCP")){
						bomberman.socketClientTCP.sendData(bomberman.playerName+"-"+bomberman.player2.x+"_"+bomberman.player2.y+"_"+onPlaceBombe);
					}
				}else{
					if(bomberman.msg.trim().split("-")[0].equals("Bombergirl")){
						int nouvellePosX = Integer.valueOf(bomberman.msg.trim().split("-")[1].trim().split("_")[0]);
						int nouvellePosY = Integer.valueOf(bomberman.msg.trim().split("-")[1].trim().split("_")[1]);
						//on bouge a la position ou se trouve le vrai joueur
						xa = nouvellePosX - bomberman.anciennePosX[1];
						ya = nouvellePosY - bomberman.anciennePosY[1];
						//System.out.println("On bouge vers: "+(nouvellePosX - bomberman.anciennePosX)+"_"+(nouvellePosY - bomberman.anciennePosY));
						
						bomberman.anciennePosX[1] = nouvellePosX;
						bomberman.anciennePosY[1] = nouvellePosY;
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
			square = SheetSquare.bomberman2_back1;
			if (isMoving){
				if (animation % 60 < 30){
					square = SheetSquare.bomberman2_back2;
				}else{
					square = SheetSquare.bomberman2_back3;
				}
			}
		}
		if(movingDir == 1) {
			square = SheetSquare.bomberman2_front1;
			if (isMoving){
				if (animation % 60 < 30){
					square = SheetSquare.bomberman2_front2;
				}else{
					square = SheetSquare.bomberman2_front3;
				}
			}
		}
		if(movingDir == 2) {
			square = SheetSquare.bomberman2_side1;
			if (isMoving){
				if (animation % 60 < 30){
					square = SheetSquare.bomberman2_side2;
				}else{
					square = SheetSquare.bomberman2_side3;
				}
			}
		}
		if(movingDir == 3) {
			square = SheetSquare.bomberman2_side1;
			if (isMoving){
				if (animation % 60 < 30){
					square = SheetSquare.bomberman2_side2;
				}else{
					square = SheetSquare.bomberman2_side3;
				}
			}
			xMirror = true;
		}
		
		if (immunisation < 300) {
			if (immunisation%60 < 10) square = SheetSquare.immu;
		}
		}
		else if (life < 1){
			square = SheetSquare.bomberman2_dead;
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

