import CustomMathLib.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Renderer extends JPanel implements ActionListener{

    Timer clockCycle =new Timer(0, this);
    ArrayList<Ball> balls;


    Renderer(ArrayList<Ball> balls){
        clockCycle.start();
        this.balls = balls;
    }

    //runs on clock cycle
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.RED);

        for (Ball ball:balls) {
            g2.setPaint(ball.getColor());
            CustomMathLib.Point ballLocation = new Point(ball.getLocation()); // prevents 2 draw calls
            int radius = ball.getRad();
            int diameter = ball.getRad()*2;
            Ellipse2D oval = new Ellipse2D.Double(ballLocation.x - radius, ballLocation.y - radius, diameter, diameter);
            g2.fill(oval);
        }
        if(MainClass.debugThreadCycles)System.out.println("Render Loop Completed");
    }

    public void actionPerformed(ActionEvent ev){
        if(ev.getSource()== clockCycle){
            repaint();// this will call at every clockcycle
        }
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
    }
}