package levels.tiles;

public class AnimatedSolidTile {
    private final Tile[] frames;

    public AnimatedSolidTile(Tile... frames) {
        this.frames = frames;
    }

    public Tile getCurrentFrame(int animation) {
        return frames[(animation / 10) % frames.length];
    }
}
