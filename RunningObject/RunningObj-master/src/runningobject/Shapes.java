/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runningobject;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author mikep
 */
public class Shapes extends JComponent implements KeyListener {

    private static final int TIMER_DELAY = 3; // map dealy 
    private static final int TIMER_DELAY1 = 1; // jump delay
    Polygon triangle;
    Polygon rect;
    public int tx = 200; // triangle top corner y axis
    public int tRight = 300; // right corner y axis
    public int tLeft = 300; // left corner y axis

    public int l = 150; // left corner x axis
    public int r = 250;//right x axis 
    public int h = 200;//top x axis

    int xC; // circle x 
    int xS2; // square2 x 
    int xS;// square x 
    int xS3;// square3 x 
    int shapeC; // circle
    int shapeS;// square
    int line;

    javax.swing.Timer timer; // jump timer 
    javax.swing.Timer timer1; // map timer 

    public int speed = 0; // map speed 
    public boolean flag = true; // space start flag 

    public Shapes() {
        Scanner inputReader = null;
        ArrayList<Integer> shape = new ArrayList<>(); // to know what kind of shape 
        ArrayList<Integer> xthesis = new ArrayList<>(); // to know starting point 
        ArrayList<String> shapeName = new ArrayList<>(); // just name 
        int count = 0; // each line of every shape 
        String str; // shape name 
        try {
            inputReader = new Scanner(new FileInputStream("txt.txt")); //Read the text
        } catch (FileNotFoundException e) {
            System.out.println("File txt.txt was not found");
            System.out.println("or could not be opened.");
            System.exit(0);
        }
        while (inputReader.hasNextLine()) {      //Group them 
            count++;
            switch (count) {
                case 1:
                    str = inputReader.nextLine();
                    shapeName.add(str);
                    break;
                case 2:
                    str = inputReader.nextLine();
                    xthesis.add(Integer.parseInt(str)); // start point 

                    break;
                case 3:
                    str = inputReader.nextLine();
                    shape.add(Integer.parseInt(str)); // kind of shape 
                    count -= 3;
                    break;
            }
        }
        inputReader.close();
        int x = 0;
        for (String element : shapeName) {
            switch (element) {
                case "circle":
                    xC = xthesis.get(x);
                    shapeC = shape.get(x);
                    break;
                case "square":
                    xS = xthesis.get(x);
                    shapeS = shape.get(x);
                    break;
                case "square2":
                    xS2 = xthesis.get(x);
                    shapeS = shape.get(x);
                    break;
                case "square3":
                    xS3 = xthesis.get(x);
                    shapeS = shape.get(x);
                    break;
                case "finalLine":
                    line = xthesis.get(x);
                    break;
            }
            x++;
        }
        ////////////////////////////////////////
        timer1 = new Timer(TIMER_DELAY, new ActionListener() { // timer for map move
            public void actionPerformed(ActionEvent arg0) {
                move();
            }
        });
        timer1.start();
        /////////////////////////////////////////
    }
    /**
     *
     * @param g
     */
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        triangle = new Polygon(new int[]{h, r, l}, new int[]{tx, tRight, tLeft}, 3);
        g.drawLine(0, 301, 1200, 301);
        g.drawPolygon(triangle);
        g.fillPolygon(triangle);
        g.drawRoundRect(xC, 240, 50, 50, shapeC, shapeC);
        g.drawRoundRect(xS2, 240, 50, 50, shapeS, shapeS);
        g.drawRoundRect(xS, 240, 50, 50, shapeS, shapeS);
        g.drawRoundRect(xS3, 240, 50, 50, shapeS, shapeS);

        g.setColor(Color.green);
        g.drawLine(line, 301, line, 30);

    }

    public void moveUp() { // jump... up move 
        tLeft = tLeft - 201; // jump strenth 
        tx = tx - 201;
        tRight = tRight - 201;
        repaint();

    }

    public void moveDown() throws InterruptedException { // jump .. down move 
        timer = new Timer(TIMER_DELAY1, new ActionListener() { // timer for jumping 
            public void actionPerformed(ActionEvent arg0) { // 
                if (tLeft != 301) {
                    tLeft = tLeft + 1; // falling down slowly depent on timer delay  
                    tx = tx + 1;
                    tRight = tRight + 1;
                    repaint();
                } else {
                    timer.stop(); // when its down to start point stop()
                }
            }
        });
        timer.start();
    }
    public void move() { // map move 
        xC = xC - speed; // all objects - speed 
        repaint();
        xS2 = xS2 - speed;
        repaint();
        xS3 = xS3 - speed;
        repaint();
        xS = xS - speed;
        repaint();
        line = line - speed;
        repaint();
        if (xS < 250 && xS > 150) {     // in objects are in the area that will collide 
            if (((tLeft < 270) && (tLeft > 220)) || (tLeft > 300)) {  
                System.out.println(tLeft + ""); // printing the point that collided 
                JOptionPane.showMessageDialog(null, "GAME OVER");
                timer1.stop();
            }
        }
        if (xS2 < 250 && xS2 > 150) {
            if (((tLeft < 270) && (tLeft > 220)) || (tLeft > 300)) {
                System.out.println(tLeft + "");
                JOptionPane.showMessageDialog(null, "GAME OVER");
                timer1.stop();
            }
        }
        if (xS3 < 250 && xS3 > 150) {
            if (((tLeft < 270) && (tLeft > 220)) || (tLeft > 300)) {
                System.out.println(tLeft + "");
                JOptionPane.showMessageDialog(null, "GAME OVER");
                timer1.stop();
            }
        }
        if (xC < 250 && xC > 150) {  // circle in colision speed up 
            if (((tLeft < 270) && (tLeft > 220)) || (tLeft > 300)) {
                speed = 3;
            }
        }
        if (line < 250 && line > 150) {    // winner finish line 
            if (((tLeft < 270) && (tLeft > 220)) || (tLeft > 300)) {
                JOptionPane.showMessageDialog(null, "WINNER WINNER CHICKEN DINNER");
                timer1.stop();
            }
        }
    }
    public void keyPressed(KeyEvent e) { // jump button 
        System.out.println("keyPressed");
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (flag) {  // only first time flag to start 
                speed = 2; // starting speed 
                flag = false;
            }
            moveUp();  // jump ! 
        }
    }
    public void keyReleased(KeyEvent e) { // jump down action when space released
        try {
            moveDown(); // falling down 
        } catch (InterruptedException ex) {
            Logger.getLogger(Shapes.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped");
    }
}
