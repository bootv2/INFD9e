/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Singh
 */
public class Doolhof {
    
    private Board b;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Doolhof();// TODO code application logic here
    }
    
    public Doolhof()
        {
            JFrame f = new JFrame();
            
            //Possible solution for Label Stappen View
            //JLabel textLabel = new JLabel("Aantal stappen: ",SwingConstants.CENTER); textLabel.setPreferredSize(new Dimension(10, 20)); //20 = up/down lenght
            //f.getContentPane().add(textLabel, BorderLayout.NORTH);
            
            f.setTitle("Spel 9e");
            b = new Board();
            f.add(b);
            f.setSize(1296, 677); //464 485
            f.setLocationRelativeTo(null); //voor centering
            f.setVisible(true);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
}
