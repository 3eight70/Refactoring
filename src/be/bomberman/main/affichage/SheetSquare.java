package be.bomberman.main.affichage;

public class SheetSquare {
	
	
	private final int SQUARESIZEx;
	private final int SQUARESIZEy;
	private SpriteSheet sheet;
	private int xSst, ySst ;
	private int[] squarePixels;
	
	//***********************************************************************************************************
	// Level1
	
	public static SheetSquare grass = new SheetSquare(32, 0, 0, SpriteSheet.minecraft);
	public static SheetSquare rock = new SheetSquare(32, 5, 14, SpriteSheet.minecraft);
	public static SheetSquare sea = new SheetSquare(32, 15, 13, SpriteSheet.minecraft);
	public static SheetSquare lightrock = new SheetSquare(32, 12, 11, SpriteSheet.minecraft);
	public static SheetSquare basicbomb = new SheetSquare(32, 8, 0, SpriteSheet.minecraft);
	public static SheetSquare fire = new SheetSquare(32, 15, 15, SpriteSheet.minecraft);
	public static SheetSquare fire2 = new SheetSquare(32, 14, 1, SpriteSheet.minecraft);
	public static SheetSquare burningGrass = new SheetSquare(32, 15, 1, SpriteSheet.minecraft);
	public static SheetSquare bonus = new SheetSquare(32, 1, 4, SpriteSheet.minecraft);
	public static SheetSquare feta = new SheetSquare(32, 9, 7, SpriteSheet.minecraft);
	public static SheetSquare immu2 = new SheetSquare(32, 3, 8, SpriteSheet.minecraft);
	//***********************************************************************************************************
	// Level2
	public static SheetSquare teleport = new SheetSquare(32, 0, 10, SpriteSheet.bomberman);
	public static SheetSquare teleport2 = new SheetSquare(32, 0, 10, SpriteSheet.bomberman);
	public static SheetSquare noteleport = new SheetSquare(32, 0, 11, SpriteSheet.bomberman);
	public static SheetSquare tp1 = new SheetSquare(32, 1, 10, SpriteSheet.bomberman);
	public static SheetSquare tp2 = new SheetSquare(32, 2, 10, SpriteSheet.bomberman);
	public static SheetSquare tp3 = new SheetSquare(32, 3, 10, SpriteSheet.bomberman);
	public static SheetSquare tp4 = new SheetSquare(32, 4, 10, SpriteSheet.bomberman);
	public static SheetSquare tp5 = new SheetSquare(32, 5, 10, SpriteSheet.bomberman);
	public static SheetSquare tp6 = new SheetSquare(32, 6, 10, SpriteSheet.bomberman);
	public static SheetSquare tp7 = new SheetSquare(64, 4, 5, SpriteSheet.bomberman);
	public static SheetSquare tp8 = new SheetSquare(64, 5, 5, SpriteSheet.bomberman);
	
	public static SheetSquare afficheon = new SheetSquare(128,544, 0,0, SpriteSheet.informations4on);
	public static SheetSquare afficheoff = new SheetSquare(128,544, 0,0, SpriteSheet.informations4off);
	public static SheetSquare affichepauseon = new SheetSquare(128,544, 0,0, SpriteSheet.informations4pauseon);
	public static SheetSquare affichepauseoff = new SheetSquare(128,544, 0,0, SpriteSheet.informations4pauseoff);
	
	public static SheetSquare spike = new SheetSquare(32, 8, 2, SpriteSheet.bomberman);	
	public static SheetSquare bonusspike = new SheetSquare(32, 11, 1, SpriteSheet.bomberman);	
	public static SheetSquare bonuslife = new SheetSquare(32, 10, 2, SpriteSheet.bomberman);	
	public static SheetSquare bonusspeed = new SheetSquare(32, 8, 1, SpriteSheet.bomberman);
	public static SheetSquare bonusrange = new SheetSquare(32, 10, 1, SpriteSheet.bomberman);
	public static SheetSquare bonusbomb = new SheetSquare(32, 9, 1, SpriteSheet.bomberman);
	
	
	public static SheetSquare grassLevel2 = new SheetSquare(32, 0, 0, SpriteSheet.minecraftModif);
	public static SheetSquare rockLevel2 = new SheetSquare(32, 5, 14, SpriteSheet.minecraftModif);
	public static SheetSquare seaLevel2 = new SheetSquare(32, 15, 13, SpriteSheet.minecraftModif);
	public static SheetSquare lightrockLevel2 = new SheetSquare(32, 12, 11, SpriteSheet.minecraftModif);
	
