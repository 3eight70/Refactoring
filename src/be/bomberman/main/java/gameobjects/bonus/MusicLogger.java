package gameobjects.bonus;

import bomberman.Bomberman;

public class MusicLogger implements MusicObserver {
    @Override
    public void notify(String sound) {
        if (!Bomberman.musicIsPaused) {
            System.out.println(sound);
        }
    }
}
