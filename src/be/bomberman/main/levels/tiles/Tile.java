package be.bomberman.main.levels.tiles;

import be.bomberman.main.affichage.Screen;
import be.bomberman.main.affichage.SheetSquare;



public abstract class Tile {	
	
	public SheetSquare square;
	protected boolean solid;
	protected boolean breakable;
	protected boolean burning;
	protected boolean telep;
	public static int tileTimer = 1500;
	protected int size = 32;     // taille d'un square
	
	
	// Icones apparaissant � l'�cran avec leur propri�t�s
	//*********************************************************************************************************
	// Level1
	
	public static Tile grass = new BasicTile(SheetSquare.grass);	
	public static Tile rock = new BasicSolidTile(SheetSquare.rock);
	public static Tile lightrock = new BasicBreakableTile(SheetSquare.lightrock);	
	public static Tile sea = new BasicTile(SheetSquare.sea);
	
	public static Tile basicbomb = new BasicSolidTile(SheetSquare.basicbomb);
	public static Tile fire = new BasicTile(SheetSquare.fire);
	public static Tile burningGrass = new BasicTile(SheetSquare.burningGrass);
	public static Tile bonus = new BasicTile(SheetSquare.bonus);
	public static Tile feta = new BasicTile(SheetSquare.bonus);
	
	//*********************************************************************************************************
	//Level2
	public static Tile teleport = new TeleportTile(SheetSquare.teleport);
	public static Tile teleport2 = new TeleportTile(SheetSquare.teleport);
	public static Tile noteleport = new BasicTile(SheetSquare.noteleport);
	public static Tile spike = new BasicTile(SheetSquare.spike);
	
	public static Tile grassLevel2 = new BasicTile(SheetSquare.grassLevel2);	
	public static Tile grassSolid = new BasicBreakableTile(SheetSquare.grassLevel2);	
	public static Tile rockLevel2 = new BasicSolidTile(SheetSquare.rockLevel2);
	public static Tile lightrockLevel2 = new BasicBreakableTile(SheetSquare.lightrockLevel2);	
	public static Tile seaLevel2 = new BasicTile(SheetSquare.seaLevel2);
	public static Tile basicbomb1 = new BasicSolidTile(SheetSquare.basicbomb1);
	public static Tile basicbomb2 = new BasicSolidTile(SheetSquare.basicbomb2);
	public static Tile basicbomb3 = new BasicSolidTile(SheetSquare.basicbomb3);
	
	public static Tile treeSO = new BasicSolidTile(SheetSquare.treeSO);
	public static Tile treeO = new BasicSolidTile(SheetSquare.treeO);
	public static Tile treeNO = new BasicSolidTile(SheetSquare.treeNO);
	public static Tile treeN = new BasicSolidTile(SheetSquare.treeN);
	public static Tile treeNE = new BasicSolidTile(SheetSquare.treeNE);
	public static Tile treeSE = new BasicSolidTile(SheetSquare.treeSE);
	public static Tile treeE = new BasicSolidTile(SheetSquare.treeE);
	public static Tile bordNO = new BasicSolidTile(SheetSquare.bordNO);
	public static Tile bordNOO = new BasicSolidTile(SheetSquare.bordNOO);
	public static Tile bordN = new BasicSolidTile(SheetSquare.bordN);
	public static Tile bordNE = new BasicSolidTile(SheetSquare.bordNE);
	public static Tile bordNEE = new BasicSolidTile(SheetSquare.bordNEE);
	public static Tile bordO = new BasicSolidTile(SheetSquare.bordO);
	public static Tile bordE = new BasicSolidTile(SheetSquare.bordE);
	public static Tile bordSO = new BasicSolidTile(SheetSquare.bordSO);
	public static Tile bordSOO = new BasicSolidTile(SheetSquare.bordSOO);
	public static Tile bordS = new BasicSolidTile(SheetSquare.bordS);
	public static Tile bordSE = new BasicSolidTile(SheetSquare.bordSE);
	public static Tile bordSEE = new BasicSolidTile(SheetSquare.bordSEE);
	
