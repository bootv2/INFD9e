/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Singh
 */
public class Doolhof {
    private ButtonPanel bPane = new ButtonPanel();
    private Board b;
    

    public Board getB() {
        return b;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args != null)
        {
            if(args.length > 0)
                new Doolhof(args[0]);// TODO code application logic here
            else new Doolhof("noarg projectd 9e");
        }
        else new Doolhof("noarg projectd 9e");
    }
    
    public Doolhof(String windowName)
        {
            JFrame f = new JFrame();
            
            //Possible solution for Label Stappen View
            JLabel textLabel = new JLabel("Aantal stappen: ",SwingConstants.CENTER); textLabel.setPreferredSize(new Dimension(10, 20)); //20 = up/down lenght
            f.getContentPane().add(textLabel, BorderLayout.NORTH);
            
            f.setTitle(windowName);//sets the windows title

            f.setSize(1296, 743); //set the window size
            f.setLocationRelativeTo(null); //voor centering
            b = new Board();//create a new board, which loads and displays the game
            
            f.add(bPane, BorderLayout.SOUTH);
            f.add(b, BorderLayout.CENTER);
            f.setVisible(true);
            f.setResizable(false);
            //f.addKeyListener(b.getM().getAl());
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit on close
            
            while(true) 
            {
                if(bPane.isNeedsReset())
                {
                    bPane.setNeedsReset(false);
                    b.setReset(true);
                }
                if(bPane.isPaused())
                {
                    bPane.setPaused(false);
                    b.togglePaused();
                }
                b.actionPerformed(new ActionEvent(this, 0, "Tick"));
                textLabel.setText("Aantal stappen: " + b.getStappen());
            }
        }
}
