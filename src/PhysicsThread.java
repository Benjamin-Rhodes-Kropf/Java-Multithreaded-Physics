import ExtraMath.Point;

import java.util.ArrayList;

public class PhysicsThread extends Thread{
    protected ArrayList<Ball> balls = new ArrayList<Ball>();
    private String threadName = null;

    public PhysicsThread(ArrayList<Ball> balls, String nameOfThread){
        this.balls = balls; //passes the class we wish to modify
        this.threadName = nameOfThread; //way to keep track of threads
    }

    public void run() {
        for (Ball ball: balls) {
            ball.applyForce(threadName, new Point((Math.random()-0.5)*2,(Math.random()-0.5)*4));
        }

        for(int i=0; i<100000; i++){
            for (Ball ball: balls) {
                //gravity
                ball.applyForce(threadName, new Point(0,0.0196));
                //floor bounce
                if((ball.getLocation().y+ball.getRad()*2)> 356-5){
                    ball.setVelocity(threadName, new Point(ball.getVelocity().x*0.95, ball.getVelocity().y*-ball.getElasticity()));
                    ball.translateLocation(threadName, new Point(ball.getVelocity().x,ball.getVelocity().y*0.25));
                }
                //walls bounce
                if((ball.getLocation().x+ball.getRad()*2)> MainClass.SCREENWIDTH-15){
                    ball.setVelocity(threadName, new Point(ball.getVelocity().x*-1, ball.getVelocity().y));
                }
                if((ball.getLocation().x)< -1){
                    ball.setVelocity(threadName, new Point(ball.getVelocity().x*-1, ball.getVelocity().y));
                }

                ball.translateLocation(threadName, new Point(ball.getVelocity()));
            }
            if(MainClass.debugThreadCycles)System.out.println("Physics Loop Completed");
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("failsafe Reached");
    }
}