	public static SheetSquare basicbomb1 = new SheetSquare(32, 8, 0, SpriteSheet.bomberman);
	public static SheetSquare basicbomb2 = new SheetSquare(32, 9, 0, SpriteSheet.bomberman);
	public static SheetSquare basicbomb3 = new SheetSquare(32, 10, 0, SpriteSheet.bomberman);
	public static SheetSquare level2fire1 = new SheetSquare(32, 5, 1, SpriteSheet.bomberman); //center
	public static SheetSquare level2fire2 = new SheetSquare(32, 6, 1, SpriteSheet.bomberman); //right
	public static SheetSquare level2fire3 = new SheetSquare(32, 7, 1, SpriteSheet.bomberman); //RIGHT
	public static SheetSquare level2fire4 = new SheetSquare(32, 5, 2, SpriteSheet.bomberman); //down
	public static SheetSquare level2fire5 = new SheetSquare(32, 5, 3, SpriteSheet.bomberman); //DOWN
	
	public static SheetSquare treeSO = new SheetSquare(32,0,2, SpriteSheet.background);
	public static SheetSquare treeO = new SheetSquare(32,0,1, SpriteSheet.background);
	public static SheetSquare treeNO = new SheetSquare(32,1,2, SpriteSheet.background);
	public static SheetSquare treeN = new SheetSquare(32,1,1, SpriteSheet.background);
	public static SheetSquare treeNE = new SheetSquare(32,2,2, SpriteSheet.background);
	public static SheetSquare treeSE = new SheetSquare(32,4,2, SpriteSheet.background);
	public static SheetSquare treeE = new SheetSquare(32,4,1, SpriteSheet.background);
	
	public static SheetSquare bordNO = new SheetSquare(32,1,6, SpriteSheet.background);
	public static SheetSquare bordNOO = new SheetSquare(32,2,7, SpriteSheet.background);
	public static SheetSquare bordN = new SheetSquare(32,2,6, SpriteSheet.background);
	public static SheetSquare bordNE = new SheetSquare(32,3,6, SpriteSheet.background);
	public static SheetSquare bordNEE = new SheetSquare(32,4,7, SpriteSheet.background);
	public static SheetSquare bordO = new SheetSquare(32,1,7, SpriteSheet.background);
	public static SheetSquare bordE = new SheetSquare(32,3,7, SpriteSheet.background);
	public static SheetSquare bordSO = new SheetSquare(32,1,8, SpriteSheet.background);
	public static SheetSquare bordSOO = new SheetSquare(32,1,7, SpriteSheet.background);
	public static SheetSquare bordS = new SheetSquare(32,2,8, SpriteSheet.background);
	public static SheetSquare bordSE = new SheetSquare(32,3,8, SpriteSheet.background);
	public static SheetSquare bordSEE = new SheetSquare(32,3,7, SpriteSheet.background);
	
