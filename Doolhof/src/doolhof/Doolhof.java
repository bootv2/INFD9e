/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import javax.swing.JFrame;

/**
 *
 * @author Singh
 */
public class Doolhof {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Doolhof();// TODO code application logic here
    }
    
    public Doolhof()
        {
            JFrame f = new JFrame();
            f.setTitle("Spel 9e");
            f.add(new Board());
            f.setSize(464, 485);
            f.setLocationRelativeTo(null); //voor centering
            f.setVisible(true);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
}
