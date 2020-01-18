/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runningobject;

import java.awt.BorderLayout;
import static java.awt.Color.blue;
import static java.awt.Color.green;
import static java.awt.Color.red;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mikep
 */
public class Graphics extends JFrame {

    JFrame frame = new JFrame();
    private final Shapes draw;

    public Graphics() {
        this.draw = new Shapes(); // call shapes 
        addKeyListener(draw);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void run() {
        JLabel lb = new JLabel("Press space to start !");
        JLabel lb1 = new JLabel("Hit the square and you lose.");
        JLabel lb2 = new JLabel("Circle make you faster !");
        JButton b1 = new JButton("Restart");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                run();  // restart 
            }
        });
        
        lb.setFont(new Font("TimesRoman", Font.PLAIN, 20));  // directions labeles 
        lb.setForeground(green);
        lb1.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        lb1.setForeground(red);
        lb2.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        lb2.setForeground(blue);
        
        JPanel p = new JPanel(); // positioning in middle 
        JPanel pp = new JPanel();
        p.setLayout(new GridLayout(4,1));
        Graphics frame = new Graphics();

        p.add(lb);
        p.add(lb1);
        p.add(lb2);
        p.add(b1);
        
        pp.add(p,BorderLayout.NORTH);
        
        frame.add(pp, BorderLayout.NORTH); // frame details 
        frame.setTitle("Running Object");
        frame.setMinimumSize(new Dimension(1200, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(frame.draw);
        frame.pack();
        frame.setVisible(true);

    }
    

}