	public static SheetSquare house1 = new SheetSquare(32,5,2, SpriteSheet.background);
	public static SheetSquare house2 = new SheetSquare(32,5,1, SpriteSheet.background);
	public static SheetSquare house2b = new SheetSquare(32,5,4, SpriteSheet.background);
	public static SheetSquare house2c = new SheetSquare(32,5,7, SpriteSheet.background);
	public static SheetSquare house2d = new SheetSquare(32,0,4, SpriteSheet.background);
	public static SheetSquare house3 = new SheetSquare(96,5,0, SpriteSheet.background);
	public static SheetSquare house3b = new SheetSquare(32,5,3, SpriteSheet.background);
	public static SheetSquare house3c = new SheetSquare(32,5,6, SpriteSheet.background);
	public static SheetSquare house3d = new SheetSquare(32,0,3, SpriteSheet.background);
	public static SheetSquare house4 = new SheetSquare(32,6,2, SpriteSheet.background);
	public static SheetSquare house4b = new SheetSquare(32,6,5, SpriteSheet.background);
	public static SheetSquare house4c = new SheetSquare(32,6,8, SpriteSheet.background);
	public static SheetSquare house4d = new SheetSquare(32,1,5, SpriteSheet.background);
	public static SheetSquare house5 = new SheetSquare(32,6,1, SpriteSheet.background);
	public static SheetSquare house5b = new SheetSquare(32,6,4, SpriteSheet.background);
	public static SheetSquare house5c = new SheetSquare(32,6,7, SpriteSheet.background);
	public static SheetSquare house5d = new SheetSquare(32,1,4, SpriteSheet.background);
	public static SheetSquare house6 = new SheetSquare(32,6,0, SpriteSheet.background);
	public static SheetSquare house6b = new SheetSquare(32,6,3, SpriteSheet.background);
	public static SheetSquare house6c = new SheetSquare(32,6,6, SpriteSheet.background);
	public static SheetSquare house6d = new SheetSquare(32,1,3, SpriteSheet.background);
	public static SheetSquare house7 = new SheetSquare(32,7,2, SpriteSheet.background);
	public static SheetSquare house7b = new SheetSquare(32,7,5, SpriteSheet.background);
	public static SheetSquare house7c = new SheetSquare(32,7,8, SpriteSheet.background);
	public static SheetSquare house7d = new SheetSquare(32,2,5, SpriteSheet.background);
	public static SheetSquare house8 = new SheetSquare(32,7,1, SpriteSheet.background);
	public static SheetSquare house8b = new SheetSquare(32,7,4, SpriteSheet.background);
	public static SheetSquare house8c = new SheetSquare(32,7,7, SpriteSheet.background);
	public static SheetSquare house8d = new SheetSquare(32,2,4, SpriteSheet.background);
	public static SheetSquare house9 = new SheetSquare(32,7,0, SpriteSheet.background); 
	public static SheetSquare house10 = new SheetSquare(32,8,2, SpriteSheet.background);
	public static SheetSquare house11 = new SheetSquare(32,8,1, SpriteSheet.background);
	public static SheetSquare house11b = new SheetSquare(32,8,4, SpriteSheet.background);
	public static SheetSquare house11c = new SheetSquare(32,8,7, SpriteSheet.background);
	public static SheetSquare house11d = new SheetSquare(32,3,4, SpriteSheet.background);
	public static SheetSquare house12 = new SheetSquare(32,8,0, SpriteSheet.background);
	public static SheetSquare house12b = new SheetSquare(32,8,3, SpriteSheet.background);
	public static SheetSquare house12c = new SheetSquare(32,8,6, SpriteSheet.background);
	public static SheetSquare house12d = new SheetSquare(32,3,3, SpriteSheet.background);
	public static SheetSquare house13 = new SheetSquare(32,9,2, SpriteSheet.background);
	public static SheetSquare house13b = new SheetSquare(32,9,5, SpriteSheet.background);
	public static SheetSquare house13c = new SheetSquare(32,9,8, SpriteSheet.background);
	public static SheetSquare house13d = new SheetSquare(32,4,5, SpriteSheet.background);
	public static SheetSquare house14 = new SheetSquare(32,9,1, SpriteSheet.background);
	public static SheetSquare house14b = new SheetSquare(32,9,4, SpriteSheet.background);
	public static SheetSquare house14c = new SheetSquare(32,9,7, SpriteSheet.background);
	public static SheetSquare house14d = new SheetSquare(32,4,4, SpriteSheet.background);
	public static SheetSquare house15 = new SheetSquare(32,9,0, SpriteSheet.background);
	public static SheetSquare house15b = new SheetSquare(32,9,3, SpriteSheet.background);
	public static SheetSquare house15c = new SheetSquare(32,9,6, SpriteSheet.background);
	public static SheetSquare house15d = new SheetSquare(32,4,3, SpriteSheet.background);
	
	//***********************************************************************************************************
	// Player 1

	public static SheetSquare player1_front2 = new SheetSquare(32, 0, 0, SpriteSheet.players);
	public static SheetSquare player1_front1 = new SheetSquare(32, 1, 0, SpriteSheet.players);
	public static SheetSquare player1_front3 = new SheetSquare(32, 2, 0, SpriteSheet.players);
	public static SheetSquare player1_back2 = new SheetSquare(32, 0, 3, SpriteSheet.players);
	public static SheetSquare player1_back1 = new SheetSquare(32, 1, 3, SpriteSheet.players);
	public static SheetSquare player1_back3 = new SheetSquare(32, 2, 3, SpriteSheet.players);
	public static SheetSquare player1_side1 = new SheetSquare(32, 0, 1, SpriteSheet.players);
	public static SheetSquare player1_side2 = new SheetSquare(32, 1, 1, SpriteSheet.players);
	public static SheetSquare player1_side3 = new SheetSquare(32, 2, 1, SpriteSheet.players);
	
