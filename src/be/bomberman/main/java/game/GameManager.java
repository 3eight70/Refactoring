//package game;
//
//import inputs.InputHandler;
//import levels.LevelManager;
//
//import javax.swing.Renderer;
//
//public class GameManager implements Runnable {
//    private boolean running;
//    private Thread thread;
//    private Renderer renderer;
//    private InputHandler inputHandler;
//    private LevelManager levelManager;
//
//    public GameManager(int width, int height) {
//        renderer = new Renderer(width, height);
//        inputHandler = new InputHandler(this);
//        levelManager = new LevelManager(inputHandler);
//    }
//
//    public synchronized void start() {
//        running = true;
//        thread = new Thread(this, "GameThread");
//        thread.start();
//    }
//
//    public synchronized void stop() {
//        running = false;
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void run() {
//        long lastTime = System.nanoTime();
//        double nsPerTick = 1000000000D / 60D;
//        double delta = 0;
//
//        while (running) {
//            long now = System.nanoTime();
//            delta += (now - lastTime) / nsPerTick;
//            lastTime = now;
//
//            while (delta >= 1) {
//                update();
//                delta--;
//            }
//            render();
//        }
//    }
//
//    private void update() {
//        inputHandler.update();
//        levelManager.update();
//    }
//
//    private void render() {
//        renderer.render(levelManager.getCurrentLevel());
//    }
//
//    public static void main(String[] args) {
//        GameManager gameManager = new GameManager(608, 544);
//        gameManager.start();
//    }
//}
