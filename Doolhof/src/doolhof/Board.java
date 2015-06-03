/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Singh
 */
public class Board extends JPanel implements ActionListener  {
    private Timer timer;
    
    private Map m;
    //private String Message = "";
    
    /**
     * creates a new Board to be used for drawing 32*32 sprites
     */
    
    public Board(){
        
        m = new Map();//create and load map
        addKeyListener(m.getAl());//add the players keylistener to the JPanel
        setFocusable(true); //adds to frame
        
        timer = new Timer(25, this);
        timer.start();
    }
    
    
    
    public void paint(Graphics g){
        super.paint(g);
        
        for(int x = 0;x < 14;x++){
            for(int y = 0;y < 14;y++){//paint every tile in the map
                g.drawImage(m.getTile(x, y).getMyItem().getMySprite(), x * 32, y * 32, this);
            }
        }
    }
    
     @Override
    public void actionPerformed(ActionEvent ae) {

       
        repaint();//every time something happens, like a pressed key, repaint the JPanel
    }
    
    
}