	//***********************************************************************************************************
	// Player 2
	
	public static SheetSquare player2_front2 = new SheetSquare(32, 9, 4, SpriteSheet.players);
	public static SheetSquare player2_front1 = new SheetSquare(32, 10, 4, SpriteSheet.players);
	public static SheetSquare player2_front3 = new SheetSquare(32, 11, 4, SpriteSheet.players);
	public static SheetSquare player2_back2 = new SheetSquare(32, 9, 7, SpriteSheet.players);
	public static SheetSquare player2_back1 = new SheetSquare(32, 10, 7, SpriteSheet.players);
	public static SheetSquare player2_back3 = new SheetSquare(32, 11, 7, SpriteSheet.players);
	public static SheetSquare player2_side1 = new SheetSquare(32, 9, 5, SpriteSheet.players);
	public static SheetSquare player2_side2 = new SheetSquare(32, 10, 5, SpriteSheet.players);
	public static SheetSquare player2_side3 = new SheetSquare(32, 11, 5, SpriteSheet.players);
	
	//***********************************************************************************************************
	// Ghost
	
	public static SheetSquare ghost_front2 = new SheetSquare(32, 7, 0, SpriteSheet.ghosts);
	public static SheetSquare ghost_front1 = new SheetSquare(32, 6, 0, SpriteSheet.ghosts);
	public static SheetSquare ghost_front3 = new SheetSquare(32, 8, 0, SpriteSheet.ghosts);
	public static SheetSquare ghost_back2 = new SheetSquare(32, 6, 3, SpriteSheet.ghosts);
	public static SheetSquare ghost_back1 = new SheetSquare(32, 7, 3, SpriteSheet.ghosts);
	public static SheetSquare ghost_back3 = new SheetSquare(32, 8, 3, SpriteSheet.ghosts);
	public static SheetSquare ghost_side1 = new SheetSquare(32, 6, 1, SpriteSheet.ghosts);
	public static SheetSquare ghost_side2 = new SheetSquare(32, 7, 1, SpriteSheet.ghosts);
	public static SheetSquare ghost_side3 = new SheetSquare(32, 8, 1, SpriteSheet.ghosts);
	
	//***********************************************************************************************************
	//bomberman 
	//1 = repos, 2 = pied droit, 3 = pied droit bis, 4 = pied gauche, 5 = pied gauche bis
	
	public static SheetSquare immu = new SheetSquare(32, 4, 4, SpriteSheet.bomberman); 
	
	public static SheetSquare bomberman1_dead = new SheetSquare(32, 5, 0, SpriteSheet.bomberman);
	public static SheetSquare bomberman1_front2 = new SheetSquare(32, 0, 0, SpriteSheet.bomberman); 
	public static SheetSquare bomberman1_front3 = new SheetSquare(32, 1, 0, SpriteSheet.bomberman); 
	public static SheetSquare bomberman1_front1 = new SheetSquare(32, 2, 0, SpriteSheet.bomberman); 
	public static SheetSquare bomberman1_front4 = new SheetSquare(32, 3, 0, SpriteSheet.bomberman);
	public static SheetSquare bomberman1_front5 = new SheetSquare(32, 4, 0, SpriteSheet.bomberman); 
	public static SheetSquare bomberman1_back2 = new SheetSquare(32, 0, 2, SpriteSheet.bomberman);
	public static SheetSquare bomberman1_back3 = new SheetSquare(32, 1, 2, SpriteSheet.bomberman);
	public static SheetSquare bomberman1_back1 = new SheetSquare(32, 2, 2, SpriteSheet.bomberman);
	public static SheetSquare bomberman1_back4 = new SheetSquare(32, 3, 2, SpriteSheet.bomberman);
	public static SheetSquare bomberman1_back5 = new SheetSquare(32, 4, 2, SpriteSheet.bomberman);
	public static SheetSquare bomberman1_side2 = new SheetSquare(32, 0, 1, SpriteSheet.bomberman);
	public static SheetSquare bomberman1_side3 = new SheetSquare(32, 1, 1, SpriteSheet.bomberman);
	public static SheetSquare bomberman1_side1 = new SheetSquare(32, 2, 1, SpriteSheet.bomberman);
	public static SheetSquare bomberman1_side4 = new SheetSquare(32, 3, 1, SpriteSheet.bomberman);
	public static SheetSquare bomberman1_side5 = new SheetSquare(32, 4, 1, SpriteSheet.bomberman);
	
