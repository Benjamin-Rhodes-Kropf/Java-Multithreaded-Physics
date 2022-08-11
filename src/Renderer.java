import ExtraMath.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Renderer extends JPanel implements ActionListener{

    Timer timer=new Timer(0, this);
    ArrayList<Ball> balls = new ArrayList<Ball>();

    Renderer(ArrayList<Ball> balls){
        timer.start();// Start the timer here.
        this.balls = balls;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.RED);

        for (Ball ball:balls) {
            g2.setPaint(ball.color);
            ExtraMath.Point ballLocation = new Point(ball.getLocation()); // prevents 2 draw calls
            Ellipse2D oval = new Ellipse2D.Double(ballLocation.x, ballLocation.y, ball.getRad()*2, ball.getRad()*2);
            g2.fill(oval);
        }
        if(MainClass.debugThreadCycles)System.out.println("Render Loop Completed");

        g2.setStroke(new BasicStroke(10));
        g2.setPaint(Color.BLACK);
        g2.drawLine(0,356, MainClass.SCREENWIDTH, 356);
    }

    public void actionPerformed(ActionEvent ev){
        if(ev.getSource()==timer){
            repaint();// this will call at every 1 second
        }
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
    }
}