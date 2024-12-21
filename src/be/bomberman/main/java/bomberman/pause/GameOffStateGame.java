package bomberman.pause;

import bomberman.Bomberman;

public class GameOffStateGame implements GamePauseState {
    @Override
    public void toggleGamePause(Bomberman bomberman) {
        bomberman.setGamePauseState(new GameOnStateGame());
        System.out.println("Game started");
    }
}
