import CustomMathLib.Point;

import java.util.ArrayList;

public class PhysicsThread extends Thread{
    protected ArrayList<Ball> balls = new ArrayList<Ball>();
    private String threadName = null;

    public PhysicsThread(ArrayList<Ball> balls, String nameOfThread){
        this.balls = balls; //passes the class we wish to modify
        this.threadName = nameOfThread; //way to keep track of threads
    }

    public void run() {
        initializeObjects();
        update();

        System.out.println("failsafe Reached");
    }

    void update(){
        for(int i=0; i<100000; i++){
            for (Ball ball: balls) {
                ball.update("physicsThread");
            }

            if(MainClass.debugThreadCycles)System.out.println("Physics Loop Completed");
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void initializeObjects(){
        for (Ball ball: balls) {
            ball.applyForce(threadName, new Point((Math.random()-0.5)*6,(Math.random()-0.5)*10));
        }
    }
}