	public static SheetSquare bomberman2_dead = new SheetSquare(32, 7, 3, SpriteSheet.bomberman); 
	public static SheetSquare bomberman2_front2 = new SheetSquare(32, 2, 4, SpriteSheet.bomberman); 
	public static SheetSquare bomberman2_front3 = new SheetSquare(32, 1, 4, SpriteSheet.bomberman); 
	public static SheetSquare bomberman2_front1 = new SheetSquare(32, 0, 4, SpriteSheet.bomberman); 
	public static SheetSquare bomberman2_back2 = new SheetSquare(32, 2, 6, SpriteSheet.bomberman);
	public static SheetSquare bomberman2_back3 = new SheetSquare(32, 1, 6, SpriteSheet.bomberman);
	public static SheetSquare bomberman2_back1 = new SheetSquare(32, 0, 6, SpriteSheet.bomberman);
	public static SheetSquare bomberman2_side2 = new SheetSquare(32, 2, 5, SpriteSheet.bomberman);
	public static SheetSquare bomberman2_side3 = new SheetSquare(32, 1, 5, SpriteSheet.bomberman);
	public static SheetSquare bomberman2_side1 = new SheetSquare(32, 0, 5, SpriteSheet.bomberman);
	
	public static SheetSquare link_dead = new SheetSquare(32, 40, 0, 0, SpriteSheet.death); 
	public static SheetSquare link_front1 = new SheetSquare(32, 0, 7, SpriteSheet.bomberman); 
	public static SheetSquare link_front2 = new SheetSquare(32, 1, 7, SpriteSheet.bomberman); 
	public static SheetSquare link_front3 = new SheetSquare(32, 2, 7, SpriteSheet.bomberman); 
	public static SheetSquare link_front4 = new SheetSquare(32, 3, 7, SpriteSheet.bomberman); 
	public static SheetSquare link_front5 = new SheetSquare(32, 4, 7, SpriteSheet.bomberman); 
	public static SheetSquare link_front6 = new SheetSquare(32, 5, 7, SpriteSheet.bomberman); 
	public static SheetSquare link_front7 = new SheetSquare(32, 6, 7, SpriteSheet.bomberman); 
	public static SheetSquare link_front8 = new SheetSquare(32, 7, 7, SpriteSheet.bomberman); 
	public static SheetSquare link_front9 = new SheetSquare(32, 8, 7, SpriteSheet.bomberman); 
	public static SheetSquare link_front10 = new SheetSquare(32, 9, 7, SpriteSheet.bomberman); 
	
	public static SheetSquare link_back1 = new SheetSquare(32, 0, 9, SpriteSheet.bomberman); 
	public static SheetSquare link_back2 = new SheetSquare(32, 1, 9, SpriteSheet.bomberman); 
	public static SheetSquare link_back3 = new SheetSquare(32, 2, 9, SpriteSheet.bomberman); 
	public static SheetSquare link_back4 = new SheetSquare(32, 3, 9, SpriteSheet.bomberman); 
	public static SheetSquare link_back5 = new SheetSquare(32, 4, 9, SpriteSheet.bomberman); 
	public static SheetSquare link_back6 = new SheetSquare(32, 5, 9, SpriteSheet.bomberman); 
	public static SheetSquare link_back7 = new SheetSquare(32, 6, 9, SpriteSheet.bomberman); 
	public static SheetSquare link_back8 = new SheetSquare(32, 7, 9, SpriteSheet.bomberman); 
	public static SheetSquare link_back9 = new SheetSquare(32, 8, 9, SpriteSheet.bomberman); 
	public static SheetSquare link_back10 = new SheetSquare(32, 9, 9, SpriteSheet.bomberman); 
	
	public static SheetSquare link_side1 = new SheetSquare(32, 0, 8, SpriteSheet.bomberman); 
	public static SheetSquare link_side2 = new SheetSquare(32, 1, 8, SpriteSheet.bomberman); 
	public static SheetSquare link_side3 = new SheetSquare(32, 2, 8, SpriteSheet.bomberman); 
	public static SheetSquare link_side4 = new SheetSquare(32, 3, 8, SpriteSheet.bomberman); 
	public static SheetSquare link_side5 = new SheetSquare(32, 4, 8, SpriteSheet.bomberman); 
	public static SheetSquare link_side6 = new SheetSquare(32, 5, 8, SpriteSheet.bomberman); 
	public static SheetSquare link_side7 = new SheetSquare(32, 6, 8, SpriteSheet.bomberman); 
	public static SheetSquare link_side8 = new SheetSquare(32, 7, 8, SpriteSheet.bomberman); 
	public static SheetSquare link_side9 = new SheetSquare(32, 8, 8, SpriteSheet.bomberman); 
	public static SheetSquare link_side10 = new SheetSquare(32, 9, 8, SpriteSheet.bomberman); 
	public static SheetSquare link_side11 = new SheetSquare(32, 10, 8, SpriteSheet.bomberman); 
	
