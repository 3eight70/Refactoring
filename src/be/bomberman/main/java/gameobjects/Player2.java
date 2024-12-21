package gameobjects;


import affichage.Screen;
import affichage.SheetSquare;
import bomberman.Bomberman;
import inputs.KeyboardInput;
import levels.Level;

public class Player2 extends Player {

    private final Bomberman bomberman;

    public Player2(Level level, int x, int y, String name, KeyboardInput input, Bomberman bomberman) {
        super(level, x, y, name, input, bomberman);
        this.bomberman = bomberman;
        // TODO Auto-generated constructor stub
    }

    public void update() {
        super.update();

        int xa = 0, ya = 0;
        String onPlaceBombe = "n";

        if (!bomberman.jouerEnLigne) {

            if (input.Z) {
                ya--;
            }
            if (input.S) {
                ya++;
            }
            if (input.Q) {
                xa--;
            }
            if (input.D) {
                xa++;
            }
            if (input.A && bombRate == 0 && Bomb.canBePlaced(x, y)) {
                Bomb bomb = new BasicBomb(x, y, level, this);
                Level.bombs.add(bomb);
                bombRate = BasicBomb.bombRate;
            }
            if (carryBonus && input.E) {
                if (bonusCarried == "firePower") {
                    if (useRate == 0 && bonusTimer > 0) {
                        useFireBonus();
                    }
                }
                if (bonusCarried == "fetaBonus") {
                    useFetaBonus();
                    System.out.println("yep");
                }

            }
        } else {
            //Verifier si c'est le personnage choisi
            if (name.equals(bomberman.playerName)) {
                //ajout des variables
                if (input.up) {
                    ya--;
                }
                if (input.down) {
                    ya++;
                }
                if (input.left) {
                    xa--;
                }
                if (input.right) {
                    xa++;
                }
                if (input.space && bombRate == 0 && Bomb.canBePlaced(x, y)) {
                    Bomb bomb = new BasicBomb(x, y, level, this);
                    Level.bombs.add(bomb);
                    bombRate = BasicBomb.bombRate;
                    onPlaceBombe = "o";
                }
                //on previens le serveur de notre mouvement et de si on place une bombe
                System.out.println("On envoie au serveur: " + bomberman.playerName + "-" + bomberman.player2.x + "_" + bomberman.player2.y + "_" + onPlaceBombe);
                if (bomberman.protocol.equals("UDP")) {
                    bomberman.socketClient.sendData((bomberman.playerName + "-" + bomberman.player2.x + "_" + bomberman.player2.y + "_" + onPlaceBombe).getBytes());
                } else if (bomberman.protocol.equals("TCP")) {
                    bomberman.socketClientTCP.sendData(bomberman.playerName + "-" + bomberman.player2.x + "_" + bomberman.player2.y + "_" + onPlaceBombe);
                }
            } else {
                int nouvellePosX = Integer.valueOf(bomberman.msg.trim().split("-")[1].trim().split("_")[0]);
                int nouvellePosY = Integer.valueOf(bomberman.msg.trim().split("-")[1].trim().split("_")[1]);
                //on bouge a la position ou se trouve le vrai joueur
                xa = nouvellePosX - bomberman.anciennePosX[1];
                ya = nouvellePosY - bomberman.anciennePosY[1];

                //System.out.println("On bouge vers: "+(nouvellePosX - bomberman.anciennePosX)+"_"+(nouvellePosY - bomberman.anciennePosY));

                bomberman.anciennePosX[1] = nouvellePosX;
                bomberman.anciennePosY[1] = nouvellePosY;

                if (bomberman.ilFautPlacerUneBombe) {
                    System.out.println("ON PLACE UNE BOMBE");
                    bomberman.ilFautPlacerUneBombe = false;
                    Bomb bomb = new BasicBomb(bomberman.coordBombe[0], bomberman.coordBombe[1], level, this);
                    Level.bombs.add(bomb);
                    bombRate = BasicBomb.bombRate;

                }
            }
        }

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            isMoving = true;
        } else {
            isMoving = false;
        }


    }


    public void render(Screen screen) {
        boolean xMirror = false;
        boolean yMirror = false;

        if (movingDir == 0) {
            square = SheetSquare.player2_back1;
            if (isMoving) {
                if (animation % 60 < 30) {
                    square = SheetSquare.player2_back2;
                } else {
                    square = SheetSquare.player2_back3;
                }
            }
        }
        if (movingDir == 1) {
            square = SheetSquare.player2_front1;
            if (isMoving) {
                if (animation % 60 < 30) {
                    square = SheetSquare.player2_front2;
                } else {
                    square = SheetSquare.player2_front3;
                }
            }
        }
        if (movingDir == 2) {
            square = SheetSquare.player2_side1;
            if (isMoving) {
                if (animation % 20 < 10) {
                    square = SheetSquare.player2_side2;
                } else {
                    square = SheetSquare.player2_side3;
                }
            }
        }
        if (movingDir == 3) {
            square = SheetSquare.player2_side1;
            if (isMoving) {
                if (animation % 20 < 10) {
                    square = SheetSquare.player2_side2;
                } else {
                    square = SheetSquare.player2_side3;
                }
            }
            xMirror = true;
        }
        if (immunisation < 300) {
            if (immunisation % 50 < 10) square = SheetSquare.immu2;
        }

        screen.renderEntity(x, y, square, 32, xMirror, yMirror, 0xffffffff);

    }

}
