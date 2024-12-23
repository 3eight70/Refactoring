package bomberman.pause;

import bomberman.Bomberman;

public class MusicOnStateGame implements MusicPauseState {
    @Override
    public void toggleMusicPause(Bomberman bomberman) {
        bomberman.setMusicPauseState(new MusicOffStateGame());
        System.out.println("Music paused");
    }
}