	public static Tile house1 = new BasicSolidTile(SheetSquare.house1);
	public static Tile house2 = new BasicSolidTile(SheetSquare.house2);
	public static Tile house2b = new BasicSolidTile(SheetSquare.house2b);
	public static Tile house2c = new BasicSolidTile(SheetSquare.house2c);
	public static Tile house2d = new BasicSolidTile(SheetSquare.house2d);
	public static Tile house3 = new BasicSolidTile(SheetSquare.house3);
	public static Tile house3b = new BasicSolidTile(SheetSquare.house3b);
	public static Tile house3c = new BasicSolidTile(SheetSquare.house3c);
	public static Tile house3d = new BasicSolidTile(SheetSquare.house3d);
	public static Tile house4 = new BasicSolidTile(SheetSquare.house4);
	public static Tile house4b = new BasicSolidTile(SheetSquare.house4b);
	public static Tile house4c = new BasicSolidTile(SheetSquare.house4c);
	public static Tile house4d = new BasicSolidTile(SheetSquare.house4d);
	public static Tile house5 = new BasicSolidTile(SheetSquare.house5);
	public static Tile house5b = new BasicSolidTile(SheetSquare.house5b);
	public static Tile house5c = new BasicSolidTile(SheetSquare.house5c);
	public static Tile house5d = new BasicSolidTile(SheetSquare.house5d);
	public static Tile house6 = new BasicSolidTile(SheetSquare.house6);
	public static Tile house6b = new BasicSolidTile(SheetSquare.house6b);
	public static Tile house6c = new BasicSolidTile(SheetSquare.house6c);
	public static Tile house6d = new BasicSolidTile(SheetSquare.house6d);
	public static Tile house7 = new BasicSolidTile(SheetSquare.house7);
	public static Tile house7b = new BasicSolidTile(SheetSquare.house7b);
	public static Tile house7c = new BasicSolidTile(SheetSquare.house7c);
	public static Tile house7d = new BasicSolidTile(SheetSquare.house7d);
	public static Tile house8 = new BasicSolidTile(SheetSquare.house8);
	public static Tile house8b = new BasicSolidTile(SheetSquare.house8b);
	public static Tile house8c = new BasicSolidTile(SheetSquare.house8c);
	public static Tile house8d = new BasicSolidTile(SheetSquare.house8d);
	public static Tile house9 = new BasicSolidTile(SheetSquare.house9);
	public static Tile house10 = new BasicSolidTile(SheetSquare.house10);
	public static Tile house11 = new BasicSolidTile(SheetSquare.house11);
	public static Tile house11b = new BasicSolidTile(SheetSquare.house11b);
	public static Tile house11c = new BasicSolidTile(SheetSquare.house11c);
	public static Tile house11d = new BasicSolidTile(SheetSquare.house11d);
	public static Tile house12 = new BasicSolidTile(SheetSquare.house12);
	public static Tile house12b = new BasicSolidTile(SheetSquare.house12b);
	public static Tile house12c = new BasicSolidTile(SheetSquare.house12c);
	public static Tile house12d = new BasicSolidTile(SheetSquare.house12d);
	public static Tile house13 = new BasicSolidTile(SheetSquare.house13);
	public static Tile house13b = new BasicSolidTile(SheetSquare.house13b);
	public static Tile house13c = new BasicSolidTile(SheetSquare.house13c);
	public static Tile house13d = new BasicSolidTile(SheetSquare.house13d);
	public static Tile house14 = new BasicSolidTile(SheetSquare.house14);
	public static Tile house14b = new BasicSolidTile(SheetSquare.house14b);
	public static Tile house14c = new BasicSolidTile(SheetSquare.house14c);
	public static Tile house14d = new BasicSolidTile(SheetSquare.house14d);
	public static Tile house15 = new BasicSolidTile(SheetSquare.house15);
	public static Tile house15b = new BasicSolidTile(SheetSquare.house15b);
	public static Tile house15c = new BasicSolidTile(SheetSquare.house15c);
	public static Tile house15d = new BasicSolidTile(SheetSquare.house15d);
	
	//*********************************************************************************************************
	
	
	public Tile(SheetSquare square, boolean isSolid, boolean isBreakable, boolean isTeleport){
		this.square = square;
		this.solid = isSolid;
		this.breakable = isBreakable;		
		this.telep = isTeleport;
	}
	
	public abstract void render(int xPos, int yPos, Screen screen);
	
	
	public boolean isSolid(){
		return solid;
	}	
	
	public boolean isTeleport(){
		return telep;
	}	
	
	public boolean isBreakable(){		
		return breakable;		
	}
			
	public boolean isBurning(){
		return burning ;
	}
	
	/*
	public void setBurning(boolean isBurning){
		if (this.isBreakable()){
			burning = isBurning; 
		}else{
			burning = false ;
		}
	}
	*/
	
	public int getSize() {
		return size;
	}	
	
	public static int getTimer() {
		return tileTimer;
	}
	
	public void setTimer(){
		tileTimer++;
	}

}
