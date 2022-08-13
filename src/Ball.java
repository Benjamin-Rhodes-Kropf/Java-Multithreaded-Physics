import CustomMathLib.Point;

import java.awt.*;

public class Ball {
    private double x;
    private double y;
    private double dx;
    private double dy;
    private int rad;
    private double elasticity;
    private Color color;

    //Constructors
    public Ball() {
        this.x = 0.0;
        this.y = 0.0;
        this.rad = 20;
        this.color = Color.magenta;
        this.elasticity = 0.5;
    }
    public Ball(double x, double y, int rad) {
        this.x = x;
        this.y = y;
        this.rad = rad;
    }
    public Ball(double x, double y, int rad, Color color) {
        this.x = x;
        this.y = y;
        this.rad = rad;
        this.color = color;
    }
    public Ball(double x, double y, int rad, double elasticity, Color color) {
        this.x = x;
        this.y = y;
        this.rad = rad;
        this.elasticity = elasticity;
        this.color = color;
    }

    public synchronized void update(String threadName){
        //apply gravity
        applyForce(threadName, new Point(0,0.0196));

        //wall bounce
        if((y + rad)> MainClass.SCREENHEIGHT){ // bottom
            dx *= 0.95;
            dy *= -elasticity;
            translateLocation(threadName, new Point(dx,dy));
        }
        if((y - rad < 0)){ // top
            dx *= 0.95;
            dy *= -elasticity;
            translateLocation(threadName, new Point(dx,dy));
        }
        if(x + rad > MainClass.SCREENWIDTH){ //left
            dx *= -elasticity;
            translateLocation(threadName, new Point(dx,dy));
        }
        if((x - rad)< 0){ //right
            dx *= -elasticity;
            translateLocation(threadName, new Point(dx,dy));
        }

        //move ball based on velocity
        translateLocation(threadName, new Point(dx,dy));
    }


    //GET
    public synchronized Point getLocation(){
        if(MainClass.debugThreadCycles)System.out.println(" is reading ball - value is: (" + x + "," + y + ")");
        return new Point(x,y);
    }
    public synchronized Point getVelocity(){
        if(MainClass.debugThreadCycles)System.out.println(" is reading ball rad - value is: (" + dx + "," + dy + ")");
        return new Point(dx, dy);
    }
    public synchronized double getElasticity(){
        if(MainClass.debugThreadCycles)System.out.println(" is reading ball elasticity - value is: (" + elasticity + ")");
        return elasticity;
    }
    public synchronized int getRad(){
        if(MainClass.debugThreadCycles)System.out.println(" is reading ball rad - value is: (" + rad + ")");
        return rad;
    }
    public synchronized Color getColor(){
        if(MainClass.debugThreadCycles)System.out.println(" is reading ball color - value is: (" + color + ")");
        return color;
    }


    //SET
    public synchronized void setLocation(String nameOfThread, Point set){
        x += set.x;
        y += set.y;
        if(MainClass.debugThreadCycles)System.out.println(nameOfThread + " is setting Location - value is: (" + x + "," + y + ")");
    }
    public synchronized void translateLocation(String nameOfThread, Point translation){
        x += translation.x;
        y += translation.y;
        if(MainClass.debugThreadCycles)System.out.println(nameOfThread + " is setting - value is: (" + x+ "," + y + ")");
    }
    public synchronized void setVelocity(String nameOfThread, Point set){
        dx = set.x;
        dy = set.y;
        if(MainClass.debugThreadCycles)System.out.println(nameOfThread + " is setting Velocity - value is: (" + dx+ "," + dy + ")");
    }
    public synchronized double setElasticity(String nameOfThread, double set){
        elasticity = set;
        if(MainClass.debugThreadCycles)System.out.println(" is setting ball elasticity - value is: (" + elasticity + ")");
        return elasticity;
    }
    public synchronized void applyForce(String nameOfThread, Point set){
        dx += set.x;
        dy += set.y;
        if(MainClass.debugThreadCycles)System.out.println(nameOfThread + " is setting Velocity - value is: (" + dx + "," + dy + ")");
    }

}

