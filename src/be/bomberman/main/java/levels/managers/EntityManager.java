package levels.managers;

import affichage.Screen;
import gameobjects.Bomb;
import gameobjects.Deflagration;
import gameobjects.GameObject;
import gameobjects.Mob;
import gameobjects.Player;
import gameobjects.bonus.Bonus;
import gameobjects.bonus.MusicObserver;
import gameobjects.bonus.UsedBonus;
import levels.Level;
import levels.tiles.Tile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class EntityManager {

    private List<MusicObserver> observers = new ArrayList<>();

    private List<Mob> players = new LinkedList<>();
    private List<Bomb> bombs = new LinkedList<>();
    private List<Deflagration> deflagrations = new LinkedList<>();
    private List<Bonus> bonusses = new LinkedList<>();
    private List<UsedBonus> usedBonusses = new LinkedList<>();
    private final Level currentLevel;

    Random r = new Random();

    public EntityManager(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void addObserver(MusicObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String sound) {
        for (MusicObserver observer : observers) {
            observer.notify(sound);
        }
    }

    public int[] bonusCoord() {

        int[] coord = new int[2];
        int x = 6;
        int y = 0;

        if (currentLevel.getLevel().equals("level1")) {
            while (currentLevel.getTile(x, y) != Tile.grass) {
                x = r.nextInt(currentLevel.getWidth());
                y = r.nextInt(currentLevel.getHeight());

            }
        } else if (currentLevel.getLevel().equals("level2")) {
            while (currentLevel.getTile(x, y) != Tile.grassLevel2) {
                x = r.nextInt(currentLevel.getWidth() - 4) + 4;
                y = r.nextInt(currentLevel.getHeight());

            }
        }

        coord[0] = x << 5;
        coord[1] = y << 5;

        return coord;
    }

    public void addPlayer(Mob entity) {
        players.add(entity);
    }

    public void updateEntities() {
        for (Mob player : players) {
            player.update();
        }
        for (Bomb bomb : bombs) {
            bomb.update();
        }
        for (Deflagration deflagration : deflagrations) {
            deflagration.update();
        }
        for (Bonus bonus : bonusses) {
            bonus.update();
        }
        for (UsedBonus usedBonus : usedBonusses) {
            usedBonus.update();
        }
    }

    public void addBomb(Bomb bomb) {
        bombs.add(bomb);
    }

    public void addBonus(Bonus bonus) {
        bonusses.add(bonus);
    }

    public void addUsedBonus(UsedBonus usedBonus) {
        usedBonusses.add(usedBonus);
    }

    public void addBonus(Level level, String bonusType) {
        bonusses.add(new Bonus(level, bonusCoord(), bonusType));
    }

    public void addDeflagration(Deflagration deflagration) {
        deflagrations.add(deflagration);
    }

    public void clearRemovedEntities() {
        clearList(bombs);
        clearList(deflagrations);
        clearList(bonusses);
        clearList(usedBonusses);
    }

    private void clearList(List<? extends GameObject> list) {
        list.removeIf(GameObject::isRemoved);
    }

    public boolean dieBonusByDef(GameObject object) {
        for (Deflagration deflagration : deflagrations) {
            if (deflagration.burnedByCollision(object.getX(), object.getY())) {
                return true;
            }
        }
        return false;
    }

    public void checkBonusCollisions(Player player) {
        for (UsedBonus usedBonus : usedBonusses) {
            int x = usedBonus.x;
            int y = usedBonus.y;
            if (player.touchUsedBonus(x, y)) {
                usedBonus.doUsedBonusActionOnCollision(player);
            }
        }
    }

    public boolean hasBombAt(int x, int y) {
        for (Bomb bomb : bombs) {
            if (bomb.getYourBomb(x, y)) {
                return true;
            }
        }
        return false;
    }

    public void takeBonus(Player player) {
        for (Bonus bonus : bonusses) {
            if (bonus.getYourBonus(player.getX(), player.getY())) {
                player.setCarryBonus(true);
                bonus.setRemoved(true);
                System.out.println(player.getName());

                String bonusType = bonus.getType();
                notifyObservers(bonusType);

                switch (bonus.getType()) {
                    case "firePower":
                        player.setBonusCarried("firePower");
                        player.setBonusTimer(900);
                        break;
                    case "fetaBonus":
                        player.setBonusCarried("fetaBonus");
                        break;
                    case "rangeBonus":
                        player.setBonusCarried("rangeBonus");
                        break;
                    case "lifeBonus":
                        player.setBonusCarried("lifeBonus");
                        break;
                    case "bombBonus":
                        player.setBonusCarried("bombBonus");
                        break;
                }
            }
        }
    }

    public void dieByDef(Player player) {
        var playerLife = player.getLife();
        var playerImmunisation = player.getImmunisation();
        for (Deflagration deflagration : deflagrations) {
            if (deflagration.burnedByCollision(player.getX(), player.getY())) {
                player.setNotifies(player.getNotifies() + 1);
                if (playerLife < 1) player.setDead(true);
                player.notifyUser();
                if (playerImmunisation > 300) playerImmunisation = 0;
                if (playerImmunisation == 0) {
                    if (playerLife > 0) player.setLife(playerLife - 1);
                    if (playerLife > 0) {
                        notifyObservers("ouch");
                    }
                    if (playerLife == 0) {
                        notifyObservers("lastlife");
                    }
                    break;
                }
            }
        }
    }

    public void renderEntities(Screen screen) {
        for (UsedBonus usedBonus : usedBonusses) {
            usedBonus.render(screen);
        }
        for (Bonus bonus : bonusses) {
            bonus.render(screen);
        }
        for (Bomb bomb : bombs) {
            bomb.render(screen);
        }
        for (Mob player : players) {
            player.render(screen);
        }
        for (Deflagration deflagration : deflagrations) {
            deflagration.render(screen);
        }
    }
}
