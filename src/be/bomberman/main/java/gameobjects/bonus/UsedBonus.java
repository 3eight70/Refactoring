package gameobjects.bonus;

import gameobjects.GameObject;
import gameobjects.Player;
import levels.Level;

public abstract class UsedBonus extends GameObject {

    public static int useRate = 20;
    protected Player player;

    public UsedBonus(Level level, int x, int y) {
        super(level);
        this.x = x;
        this.y = y;
        center();
    }

    public abstract void doUsedBonusAction(Player player);

    public abstract void doUsedBonusActionOnCollision(Player player);
}
