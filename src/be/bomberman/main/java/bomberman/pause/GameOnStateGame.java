package bomberman.pause;

import bomberman.Bomberman;

public class GameOnStateGame implements GamePauseState {
    @Override
    public void toggleGamePause(Bomberman bomberman) {
        bomberman.setGamePauseState(new GameOffStateGame());
        System.out.println("Game paused");
    }
}
