package com.company;

import javax.swing.*;
import java.awt.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import static java.lang.Math.*;



/**
 * Created by juliettecoia on 2/25/17.
 */
public class Windo extends JFrame{
    private JPanel panel;
    private JTextField textField1;
    private double pi = 3.141592653589;

    /*public abstract class Graphics2D extends Graphics{}*/

    public Windo() {
        super ("Lotka-Volterra Graph");
        setSize(400,300); //1
        setContentPane(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    void drawLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawLine(120,50,250,50);
        g2d.draw(new Line2D.Double(59.2d, 99.8d, 319.1d, 99.8d));
        g2d.draw(new Line2D.Float(21.50f, 132.50f, 459.50f,132.50f));


        double yankee;

        for (int i = 0; i < 2*pi; i++)
        {
            yankee = cos(i);
            g2d.draw(new Line2D.Double(i, yankee, 319.1d, 99.8d));

        }


    }


    public void paint (Graphics g) {
        super.paint(g);
        drawLines(g);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Windo().setVisible(true);
            }
        });
    }

}
