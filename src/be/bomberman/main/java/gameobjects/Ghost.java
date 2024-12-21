package gameobjects;


import affichage.Screen;
import affichage.SheetSquare;
import inputs.KeyboardInput;
import levels.Level;

public class Ghost extends Mob {

    private final KeyboardInput input;

    public Ghost(Level level, int x, int y, KeyboardInput input) {
        super(level, x, y, 1, 1);
        this.input = input;

    }

    @Override
    public boolean collision(int xa, int ya) {
        return false;
    }

    @Override
    public void update() {

        int xa = 0, ya = 0;

        if (input.T) {
            ya--;
        }
        if (input.G) {
            ya++;
        }
        if (input.F) {
            xa--;
        }
        if (input.H) {
            xa++;
        }

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            isMoving = true;
        } else {
            isMoving = false;
        }

        if (animation < 130000) animation++; // augmente de 60 toute les secondes
        else animation = 0;
    }

    public void render(Screen screen) {
        boolean xMirror = false;
        boolean yMirror = false;

        if (movingDir == 0) {
            square = SheetSquare.ghost_back1;
            if (isMoving) {
                if (animation % 60 < 30) {
                    square = SheetSquare.ghost_back2;
                } else {
                    square = SheetSquare.ghost_back3;
                }
            }
        }
        if (movingDir == 1) {
            square = SheetSquare.ghost_front1;
            if (isMoving) {
                if (animation % 60 < 30) {
                    square = SheetSquare.ghost_front2;
                } else {
                    square = SheetSquare.ghost_front3;
                }
            }
        }
        if (movingDir == 2) {
            square = SheetSquare.ghost_side1;
            if (isMoving) {
                if (animation % 20 < 10) {
                    square = SheetSquare.ghost_side2;
                } else {
                    square = SheetSquare.ghost_side3;
                }
            }
        }
        if (movingDir == 3) {
            square = SheetSquare.ghost_side1;
            if (isMoving) {
                if (animation % 20 < 10) {
                    square = SheetSquare.ghost_side2;
                } else {
                    square = SheetSquare.ghost_side3;
                }
            }
            xMirror = true;
        }

        screen.renderEntity(x, y, square, 32, xMirror, yMirror, 0xffffffff);

    }

    @Override
    public void remove() {


    }

}
