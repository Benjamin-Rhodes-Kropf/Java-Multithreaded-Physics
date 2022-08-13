import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainClass {
    public static boolean debugDrawCalls = false;
    public static boolean debugThreadCycles = false;

    public static int SCREENWIDTH = 2000;
    public static int SCREENHEIGHT = 800;

    public static void main(String[] args) {
        ArrayList<Ball> balls = new ArrayList<Ball>();
        for(int i = 0; i < 800; i++){
            balls.add(new Ball(SCREENWIDTH/2,SCREENHEIGHT/2, (int)(Math.random()*10+5), Math.random()*0.25+0.75, new Color((int)(Math.random()*100 +125),(int)(Math.random()*100 +125),(int)(Math.random()*100 +125))));
        }

        //START THREADS
        Thread physicsThread = new PhysicsThread(balls, "physicsThread");
        physicsThread.start();

        //Setup Window
        Renderer renderEngine = new Renderer(balls);
        JFrame jFrame = new JFrame();
        jFrame.add(renderEngine);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(SCREENWIDTH+17, SCREENHEIGHT+38);
    }
}