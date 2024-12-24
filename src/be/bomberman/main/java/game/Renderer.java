//package game;
//
//import affichage.Screen;
//import levels.Level;
//
//import java.awt.Graphics;
//import java.awt.image.BufferStrategy;
//
//public class Renderer {
//    private int width;
//    private int height;
//    private Screen screen;
//
//    public Renderer(int width, int height) {
//        this.width = width;
//        this.height = height;
//        this.screen = new Screen(width, height);
//    }
//
//    public void render(Level level) {
//        BufferStrategy bs = level.getCanvas().getBufferStrategy();
//        if (bs == null) {
//            level.getCanvas().createBufferStrategy(3);
//            return;
//        }
//
//        screen.clear();
//        level.render(screen);
//
//        Graphics g = bs.getDrawGraphics();
//        g.drawImage(screen.getImage(), 0, 0, width, height, null);
//        g.dispose();
//        bs.show();
//    }
//}