	public static SheetSquare zelda_dead = new SheetSquare(32, 6, 3, SpriteSheet.bomberman); 
	public static SheetSquare zelda_front1 = new SheetSquare(32, 5, 4, SpriteSheet.bomberman); 
	public static SheetSquare zelda_front2 = new SheetSquare(32, 6, 4, SpriteSheet.bomberman); 
	public static SheetSquare zelda_front3 = new SheetSquare(32, 7, 4, SpriteSheet.bomberman); 
	public static SheetSquare zelda_front4 = new SheetSquare(32, 8, 4, SpriteSheet.bomberman); 
	public static SheetSquare zelda_front5 = new SheetSquare(32, 9, 4, SpriteSheet.bomberman); 
	public static SheetSquare zelda_back1 = new SheetSquare(32, 5, 6, SpriteSheet.bomberman);
	public static SheetSquare zelda_back2 = new SheetSquare(32, 6, 6, SpriteSheet.bomberman);
	public static SheetSquare zelda_back3 = new SheetSquare(32, 7, 6, SpriteSheet.bomberman);
	public static SheetSquare zelda_back4 = new SheetSquare(32, 8, 6, SpriteSheet.bomberman);
	public static SheetSquare zelda_side1 = new SheetSquare(32, 5, 5, SpriteSheet.bomberman);
	public static SheetSquare zelda_side2 = new SheetSquare(32, 6, 5, SpriteSheet.bomberman);
	public static SheetSquare zelda_side3 = new SheetSquare(32, 7, 5, SpriteSheet.bomberman);
	public static SheetSquare zelda_side4 = new SheetSquare(32, 8, 5, SpriteSheet.bomberman);
	public static SheetSquare zelda_side5 = new SheetSquare(32, 9, 5, SpriteSheet.bomberman);
		
	//***********************************************************************************************************
	
		
	public SheetSquare(int size, int x, int y, SpriteSheet sheet){
		/*
		 * x,y sont les coordon�es du coin superieur gauche du carr� que l'on veut charger.
		 * Ces coordon�es ne sont pas en pixels mes bien en unit� de size
		 * size = la taille du carr� qu'on veut recuperer en pixels
		 */
		SQUARESIZEx = size;
		SQUARESIZEy = size;
		squarePixels = new int[size*size];
		this.sheet = sheet;
		this.xSst = x*size ;
		this.ySst = y*size ;
		loadSquare() ;		
	}
	
	
	public SheetSquare(int xsize, int ysize, int x, int y, SpriteSheet sheet){
		/*
		 * x,y sont les coordon���es du coin superieur gauche du carr��� que l'on veut charger.
		 * Ces coordon���es ne sont pas en pixels mes bien en unit��� de size
		 * size = la taille du carr��� qu'on veut recuperer en pixels en x et en y
		 */
		SQUARESIZEx = xsize; //seulement si carre
		SQUARESIZEy = ysize; //seulement si carre
		squarePixels = new int[xsize*ysize];
		this.sheet = sheet;
		this.xSst = x*xsize ;
		this.ySst = y*ysize ;
		// position du pixel de debut de parcours
		loadSquare() ;		
	}
	
	
	private void loadSquare(){
		/*
		 * Recupere les pixels correspondant au carr� dans spriteSheetPixels
		 */
		for (int y = 0; y < SQUARESIZEy; y++){
			for (int x = 0; x < SQUARESIZEx; x++){
				squarePixels[x + y*SQUARESIZEx] = sheet.getSpriteSheetPixels()[(this.xSst + x) + (this.ySst + y)*sheet.getWidth()] ;
			}
		}		
	}
	
	
	public int[] getSquarePixels(){return squarePixels;}
	public SpriteSheet getSheet(){return sheet;}
	public int getSQUARESIZEx(){return SQUARESIZEx;}
	public int getSQUARESIZEy(){return SQUARESIZEy;}

}
