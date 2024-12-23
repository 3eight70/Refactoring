package bomberman.pause;

import bomberman.Bomberman;

public class MusicOffStateGame implements MusicPauseState {
    @Override
    public void toggleMusicPause(Bomberman bomberman) {
        bomberman.setMusicPauseState(new MusicOnStateGame());
        System.out.println("Music paused");
    }
}
